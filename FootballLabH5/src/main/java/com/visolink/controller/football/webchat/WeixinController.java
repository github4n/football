package com.visolink.controller.football.webchat;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.sd4324530.fastweixin.api.MenuAPI;
import com.github.sd4324530.fastweixin.api.OauthAPI;
import com.github.sd4324530.fastweixin.api.UserAPI;
import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.github.sd4324530.fastweixin.api.entity.Menu;
import com.github.sd4324530.fastweixin.api.entity.MenuButton;
import com.github.sd4324530.fastweixin.api.enums.MenuType;
import com.github.sd4324530.fastweixin.api.enums.ResultType;
import com.github.sd4324530.fastweixin.api.response.GetMenuResponse;
import com.github.sd4324530.fastweixin.api.response.GetUserInfoResponse;
import com.github.sd4324530.fastweixin.message.Article;
import com.github.sd4324530.fastweixin.message.BaseMsg;
import com.github.sd4324530.fastweixin.message.NewsMsg;
import com.github.sd4324530.fastweixin.message.TextMsg;
import com.github.sd4324530.fastweixin.message.req.BaseEvent;
import com.github.sd4324530.fastweixin.message.req.QrCodeEvent;
import com.github.sd4324530.fastweixin.message.req.TextReqMsg;
import com.github.sd4324530.fastweixin.servlet.WeixinControllerSupport;
import com.visolink.service.football.experts.ExpertsService;
import com.visolink.service.football.member.MemberService;
import com.visolink.service.football.message.WxEventService;
import com.visolink.service.football.message.WxMessageService;
import com.visolink.service.football.qrcode.WxQrCodeService;
import com.visolink.util.PageData;
import com.visolink.util.UuidUtil;
import com.visolink.util.football.EmojiFilter;

@RestController
@RequestMapping("/weixin")
public class WeixinController extends MyWeixinControllerSupport {
	private static final Logger log = LoggerFactory.getLogger(WeixinController.class);
	private static final String TOKEN = "football123";
	private final static SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Resource(name = "wxMessageService")
	private WxMessageService wxMessageService;

	@Resource(name = "wxEventService")
	private WxEventService wxEventService;

	@Resource(name = "expertsService")
	private ExpertsService expertsService;

	@Resource(name = "memberService")
	private MemberService memberService;

	@Resource(name = "wxQrcodeService")
	private WxQrCodeService wxQrCodeService;

	// 设置TOKEN，用于绑定微信服务器
	@Override
	protected String getToken() {
		return TOKEN;
	}

	// 使用安全模式时设置：APPID
	// 不再强制重写，有加密需要时自行重写该方法
	@Override
	protected String getAppId() {
		return null;
	}

	// 使用安全模式时设置：密钥
	// 不再强制重写，有加密需要时自行重写该方法
	@Override
	protected String getAESKey() {
		return null;
	}

