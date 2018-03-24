package com.sql.requests;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class SqlInit {
	static String friendTable="friendlist";
	static String userTable="userprofile";
	static String pendingRequestTable="pending_request";
	static String postLikeTable="post_likes";
	static String groupLikeTable="group_likes";
	static String eventLikeTable="event_likes";
	static String postInfoTable="postinfo";
	static String extrainfo="userotherinfo";
	static String groupinfo="group_info";
	static String grouppostinfo="group_postinfo";
	static String eventpostinfo="event_postinfo";
	static String groupmembers="group_members";
	static String eventinfo="event_info";
	static String eventparticipants="event_participants";
	static String usersports="sports";
	static String chat="chat";
	static String postCommentTable="post_comments";
	static String groupCommentTable="group_comments";
	static String eventCommentTable="event_comments";
	private static String database="facebookdb";
	private static String userName="root";
	private static String password="    ";
	
	public static Connection makeSQLConn() throws SQLException{
		try{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		String connectionUrl = "jdbc:mysql://localhost:3306/" + database + "?useSSL=false";
		String connectionUser = userName;
		String connectionPassword = password;
		return DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
		}
		catch (Exception e) {
			e.printStackTrace();
		} 
		
		return null;
		
	}
}
