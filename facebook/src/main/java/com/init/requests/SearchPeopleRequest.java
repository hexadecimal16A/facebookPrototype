package com.init.requests;

import java.sql.SQLException;
import java.util.ArrayList;

import com.sql.data.GroupInfoData;
import com.sql.data.SqlUserProfileData;
import com.sql.requests.SqlSearchRequest;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;



@Path("/search")
public class SearchPeopleRequest {
	private static String searchText="";
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public boolean text(@FormParam("searchPeople") String searchPeople){
		//System.out.println("searchPeople="+searchPeople);
		searchText=searchPeople;
		return true;
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getPeople() throws SQLException{
		//System.out.println("yes"+searchText);
		String a=searchText;
		searchText="";
		return a;
	}
	
	@GET
	@Path("/{people}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<SqlUserProfileData> getPeopleSearch(@PathParam("people") String people) throws SQLException{
		//System.out.println(people);
		SqlSearchRequest sp=new SqlSearchRequest();
		return sp.searchPeopleRequest(people);
	}
	
	@POST
	@Path("/{group}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<GroupInfoData> getGroupSearch(@PathParam("group") String group) throws SQLException{
		//System.out.println(group);
		
		SqlSearchRequest sp=new SqlSearchRequest();
		return sp.searchGroupRequest(group);
	}
	
	
}
