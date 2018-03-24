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

import com.sql.data.Groupfeeddata;
import com.sql.requests.SqlGroupFeedPost;

@Path("/groupfeed")
public class GroupFeedRequest {
	@GET
	@Path("/{group_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Groupfeeddata> getNewsFeed(@PathParam("group_id") String group_id) throws SQLException{
		SqlGroupFeedPost getPost=new SqlGroupFeedPost();
		ArrayList<Groupfeeddata> fl=getPost.getgroupnewsfeed(group_id);
		return fl;
	}
	
	@POST
	@Path("/{group_id}")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.TEXT_PLAIN)
	public String putNewsFeed( @FormDataParam("newsfeedPic") InputStream fileInputStream,
            @FormDataParam("newsfeedPic") FormDataContentDisposition fileMetaData,
            @PathParam("group_id") String fullId,@FormDataParam("newsfeedStatus") String status) throws SQLException{
		//System.out.println(fileInputStream+" --- "+fileMetaData);
		String group_id=fullId.split("_")[0];
		String user_id=fullId.split("_")[1];
		String UPLOAD_PATH = GROUP_PATH+group_id+"/";
		//System.out.println(fileMetaData.getFileName());
		SqlGroupFeedPost insertPost=new SqlGroupFeedPost();
		Long time=System.currentTimeMillis();
		try
		{
		System.out.println(fileMetaData.getFileName());
		if(fileMetaData.getFileName().length()>0){
		int read = 0;
		//System.out.println(fileMetaData.getFileName());
		//System.out.println(group_id);
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
		insertPost.postgroupnewsfeed(group_id,user_id,status,"groups/"+group_id+"/"+time+"_"+fileMetaData.getFileName(),time.toString());//(Id,status,time);
			return "groups/"+group_id+"/"+time+"_"+fileMetaData.getFileName();
		}
		else
			insertPost.postgroupnewsfeed(group_id,user_id,status,null,time.toString());//(Id,status,time);
		//"users/"+Id+"/"+time+"profile.jpg";
		return "null";
		} catch (IOException e) 
		{
		throw new WebApplicationException("Error while uploading file. Please try again !!");
		
		}
		
	}

}
