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

public class SqlGroupAddMember {
	private String Name;
	private String Email;
	private String DOB;
	private String Mobile;
	private String ProfileImage;
	private String Id;

	private Connection myConn=null;
	private PreparedStatement preparedStatement=null;
	
	public ArrayList<SqlUserProfileData> getList(String... args) throws SQLException {
		//System.out.println(Arrays.toString(args));
		ResultSet rs = null;
		ArrayList<Integer> ids=new ArrayList<Integer>();
		ArrayList<SqlUserProfileData> pro=new ArrayList<SqlUserProfileData>();
		try {
			String sql = "select id from(((select id2 as id from friendlist "
					+ "where id1=?) union (select id1 as id from friendlist "
					+ "where id2=?)) as t2) where id not in (select member_id as "
					+ "id from group_members where group_id=?);";
				
				// 1. Get a connection to database
				myConn = makeSQLConn();
				// 2. Create a statement
				preparedStatement = myConn.prepareStatement(sql);
				preparedStatement.setLong(1,Long.parseLong(args[1]));
				preparedStatement.setLong(2,Long.parseLong(args[1]));
				preparedStatement.setLong(3,Long.parseLong(args[0]));
				
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
				//System.out.println(temp);
				sql = "select * from "+userTable+" where Id = '" + idd + "' and concat(fname,lname) LIKE '"+temp+"'";
				//System.out.println(sql);
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
					////System.out.println(pro.get(0).getName());
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
