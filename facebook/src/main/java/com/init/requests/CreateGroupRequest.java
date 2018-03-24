package com.init.requests;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sql.requests.SqlGroupCreateRequest;




@Path("/group")
public class CreateGroupRequest {
	
	@POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String createMessage(@FormParam("group_name") String group_name,
			@FormParam("admin_id") String admin_id,
            @FormParam("privacy") String privacy
            ) throws SQLException {
		//System.out.println(group_name+admin_id+privacy);
		//return true;
			//return "something";
			return new SqlGroupCreateRequest().insert(group_name,admin_id,privacy);
	}
	

}
