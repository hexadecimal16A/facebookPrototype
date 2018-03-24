package com.sql.requests;
import static com.sql.requests.SqlInit.friendTable;
import static com.sql.requests.SqlInit.makeSQLConn;
import static com.sql.requests.SqlInit.pendingRequestTable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class SqlFriendRequestGETPOST {
	private Connection conn=null;
	private PreparedStatement preparedStatement=null;
	
	
	public String friendCheck(Long id1,Long id2) throws SQLException{
		String checkSQL="select id1 from "+friendTable+" where "
				+ "(id1=? and id2=?) or (id1=? and id2=?)";
		String checkSQL2="select from_id from "+pendingRequestTable+" where "
				+ "(from_id=? and to_id=?)";
		conn=makeSQLConn();
		preparedStatement=conn.prepareStatement(checkSQL);
		preparedStatement.setLong(1,id1);
		preparedStatement.setLong(2, id2);
		preparedStatement.setLong(3,id2);
		preparedStatement.setLong(4, id1);
		ResultSet rs=preparedStatement.executeQuery();
		if(rs.isBeforeFirst()){
			conn.close();
			return "yes";
		}
		preparedStatement=conn.prepareStatement(checkSQL2);
		preparedStatement.setLong(1,id1);
		preparedStatement.setLong(2, id2);
		//preparedStatement.setLong(3,id2);
		//preparedStatement.setLong(4, id1);
		rs=preparedStatement.executeQuery();
		if(rs.isBeforeFirst()){
			conn.close();
			return "respond";
		}
		preparedStatement=conn.prepareStatement(checkSQL2);
		preparedStatement.setLong(2,id1);
		preparedStatement.setLong(1, id2);
		//preparedStatement.setLong(3,id2);
		//preparedStatement.setLong(4, id1);
		rs=preparedStatement.executeQuery();
		if(rs.isBeforeFirst()){
			conn.close();
			return "sent";
		}
		conn.close();
		return "no";
	}
	
	public boolean pendingRequest(Long from_id,Long to_id,Long extra) throws SQLException{
		try{
			String SqlPending="insert into "+pendingRequestTable+"(from_id,to_id) values (?,?)";
			conn=makeSQLConn();
			preparedStatement=conn.prepareStatement(SqlPending);
			preparedStatement.setLong(1,from_id);
			preparedStatement.setLong(2, to_id);
			if(preparedStatement.executeUpdate()>0){
				//System.out.println(" asas "+extra);
				if(extra==1){
					SqlPending="delete from suggestion where to_id=? and user_id=?";
					preparedStatement=conn.prepareStatement(SqlPending);
					preparedStatement.setLong(1,from_id);
					preparedStatement.setLong(2, to_id);
					preparedStatement.executeUpdate();
				}
				String sql="select id from (select id1 as id from "+friendTable+" where id1=? and id2=?"
						+ " union select id2 as id from "+friendTable+" where id2=? and id1=? "
								+ "union select from_id as id from "+pendingRequestTable+" where from_id=? and to_id=?) as t";
				preparedStatement=conn.prepareStatement(sql);
				preparedStatement.setLong(1,from_id);
				preparedStatement.setLong(2, to_id);
				preparedStatement.setLong(3,from_id);
				preparedStatement.setLong(4, to_id);
				preparedStatement.setLong(6,from_id);
				preparedStatement.setLong(5, to_id);
				if(preparedStatement.executeQuery().isBeforeFirst()){
					sql="delete from "+pendingRequestTable+" where (from_id=? and to_id=?)";
					preparedStatement=conn.prepareStatement(sql);
					preparedStatement.setLong(1,from_id);
					preparedStatement.setLong(2, to_id);
					preparedStatement.executeUpdate();
					return false;
				}
			}
			conn.close();
			return true;
		}
		catch(Exception e){
			System.out.println(e.toString());
			conn.close();
			return false;
		}
			
	}
	
	public boolean responseToRequest(Long req_id,String response) throws SQLException{
		String Sqldel="delete from "+pendingRequestTable+" where req_id=?";
		conn=makeSQLConn();
		if(response.compareTo("confirm")==0){
			
			String SqlAdd="insert into "+friendTable+"(id1,id2) select "
					+ "from_id,to_id from "+pendingRequestTable+" where req_id=?";
			preparedStatement=conn.prepareStatement(SqlAdd);
			preparedStatement.setLong(1,req_id);
			if(preparedStatement.executeUpdate()<=0){
				conn.close();
				return false;
			}
		}
		if(response.compareTo("confirm")==0||response.compareTo("delete")==0){
			preparedStatement=conn.prepareStatement(Sqldel);
			preparedStatement.setLong(1,req_id);
			if(preparedStatement.executeUpdate()>0){
				conn.close();
				return true;
			}
			conn.close();
			return false;
		}
		if(response.compareTo("notnow")==0){
			Sqldel="update "+pendingRequestTable+" set onhold=1 where req_id=?";
			preparedStatement=conn.prepareStatement(Sqldel);
			preparedStatement.setLong(1,req_id);
			if(preparedStatement.executeUpdate()>0){
				conn.close();
				return true;
			}
			conn.close();
			return false;
		}
		return false;
		
	}
	
	public boolean unfriend(Long id1,Long id2) throws SQLException{
		String SqlDelete="delete from "+friendTable+" where (id1=? and id2=?) or (id1=? and id2=?)";
		conn=makeSQLConn();
		try{
		preparedStatement=conn.prepareStatement(SqlDelete);
		preparedStatement.setLong(1,id1);
		preparedStatement.setLong(2, id2);
		preparedStatement.setLong(3,id2);
		preparedStatement.setLong(4, id1);
		if(preparedStatement.executeUpdate()>0){
			conn.close();
			return true;
		}
		}
		catch(Exception e){
			conn.close();
			return false;
		}
		conn.close();
		return false;
	}
	
}
