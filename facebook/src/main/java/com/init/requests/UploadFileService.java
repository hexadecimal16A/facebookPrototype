package com.init.requests;
import static com.init.requests.PathInit.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.sql.requests.SqlProfilePhotoUpdateRequest;


@Path("/file")
public class UploadFileService {

	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.TEXT_PLAIN)
	public String uploadPdfFile(  @FormDataParam("file") InputStream fileInputStream,
            @FormDataParam("file") FormDataContentDisposition fileMetaData,
            @FormDataParam("Id") String Id) throws Exception{
		//System.out.println(Id);
		String UPLOAD_PATH = USER_PATH+Id+"/";
		//System.out.println(fileMetaData.getFileName());
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
		SqlProfilePhotoUpdateRequest.updateProfile("users/"+Id+"/"+time+"profile.jpg",Id);
		//System.out.println(Id);
		return "users/"+Id+"/"+time+"profile.jpg";
		} catch (IOException e) 
		{
		throw new WebApplicationException("Error while uploading file. Please try again !!");
		}
		
	}
	
	@POST
	@Path("/uploadGroup")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.TEXT_PLAIN)
	public String uploadPdfFileGroup(  @FormDataParam("file") InputStream fileInputStream,
            @FormDataParam("file") FormDataContentDisposition fileMetaData,
            @FormDataParam("Id") String Id) throws Exception{
		//System.out.println(Id);
		String UPLOAD_PATH = GROUP_PATH+Id+"/";
		//System.out.println(fileMetaData.getFileName());
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
		SqlProfilePhotoUpdateRequest.updateGroup("groups/"+Id+"/"+time+"profile.jpg",Id);
		//System.out.println(Id);
		return "groups/"+Id+"/"+time+"profile.jpg";
		} catch (IOException e) 
		{
		throw new WebApplicationException("Error while uploading file. Please try again !!");
		}
		
	}


}