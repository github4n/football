package com.visolink.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import com.palmcommerce.util.network.HttpClientHelper;

public class TestPhoneSend {
	private static final Logger log = LoggerFactory.getLogger(TestPhoneSend.class);
	
	public static String CODE_KEY="phoneCode:";
	
	public static long liveTime = 60*30;
	
	public static int db=2;
	
	public static void main(String[] args) {

		String mobile = "15991766473";
		String sendMesg = "【足球实验室】您的手机验证码为：4567" + ",有效期30分钟。如非本人操作，请忽略本短信。（请勿向他人泄露此验证码！）";
		try {
			sendMesg = java.net.URLEncoder.encode(sendMesg, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		StringBuffer smsSb = new StringBuffer();
		smsSb.append("/SendEx?UserId=").append(Const.P_userId);
		smsSb.append("&Password=").append(Const.P_password);
		smsSb.append("&MsgContent=").append(sendMesg);
		smsSb.append("&DestNumber=").append(mobile);
		smsSb.append("&SendTime=");
		smsSb.append("&SubNumber=");
		smsSb.append("&BatchSendID=");
		smsSb.append("&BizType=").append(Const.P_bizType);
		smsSb.append("&WapURL=");

		sendGet(Const.P_remoteUrl + smsSb.toString());

		// HttpClientHelper.httpGet(Const.P_remoteUrl+ smsSb.toString());

	}

	public static String sendGet(String url) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	/*public static String phoneSend(HttpServletRequest request, HttpServletResponse response, String phone)
			throws ServletException, IOException {
		String result = "success";
		
		HttpSession session = request.getSession();
		String mobile = phone;
		response.setContentType("text/text;charset=UTF-8");
		
			if (mobile != null && !"".equals(mobile)) {
				Random random = new Random();
				StringBuffer sbf = new StringBuffer();
				for (int i = 0; i < 4; i++) {
					String rand = String.valueOf(String.valueOf((random.nextInt(10))));
					sbf.append(rand);
				}

				session.setAttribute(phone, sbf.toString());
				session.setMaxInactiveInterval(30 * 60);// [色]ssion 失效时间为 1分钟
				String sendMesg = "【斗球】您的手机验证码为：" + sbf.toString() + ",有效期30分钟。如非本人操作，请忽略本短信。（请勿向他人泄露此验证码！）";
				log.info(sendMesg);
				try {
					sendMesg = java.net.URLEncoder.encode(sendMesg, "utf-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				StringBuffer smsSb = new StringBuffer();
				smsSb.append("/SendEx?UserId=").append(Const.P_userId);
				smsSb.append("&Password=").append(Const.P_password);
				smsSb.append("&MsgContent=").append(sendMesg);
				smsSb.append("&DestNumber=").append(mobile);
				smsSb.append("&SendTime=");
				smsSb.append("&SubNumber=");
				smsSb.append("&BatchSendID=");
				smsSb.append("&BizType=").append(Const.P_bizType);
				smsSb.append("&WapURL=");
				
				sendGet(Const.P_remoteUrl + smsSb.toString());
			} else{ 
				 result = "notExist";
			}
			return result; 
		}*/ 
	
	/**
	 * 创蓝短信接口
	 * @param request
	 * @param response
	 * @param phone
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public static String phoneSend(HttpServletRequest request, HttpServletResponse response, String phone)
			throws ServletException, IOException {
		String result = "success";
		
		/*HttpSession session = request.getSession();*/
		String mobile = phone;
		response.setContentType("text/text;charset=UTF-8");
		
		try {
			if (mobile != null && !"".equals(mobile)) {
				Random random = new Random();
				StringBuffer sbf = new StringBuffer();
				for (int i = 0; i < 4; i++) {
					String rand = String.valueOf(String.valueOf((random.nextInt(10))));
					sbf.append(rand);
				}

				/*session.setAttribute(phone, sbf.toString());
				session.setMaxInactiveInterval(30 * 60);// [色]ssion 失效时间为 1分钟
*/				
				RedisUtils.add(CODE_KEY+phone, sbf.toString(), liveTime, db);
				String sendMesg = "您的手机验证码为：" + sbf.toString() + ",有效期30分钟。如非本人操作，请忽略本短信。（请勿向他人泄露此验证码！）";
				log.info(sendMesg);
				HttpClient client = new HttpClient(new HttpClientParams(), new SimpleHttpConnectionManager(true));
				GetMethod method = new GetMethod();
				URI base = new URI(Const.P_remoteUrl_cl, false);
				method.setURI(new URI(base, "HttpBatchSendSM", false));
				method.setQueryString(new NameValuePair[] { 
						new NameValuePair("account", Const.P_userId_cl),
						new NameValuePair("pswd",Const.P_password_cl), 
						new NameValuePair("mobile", mobile),
						new NameValuePair("needstatus", String.valueOf(true)), 
						new NameValuePair("msg", sendMesg),
						new NameValuePair("extno", null), 
					});
				client.executeMethod(method);
			} else{ 
				 result = "notExist";
			}
		} catch (Exception e) {
			result = "notExist";
		}
		
		return result; 
    }
	
	public static String contentSend(HttpServletRequest request, HttpServletResponse response, String phone, String content)
			throws ServletException, IOException {
		String result = "success";
		String sendMesg = content;
		String mobile = phone;
		response.setContentType("text/text;charset=UTF-8");
		
			if (mobile != null && !"".equals(mobile)) {
				log.info(sendMesg);
				try {
					sendMesg = java.net.URLEncoder.encode(sendMesg, "utf-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				StringBuffer smsSb = new StringBuffer();
				smsSb.append("/SendEx?UserId=").append(Const.P_userId);
				smsSb.append("&Password=").append(Const.P_password);
				smsSb.append("&MsgContent=").append(sendMesg);
				smsSb.append("&DestNumber=").append(mobile);
				smsSb.append("&SendTime=");
				smsSb.append("&SubNumber=");
				smsSb.append("&BatchSendID=");
				smsSb.append("&BizType=").append(Const.P_bizType);
				smsSb.append("&WapURL=");
				
				sendGet(Const.P_remoteUrl + smsSb.toString());
			} else{ 
				 result = "notExist";
			}
			return result; 
		} 

}
