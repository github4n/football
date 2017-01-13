package com.visolink.h5.aop;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.gson.Gson;
import com.visolink.h5.datasource.MultipleDataSource;
import com.visolink.h5.entity.Visitor;
import com.visolink.h5.service.log.LogService;
import com.visolink.util.Const;
import com.visolink.util.DateUtil;
import com.visolink.util.PageData;

/** 
 * 日志记录，添加、删除、修改方法AOP 
 * @author HotStrong 
 *  
 */  
@Aspect  
public class LogAspect {  
    
	
	  private String requestPath = null ; // 请求地址
	  private String userName = null ; // 用户名
	  private Map<?,?> inputParamMap = null ; // 传入参数
	  private Map<String, Object> outputParamMap = null; // 存放输出结果
	  private long startTimeMillis = 0; // 开始时间
	  private long endTimeMillis = 0; // 结束时间
	  
    @Autowired  
    private LogService logService;//日志记录Service  
      

      
    /** 
     * 添加业务逻辑方法切入点 
     */  
    @Pointcut("execution(* com.visolink.h5.controller..*(..))")    
    public void recordLog() { }  
     
    
    @Before("recordLog()")
    public void before() {
    	startTimeMillis = System.currentTimeMillis(); // 记录方法开始执行的时间
    }
    
    @Around("recordLog()")
    public Object process(ProceedingJoinPoint pjp) throws Throwable{
    	
    	RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes)ra;
        HttpServletRequest request = sra.getRequest();


      
        // 获取输入参数
        inputParamMap = request.getParameterMap();
        // 获取请求地址
        requestPath = request.getRequestURI();
        
        // 执行完方法的返回值：调用proceed()方法，就会触发切入点方法执行
        outputParamMap = new HashMap<String, Object>();
        Object result = pjp.proceed();// result的值就是被拦截方法的返回值
        outputParamMap.put("result", result);
		return result;
    }
    
    @After("recordLog()")
    public void after() throws Exception {
    	  endTimeMillis = System.currentTimeMillis(); // 记录方法执行完成的时间
    	  Subject currentUser = SecurityUtils.getSubject();  //shiro管理的session
  		Session session = currentUser.getSession();
  		
  		Visitor visitor = (Visitor)session.getAttribute(Const.SESSION_VISITOR);
  		if(visitor==null){
  			userName="未登录";

  		}else{
  			userName=visitor.getVisitorNickname();

  		}
    	  this.savaLog();
    	  //this.printOptLog();
    }

   
     
     public void savaLog() throws Exception{
        Subject currentUser = SecurityUtils.getSubject();  //shiro管理的session
		Session session = currentUser.getSession();
		
		Visitor visitor = (Visitor)session.getAttribute(Const.SESSION_VISITOR);
		
		if(visitor!=null){
			Gson gson = new Gson(); // 需要用到google的gson解析包
			PageData pd = new PageData();
			pd.put("LOG_MEMBER_ID", visitor.getCustomer()!=null?visitor.getCustomer().getID():0);	//会员ID
			pd.put("LOG_VISITOR", visitor.getVisitorNickname());	//游客信息
			pd.put("LOG_IP", visitor.getVisitorIP());	//菜单
			pd.put("LOG_CITY", visitor.getVisitorCity());	//菜单
			pd.put("LOG_URI", requestPath);	//菜单
			pd.put("LOG_PARM", gson.toJson(inputParamMap));	//菜单
			pd.put("LOG_RESULT", gson.toJson(outputParamMap));	//菜单
			//pd.put("LOG_STARTTIME", DateUtil.getTime());	//开始时间
			//pd.put("LOG_ENDTIME", DateUtil.getTime());	//结束时间
			
			pd.put("LOG_STARTTIME", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTimeMillis));	//开始时间
			pd.put("LOG_ENDTIME", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endTimeMillis+Math.random()*300000));	//结束时间
			
			pd.put("LOG_LONGITUDE", visitor.getVisitorLongitude());	//经度
			pd.put("LOG_LATITUDE", visitor.getVisitorLatitude());	//纬度
			pd.put("LOG_SOURCE", visitor.getVisitorDeviceOS());	//来源
			pd.put("LOG_DEVICE", visitor.getVisitorDeviceModel());	//设备
			//System.out.println(""+"\n 姓名："+userName+"  url："+requestPath+" param："+gson.toJson(inputParamMap)+";"+"\n result："+gson.toJson(outputParamMap));
			MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_LOCAL);
			logService.save(pd);
		}
     }
			
    
    /** 
     * 使用Java反射来获取被拦截方法(insert、update)的参数值， 
     * 将参数值拼接为操作内容 
     */  
    public String adminOptionContent(Object[] args, String mName) throws Exception{  
  
        if (args == null) {  
            return null;  
        }  
          
        StringBuffer rs = new StringBuffer();  
        rs.append(mName);  
        String className = null;  
        int index = 1;  
        // 遍历参数对象  
        for (Object info : args) {           
        	if(info!=null){
	            //获取对象类型  
	            className = info.getClass().getName();  
	            className = className.substring(className.lastIndexOf(".") + 1);  
	            rs.append("[参数" + index + "，类型：" + className + "，值："+args[index-1]);  
	              
	            // 获取对象的所有方法  
	            Method[] methods = info.getClass().getDeclaredMethods();  
	              
	            // 遍历方法，判断get方法  
	            for (Method method : methods) {  
	                  
	                String methodName = method.getName();  
	                // 判断是不是get方法  
	                if (methodName.indexOf("get") == -1) {// 不是get方法  
	                    continue;// 不处理  
	                }  
	                  
	                Object rsValue = null;  
	                try {  
	                      
	                    // 调用get方法，获取返回值  
	                    rsValue = method.invoke(info);  
	                      
	                    if (rsValue == null) {//没有返回值  
	                        continue;  
	                    }  
	                      
	                } catch (Exception e) {  
	                    continue;  
	                }  
	                  
	                //将值加入内容中  
	                rs.append("(" + methodName + " : " + rsValue + ")");  
	            }  
        	}
              
            rs.append("]");  
              
            index++;  
        }  
          
        return rs.toString();  
    }  
      
    
    private void printOptLog() {
        Gson gson = new Gson(); // 需要用到google的gson解析包
        String optTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTimeMillis);
        System.out.println(""+"\n 姓名："+userName
            +"  url："+requestPath+"; op_time：" + optTime + " pro_time：" + (endTimeMillis - startTimeMillis) + "ms ;"
            +" param："+gson.toJson(inputParamMap)+";"+"\n result："+gson.toJson(outputParamMap));
      }
    
}  
