package com.init.requests;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sql.requests.SqlSportsRequest;

@Path("/sportsofinterest")
public class SportsOfInterestRequest {
	@GET
	@Path("/{id}")
	public String temp(@PathParam("id") Long id) throws SQLException{
		return new SqlSportsRequest().getSports(id);
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public boolean updateSOI(@FormParam("sports") String sports,@PathParam("id") Long id) throws SQLException{
		//System.out.println(sports+" "+id);
		
		return new SqlSportsRequest().updateSports(id, sports);
	}
}
