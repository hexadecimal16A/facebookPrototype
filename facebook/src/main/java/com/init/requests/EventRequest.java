package com.init.requests;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.sql.data.SqlUserProfileData;
import com.sql.requests.SqlEventInvitationRequest;
import com.sql.requests.SqlEventInvitationResponse;
import com.sql.requests.SqlEventRequest;

@Path("/eventadd")
public class EventRequest {
	
	
	@POST
	@Path("/eventinvitation")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean invitation(@QueryParam("event_id") String event_id,
			@QueryParam("member_id") String member_id,@QueryParam("from_id") String from_id
			) throws SQLException {
		
	 return new SqlEventInvitationRequest().insert(event_id,member_id,from_id);
	}
	
	
	@POST
	@Path("/updateSummary/{id}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public boolean updateSummary(@FormParam("summaryStatus") String summary,@PathParam("id") int id) throws SQLException {
	 return new SqlEventRequest().summaryUpdate(summary, id);
	}
	
	@GET
	@Path("/checkresponse")
	@Produces(MediaType.TEXT_PLAIN)
	public String responceCheck(@QueryParam("event_id") long event_id,
			@QueryParam("member_id") long member_id
			) throws SQLException {
		System.out.println("evemnt_id"+event_id+"member_id"+member_id);
	 return new SqlEventRequest().checkStatus(event_id,member_id);
	}
	
	@GET
	@Path("/inviteFriends")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<SqlUserProfileData> inviteFriends(@QueryParam("event_id") String event_id,
			@QueryParam("member_id") String member_id,@QueryParam("searchval") String name
			) throws SQLException {
		System.out.println("evemnt_id"+event_id+"member_id"+member_id);
	 return new SqlEventRequest().geteventList(event_id,member_id,name);
	}
	
	@PUT
	@Path("/EventResponse")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean response(@QueryParam("event_id") String event_id,
			@QueryParam("member_id") String member_id,
			@QueryParam("status") String status
			) throws SQLException {
		
	 return new SqlEventInvitationResponse().insert(event_id,member_id,status);
	}
}
