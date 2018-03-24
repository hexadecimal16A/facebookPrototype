package com.sql.data;


public class NewsFeedData implements Comparable<NewsFeedData>{
	private String postid;
	private String id;
	private String textFeild;
	private String picLocation;
	private String creationTime;
	private String userName;
	private String picturePath;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	public NewsFeedData(){
	}
	
	public NewsFeedData(String postid,String id,String textfeild,String piclocation,
			String creation_time,String userName,String picturePath){
		this.postid=postid;
		this.id=id;
		this.textFeild=textfeild;
		this.picLocation=piclocation;
		this.creationTime=creation_time;
		this.userName=userName;
		this.picturePath=picturePath;
	}
	
	public String getPostid() {
		return postid;
	}
	public void setPostid(String postid) {
		this.postid = postid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTextFeild() {
		return textFeild;
	}
	public void setTextFeild(String textFeild) {
		this.textFeild = textFeild;
	}
	public String getPicLocation() {
		return picLocation;
	}
	public void setPicLocation(String picLocation) {
		this.picLocation = picLocation;
	}
	public String getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}
	
	@Override
	public int compareTo(NewsFeedData e){
		return e.getCreationTime().compareTo(this.creationTime);
	}
}
