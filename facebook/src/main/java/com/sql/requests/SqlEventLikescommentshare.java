package com.sql.requests;

import static com.sql.requests.SqlInit.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sql.data.CommentsData;
import com.sql.data.SqlUserProfileData;

public class SqlEventLikescommentshare {
	private Connection conn=null;
	private PreparedStatement preparedStatement=null;
	private String sqlQuery;
	public boolean putLike(Long post_id,Long user_id) throws SQLException{
		try{
		conn=makeSQLConn();
		sqlQuery="insert into "+eventLikeTable+" (post_id,user_id) values (?,?)";
		preparedStatement=conn.prepareStatement(sqlQuery);
		preparedStatement.setLong(1,post_id);
		preparedStatement.setLong(2,user_id);
		preparedStatement.executeUpdate();
		conn.close();
		return true;
		}
		catch(Exception e){
			sqlQuery="delete from "+eventLikeTable+" where post_id=? and user_id=?";
			preparedStatement=conn.prepareStatement(sqlQuery);
			preparedStatement.setLong(1,post_id);
			preparedStatement.setLong(2,user_id);
			preparedStatement.executeUpdate();
			conn.close();
			return false;
		}
	}
	
	//-------------------------------//
	
	public String getLike(Long post_id,Long user_id){
		String outString=null;
		try{
			conn=makeSQLConn();
			sqlQuery="select * from "+eventLikeTable+" where post_id=? and user_id=?";
			preparedStatement=conn.prepareStatement(sqlQuery);
			preparedStatement.setLong(1,post_id);
			preparedStatement.setLong(2,user_id);
			ResultSet rs=preparedStatement.executeQuery();
			if(!rs.isBeforeFirst())
				outString="false";
			else
				outString="true";
			sqlQuery="select count(*) from "+eventLikeTable+" where post_id=?";
			preparedStatement=conn.prepareStatement(sqlQuery);
			preparedStatement.setLong(1,post_id);
			rs=preparedStatement.executeQuery();
			if(!rs.isBeforeFirst()){
				outString=outString+"_0";
			}
			else{
				while(rs.next())
					outString=outString+"_"+rs.getString(1);
			}
				
			return outString;
		}
			catch(Exception e){
				return e.toString();
			}
	}
	
	//-------------------------------------//
	
	public ArrayList<SqlUserProfileData> getLikeInfo(Long post_id) throws SQLException{
		ArrayList<SqlUserProfileData> pro=new ArrayList<SqlUserProfileData>();
		conn=makeSQLConn();
		try{
		
		sqlQuery="select fname,lname,id,picturepath from "+userTable+" inner join  "
				+eventLikeTable+" on "+eventLikeTable+".user_id="+userTable+".id where post_id=?";
		preparedStatement=conn.prepareStatement(sqlQuery);
		////System.out.println("--aa");
		preparedStatement.setLong(1, post_id);
		////System.out.println("aa");
		ResultSet rs=preparedStatement.executeQuery();
		////System.out.println("aa--");
		if(!rs.isBeforeFirst())
			return null;
		while(rs.next()){
			pro.add(new SqlUserProfileData((rs.getString("fname")+" "+rs.getString("lname")),null
					,null,null,null,null,null,rs.getString("picturepath"),null,null,null,null,rs.getString("id")));
		}
		conn.close();
		return pro;
		}
		catch(Exception e){
			conn.close();
			return null;
		}
	}
	
	//----------------------------------//
	
	public String getComments(Long post_id) throws SQLException{
		conn=makeSQLConn();
		try{
			
			sqlQuery="select count(*) from "+eventCommentTable+" where post_id=?";
			preparedStatement=conn.prepareStatement(sqlQuery);
			preparedStatement.setLong(1,post_id);
			ResultSet rs=preparedStatement.executeQuery();
			if(!rs.isBeforeFirst()){
				conn.close();
				return "0";
			}
			else{
				while(rs.next()){
					//conn.close();
					return rs.getString(1);	
				}
			}
			conn.close();
			return "0";	
		}
			catch(Exception e){
				conn.close();
				return e.toString();
			}
	}
	
	//------------------------------//
	
	public ArrayList<CommentsData> getCommentsInfo(Long post_id) throws SQLException{
		ArrayList<CommentsData> pro=new ArrayList<CommentsData>();
		conn=makeSQLConn();
		try{
		
		sqlQuery="select fname,lname,id,picturepath,commenttext,comment_id from "
				+userTable+","+eventCommentTable+" where "+eventCommentTable
				+".user_id="+userTable+".id and post_id=?";
		preparedStatement=conn.prepareStatement(sqlQuery);
		////System.out.println("--aa");
		preparedStatement.setLong(1, post_id);
		////System.out.println("aa");
		ResultSet rs=preparedStatement.executeQuery();
		////System.out.println("aa--");
		if(!rs.isBeforeFirst())
			return null;
		while(rs.next()){
			pro.add(new CommentsData(rs.getString("commenttext"),rs.getString("picturepath"),
					rs.getString("id"),rs.getString("comment_id"),rs.getString("fname")+" "+rs.getString("lname")));
		}
		conn.close();
		return pro;
		}
		catch(Exception e){
			//System.out.println(e.toString());
			conn.close();
			return null;
		}
	}
	
	public boolean putComment(Long post_id,Long user_id,String commenttext) throws SQLException{
		try{
		conn=makeSQLConn();
		sqlQuery="insert into "+eventCommentTable+" (post_id,user_id,commenttext,creation_time) values (?,?,?,?)";
		preparedStatement=conn.prepareStatement(sqlQuery);
		preparedStatement.setLong(1,post_id);
		preparedStatement.setLong(2,user_id);
		preparedStatement.setString(3,commenttext);
		preparedStatement.setString(4,String.valueOf(System.currentTimeMillis()));
		preparedStatement.executeUpdate();
		conn.close();
		return true;
		}
		catch(Exception e){
			conn.close();
			//System.out.println(e);
			return false;
		}
	}
	
}
