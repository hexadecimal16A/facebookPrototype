package com.sql.requests;

import static com.sql.requests.SqlInit.friendTable;
import static com.sql.requests.SqlInit.makeSQLConn;
import static com.sql.requests.SqlInit.postInfoTable;
import static com.sql.requests.SqlInit.userTable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import com.sql.data.NewsFeedData;

public class SqlNewsFeedPostGet {
	private String postid;
	private String id;
	private String textfeild;
	private String piclocation;
	private String creationTime;
	private String userName;
	private String picturePath;
	private Connection conn=null;
	private PreparedStatement preparedStatement=null;
	private String insertTableSQL = "INSERT INTO " +postInfoTable
			+ " (id,textfeild,piclocation,creation_time) VALUES"
			+ "(?,?,?,?)";
	public void postNewsfeed(String... args) throws SQLException{
		conn=makeSQLConn();
		preparedStatement=conn.prepareStatement(insertTableSQL);
		preparedStatement.setLong(1,Long.parseLong(args[0]));
		preparedStatement.setString(2,args[1]);
		preparedStatement.setString(3,args[2]);
		preparedStatement.setString(4,args[3]);
		preparedStatement.executeUpdate();
		conn.close();
	}
	
	public ArrayList<NewsFeedData> getNewsfeed(String Id,String type) throws SQLException{
		 insertTableSQL="select "+postInfoTable+".id,concat(fname,' ',lname) as name,"
		 		+ "piclocation,creation_time,textfeild,picturepath,postid "
		 		+ "from "+userTable+","+postInfoTable+","+friendTable
		 		+ " where "+friendTable+".id2=? and "+friendTable+".id1="+postInfoTable+".id "
		 				+ "and "+postInfoTable+".id="+userTable+".id";

		 //insertTableSQL="select * from "+postInfoTable;
		 conn=makeSQLConn();
		 ResultSet rs;
		 ArrayList<NewsFeedData> pro=new ArrayList<NewsFeedData>();
		 if(type==null){
		 preparedStatement =conn.prepareStatement(insertTableSQL);
		 preparedStatement.setInt(1,Integer.parseInt(Id));
		 rs=preparedStatement.executeQuery();
		 if(rs.isBeforeFirst())
		 while(rs.next()){
			 postid=rs.getString("postid");
			 id=rs.getString("id");
			 textfeild=rs.getString("textfeild");
			 piclocation=rs.getString("piclocation");
			 creationTime=rs.getString("creation_time");
			 userName=rs.getString("name");
			 picturePath=rs.getString("picturepath");
			 pro.add(new NewsFeedData(postid,id,textfeild,piclocation,creationTime,userName,picturePath));
		 }
		 
		 //-------------------------//
		 
		 insertTableSQL="select "+postInfoTable+".id,concat(fname,' ',lname) as name,"
			 		+ "piclocation,creation_time,textfeild,picturepath,postid "
			 		+ "from "+userTable+","+postInfoTable+","+friendTable
			 		+ " where "+friendTable+".id1=? and "+friendTable+".id2="+postInfoTable+".id "
			 				+ "and "+postInfoTable+".id="+userTable+".id";
			 //insertTableSQL="select * from "+postInfoTable;
			 preparedStatement =conn.prepareStatement(insertTableSQL);
			 preparedStatement.setInt(1,Integer.parseInt(Id));
			 rs=preparedStatement.executeQuery();
			 if(rs.isBeforeFirst())
			 while(rs.next()){
				 postid=rs.getString("postid");
				 id=rs.getString("id");
				 textfeild=rs.getString("textfeild");
				 piclocation=rs.getString("piclocation");
				 creationTime=rs.getString("creation_time");
				 userName=rs.getString("name");
				 picturePath=rs.getString("picturepath");
				 pro.add(new NewsFeedData(postid,id,textfeild,piclocation,creationTime,userName,picturePath));
			 }
		 }
		insertTableSQL="select "+postInfoTable+".id,concat(fname,' ',lname) as name,"
			 		+ "piclocation,creation_time,textfeild,picturepath,postid"
				+" from "+postInfoTable+","+userTable+" where "
				+ userTable+".id=? and "+postInfoTable+".id="+userTable+".id";
		
			 preparedStatement =conn.prepareStatement(insertTableSQL);
			 preparedStatement.setInt(1,Integer.parseInt(Id));
			 rs=preparedStatement.executeQuery();
			 if(rs.isBeforeFirst())
			 while(rs.next()){
				 postid=rs.getString("postid");
				 id=rs.getString("id");
				 textfeild=rs.getString("textfeild");
				 piclocation=rs.getString("piclocation");
				 creationTime=rs.getString("creation_time");
				 userName=rs.getString("name");
				 picturePath=rs.getString("picturepath");
				 ////System.out.println(piclocation+" "+id);
				 pro.add(new NewsFeedData(postid,id,textfeild,piclocation,creationTime,userName,picturePath));
			 }
		conn.close();
		Collections.sort(pro);
		return pro;
	}
}
