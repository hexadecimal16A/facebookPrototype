package com.sql.requests;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static com.sql.requests.SqlInit.*;

import com.sql.data.SqlUserProfileData;

public class SqlMutualFriend {
	private Connection conn=null;
	private PreparedStatement preparedStatement=null;
	public ArrayList<SqlUserProfileData> MutualFriend(Long id1,Long id2) throws SQLException{
		ArrayList<SqlUserProfileData> pro = new ArrayList<SqlUserProfileData>();
		try{
		conn=makeSQLConn();
		String sql="select * from userprofile where id in (select id from (select id1 as id from friendlist where id2=? union select id2 as id from friendlist where id1=?) as t1 where id in (select id1 as id from friendlist where id2=? union select id2 as id from friendlist where id1=?))";
		preparedStatement=conn.prepareStatement(sql);
		preparedStatement.setLong(1, id1);
		preparedStatement.setLong(2, id1);
		preparedStatement.setLong(3, id2);
		preparedStatement.setLong(4, id2);
		ResultSet rs=null;
		rs=preparedStatement.executeQuery();
		if(!rs.isBeforeFirst())
			return null;
		while(rs.next()){
			pro.add(new SqlUserProfileData(rs.getString("fname")+" "+rs.getString("lname"),rs.getString("Email"),
				rs.getString("DateOfBirth"),null,null,null,null,
				rs.getString("picturepath"),null,null,null,null,rs.getString("id")
				));
		}
		conn.close();
		return pro;
		}
		catch(Exception e){
			conn.close();
			System.out.println(e.toString());
			return null;
		}
	}
}
