package com.sql.data;

public class FriendPendingRequestData {
	private String name;
	private String dOB;
	private String id;
	private String email;
	private String picturePath;
	private String reqId;
	private String count;
	public FriendPendingRequestData(){
	}
	
	public FriendPendingRequestData(String name,String dOB,String id,String email,String picturePath,String reqId,String count){
		this.name=name;
		this.dOB=dOB;
		this.id=id;
		this.email=email;
		this.picturePath=picturePath;
		this.reqId=reqId;
		this.count=count;
	}
	
	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getdOB() {
		return dOB;
	}
	public void setdOB(String dOB) {
		this.dOB = dOB;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPicturePath() {
		return picturePath;
	}
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}
	public String getReqId() {
		return reqId;
	}
	public void setReqId(String reqId) {
		this.reqId = reqId;
	}
}