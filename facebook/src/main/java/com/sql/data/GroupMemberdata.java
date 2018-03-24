package com.sql.data;

public class GroupMemberdata {
	
	private String id;
	private String userName;
	private String Email;
	private String DOB;
	private String Mobile;
	private String ProfileImage;
	
	
	public GroupMemberdata()
	{
		
	}


	public GroupMemberdata(String id, String userName, String email, String dOB, String mobile, String profileImage) {
		super();
		this.id = id;
		this.userName = userName;
		Email = email;
		DOB = dOB;
		Mobile = mobile;
		ProfileImage = profileImage;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getEmail() {
		return Email;
	}


	public void setEmail(String email) {
		Email = email;
	}


	public String getDOB() {
		return DOB;
	}


	public void setDOB(String dOB) {
		DOB = dOB;
	}


	public String getMobile() {
		return Mobile;
	}


	public void setMobile(String mobile) {
		Mobile = mobile;
	}


	public String getProfileImage() {
		return ProfileImage;
	}


	public void setProfileImage(String profileImage) {
		ProfileImage = profileImage;
	}
	

}