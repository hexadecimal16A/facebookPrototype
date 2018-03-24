package com.init.requests;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.sql.requests.SqlLogInRequest;
import com.sql.requests.SqlNewUserRequest;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LoginRequests {
	
	//private MessageService messageService=new MessageService();
	private SqlLogInRequest sqlLogInRequest=new SqlLogInRequest();
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getMessages(@QueryParam("email") String email
										,@QueryParam("password") String password
										) throws SQLException{
		//System.out.println(email);
		return  sqlLogInRequest.select(email,password);
	}
	
	@POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public boolean createMessage(@FormParam("fname") String fname,
			@FormParam("lname") String lname,
            @FormParam("email") String email,
            @FormParam("dOB") String dOB,
            @FormParam("password") String password,
            @FormParam("gender") String gender) throws SQLException {
		//System.out.println(fname+lname+email+dOB+password+gender);
		String actualDate = dOB;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
		DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ENGLISH);
		LocalDate ld = LocalDate.parse(actualDate, dtf);
		dOB = dtf2.format(ld);
		return new SqlNewUserRequest().insert(fname,lname,email,password,dOB,gender);
	}
	/*@POST
	public Messages addMessage(Messages message){
		return messageService.addMessage(message);
		
	}
	*/
	
	/*
	@DELETE
	@Path("/{messageId}")
	@Produces(MediaType.TEXT_PLAIN)
	public String removeMessage(@PathParam("messageId") long id){
		messageService.removeMessage(id);
		return "Deleted";
	}
	
	/*@GET
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Messages getMessage(@PathParam("messageId") long id){
		return messageService.getMessage(id);
	}*/
	
	
}

