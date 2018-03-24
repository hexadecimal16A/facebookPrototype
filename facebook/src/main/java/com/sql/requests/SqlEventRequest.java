package com.sql.requests;

import static com.sql.requests.SqlInit.eventinfo;
import static com.init.requests.PathInit.*;
import static com.sql.requests.SqlInit.groupinfo;
import static com.sql.requests.SqlInit.makeSQLConn;
import static com.sql.requests.SqlInit.pendingRequestTable;
import static com.sql.requests.SqlInit.userTable;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import com.mysql.jdbc.Statement;
import com.sql.data.EventInfoData;
import com.sql.data.FriendPendingRequestData;
import com.sql.data.SqlUserProfileData;

public class SqlEventRequest {
	private String event_id;
	private String event_name;
	private String description;
	private String admin_id;
	private String piclocation;
	private String event_type;
	private String start_date;
	private String end_date;
	private String location;
	private String summary;

	public String insert(String... args) throws SQLException {
		Connection myConn = null;
		PreparedStatement preparedStatement = null;
		String insertTableSQL = "insert into "
				+ eventinfo
				+ " (event_name,description,admin_id,start_date,end_date,location) values"

				+ " (?,?,?,?,?,?)";
		try {
			// 1. Get a connection to database
			myConn = makeSQLConn();
			// 2. Create a statement
			preparedStatement = myConn.prepareStatement(insertTableSQL);
			preparedStatement.setString(1, args[0]);
			preparedStatement.setString(2, (args[1]));
			preparedStatement.setLong(3, Long.parseLong(args[2]));
			preparedStatement.setString(4, (args[3]));
			preparedStatement.setString(5, (args[4]));
			preparedStatement.setString(6, (args[5]));

			preparedStatement.executeUpdate();
			myConn.close();
			System.out.println("1st executed");
			return createDir(args[0], args[2]);

			// 4. Process the result se

		} catch (Exception exc) {
			exc.printStackTrace();
		}
		myConn.close();
		return null;
	}

	private String createDir(String... args) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		int id = Integer.parseInt(args[1]);
		String sql = "select event_id from " + eventinfo
				+ " where event_name='" + args[0] + "'and admin_id=" + id;
		// String Id=new SqlLogInRequest().select(args);
		System.out.println(sql);
		Connection myConn = makeSQLConn();
		stmt = (Statement) myConn.createStatement();
		rs = stmt.executeQuery(sql);
		if (!rs.isBeforeFirst())
			return null;
		else
			while (rs.next()) {
				event_id = rs.getString("event_id");
				SqlGroupMember s1 = new SqlGroupMember();
				s1.insert(args[1], event_id);

				// conn.close();
				// return group_id;
			}

		File theDir = new File(EVENT_PATH + event_id);

