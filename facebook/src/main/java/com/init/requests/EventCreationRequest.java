package com.init.requests;
import java.util.Calendar;

import static com.init.requests.PathInit.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.sql.data.EventInfoData;
import com.sql.requests.SqlEventRequest;
import com.sql.requests.SqlProfilePhotoUpdateRequest;

@Path("/events")
public class EventCreationRequest {
	
	SqlEventRequest s1=new SqlEventRequest();
	@POST
	@Path("/{id}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.TEXT_PLAIN)
	public String createEvent(
			@FormDataParam("file") InputStream fileInputStream,
			@FormDataParam("file") FormDataContentDisposition fileMetaData,
			@FormDataParam("event_name") String event_name,
			@FormDataParam("description") String description,
            @PathParam("id") String admin_id,
            @FormDataParam("start_date") String start_date,
            @FormDataParam("start_time") String start_time,
            @FormDataParam("end_date") String end_date,
            @FormDataParam("end_time") String end_time,
            @FormDataParam("location") String location
			) throws SQLException {
		Calendar c=Calendar.getInstance();
		Calendar end=Calendar.getInstance();
		System.out.println(start_date);
		c.set(Integer.parseInt(start_date.split("-")[0]),Integer.parseInt(start_date.split("-")[1])-1,Integer.parseInt(start_date.split("-")[2]),Integer.parseInt(start_time.split(":")[0]),Integer.parseInt(start_time.split(":")[1]));
		
		if(!end_date.isEmpty()){
			end.set(Integer.parseInt(end_date.split("-")[0]),Integer.parseInt(end_date.split("-")[1])-1,Integer.parseInt(end_date.split("-")[2]),Integer.parseInt(end_time.split(":")[0]),Integer.parseInt(end_time.split(":")[1]));
			end_time=String.valueOf(end.getTimeInMillis());
			if(end.getTimeInMillis()<=c.getTimeInMillis())
				return "0";
		}
		System.out.println(event_name+" "+description+" " + admin_id+"  " + start_time+ " "+start_date+" "+end_date+" "+end_time);
		//return true;
		String id=s1.insert(event_name,description,admin_id,String.valueOf(c.getTimeInMillis()),end_time,location);
		
		
		String UPLOAD_PATH = EVENT_PATH +id+"/";
		//System.out.println(fileMetaData.getFileName());
		
		if(fileMetaData.getFileName().length()>0)
		try
		{
		
		int read = 0;
		byte[] bytes = new byte[2048];
		Long time=System.currentTimeMillis();
		OutputStream out = new FileOutputStream(new File(UPLOAD_PATH +time+"profile.jpg"));
		while ((read = fileInputStream.read(bytes)) != -1) 
		{
		out.write(bytes, 0, read);
		}
		out.flush();
		out.close();
		SqlProfilePhotoUpdateRequest.updateEvent("events/"+id+"/"+time+"profile.jpg",id);
		//System.out.println(Id);
		} catch (IOException e) 
		{
		throw new WebApplicationException("Error while uploading file. Please try again !!");
		}
		
		
		return id;
	}
 
	
	
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<EventInfoData> listEvents() throws SQLException{
		return new SqlEventRequest().display();
	}
	
	@PUT
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public boolean updateEvents(@FormParam("event_name") String event_name,
			@FormParam("description") String description,
            @PathParam("id") String id,
            @FormParam("start_date") String start_date,
            @FormParam("start_time") String start_time,
            @FormParam("end_date") String end_date,
            @FormParam("end_time") String end_time,
            @FormParam("location") String location) throws SQLException{
		Calendar c=Calendar.getInstance();
		Calendar end=Calendar.getInstance();
		c.set(Integer.parseInt(start_date.split("-")[0]),Integer.parseInt(start_date.split("-")[1])-1,Integer.parseInt(start_date.split("-")[2]),Integer.parseInt(start_time.split(":")[0]),Integer.parseInt(start_time.split(":")[1]));
		start_date=String.valueOf(c.getTimeInMillis());
		if(!end_date.isEmpty()){
			end.set(Integer.parseInt(end_date.split("-")[0]),Integer.parseInt(end_date.split("-")[1])-1,Integer.parseInt(end_date.split("-")[2]),Integer.parseInt(end_time.split(":")[0]),Integer.parseInt(end_time.split(":")[1]));
			end_date=String.valueOf(end.getTimeInMillis());
			if(end.getTimeInMillis()<=c.getTimeInMillis())
				return false;
		}
		System.out.println(event_name+" "+description+" " + id+"  " + start_date+ " "+start_date+" "+end_date+" "+end_date);
		return new SqlEventRequest().update(event_name,description,start_date,end_date,location,id);
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<EventInfoData> listEvents(@PathParam("id") String id) throws SQLException{
		return new SqlEventRequest().singleventinfo(id);
	}
	
	//calling the main event page
		@PUT
		@Path("/listevents/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public ArrayList<EventInfoData> listinterestEvents(@PathParam("id") long id) throws SQLException{
			return new SqlEventRequest().eventparticipants(id);
		}
		
		//discover event page
		@POST
		@Path("/discover")
		@Produces(MediaType.APPLICATION_JSON)
		public ArrayList<EventInfoData> listEventDiscover() throws SQLException{
					//System.out.println("id recieved is:: "+id);
			
			return new SqlEventRequest().display();
		}
		
		@POST
		@Path("/invitation/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public ArrayList<EventInfoData> listEventInvites(@PathParam("id") int id) throws SQLException{
					System.out.println("id for invitaion requests is:: "+id);
			
			return new SqlEventRequest().displayInvites(id);
		}
		
		// event that i am admin of
		@GET
		@Path("/myevent/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public ArrayList<EventInfoData> listmyEvents(@PathParam("id") int id) throws SQLException{
			//System.out.println(id);
			System.out.println("id recieved is in my event:: "+id);
			
			
			return new SqlEventRequest().myevent(id);
		}
		//count of event
	
	
	
	
	
}
