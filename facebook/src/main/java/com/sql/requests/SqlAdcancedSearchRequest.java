package com.sql.requests;

import static com.sql.requests.SqlInit.makeSQLConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sql.data.SqlUserProfileData;

public class SqlAdcancedSearchRequest {

	private String Name;
	private String Email;
	private String DOB;
	private String Mobile;
	private String ProfilePic;
	private String Id;
	private Connection Conn = null;
	private PreparedStatement preparedStatement = null;

	public ArrayList<SqlUserProfileData> searchPeople(String... data)
			throws SQLException {
		// preparedStatementtsetString(1,people);
		// System.out.println(data[1].isEmpty());
		// if(data[1].equals(""))
		// System.out.println("right");

		String sql = "select * from userprofile where id in ( select a.id from userprofile  a,userotherinfo b where a.id=b.id";
		if (!data[0].equals(""))
			sql+=" and concat(fname,' ',lname) like '%"+data[0]+"%'";

		// System.out.println(data[1].equals(""));
		if (!data[1].equals(""))
			sql+=" and hometown like '%"+data[1]+"%'";

		if (!data[2].equals("")) 
			sql+=" and country like '%"+data[2]+"%'";

		if (!data[3].equals("")) 
			sql+=" and work like '%"+data[3]+"%'";

		if (!data[4].equals(""))
			sql+=" and college like '%"+data[4]+"%'";
		
		sql+=")";

		// System.out.print(SQLQuery);

		try {
			Conn = makeSQLConn();
			preparedStatement = Conn.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			ArrayList<SqlUserProfileData> pro = new ArrayList<SqlUserProfileData>();
			if (!rs.isBeforeFirst())
				return null;
			while (rs.next()) {
				Name = rs.getString("fname") + " " + rs.getString("lname");
				Email = rs.getString("Email");
				Mobile = rs.getString("Mobileno");
				DOB = rs.getString("DateOfBirth");
				ProfilePic = rs.getString("picturepath");
				Id = rs.getString("Id");
				pro.add(new SqlUserProfileData(Name, Email, DOB, null, Mobile,
						null, null, ProfilePic, null, null, null, null, Id));
			}
			Conn.close();
			return pro;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		Conn.close();
		return null;
	}

}
