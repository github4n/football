/*package com.visolink;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.visolink.service.football.transaction.TransactionService;

@RunWith(SpringJUnit4ClassRunner.class) // 整合 
@ContextConfiguration(locations="classpath:spring/ApplicationContext.xml") // 加载配置
public class Test1 {
	
	@Resource
	private TransactionService transactionService;
	
	
	//@Test
	public void testAdPageService() throws Exception{
		List<AdPageResultDTO> list = adPageService.getSingleList();
		for (AdPageResultDTO adPageResultDTO : list) {
			System.out.println(JsonUtil.beanToJson(adPageResultDTO));
		}
		List<AdPageResultDTO> list = adPageService.getAsianList();
		for (AdPageResultDTO adPageResultDTO : list) {
			System.out.println(JsonUtil.beanToJson(adPageResultDTO));
		}
	}
	
	//@Test
	public void sendMessage() throws Exception{
		List<String> allPhone = (List<String>) dao.findForList("MemberMapper.selectAllPhoneNum", null);
		System.out.println(allPhone.size());
		StringBuffer sb = new StringBuffer();
		int i = 0;
		for (String string : allPhone) {
			sb.append(string);
			i++;
			if(i==10 || allPhone.indexOf(string) == allPhone.size()-1){
				String sendMesg = "有一种福利叫“斗球老料”，微信关注公众号dqll310，参与不盈双倍返活动，就有机会获得30元红包或抵现积分！";
				HttpClient client = new HttpClient(new HttpClientParams(), new SimpleHttpConnectionManager(true));
				GetMethod method = new GetMethod();
				URI base = new URI(Const.P_remoteUrl_cl, false);
				method.setURI(new URI(base, "HttpBatchSendSM", false));
				method.setQueryString(new NameValuePair[] { 
						new NameValuePair("account", Const.P_userId_cl),
						new NameValuePair("pswd",Const.P_password_cl), 
						new NameValuePair("mobile", sb.toString()),
						new NameValuePair("needstatus", String.valueOf(true)), 
						new NameValuePair("msg", sendMesg),
						new NameValuePair("extno", null), 
					});
				client.executeMethod(method);
				i=0;
				sb = new StringBuffer();
			}else{
				sb.append(",");
			}
		}
		
	}
	
	@Test
	public void test3() throws Exception{
		transactionService.givePointsByDuiduTask();
	}
	

}
*/