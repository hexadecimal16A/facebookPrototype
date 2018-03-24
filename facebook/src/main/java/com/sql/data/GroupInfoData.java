package com.sql.data;

public class GroupInfoData {
	
	private String group_id;
	private String group_name;
	private String admin_id;
	private String piclocation;
	private String privacy;
	private String group_count;
	
	
	public GroupInfoData(){
		
	}
	
	public GroupInfoData(String group_id, String group_name, String admin_id, String piclocation,String privacy,String group_count) {
		super();
		this.group_id = group_id;
		this.group_name = group_name;
		this.admin_id = admin_id;
		this.piclocation = piclocation;
		this.privacy=privacy;
		this.group_count=group_count;
	}
	
	
	public String getGroup_id() {
		return group_id;
	}
	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
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

	public String getPrivacy() {
		return privacy;
	}

	public void setPrivacy(String privacy) {
		this.privacy = privacy;
	}

	public String getGroup_count() {
		return group_count;
	}

	public void setGroup_count(String group_count) {
		this.group_count = group_count;
	}
	
	
	
	
	

}
