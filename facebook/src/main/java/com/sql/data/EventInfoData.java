package com.sql.data;

public class EventInfoData {
	
	
	
	
	private String event_id;
	private String event_name;
	private String event_detail;
	private String admin_id;
	private String piclocation;
	private String event_type;
	private String start_time;
	private String end_time;
	private String location;
	private String summary;
	
	public EventInfoData(){
		
	}
	
	
	

	public EventInfoData(String event_id, String event_name, String event_detail, String admin_id, String piclocation,
			String event_type, String start_time, String end_time, String location, String summary) {
		super();
		this.event_id = event_id;
		this.event_name = event_name;
		this.event_detail = event_detail;
		this.admin_id = admin_id;
		this.piclocation = piclocation;
		this.event_type = event_type;
		this.start_time = start_time;
		this.end_time = end_time;
		this.location = location;
		this.summary = summary;
	}




	public String getEvent_id() {
		return event_id;
	}
	public void setEvent_id(String event_id) {
		this.event_id = event_id;
	}
	public String getEvent_name() {
		return event_name;
	}
	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}
	public String getEvent_detail() {
		return event_detail;
	}
	public void setEvent_detail(String event_detail) {
		this.event_detail = event_detail;
	}
	public String getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}
	public String getPiclocation() {
		return piclocation;
	}
	public void setPiclocation(String piclocation) {
		this.piclocation = piclocation;
	}
	public String getEvent_type() {
		return event_type;
	}
	public void setEvent_type(String event_type) {
		this.event_type = event_type;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}



	public String getLocation() {
		return location;
	}



	public void setLocation(String location) {
		this.location = location;
	}



	public String getSummary() {
		return summary;
	}



	public void setSummary(String summary) {
		this.summary = summary;
	}


}
