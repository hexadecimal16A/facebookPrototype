package com.init.requests;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sql.data.SqlUserProfileData;
import com.sql.requests.SqlProfileRequest;
import com.sql.requests.SqlProfileUpdateRequest;


@Path("/profile")
public class ProfileDataRequest {
	private SqlProfileRequest sqlProfileRequest=new SqlProfileRequest();
	
	
	@POST
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public SqlUserProfileData getProfileData(@PathParam("id") String id) throws SQLException{
		return sqlProfileRequest.select(id);
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public boolean updateProfileData(@PathParam("id") String id,
			@FormParam("name") String name,
			@FormParam("fname") String fname,
            @FormParam("dOB") String dOB,
            @FormParam("mobile") String mobile,
            @FormParam("hometown") String hometown,
            @FormParam("country") String country,
            @FormParam("lname") String lname,
            @FormParam("works") String works,
            @FormParam("livesin") String livesin,
            @FormParam("college") String college) throws SQLException{
			//System.out.println(country);
		fname=name.split(" ")[0];
		lname=name.split(" ")[1];
		try{
		String actualDate = dOB;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
		DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ENGLISH);
		LocalDate ld = LocalDate.parse(actualDate, dtf);
		dOB = dtf2.format(ld);
		}
		catch(Exception e){
			System.out.println(e.toString());
		}
		return SqlProfileUpdateRequest.update(fname,lname,dOB,mobile,hometown,country,id,works,livesin,college);
	}
}
