package com.init.requests;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sql.data.SqlUserProfileData;
import com.sql.requests.SqlAdcancedSearchRequest;

@Path("/advancedsearch")
public class AdvancedSearchPeople {

	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public ArrayList<SqlUserProfileData> getGroupSearch(@FormParam("hometown") String hometown,
            @FormParam("country") String country,@FormParam("works") String works,
            @FormParam("college") String college,@FormParam("name") String name) throws SQLException{
		
		SqlAdcancedSearchRequest sp=new SqlAdcancedSearchRequest();
		return sp.searchPeople(name,hometown,country,works,college);
	}
}
