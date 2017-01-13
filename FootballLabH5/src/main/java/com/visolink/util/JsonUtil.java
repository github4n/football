package com.visolink.util;

import java.io.IOException;
import java.io.StringWriter;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

/**
 * 基于jackson 的json util 
 * Title:JsonUtil
 * @Description:
 * @Create_by:翟春龙
 * @Create_date:2015年6月10日
 * @Last_Edit_By:
 * @Edit_Description:
 * @version:framelib.maxtp 1.0
 */
public class JsonUtil {

	/**
	 * bean to json
	 * 
	 * @Create_by:翟春龙
	 * @Create_date:2015年6月10日
	 * @param obj
	 * @return
	 * @Last_Edit_By:
	 * @Edit_Description:
	 * @Create_Version:framelib.maxtp 1.0
	 */
	public static String beanToJson(Object obj) {
		StringWriter writer = null;
		JsonGenerator gen = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			writer = new StringWriter();
			gen = new JsonFactory().createJsonGenerator(writer);
			mapper.writeValue(gen, obj);
			String json = writer.toString();
			return json;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (gen != null) {
				try {
					gen.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * json to object
	 * 
	 * @Create_by:翟春龙
	 * @Create_date:2015年6月10日
	 * @param json
	 * @param cls
	 * @return
	 * @Last_Edit_By:
	 * @Edit_Description:
	 * @Create_Version:framelib.maxtp 1.0
	 */
	public static Object jsonToBean(String json, Class<?> cls) {
		ObjectMapper mapper = new ObjectMapper();
		Object vo;
		try {
			vo = mapper.readValue(json, cls);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return vo;
	}
	
	/**
	 * 
	 * json to bean
	 * jsonToBean("{}", new TypeReference<Map<String,User>>(){});
	 * @Create_by:翟春龙
	 * @Create_date:2015年6月10日
	 * @param json
	 * @param type
	 * @return
	 * @Last_Edit_By:
	 * @Edit_Description:
	 * @Create_Version:framelib.maxtp 1.0
	 */
	public static Object jsonToBean(String json, TypeReference<?> type) {
		ObjectMapper mapper = new ObjectMapper();
		Object vo;
		try {
			vo = mapper.readValue(json, type); 
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return vo;
	}
}
