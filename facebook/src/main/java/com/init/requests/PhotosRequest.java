package com.init.requests;
import static com.init.requests.PathInit.*;
import java.io.File;
import java.sql.SQLException;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/photos")
public class PhotosRequest {
	@POST
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getUsersPhoto(@PathParam("id") String id) throws SQLException{
		String UPLOAD_PATH = USER_PATH+id+"/";
		String fileInfo="";
		File folder = new File(UPLOAD_PATH);
		File[] listOfFiles = folder.listFiles();

		    for (int i = 0; i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile()) {
		    	  fileInfo+=listOfFiles[i].getName()+"#";
		      }
		    }
		return fileInfo;
	}
	
	@POST
	@Path("/group/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getGroupPhoto(@PathParam("id") String id) throws SQLException{
		String UPLOAD_PATH = GROUP_PATH+id+"/";
		String fileInfo="";
		File folder = new File(UPLOAD_PATH);
		File[] listOfFiles = folder.listFiles();

		    for (int i = 0; i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile()) {
		    	  fileInfo+=listOfFiles[i].getName()+"#";
		      }
		    }
		return fileInfo;
	}
}
