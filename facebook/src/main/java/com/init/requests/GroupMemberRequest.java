package com.init.requests;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.sql.data.GroupMemberdata;
import com.sql.data.SqlUserProfileData;
import com.sql.requests.SqlGroupAddMember;
import com.sql.requests.SqlGroupMember;

@Path("/groupinfo")
public class GroupMemberRequest {
	

	@POST
	@Path("/add/{completeids}")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean addmember(@PathParam("completeids") String completeids)
			throws SQLException {
		String group_id = completeids.split("_")[0];
		String member_id = completeids.split("_")[1];
		//System.out.println(group_id + member_id);
		return new SqlGroupMember().insert(group_id, member_id);
	}

	@DELETE
	@Path("/delete")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean deteteMember(@QueryParam("group_id") String group_id,
			@QueryParam("member_id") String member_id) throws SQLException {
		//System.out.println(group_id + member_id);
		return new SqlGroupMember().delete(group_id, member_id);
	}

	@PUT
	@Path("/list/{group_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<GroupMemberdata> listMember(
			@PathParam("group_id") String group_id) throws SQLException {
		//System.out.println(group_id);
		return new SqlGroupMember().getMembers(group_id);
	}

	@GET
	@Path("/groupMemberCheck")
	@Produces(MediaType.TEXT_PLAIN)
	public String groupMemberCheck(@QueryParam("group_id") Long id1,
			@QueryParam("member_id") Long id2) throws SQLException {
		//System.out.println(id1 + " " + id2);
		return new SqlGroupMember().groupMemberCheck(id1, id2);
	}
	
	@GET
	@Path("/addMembersList")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<SqlUserProfileData> addMembersList(@QueryParam("group_id") String id1,
			@QueryParam("user_id") String id2,@QueryParam("searchval") String searchval) throws SQLException {
		//System.out.println(id1 + " " + id2);
		return new SqlGroupAddMember().getList(id1,id2,searchval);
	}

}
