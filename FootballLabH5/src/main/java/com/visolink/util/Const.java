package com.visolink.util;

import java.io.IOException;
import java.util.Properties;

import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
/**
 * 项目名称：
 * @author:fh
 * 
*/
public class Const {
	public static final String SESSION_SECURITY_CODE = "sessionSecCode";
	public static final String SESSION_USER = "sessionUser";
	public static final String SESSION_VISITOR = "sessionVisitor";//手机端登陆
	public static final String SESSION_ROLE_RIGHTS = "sessionRoleRights";
	public static final String SESSION_menuList = "menuList";			//当前菜单
	public static final String SESSION_allmenuList = "allmenuList";		//全部菜单
	public static final String SESSION_QX = "QX";
	public static final String SESSION_userpds = "userpds";			
	public static final String SESSION_USERROL = "USERROL";				//用户对象
	public static final String SESSION_USERNAME = "USERNAME";			//用户名
	public static final String TRUE = "T";
	public static final String FALSE = "F";
	public static final String LOGIN = "/login_toLogin";				//登录地址
	public static final String FILEPATHIMG = "uploadFiles/uploadImgs/";	//图片上传路径
	public static final String FILEPATHFILE = "uploadFiles/file/";		//文件上传路径
	public static final String FILEPATHTWODIMENSIONCODE = "uploadFiles/twoDimensionCode/"; //二维码存放路径
	public static final String NO_INTERCEPTOR_PATH = ".*/((login)|(logout)|(code)|(app)|(uploadFiles)|(weixin)|(webChat)|(wechat)|(TP)|(static)|(main)|(websocket)|(css)|(images)|(js)|(pic)|(ad)).*";	//不对匹配该值的访问路径拦截（正则）(artist)|(memberappointment)|(optimization)|(homeDecoration)|(thermometer)|
	private static Properties props;
	static{
	Resource resource = new ClassPathResource("/settingconfig.properties");
	try {
		 props = PropertiesLoaderUtils.loadProperties(resource);
	} catch (IOException e) {
		System.out.println("启动时读取配置文件错误。。。。。。。。。。。。。。。");
	}
	}
	public static final String SYSNAME = props.get("SYSNAME").toString();//系统名称路径
	public static final String PAGE	= props.get("PAGE").toString();			//分页条数配置路径
	public static final String EMAIL = props.get("EMAIL").toString();		//邮箱服务器配置路径
	public static final String SMS1 = props.get("SMS1").toString();			//短信账户配置路径1
	public static final String SMS2 = props.get("SMS2").toString();			//短信账户配置路径2
	public static final String FWATERM = props.get("FWATERM").toString();	//文字水印配置路径
	public static final String IWATERM = props.get("IWATERM").toString();	//图片水印配置路径
	public static final String WEIXIN	= props.get("WEIXIN").toString();	//微信配置路径
	public static final String REMOTEPICADDRESS=props.get("REMOTEPICADDRESS").toString();//远程图片地址
	
	public static final int CITYID=Integer.parseInt(props.get("CITYID").toString());//默认城市id
	public static final String CITYNAME=props.get("CITYNAME").toString();//默认城市名称
	
	public static ApplicationContext WEB_APP_CONTEXT = null; //该值会在web容器启动时由WebAppContextListener初始化
	
	/**
	 * APP Constants
	 */
	//app注册接口_请求协议参数)
	public static final String[] APP_REGISTERED_PARAM_ARRAY = new String[]{"countries","uname","passwd","title","full_name","company_name","countries_code","area_code","telephone","mobile"};
	public static final String[] APP_REGISTERED_VALUE_ARRAY = new String[]{"国籍","邮箱帐号","密码","称谓","名称","公司名称","国家编号","区号","电话","手机号"};
	
	//app根据用户名获取会员信息接口_请求协议中的参数
	public static final String[] APP_GETAPPUSER_PARAM_ARRAY = new String[]{"USERNAME"};
	public static final String[] APP_GETAPPUSER_VALUE_ARRAY = new String[]{"用户名"};
	

	

	//app分页，每页显示条数
	public static final int PAGESIZE  = 4;
	//服务接口IP
	public static String serviceIp=props.getProperty("serviceIp").toLowerCase();
	//系统编码
	public static String sysCode=props.getProperty("sysCode").toLowerCase();
	
	//未登录的memberid
	public static Integer UNLOGINMEMBERID=-999;
	
	//设备类型
	public static  String SESSION_DEVICEMODEL = "未知";
	//操作系统
	public static  String SESSION_OS = "微信";
	
	//操作系统
	public static  String GAME_TIME = " 11:30:00";
	
	/**
	 * redis相关信息
	 */
	public static String REDIS_IP = props.getProperty("redis_ip");
	
	public static Integer REDIS_PORT = Integer.parseInt(props.getProperty("redis_port"));
	
    public static String REDIS_PASSWORD = props.getProperty("redis_password");
	
	
	
	//短信配置
	public static  String P_remoteUrl = "http://sms.hcsdsms.com:8080/SmsService/SmsService.asmx";

	public static  String P_userId = "E34314D424";

	public static  String P_password = "981678";

	public static  String P_bizType = "16";
	
	/**
	 * 创蓝短信链接
	 */
	public static  String P_remoteUrl_cl = "http://sapi.253.com/msg/";

	public static  String P_userId_cl = "Vip-ztty8";

	public static  String P_password_cl = "Vip-ztty888";

	
	//抽奖一次消耗积分
	public static Integer PRIZE_POINTS = 100;
	
	//首页查最近多少天的数据
	public static Integer INDEX_DAYS = 90;
	
	//通知回调地址
	public static String notifyUrl = "http://www.01588.com/webChat/notice";
	
	//方案金额-1月
	public static Double TOTAL_FEE_MONTH = 88.00;
	//方案金额-1天
	public static Double TOTAL_FEE_DAY = 2.00;
	
	//方案金额-1月
	public static Double SINGLE_FEE_MONTH = 58.00;
	//方案金额-1天
	public static Double SINGLE_FEE_DAY = 2.00;
	
	//对赌金额
	public static Double DUIDU_FEE = 15.00;
	
	//对赌赔偿积分
	public static Integer DUIDU_POINTS = 100;
	
	//积分单价（1元）
	public static Integer POINT_PRICE = 100;
	
	//订单有效时间  单位分钟。
	public static Integer ACTIVE_TIME = 30;
	
	public static String picUrl = "http://www.01588.com/pic/";
}
