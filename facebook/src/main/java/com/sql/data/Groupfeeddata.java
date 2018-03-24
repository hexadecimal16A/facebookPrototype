package com.sql.data;

public class Groupfeeddata implements Comparable<Groupfeeddata>{
	private String postid;
	private String id;
	private String textfield;
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

	public Groupfeeddata(){
	}
	
	public Groupfeeddata(String postid,String id,String textfield,String piclocation,
			String creation_time,String userName,String picturePath){
		this.postid=postid;
		this.id=id;
		this.textfield=textfield;
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
	public String gettextfield() {
		return textfield;
	}
	public void settextfield(String textfield) {
		this.textfield = textfield;
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
	public int compareTo(Groupfeeddata e){
		return e.getCreationTime().compareTo(this.creationTime);
	}
}
