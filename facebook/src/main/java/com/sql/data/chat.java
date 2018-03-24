package com.sql.data;

public class chat {
	private String message;
	private long from_id;
	private long chat_id;
	
	public chat(){}
	
	public chat(String message,long from_id,long chat_id){
		this.message=message;
		this.from_id=from_id;
		this.chat_id=chat_id;
	}
	
	public long getChat_id() {
		return chat_id;
	}

	public void setChat_id(long chat_id) {
		this.chat_id = chat_id;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public long getFrom_id() {
		return from_id;
	}
	public void setFrom_id(long from_id) {
		this.from_id = from_id;
	}
	
	
}
