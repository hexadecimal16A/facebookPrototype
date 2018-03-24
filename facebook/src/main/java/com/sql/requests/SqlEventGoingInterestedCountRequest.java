package com.sql.requests;

import static com.sql.requests.SqlInit.makeSQLConn;
import static com.sql.requests.SqlInit.userTable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;
import com.sql.data.SqlUserProfileData;

public class SqlEventGoingInterestedCountRequest {

	private String Name;
	private String Email;
	private String DOB;
	private String Mobile;
	private String ProfileImage;
	private String Id;
	private int countGoing;
	private int countInterested;

	public int countInterested(long event_id) throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		countInterested = 0;
		PreparedStatement preparedStatement = null;
		try {
			conn = SqlInit.makeSQLConn();
			stmt = (Statement) conn.createStatement();
			// String sql="Insert into "+eventparticipants+"
			// values("+event_id+"," +member_id+","+event_type+")";
			String sql = "select count(*) as count from event_participants where event_id=? and status=1 ";
			System.out.println(sql);
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setLong(1, event_id);
			rs=preparedStatement.executeQuery();
			if (rs.isBeforeFirst())
				while (rs.next()) {
					countInterested = rs.getInt("count");
				}

			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return countInterested;
	}

	public ArrayList<SqlUserProfileData> listInterested(long event_id) throws SQLException {

		Connection myConn = null;
		Statement stmt = null;
		ResultSet rs = null;
		// countInterested=0;

		PreparedStatement preparedStatement = null;
		ArrayList<Integer> ids = new ArrayList<Integer>();
		ArrayList<SqlUserProfileData> pro = new ArrayList<SqlUserProfileData>();
		try {
			// stmt = (Statement) conn.createStatement();
			// String sql="Insert into "+eventparticipants+"
			// values("+event_id+"," +member_id+","+event_type+")";
			String sql = "select member_id from event_participants where event_id=? and status=1 ";
			System.out.println(sql);
			myConn = makeSQLConn();
			preparedStatement = myConn.prepareStatement(sql);
			preparedStatement.setLong(1, event_id);
			rs=preparedStatement.executeQuery();
			if (rs.isBeforeFirst())
				while (rs.next()) {
					ids.add(Integer.parseInt(rs.getString("member_id")));

				}
			// Statement stmt = null;
			stmt = (Statement) myConn.createStatement();

			for (int idd : ids) {
				// String temp="%"+args[2]+"%";
				// System.out.println(temp);
				sql = "select * from " + userTable + " where Id = '" + idd + "'";
				System.out.println(sql);
				rs = stmt.executeQuery(sql);
				if (rs.isBeforeFirst())
					while (rs.next()) {

						Name = rs.getString("fname") + " " + rs.getString("lname");
						Email = rs.getString("Email");
						Mobile = rs.getString("Mobileno");
						DOB = rs.getString("DateOfBirth");
						;
						ProfileImage = rs.getString("picturepath");
						Id = rs.getString("Id");

						// return new
						// SqlUserProfileData(Name,Email,DOB,null,Mobile,Hometown,Country,ProfilePic,null);
						pro.add(new SqlUserProfileData(Name, Email, DOB, null, Mobile, null, null, ProfileImage, null,
								null, null, null, Id));
						// System.out.println(pro.get(0).getName());
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

	
	
	
	
	
	public int countGoing(long event_id) throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		countGoing = 0;
		PreparedStatement preparedStatement = null;
		try {
			conn = SqlInit.makeSQLConn();
			stmt = (Statement) conn.createStatement();
			// String sql="Insert into "+eventparticipants+"
			// values("+event_id+"," +member_id+","+event_type+")";
			String sql = "select count(*) as count from event_participants where event_id=? and status=2 ";
			System.out.println(sql);
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setLong(1, event_id);
			rs=preparedStatement.executeQuery();
			if (rs.isBeforeFirst())
				while (rs.next()) {
					countGoing = rs.getInt("count");
				}

			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return countGoing;
	}

	public ArrayList<SqlUserProfileData> listGoing(long event_id) throws SQLException {

		Connection myConn = null;
		Statement stmt = null;
		ResultSet rs = null;
		// countInterested=0;

		PreparedStatement preparedStatement = null;
		ArrayList<Integer> ids = new ArrayList<Integer>();
		ArrayList<SqlUserProfileData> pro = new ArrayList<SqlUserProfileData>();
		try {
			// stmt = (Statement) conn.createStatement();
			// String sql="Insert into "+eventparticipants+"
			// values("+event_id+"," +member_id+","+event_type+")";
			String sql = "select member_id from event_participants where event_id=? and status=2 ";
			System.out.println(sql);
			myConn = makeSQLConn();
			preparedStatement = myConn.prepareStatement(sql);
			preparedStatement.setLong(1, event_id);
			rs=preparedStatement.executeQuery();
			if (rs.isBeforeFirst())
				while (rs.next()) {
					ids.add(Integer.parseInt(rs.getString("member_id")));

				}
			// Statement stmt = null;
			stmt = (Statement) myConn.createStatement();

			for (int idd : ids) {
				// String temp="%"+args[2]+"%";
				// System.out.println(temp);
				sql = "select * from " + userTable + " where Id = '" + idd + "'";
				System.out.println(sql);
				rs = stmt.executeQuery(sql);
				if (rs.isBeforeFirst())
					while (rs.next()) {

						Name = rs.getString("fname") + " " + rs.getString("lname");
						Email = rs.getString("Email");
						Mobile = rs.getString("Mobileno");
						DOB = rs.getString("DateOfBirth");
						;
						ProfileImage = rs.getString("picturepath");
						Id = rs.getString("Id");

						// return new
						// SqlUserProfileData(Name,Email,DOB,null,Mobile,Hometown,Country,ProfilePic,null);
						pro.add(new SqlUserProfileData(Name, Email, DOB, null, Mobile, null, null, ProfileImage, null,
								null, null, null, Id));
						// System.out.println(pro.get(0).getName());
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

}
