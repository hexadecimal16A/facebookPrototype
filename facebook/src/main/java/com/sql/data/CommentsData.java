package com.sql.data;

public class CommentsData {
	private String commentText=null;
	private String profileImg=null;
	private String user_id=null;
	private String comment_id=null;
	private String name=null;
	
	public CommentsData(){}
	
	public CommentsData(String commentText,String profileImg,
			String user_id,String comment_id,String name){
		
		this.commentText=commentText;
		this.profileImg=profileImg;
		this.user_id=user_id;
		this.comment_id=comment_id;
		this.name=name;
		
	}
	
	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public String getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getComment_id() {
		return comment_id;
	}

	public void setComment_id(String comment_id) {
		this.comment_id = comment_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