	// 重写父类方法，处理对应的微信消息
	@Override
	protected BaseMsg handleTextMsg(TextReqMsg msg) {
		String content = msg.getContent();
		PageData pd = new PageData();
		pd.put("id", UuidUtil.get32UUID());
		pd.put("create_time", sdfTime.format(new Date(msg.getCreateTime() * 1000)));
		pd.put("content", content);
		pd.put("to_user_name", msg.getToUserName());
		try {
			PageData expertsPd = new PageData();
			expertsPd.put("WX_NUM", msg.getToUserName());
			expertsPd = expertsService.findByWxNum(expertsPd);

			pd.put("fk_experts_id", expertsPd.get("experts_id"));

			PageData memberPd = new PageData();
			memberPd.put("openId", msg.getFromUserName());
			memberPd.put("experts_code", expertsPd.get("experts_code"));
			memberPd = memberService.findByOpenId(memberPd);

			pd.put("weixin", memberPd.getString("weixin"));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			wxMessageService.save(pd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		log.debug("用户发送到服务器的内容:{}", content);
		return new TextMsg("感谢您的关注和使用!");
	}

	@Override
	protected BaseMsg handleSubscribe(BaseEvent event) {
		super.handleSubscribe(event);
		NewsMsg newsMessage = new NewsMsg();

		log.info("有人关注了：ToUserName-" + event.getToUserName());

		try {

			String openId = event.getFromUserName();

			// 获取当前专家
			PageData expertsPd = new PageData();
			expertsPd.put("WX_NUM", event.getToUserName());
			expertsPd = expertsService.findByWxNum(expertsPd);

			String appid = expertsPd.getString("app_id");
			String secret = expertsPd.getString("app_secret");

			// 获取当前用户信息
			PageData memberPd = new PageData();
			memberPd.put("openId", openId);
			memberPd.put("experts_code", expertsPd.get("experts_code"));
			memberPd = memberService.findByOpenId(memberPd);

			WebChatController.init(appid, secret);
			UserAPI userAPI = new UserAPI(WebChatController.configMap.get(appid));
			GetUserInfoResponse userInfo1 = userAPI.getUserInfo(openId);
			String errcode = userInfo1.getErrcode();
			if (StringUtils.hasText(errcode)) {
				WebChatController.configMap.remove(appid);
				WebChatController.init(appid, secret);
				userInfo1 = userAPI.getUserInfo(openId);
			}

			Integer subscribe = userInfo1.getSubscribe();
			Long time = userInfo1.getSubscribeTime();

			// 保存用户信息
			if (null == memberPd) {
				memberPd = new PageData();
				memberPd.put("member_id", UuidUtil.get32UUID());
				memberPd.put("member_name", EmojiFilter.filterEmoji(userInfo1.getNickname()));
				memberPd.put("weixin", EmojiFilter.filterEmoji(userInfo1.getNickname()));
				memberPd.put("membe_point", 0);
				if (null != subscribe) {
					memberPd.put("focus_status", subscribe);
				}
				memberPd.put("register_status", 0);
				memberPd.put("member_status", 0);
				memberPd.put("fk_experts_id", expertsPd.getString("experts_id"));
				memberPd.put("open_id", openId);
				if (null != time) {
					memberPd.put("focus_time", sdfTime.format(new Date(time * 1000)));
				}
				if (StringUtils.hasText(userInfo1.getHeadimgurl())) {
					memberPd.put("headimgurl", userInfo1.getHeadimgurl());
				}
				memberService.save(memberPd);
			} else {
				memberPd.put("member_name", EmojiFilter.filterEmoji(userInfo1.getNickname()));
				memberPd.put("weixin", EmojiFilter.filterEmoji(userInfo1.getNickname()));
				if (null != subscribe) {
					memberPd.put("focus_status", subscribe);
				}
				if (null != time) {
					memberPd.put("focus_time", sdfTime.format(new Date(time * 1000)));
				}
				if (StringUtils.hasText(userInfo1.getHeadimgurl())) {
					memberPd.put("headimgurl", userInfo1.getHeadimgurl());
				}
				memberService.edit(memberPd);
			}

			List<Article> articleList = new ArrayList<Article>();

			PageData subscribePd = new PageData();
			subscribePd.put("experts_code", expertsPd.getString("experts_code"));

			List<PageData> subscribeList = wxEventService.subscribeListAll(subscribePd);
			newsMessage.setMaxSize(subscribeList.size());
			for (PageData pd : subscribeList) {
				Article article = new Article();
				article.setTitle(pd.getString("title"));
				article.setDescription(pd.getString("description"));
				article.setPicUrl(pd.getString("picUrl"));
				article.setUrl(pd.getString("url"));
				articleList.add(article);
			}

			newsMessage.setArticles(articleList);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newsMessage;
	}

	@Override
	protected BaseMsg handleUnsubscribe(BaseEvent event) {
		super.handleUnsubscribe(event);
		String openId = event.getFromUserName();
		try {
			// 获取当前专家
			PageData expertsPd = new PageData();
			expertsPd.put("WX_NUM", event.getToUserName());
			expertsPd = expertsService.findByWxNum(expertsPd);

			// 获取当前用户信息
			PageData memberPd = new PageData();
			memberPd.put("openId", openId);
			memberPd.put("experts_code", expertsPd.get("experts_code"));
			memberPd = memberService.findByOpenId(memberPd);
			if (null != memberPd) {
				memberPd.put("focus_status", "0");
				memberService.edit(memberPd);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new TextMsg("期待您的再次关注和使用!");
	}

	@Override
	protected BaseMsg handleSubscribeQr(QrCodeEvent event) {
		super.handleQrCodeEvent(event);
		NewsMsg newsMessage = new NewsMsg();
		try {
			String eventKey = event.getEventKey();
			String openId = event.getFromUserName();

			// 获取当前专家
			PageData expertsPd = new PageData();
			expertsPd.put("WX_NUM", event.getToUserName());
			expertsPd = expertsService.findByWxNum(expertsPd);

			String appid = expertsPd.getString("app_id");
			String secret = expertsPd.getString("app_secret");

			// 获取当前用户信息
			PageData memberPd = new PageData();
			memberPd.put("openId", openId);
			memberPd.put("experts_code", expertsPd.get("experts_code"));
			memberPd = memberService.findByOpenId(memberPd);

			WebChatController.init(appid, secret);
			UserAPI userAPI = new UserAPI(WebChatController.configMap.get(appid));
			GetUserInfoResponse userInfo1 = userAPI.getUserInfo(openId);
			String errcode = userInfo1.getErrcode();
			if (StringUtils.hasText(errcode)) {
				WebChatController.configMap.remove(appid);
				WebChatController.init(appid, secret);
				userInfo1 = userAPI.getUserInfo(openId);
			}

			log.info("有人扫二维码了：ToUserName-" + expertsPd.get("experts_name") + "From-" + userInfo1.getNickname());

			Integer subscribe = userInfo1.getSubscribe();
			Long time = userInfo1.getSubscribeTime();

			PageData qrCodePd = new PageData();

			// 获取当前渠道
			qrCodePd.put("scene_id", eventKey.replace("qrscene_", ""));
			qrCodePd = wxQrCodeService.findBySceneId(qrCodePd);

			// 保存用户信息
			if (null == memberPd) {
				memberPd = new PageData();
				memberPd.put("member_id", UuidUtil.get32UUID());
				memberPd.put("member_name", EmojiFilter.filterEmoji(userInfo1.getNickname()));
				memberPd.put("weixin", EmojiFilter.filterEmoji(userInfo1.getNickname()));
				memberPd.put("membe_point", 0);
				if (null != subscribe) {
					memberPd.put("focus_status", subscribe);
				}
				memberPd.put("register_status", 0);
				memberPd.put("member_status", 0);
				memberPd.put("fk_experts_id", expertsPd.getString("experts_id"));
				memberPd.put("open_id", openId);
				if (null != time) {
					memberPd.put("focus_time", sdfTime.format(new Date(time * 1000)));
				}
				if (StringUtils.hasText(userInfo1.getHeadimgurl())) {
					memberPd.put("headimgurl", userInfo1.getHeadimgurl());
				}
				memberPd.put("fk_qrcode_id", qrCodePd.get("qrcode_id"));
				memberService.save(memberPd);
			} else {
				memberPd.put("member_name", EmojiFilter.filterEmoji(userInfo1.getNickname()));
				memberPd.put("weixin", EmojiFilter.filterEmoji(userInfo1.getNickname()));
				if (null != subscribe) {
					memberPd.put("focus_status", subscribe);
				}
				if (null != time) {
					memberPd.put("focus_time", sdfTime.format(new Date(time * 1000)));
				}
				if (StringUtils.hasText(userInfo1.getHeadimgurl())) {
					memberPd.put("headimgurl", userInfo1.getHeadimgurl());
				}
				memberPd.put("fk_qrcode_id", qrCodePd.get("qrcode_id"));
				memberService.edit(memberPd);
			}

			// 保存扫描记录
			PageData recordPd = new PageData();
			recordPd.put("qr_record_id", UuidUtil.get32UUID());
			recordPd.put("from_user", memberPd.get("member_name"));
			recordPd.put("to_user", expertsPd.get("experts_name"));
			recordPd.put("create_time", sdfTime.format(new Date(event.getCreateTime() * 1000)));
			recordPd.put("msg_type", event.getMsgType());
			recordPd.put("event", event.getEvent());
			recordPd.put("event_key", event.getEventKey());
			recordPd.put("ticket", event.getTicket());

			wxQrCodeService.saveRecord(recordPd);

			// 发关注消息
			List<Article> articleList = new ArrayList<Article>();

			PageData subscribePd = new PageData();
			subscribePd.put("experts_code", expertsPd.getString("experts_code"));

			List<PageData> subscribeList = wxEventService.subscribeListAll(subscribePd);
			newsMessage.setMaxSize(subscribeList.size());
			for (PageData pd : subscribeList) {
				Article article = new Article();
				article.setTitle(pd.getString("title"));
				article.setDescription(pd.getString("description"));
				article.setPicUrl(pd.getString("picUrl"));
				article.setUrl(pd.getString("url"));
				articleList.add(article);
			}

			newsMessage.setArticles(articleList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return newsMessage;
	}

	@RequestMapping(value = "/delMenu")
	public String delMenu(String appid, String secret, String experts_code) {
		WebChatController.init(appid, secret);
		MenuAPI menuAPI = new MenuAPI(WebChatController.configMap.get(appid));

		// 先删除之前的菜单
		ResultType rt = menuAPI.deleteMenu();
		return rt.getDescription();
	}

	/**
	 * 创建菜单
	 *
	 * @param config
	 *            API配置
	 */
	@RequestMapping(value = "/createMenu")
	public String createMenu(String appid, String secret, String experts_code) {
		WebChatController.init(appid, secret);
		MenuAPI menuAPI = new MenuAPI(WebChatController.configMap.get(appid));

		// 先删除之前的菜单
		menuAPI.deleteMenu();
		Menu request = new Menu();
		// 准备一级主菜单
		MenuButton main1 = new MenuButton();
		main1.setType(MenuType.VIEW);
		main1.setKey("laoliao1");
		main1.setName("专家方案");

		String zjfaUrl = "http://www.01588.com/wechat/index.html?experts_code=" + experts_code;
		main1.setUrl(zjfaUrl);

		MenuButton main2 = new MenuButton();
		main2.setType(MenuType.VIEW);
		main2.setKey("laoliao2");
		main2.setName("我的");

		String wdUrl = "http://www.01588.com/wechat/my.html?experts_code=" + experts_code;
		main2.setUrl(wdUrl);

		List<MenuButton> mainList = new ArrayList<MenuButton>();
		mainList.add(main1);
		mainList.add(main2);
		// 将主菜单加入请求对象
		request.setButton(mainList);
		log.debug(request.toJsonString());
		// 创建菜单
		ResultType resultType = menuAPI.createMenu(request);
		log.debug(resultType.toString());
		return resultType.getDescription();
	}

	@RequestMapping(value = "/getMenu")
	public void getMenu(String appid, String secret, HttpServletResponse servletResponse) {
		WebChatController.init(appid, secret);
		MenuAPI api = new MenuAPI(WebChatController.configMap.get(appid));
		GetMenuResponse response = api.getMenu();
		log.debug("菜单:{}", response.toJsonString());
		try {
			servletResponse.getWriter().print(response.toJsonString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void oauthGetUserInfo(ApiConfig config) {
		OauthAPI oauthAPI = new OauthAPI(config);
		GetUserInfoResponse response = oauthAPI.getUserInfo(
				"OezXcEiiBSKSxW0eoylIeKoEzhGrPf8vRE3NugAdMy16Em-NimErLsOMfMlZBW0P0wauuYLIzl1soHnV-9CGvQtUYxmd3F6ruwjs_SQNw90aZd_yFlVc85P2FlC01QVNyRktVrSX5zHIMkETyjZojQ",
				"opZYwt-OS8WFxwU-colRzpu50eOQ");
		log.debug("response:{}", response.toJsonString());

	}
}