		// if the directory does not exist, create it
		if (!theDir.exists()) {
			System.out.print("DIR");
			System.out.println("creating directory: " + event_id);
			boolean result = false;

			try {
				theDir.mkdir();
				result = true;
			} catch (SecurityException se) {
				myConn.close();
				return null;// handle it
			}
			if (result) {
				System.out.println("DIR created");
				myConn.close();
				return event_id;
			}
			return null;
		}
		return event_id;
	}

	public ArrayList<EventInfoData> display() throws SQLException {

		Connection conn = null;
		ArrayList<EventInfoData> pro = new ArrayList<EventInfoData>();
		conn = makeSQLConn();
		Statement stmt = (Statement) conn.createStatement();
		String sql = "select * from event_info where event_type=0";
		ResultSet rs = stmt.executeQuery(sql);
		if (!rs.isBeforeFirst())
			return null;
		else {
			while (rs.next()) {
				event_id = rs.getString("event_id");
				event_name = rs.getString("event_name");
				description = rs.getString("description");
				admin_id = rs.getString("admin_id");
				piclocation = rs.getString("piclocation");
				event_type = rs.getString("event_type");
				start_date = rs.getString("start_date");
				end_date = rs.getString("end_date");
				location = rs.getString("location");
				summary = rs.getString("summary");
				pro.add(new EventInfoData(event_id, event_name, description,
						admin_id, piclocation, event_type, start_date,
						end_date, location, summary));
			}
		}
		conn.close();
		return pro;
	}

	public ArrayList<EventInfoData> myevent(int id) throws SQLException {

		Connection conn = null;
		ArrayList<EventInfoData> pro = new ArrayList<EventInfoData>();
		conn = makeSQLConn();
		Statement stmt = (Statement) conn.createStatement();
		String sql = "select * from event_info where admin_id=" + id;
		ResultSet rs = stmt.executeQuery(sql);
		if (!rs.isBeforeFirst())
			return null;
		else {
			while (rs.next()) {
				event_id = rs.getString("event_id");
				event_name = rs.getString("event_name");
				description = rs.getString("description");
				admin_id = rs.getString("admin_id");
				piclocation = rs.getString("piclocation");
				event_type = rs.getString("event_type");
				start_date = rs.getString("start_date");
				end_date = rs.getString("end_date");
				location = rs.getString("location");
				summary = rs.getString("summary");
				pro.add(new EventInfoData(event_id, event_name, description,
						admin_id, piclocation, event_type, start_date,
						end_date, location, summary));
			}
		}
		conn.close();
		return pro;
	}

	public ArrayList<EventInfoData> singleventinfo(String id)
			throws SQLException {

		Connection conn = null;
		ArrayList<EventInfoData> pro = new ArrayList<EventInfoData>();
		conn = makeSQLConn();
		Statement stmt = (Statement) conn.createStatement();
		String sql = "select * from event_info where event_id="
				+ Integer.parseInt(id);
		ResultSet rs = stmt.executeQuery(sql);
		if (!rs.isBeforeFirst())
			return null;
		else {
			while (rs.next()) {
				event_id = rs.getString("event_id");
				event_name = rs.getString("event_name");
				description = rs.getString("description");
				admin_id = rs.getString("admin_id");
				piclocation = rs.getString("piclocation");
				event_type = rs.getString("event_type");
				start_date = rs.getString("start_date");
				end_date = rs.getString("end_date");
				location = rs.getString("location");
				summary = rs.getString("summary");
				pro.add(new EventInfoData(event_id, event_name, description,
						admin_id, piclocation, event_type, start_date,
						end_date, location, summary));
			}
		}
		conn.close();
		return pro;

	}

	public ArrayList<EventInfoData> displayOtherEvent(int id)
			throws SQLException {

		Connection conn = null;
		ArrayList<EventInfoData> pro = new ArrayList<EventInfoData>();
		conn = makeSQLConn();
		Statement stmt = (Statement) conn.createStatement();
		String sql = "select * from event_info where event_id not in (select event_id from event_info where admin_id="
				+ id + ")";
		ResultSet rs = stmt.executeQuery(sql);
		if (!rs.isBeforeFirst())
			return null;
		else {
			while (rs.next()) {
				event_id = rs.getString("event_id");
				event_name = rs.getString("event_name");
				description = rs.getString("description");
				admin_id = rs.getString("admin_id");
				piclocation = rs.getString("piclocation");
				event_type = rs.getString("event_type");
				start_date = rs.getString("start_date");
				end_date = rs.getString("end_date");
				location = rs.getString("location");
				summary = rs.getString("summary");
				pro.add(new EventInfoData(event_id, event_name, description,
						admin_id, piclocation, event_type, start_date,
						end_date, location, summary));
			}
		}
		conn.close();
		return pro;
	}

	public boolean update(String... args) throws SQLException {
		Connection myConn = null;
		PreparedStatement preparedStatement = null;
		String insertTableSQL = "update "
				+ eventinfo
				+ " set event_name=?,description=?,start_date=?,end_date=?,location=? where event_id=?";

		try {
			// 1. Get a connection to database
			myConn = makeSQLConn();
			// 2. Create a statement
			preparedStatement = myConn.prepareStatement(insertTableSQL);
			preparedStatement.setString(1, args[0]);
			preparedStatement.setString(2, (args[1]));
			preparedStatement.setString(3, (args[2]));
			preparedStatement.setString(4, (args[3]));
			preparedStatement.setString(5, (args[4]));
			preparedStatement.setLong(6, Long.parseLong(args[5]));
			preparedStatement.executeUpdate();
			myConn.close();
			System.out.println("1st executed");
			return true;

			// 4. Process the result se

		} catch (Exception exc) {
			exc.printStackTrace();
		}
		myConn.close();
		return false;
	}

	public boolean summaryUpdate(String summary, int id) throws SQLException {
		Connection myConn = null;
		PreparedStatement preparedStatement = null;
		String insertTableSQL = "update " + eventinfo
				+ " set summary=? where event_id=?";

		try {
			// 1. Get a connection to database
			myConn = makeSQLConn();
			// 2. Create a statement
			preparedStatement = myConn.prepareStatement(insertTableSQL);
			preparedStatement.setString(1, summary);

			preparedStatement.setInt(2, id);

			preparedStatement.executeUpdate();
			myConn.close();

			return true;

			// 4. Process the result se

		} catch (Exception exc) {
			exc.printStackTrace();
		}
		myConn.close();
		return false;
	}
	
	
	public String checkStatus(long event_id,long member_id) throws SQLException {
		Connection conn = makeSQLConn();
		PreparedStatement preparedStatement = null;
		String Status="0";
		
		try{
		String sql = "select * from event_participants where event_id=? and member_id=?";
		preparedStatement= conn.prepareStatement(sql);
		preparedStatement.setLong(1,event_id);
		preparedStatement.setLong(2,member_id);
		ResultSet result = preparedStatement.executeQuery();
		
		
		if(!result.isBeforeFirst())
		{	
			return Status;//System.out.println("no entry found for member id="+member_id+"in event_id="+event_id);
			}
		else
		{
			System.out.println("aaaaaaaaa");
			while(result.next())
				Status=String.valueOf(result.getInt("status"));
				
			}											
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}
		return Status;
			// TODO: handle exception

	}
	
	public ArrayList<SqlUserProfileData> geteventList(String... args) throws SQLException {
		
		String Name;
		String Email;
		String DOB;
		String Mobile;
		String ProfileImage;
		String Id;

		Connection myConn=null;
		PreparedStatement preparedStatement=null;
		System.out.println(Arrays.toString(args));
		ResultSet rs = null;
		ArrayList<Integer> ids=new ArrayList<Integer>();
		ArrayList<SqlUserProfileData> pro=new ArrayList<SqlUserProfileData>();
		try {
			String sql = "select id from(((select id2 as id from friendlist "
					+ "where id1=?) union (select id1 as id from friendlist "
					+ "where id2=?)) as t2) where id not in (select member_id as "
					+ "id from event_participants where event_id=? union select admin_id as id from event_info where event_id=?);";
				
				// 1. Get a connection to database
				myConn = makeSQLConn();
				// 2. Create a statement
				preparedStatement = myConn.prepareStatement(sql);
				preparedStatement.setLong(1,Long.parseLong(args[1]));
				preparedStatement.setLong(2,Long.parseLong(args[1]));
				preparedStatement.setLong(3,Long.parseLong(args[0]));
				preparedStatement.setLong(4,Long.parseLong(args[0]));
				rs=preparedStatement.executeQuery();
			if(rs.isBeforeFirst())
			while (rs.next()) {
				ids.add(Integer.parseInt(rs.getString("id")));
				
			}
			Statement stmt = null;
			stmt = (Statement) myConn.createStatement();
			
			for(int idd:ids)
			{
				String temp="%"+args[2]+"%";
				System.out.println(temp);
				sql = "select * from "+userTable+" where Id = '" + idd + "' and concat(fname,lname) LIKE '"+temp+"'";
				System.out.println(sql);
				rs = stmt.executeQuery(sql);
				if(rs.isBeforeFirst())
				while (rs.next()) {
				
					Name = rs.getString("fname")+" "+rs.getString("lname");
					Email = rs.getString("Email");
					Mobile = rs.getString("Mobileno");
					DOB=rs.getString("DateOfBirth");;
					ProfileImage=rs.getString("picturepath");
					Id=rs.getString("Id");
				
					//return new SqlUserProfileData(Name,Email,DOB,null,Mobile,Hometown,Country,ProfilePic,null);
					pro.add(new SqlUserProfileData(Name,Email,DOB,null,Mobile,null,null,ProfileImage,null,null,null,null,Id));
					//System.out.println(pro.get(0).getName());
				}	
			}
			myConn.close();
			return pro;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		myConn.close();
		return null;
	}
	
	public ArrayList<EventInfoData> displayInvites(int id) throws SQLException {

		Connection conn = null;
		ArrayList<EventInfoData> pro = new ArrayList<EventInfoData>();
		conn = makeSQLConn();
		Statement stmt = (Statement) conn.createStatement();
		String sql = "select * from event_info where event_id  in (select event_id from event_participants where member_id="
				+ id + " and flag<>0)";
		ResultSet rs = stmt.executeQuery(sql);
		if (!rs.isBeforeFirst())
			return null;
		else {
			while (rs.next()) {
				event_id = rs.getString("event_id");
				event_name = rs.getString("event_name");
				description = rs.getString("description");
				admin_id = rs.getString("admin_id");
				piclocation = rs.getString("piclocation");
				event_type = rs.getString("event_type");
				start_date = rs.getString("start_date");
				end_date = rs.getString("end_date");
				location = rs.getString("location");
				summary = rs.getString("summary");
				pro.add(new EventInfoData(event_id, event_name, description, admin_id, piclocation, event_type,
						start_date, end_date, location, summary));
			}
		}
		conn.close();
		return pro;
	}
	
	public ArrayList<EventInfoData> eventparticipants(long id) throws SQLException {
		// preparedStatement.setString(1,people);
		
		
		String SQLQuery="select event_id from event_participants where member_id=? and (status=1 or status=2)";
		
		Connection conn = null;
		ArrayList<EventInfoData> pro = new ArrayList<EventInfoData>();
		conn = makeSQLConn();
		Statement stmt = (Statement) conn.createStatement();
		ArrayList<String> pro1=new ArrayList<String>();
		PreparedStatement preparedStatement = null;
		try {
			conn = makeSQLConn();
			preparedStatement = conn.prepareStatement(SQLQuery);
			preparedStatement.setLong(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			if (!rs.isBeforeFirst())
				return null;
			while (rs.next()) {
				pro1.add(rs.getString("event_id"));
			}
			// return pro;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			conn.close();
		}
		// return null;
		for (String s : pro1) {
			long x = Long.parseLong(s);
			String S1 = "select * from " + eventinfo + " where event_id=? or admin_id=?";
			preparedStatement=conn.prepareStatement(S1);
			preparedStatement.setLong(1, x);
			preparedStatement.setLong(2, id);
			ResultSet rs = preparedStatement.executeQuery();
			if (!rs.isBeforeFirst())
				return null;
			while (rs.next()) {
				 event_id = rs.getString("event_id");
				 event_name = rs.getString("event_name");
				description = rs.getString("description");
				 admin_id = rs.getString("admin_id");
				 piclocation = rs.getString("piclocation");
				 event_type = rs.getString("event_type");
				 start_date = rs.getString("start_date");
				 end_date = rs.getString("end_date");
				 location = rs.getString("location");
				 summary = rs.getString("summary");
				pro.add(new EventInfoData(event_id, event_name, description, admin_id, piclocation, event_type,
						start_date, end_date, location, summary));
			}

		}
		conn.close();
		return pro;
	}



}
