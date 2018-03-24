package com.init.requests;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.sql.data.SqlUserProfileData;
import com.sql.requests.SqlEventGoingInterestedCountRequest;

@Path("/count")
public class EventGoingIntrestedCount {
	
	@GET
	@Path("/going/{id}")
	public int getGoingCount(@PathParam("id") long event_id) throws SQLException{
		return new SqlEventGoingInterestedCountRequest().countGoing(event_id);
		
	}
	
	@GET
	@Path("/interested/{id}")
	public int getInterestedCount(@PathParam("id") long event_id) throws SQLException{
		return new SqlEventGoingInterestedCountRequest().countInterested(event_id);
		
	}
	
	@GET
	@Path("/listinterested/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<SqlUserProfileData> getInterestedList(@PathParam("id") long event_id) throws SQLException{
		return new SqlEventGoingInterestedCountRequest().listInterested(event_id);
		
	}
	
	@GET
	@Path("/listgoing/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<SqlUserProfileData> getGoingList(@PathParam("id") long event_id) throws SQLException{
		return new SqlEventGoingInterestedCountRequest().listGoing(event_id);
		
	}
	
}
