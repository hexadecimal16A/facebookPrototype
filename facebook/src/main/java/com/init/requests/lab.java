package com.init.requests;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.sql.data.GroupInfoData;
import com.sql.requests.SqlLab;

@Path("/lab")
public class lab {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<GroupInfoData> getList(@QueryParam("user_id") long id,@QueryParam("email_id") String email_id) throws SQLException{
		System.out.println(id+" "+email_id);
		
		return new SqlLab().getList(email_id, id);
	}
	
	@GET
	@Path("/getCountName")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCountName(@QueryParam("email_id") String email_id ,@QueryParam("group_id") long group_id,@QueryParam("member_id") long member_id,@QueryParam("id") long id) throws SQLException{
		System.out.println(group_id+" "+member_id+" "+email_id+" "+id);
		
		return new SqlLab().getCountAndName(group_id, member_id,email_id,id);
	}
}
