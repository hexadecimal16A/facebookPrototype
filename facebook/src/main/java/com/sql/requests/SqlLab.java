package com.sql.requests;

import static com.sql.requests.SqlInit.makeSQLConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sql.data.GroupInfoData;

public class SqlLab {
	private String group_id;
	private String group_name;
	private String admin_id;
	private String piclocation;
	private String privacy;
	private String group_count;

	private Connection conn = null;
	PreparedStatement preparedStatement = null;
	ResultSet rs = null;

	public ArrayList<GroupInfoData> getList(String email_id, long id)
			throws SQLException {
		ArrayList<GroupInfoData> pro = new ArrayList<GroupInfoData>();
		conn = makeSQLConn();
		String sql1="select * from friendlist where (id1 = (select id from userprofile where email=?) and id2=?) or (id1=? and id2=(select id from userprofile where email=?))";
		preparedStatement = conn.prepareStatement(sql1);
		preparedStatement.setLong(2, id);
		preparedStatement.setString(1, email_id);
		preparedStatement.setLong(3, id);
		preparedStatement.setString(4, email_id);
		rs = preparedStatement.executeQuery();
		if (!rs.isBeforeFirst()) {
			conn.close();
			return pro;
		}
		String sql = "select * from group_info where group_id in (select group_id from group_members where member_id=(select id from userprofile where email=?) and group_id in (select group_id from group_members where member_id=?))";


		preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setLong(2, id);
		preparedStatement.setString(1, email_id);
		rs = preparedStatement.executeQuery();
		if (!rs.isBeforeFirst()) {
			return pro;
		} else {

			while (rs.next()) {
				group_id = rs.getString("group_id");
				group_name = rs.getString("group_name");
				admin_id = rs.getString("admin_id");
				piclocation = rs.getString("piclocation");
				pro.add(new GroupInfoData(group_id, group_name, admin_id,
						piclocation, null, null));
			}
		}
		conn.close();
		return pro;
	}
	
	//select concat(fname,' ',lname) from userprofile where id=(select admin_id from group_info where group_id=4);
	//select count(*) from group_members where member_id=7;
	public String getCountAndName(long group_id,long member_id,String email_id,long id) throws SQLException{
		String name=null;
		String count=null;
		conn = makeSQLConn();
		String sql = "select concat(fname,' ',lname) as name from userprofile "
				+ "where id=(select admin_id from group_info where group_id=?)";
		preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setLong(1, group_id);
		rs = preparedStatement.executeQuery();
		while(rs.next()){
			name=rs.getString("name");
		}
		
		/*sql = "select count(*) as count from group_members where member_id=?";
		preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setLong(1, member_id);
		rs = preparedStatement.executeQuery();*/
		sql = "select count(*) as count from group_members where group_id in (select group_id from group_members where member_id=(select id from userprofile where email=?) and group_id in (select group_id from group_members where member_id=?)) and member_id=?";
		preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setString(1, email_id);
		preparedStatement.setLong(3, member_id);
		preparedStatement.setLong(2, id);
		rs = preparedStatement.executeQuery();
		while(rs.next()){
			count=rs.getString("count");
		}
		return name+"_"+count;
	}

}
