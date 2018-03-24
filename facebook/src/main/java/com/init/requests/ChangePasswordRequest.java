package com.init.requests;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sql.requests.SqlChangePasswordRequest;
@Path("/changepwd")
public class ChangePasswordRequest {
	SqlChangePasswordRequest s1=new SqlChangePasswordRequest();
	
	@POST
	@Path("/{id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public boolean checkpwd(@PathParam("id") int id,
			@FormParam("password") String password
            ) throws SQLException {
		//System.out.println("id= : "+id+"AND PASSword :: "+password);
	
	return	s1.check(id,password);
	}
	
	@PUT
	@Path("/{id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public boolean updatepwd(@PathParam("id") int id,
			@FormParam("password") String password
            ) throws SQLException {
		//System.out.println(id+password);
		//return true;
			//return "something";
			return s1.update(id,password);
	}
	

}
