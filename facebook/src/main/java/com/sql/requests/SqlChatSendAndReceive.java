package com.sql.requests;
import static com.sql.requests.SqlInit.chat;
import static com.sql.requests.SqlInit.makeSQLConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sql.data.chat;

public class SqlChatSendAndReceive {
	Connection conn=null;
	PreparedStatement preparedStatement=null;
	ResultSet rs=null;
	
	public boolean postChat(String text,long to_id,long from_id) throws SQLException{
		conn=makeSQLConn();
		String sql="Insert into "+chat+"(to_id,from_id,flag,text) values(?,?,'0',?)";
		preparedStatement=conn.prepareStatement(sql);
		preparedStatement.setLong(1,to_id);
		preparedStatement.setLong(2,from_id);
		preparedStatement.setString(3,text);
		if(preparedStatement.execute())
			return true;
		else
			return false;
	}
	
	public ArrayList<chat> getChat(long to_id) throws SQLException{
		String text=null;
		long from_id=0;
		long chat_id=0;
		conn=makeSQLConn();
		String sql="select * from "+chat+" where to_id = ? and flag='0'";
		preparedStatement=conn.prepareStatement(sql);
		preparedStatement.setLong(1,to_id);
		rs=preparedStatement.executeQuery();
		ArrayList<chat> pro=new ArrayList<chat>();
		if(!rs.isBeforeFirst())
			return null;
		
		while(rs.next())
		{
			from_id=rs.getLong("from_id");
			text=rs.getString("text");
			chat_id=rs.getLong("chat_id");
			pro.add(new chat(text,from_id,chat_id));
		}
		return pro;
	}
	
	public ArrayList<chat> getOldChat(long id1,long id2) throws SQLException{
		String text=null;
		long from_id=0;
		long chat_id=0;
		conn=makeSQLConn();
		String sql="select * from chat where ((to_id=? and from_id=?) or (from_id=? and to_id=?))";
		preparedStatement=conn.prepareStatement(sql);
		preparedStatement.setLong(1,id1);
		preparedStatement.setLong(2,id2);
		preparedStatement.setLong(3,id1);
		preparedStatement.setLong(4,id2);
		rs=preparedStatement.executeQuery();
		ArrayList<chat> pro=new ArrayList<chat>();
		if(!rs.isBeforeFirst())
			return null;
		
		while(rs.next())
		{
			from_id=rs.getLong("from_id");
			text=rs.getString("text");
			chat_id=rs.getLong("chat_id");
			pro.add(new chat(text,from_id,chat_id));
		}
		return pro;
	}
	
	public void makeRead(long id) throws SQLException{
		conn=makeSQLConn();
		String sql="update "+chat+" set flag='1' where chat_id=?";
		preparedStatement=conn.prepareStatement(sql);
		preparedStatement.setLong(1, id);
		preparedStatement.execute();
	}
	
}
