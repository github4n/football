package com.visolink.entity;

import java.util.Date;
/**
 * 比赛数据
 * @author liujin
 *
 */
public class TwoOnOne {

	    private String id;

	    private Integer  match_one_id;
	    
	    private Integer match_two_id;

	    private Integer company_id;
	    
	    private Date create_time;
	    
	    private String service_id;

	    
		public Integer getMatch_one_id() {
			return match_one_id;
		}

		public void setMatch_one_id(Integer match_one_id) {
			this.match_one_id = match_one_id;
		}

		public Integer getMatch_two_id() {
			return match_two_id;
		}

		public void setMatch_two_id(Integer match_two_id) {
			this.match_two_id = match_two_id;
		}

		
		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public Integer getCompany_id() {
			return company_id;
		}

		public void setCompany_id(Integer company_id) {
			this.company_id = company_id;
		}

		public Date getCreate_time() {
			return create_time;
		}

		public void setCreate_time(Date create_time) {
			this.create_time = create_time;
		}

		public String getService_id() {
			return service_id;
		}

		public void setService_id(String service_id) {
			this.service_id = service_id;
		}
	    
}
