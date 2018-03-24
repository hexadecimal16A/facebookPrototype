package com.sql.requests;

import static com.sql.requests.SqlInit.eventpostinfo;
import static com.sql.requests.SqlInit.makeSQLConn;
import static com.sql.requests.SqlInit.userTable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import com.sql.data.Eventfeeddata;

public class SqlEventFeedPost {
	
	private String postid;
	private String id;
	private String textfield;
	private String piclocation;
	private String creationTime;
	private String userName;
	private String picturePath;
	private Connection conn=null;
	private PreparedStatement preparedStatement=null;
	private String insertTableSQL = "INSERT INTO " +eventpostinfo
			+ " (event_id,member_id,textfield,piclocation,creationtime) VALUES"
			+ "(?,?,?,?,?)";
	public void posteventnewsfeed(String... args) throws SQLException{
		conn=makeSQLConn();
		preparedStatement=conn.prepareStatement(insertTableSQL);
		preparedStatement.setLong(1,Long.parseLong(args[0]));
		preparedStatement.setLong(2,Long.parseLong(args[1]));
		preparedStatement.setString(3,args[2]);
		preparedStatement.setString(4,args[3]);
		preparedStatement.setString(5,args[4]);
		preparedStatement.executeUpdate();
		conn.close();
	}
	
	public ArrayList<Eventfeeddata> geteventnewsfeed(String event_id) throws SQLException{
		 insertTableSQL="select id,concat(fname,' ',lname) as name,"
		 		+ "piclocation,creationtime,textfield,picturepath,post_id "
		 		+ "from "+userTable+","+eventpostinfo+" where  event_id=? and member_id=id";
		 conn=makeSQLConn();
		 preparedStatement =conn.prepareStatement(insertTableSQL);
		 preparedStatement.setInt(1,Integer.parseInt(event_id));
		 ResultSet rs=preparedStatement.executeQuery();
		 ArrayList<Eventfeeddata> pro=new ArrayList<Eventfeeddata>();
		 if(rs.isBeforeFirst())
		 while(rs.next()){
			 postid=rs.getString("post_id");
			 id=rs.getString("id");
			 textfield=rs.getString("textfield");
			 piclocation=rs.getString("piclocation");
			 creationTime=rs.getString("creationtime");
			 userName=rs.getString("name");
			 picturePath=rs.getString("picturepath");
			 pro.add(new Eventfeeddata(postid,id,textfield,piclocation,creationTime,userName,picturePath));
		 }
			conn.close();
			Collections.sort(pro);
			return pro;
	}
}
