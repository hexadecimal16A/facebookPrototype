package com.sql.requests;

import static com.sql.requests.SqlInit.extrainfo;
import static com.sql.requests.SqlInit.makeSQLConn;
import static com.sql.requests.SqlInit.userTable;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;
import com.sql.data.SqlUserProfileData;
public class SqlProfileRequest {
	private String Name;
	private String Email;
	private String DOB;
	private String Mobile;
	private String Hometown;
	private String Country;
	private String ProfilePic;
	private String Work;
	private String Livesin;
	private String College;
	private String Gender;
	
	public SqlUserProfileData select(String... args) throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn=makeSQLConn();
			stmt = (Statement) conn.createStatement();
			long id = Long.parseLong(args[0]);
			String sql = "select * from "+userTable+","+extrainfo+" where "+userTable+".id="+id +" and "+userTable+".id="+extrainfo+".id";

			rs = stmt.executeQuery(sql);
			if(rs.isBeforeFirst())
			while (rs.next()) {
				Name = rs.getString("fname")+" "+rs.getString("lname");
				Email = rs.getString("Email");
				Mobile = rs.getString("Mobileno");
				Hometown=rs.getString("Hometown");
				DOB=rs.getString("DateOfBirth");
				Country=rs.getString("Country");
				ProfilePic=rs.getString("picturepath");
				Work=rs.getString("work");
				Livesin=rs.getString("livesin");
				College=rs.getString("college");
				Gender=rs.getString("gender");
				////System.out.println(Name+Email+Mobile+Hometown+DOB+Country+Work+Livesin+College+Gender);
				conn.close();
				return new SqlUserProfileData(Name,Email,DOB,null,Mobile,Hometown,Country,ProfilePic,Work,Livesin,College,Gender,null);
			}
			
		} catch (Exception e) {
			conn.close();
			e.printStackTrace();
		} finally {
			try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		conn.close();
		return null;
	}
	
	
}
