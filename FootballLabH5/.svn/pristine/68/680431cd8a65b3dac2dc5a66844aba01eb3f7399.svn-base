package com.visolink.util.jackson;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

/**
 * json 序列化时 格式化时间格式 MM-dd HH:mm
 * Title:JsonDateSerializer
 * @Description:
 * @Create_by:牛骞
 * @Create_date:2016年11月16日
 * @Last_Edit_By:
 * @Edit_Description:
 * @version:maxtp.framelib 1.0
 */
public class JsonMonthDaySerializer extends JsonSerializer<Date> {

	@Override  
    public void serialize(Date value, JsonGenerator jgen,  
            SerializerProvider provider) throws IOException,  
            JsonProcessingException {  
		SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm");
        String formattedDate=format.format(value);
        jgen.writeString(formattedDate);  
    }}