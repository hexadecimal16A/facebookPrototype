package com.init.requests;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sql.data.GroupInfoData;
import com.sql.requests.SqlGroupList;
@Path("/groupinfo")
public class GroupListRequest {
	
	@POST
	@Path("/{user_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<GroupInfoData> allgroupdetailofuser(@PathParam("user_id") long user_id
			) throws SQLException {
		//System.out.println(event_name+" "+evet_details+" " + admin_id+" " +piclocation +" " +event_type+" " + start_time+ " "+end_time);
		//return true;
		return new SqlGroupList().displayusergroupinfo(user_id);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{user_id}")
	public ArrayList<GroupInfoData> allgroupdetails(@PathParam("user_id") long user_id) throws SQLException {
		//System.out.println(event_name+" "+evet_details+" " + admin_id+" " +piclocation +" " +event_type+" " + start_time+ " "+end_time);
		//return true;
		return new SqlGroupList().displayremaininggroupinfo(user_id);
	}
	
	@PUT
	@Path("/{group_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<GroupInfoData> groupdetail(@PathParam("group_id") Long group_id
			) throws SQLException {
		//System.out.println(group_id);
		//return true;
		return new SqlGroupList().groupinfo(group_id);
	}


}
