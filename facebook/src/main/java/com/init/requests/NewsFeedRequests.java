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
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import com.sql.data.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.sql.requests.SqlNewsFeedPostGet;

@Path("/newsfeed")
public class NewsFeedRequests {
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<NewsFeedData> getNewsFeed(@PathParam("id") String Id ,@QueryParam("type") String type) throws SQLException{
		SqlNewsFeedPostGet getPost=new SqlNewsFeedPostGet();
		ArrayList<NewsFeedData> fl=getPost.getNewsfeed(Id,type);
		return fl;
	}
	
	@POST
	@Path("/{id}")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.TEXT_PLAIN)
	public String putNewsFeed( @FormDataParam("newsfeedPic") InputStream fileInputStream,
            @FormDataParam("newsfeedPic") FormDataContentDisposition fileMetaData,
            @PathParam("id") String Id,@FormDataParam("newsfeedStatus") String status) throws SQLException{
		//System.out.println(fileInputStream+" --- "+fileMetaData);
		String UPLOAD_PATH = USER_PATH+Id+"/";
		//System.out.println(fileMetaData.getFileName());
		SqlNewsFeedPostGet insertPost=new SqlNewsFeedPostGet();
		Long time=System.currentTimeMillis();
		try
		{
		if(fileMetaData.getFileName().length()>0){
		int read = 0;
		byte[] bytes = new byte[2048];
		OutputStream out = new FileOutputStream(new File(UPLOAD_PATH +time+"_"+fileMetaData.getFileName()));
		while ((read = fileInputStream.read(bytes)) != -1) 
		{
		out.write(bytes, 0, read);
		}
		out.flush();
		out.close();
		
		insertPost.postNewsfeed(Id,status,"users/"+Id+"/"+time+"_"+fileMetaData.getFileName(),time.toString());//(Id,status,time);
			return "users/"+Id+"/"+time+"_"+fileMetaData.getFileName();
		}
		else
			insertPost.postNewsfeed(Id,status,null,time.toString());//(Id,status,time);
		//"users/"+Id+"/"+time+"profile.jpg";
		return "null";
		} catch (IOException e) 
		{
		throw new WebApplicationException("Error while uploading file. Please try again !!");
		}
		
		
	}
}
