package com.sql.requests;

import static com.sql.requests.SqlInit.makeSQLConn;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;
import com.sql.data.GroupInfoData;
public class SqlGroupList {
	
	private String group_id;
	private String group_name;
	private String admin_id;
	private String piclocation;
	private String privacy;
	private String group_count;
	
	public ArrayList<GroupInfoData> displayusergroupinfo(Long id) throws SQLException
	{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<GroupInfoData> pro=new ArrayList<GroupInfoData>();
		
		String sql=	"select group_info.group_id,group_name,admin_id,piclocation,privacy,e.group_count "
				+ "from group_info,group_members,(select count(member_id) as group_count,"
				+ " group_id from group_members group by group_id) e "
				+ "where group_info.group_id=group_members.group_id and member_id="+id+" and "
						+ "group_info.group_id=e.group_id";
	////System.out.println(sql);
	conn = makeSQLConn();
	stmt = (Statement) conn.createStatement();
	rs = stmt.executeQuery(sql);
	////System.out.println(sql+"\n");
	if(!rs.isBeforeFirst())
		return null;
	else
	{
	while(rs.next()){
			group_id=rs.getString("group_id");
			group_name=rs.getString("group_name");
			admin_id=rs.getString("admin_id");
			piclocation=rs.getString("piclocation");
			privacy=rs.getString("privacy");
			group_count=rs.getString("group_count");
			pro.add(new GroupInfoData(group_id,group_name,admin_id,piclocation,privacy,group_count));
		}
	}
	conn.close();	
	return pro;
	}
	
	
	public ArrayList<GroupInfoData> displayremaininggroupinfo(Long id) throws SQLException
	{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<GroupInfoData> pro=new ArrayList<GroupInfoData>();
		
		String sql=	"select f.group_id,group_name,admin_id,piclocation,privacy,e.group_count from "
				+ "group_info f,(select count(member_id) as group_count, group_id from group_members group by group_id) e "
				+ "where f.group_id not in (select group_id from group_members where member_id="+id+") "
				+ "and f.group_id=e.group_id and privacy=0";
	////System.out.println(sql);
	conn = makeSQLConn();
	stmt = (Statement) conn.createStatement();
	rs = stmt.executeQuery(sql);
	////System.out.println(sql+"\n");
	if(!rs.isBeforeFirst())
		return null;
	else
	{
	while(rs.next()){
			group_id=rs.getString("group_id");
			group_name=rs.getString("group_name");
			admin_id=rs.getString("admin_id");
			piclocation=rs.getString("piclocation");
			privacy=rs.getString("privacy");
			group_count=rs.getString("group_count");
			pro.add(new GroupInfoData(group_id,group_name,admin_id,piclocation,privacy,group_count));
		}
	}
	conn.close();	
	return pro;
	}
	
	
	public ArrayList<GroupInfoData> groupinfo(Long id) throws SQLException
	{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<GroupInfoData> pro=new ArrayList<GroupInfoData>();
		
		String sql=	"select e.group_id,group_name,privacy,admin_id,piclocation,"
				+ "count(member_id) as group_count from group_info e,group_members f"
				+ " where e.group_id=f.group_id and e.group_id="+id;
	////System.out.println(sql);
	conn = makeSQLConn();
	stmt = (Statement) conn.createStatement();
	rs = stmt.executeQuery(sql);
	////System.out.println(sql+"\n");
	if(!rs.isBeforeFirst())
		return null;
	else
	{
	while(rs.next()){
			group_id=rs.getString("group_id");
			group_name=rs.getString("group_name");
			admin_id=rs.getString("admin_id");
			piclocation=rs.getString("piclocation");
			privacy=rs.getString("privacy");
			group_count=rs.getString("group_count");
			pro.add(new GroupInfoData(group_id,group_name,admin_id,piclocation,privacy,group_count));
		}
	}
	conn.close();	
	return pro;
	}
	
	
	
	
}