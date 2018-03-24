package com.init.requests;
import static com.init.requests.PathInit.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.sql.data.Eventfeeddata;
import com.sql.requests.SqlEventFeedPost;

@Path("/eventfeed")
public class EventFeedRequest {
	@GET
	@Path("/{event_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Eventfeeddata> getNewsFeed(@PathParam("event_id") String event_id) throws SQLException{
		SqlEventFeedPost getPost=new SqlEventFeedPost();
		ArrayList<Eventfeeddata> fl=getPost.geteventnewsfeed(event_id);
		return fl;
	}
	
	@POST
	@Path("/{event_id}")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.TEXT_PLAIN)
	public String putNewsFeed( @FormDataParam("newsfeedPic") InputStream fileInputStream,
            @FormDataParam("newsfeedPic") FormDataContentDisposition fileMetaData,
            @PathParam("event_id") String fullId,@FormDataParam("newsfeedStatus") String status) throws SQLException{
		//System.out.println(fileInputStream+" --- "+fileMetaData);
		String event_id=fullId.split("_")[0];
		String user_id=fullId.split("_")[1];
		String UPLOAD_PATH = EVENT_PATH+event_id+"/";
		//System.out.println(fileMetaData.getFileName());
		SqlEventFeedPost insertPost=new SqlEventFeedPost();
		Long time=System.currentTimeMillis();
		try
		{
		System.out.println(fileMetaData.getFileName());
		if(fileMetaData.getFileName().length()>0){
		int read = 0;
		//System.out.println(fileMetaData.getFileName());
		//System.out.println(event_id);
		byte[] bytes = new byte[2048];
		OutputStream out = new FileOutputStream(new File(UPLOAD_PATH +time+"_"+fileMetaData.getFileName()));
		//System.out.println("asasasasasas");
		while ((read = fileInputStream.read(bytes)) != -1) 
		{
		out.write(bytes, 0, read);
		}
		out.flush();
		out.close();
		//System.out.println("asasasasasasasasasasasas");
		insertPost.posteventnewsfeed(event_id,user_id,status,"events/"+event_id+"/"+time+"_"+fileMetaData.getFileName(),time.toString());//(Id,status,time);
			return "events/"+event_id+"/"+time+"_"+fileMetaData.getFileName();
		}
		else
			insertPost.posteventnewsfeed(event_id,user_id,status,null,time.toString());//(Id,status,time);
		//"users/"+Id+"/"+time+"profile.jpg";
		return "null";
		} catch (IOException e) 
		{
		throw new WebApplicationException("Error while uploading file. Please try again !!");
		
		}
		
	}

}
