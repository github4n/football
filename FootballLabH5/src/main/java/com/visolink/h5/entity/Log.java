package com.visolink.h5.entity;

import java.util.Date;

public class Log {

	private Integer id;
	private Integer log_member_id;
    private String  LOG_VISITOR;
 
    private Integer  log_menu_id;
    
    private Integer Log_Uri_ID;
    private Integer Log_Object_ID;
    
  
    private Date Log_StartTime;

    private Date Log_EndTime;

    private String Log_Longitude;

    private String Log_Latitude;
    
    private String Log_Key_Word;
    
    private String Log_Error;

    private String Log_DataType;
    
    private String Log_Description;
    
    private String LOG_SOURCE;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLog_member_id() {
		return log_member_id;
	}

	public void setLog_member_id(Integer log_member_id) {
		this.log_member_id = log_member_id;
	}

	public String getLOG_VISITOR() {
		return LOG_VISITOR;
	}

	public void setLOG_VISITOR(String lOG_VISITOR) {
		LOG_VISITOR = lOG_VISITOR;
	}

	public Integer getLog_menu_id() {
		return log_menu_id;
	}

	public void setLog_menu_id(Integer log_menu_id) {
		this.log_menu_id = log_menu_id;
	}

	public Integer getLog_Uri_ID() {
		return Log_Uri_ID;
	}

	public void setLog_Uri_ID(Integer log_Uri_ID) {
		Log_Uri_ID = log_Uri_ID;
	}

	public Integer getLog_Object_ID() {
		return Log_Object_ID;
	}

	public void setLog_Object_ID(Integer log_Object_ID) {
		Log_Object_ID = log_Object_ID;
	}

	public Date getLog_StartTime() {
		return Log_StartTime;
	}

	public void setLog_StartTime(Date log_StartTime) {
		Log_StartTime = log_StartTime;
	}

	public Date getLog_EndTime() {
		return Log_EndTime;
	}

	public void setLog_EndTime(Date log_EndTime) {
		Log_EndTime = log_EndTime;
	}

	public String getLog_Longitude() {
		return Log_Longitude;
	}

	public void setLog_Longitude(String log_Longitude) {
		Log_Longitude = log_Longitude;
	}

	public String getLog_Latitude() {
		return Log_Latitude;
	}

	public void setLog_Latitude(String log_Latitude) {
		Log_Latitude = log_Latitude;
	}

	public String getLog_Key_Word() {
		return Log_Key_Word;
	}

	public void setLog_Key_Word(String log_Key_Word) {
		Log_Key_Word = log_Key_Word;
	}

	public String getLog_Error() {
		return Log_Error;
	}

	public void setLog_Error(String log_Error) {
		Log_Error = log_Error;
	}

	public String getLog_DataType() {
		return Log_DataType;
	}

	public void setLog_DataType(String log_DataType) {
		Log_DataType = log_DataType;
	}

	public String getLog_Description() {
		return Log_Description;
	}

	public void setLog_Description(String log_Description) {
		Log_Description = log_Description;
	}

	public String getLOG_SOURCE() {
		return LOG_SOURCE;
	}

	public void setLOG_SOURCE(String lOG_SOURCE) {
		LOG_SOURCE = lOG_SOURCE;
	}
    
    
    
    
    
    

   

    
}
