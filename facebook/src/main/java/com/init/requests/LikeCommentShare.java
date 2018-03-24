package com.init.requests;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.sql.requests.SqlLikescommentshare;
import com.sql.data.CommentsData;
import com.sql.data.SqlUserProfileData;
@Path("/lcm")
public class LikeCommentShare {
	@GET
	@Path("/likes")
	@Produces(MediaType.TEXT_PLAIN)
	public String getlike(@QueryParam("post_id") Long post_id,@QueryParam("user_id") Long user_id) throws SQLException{
		//System.out.println(post_id+" "+user_id);
		//System.out.println(new SqlLikescommentshare().getLike(post_id, user_id));
		return new SqlLikescommentshare().getLike(post_id, user_id);
	}
	
	@PUT
	@Path("/likes")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean putlike(@QueryParam("post_id") Long post_id,@QueryParam("user_id") Long user_id) throws SQLException{
		//System.out.println(post_id+" "+user_id);
		return new SqlLikescommentshare().putLike(post_id, user_id);
	}
	
	@Path("/likes/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<SqlUserProfileData> getLikeInfo(@PathParam("id") Long id) throws SQLException{
		//System.out.println(id);
		//System.out.println(new SqlLikescommentshare().getLike(post_id, user_id));
		return new SqlLikescommentshare().getLikeInfo(id);
	}
	
	@GET
	@Path("/comments")
	@Produces(MediaType.TEXT_PLAIN)
	public String getComments(@QueryParam("post_id") Long post_id) throws SQLException{
		//System.out.println(new SqlLikescommentshare().getLike(post_id, user_id));
		return new SqlLikescommentshare().getComments(post_id);
	}
	
	@GET
	@Path("/comments/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<CommentsData> getComentInfo(@PathParam("id") Long post_id) throws SQLException{
		//System.out.println(post_id);
		//System.out.println(new SqlLikescommentshare().getLike(post_id, user_id));
		return new SqlLikescommentshare().getCommentsInfo(post_id);
	}
	
	@PUT
	@Path("/comments")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public boolean putComent(@FormParam("commentText") String commentText,
			@QueryParam("post_id") Long post_id,
			@QueryParam("user_id") Long user_id) throws SQLException{
		//System.out.println(post_id+" "+user_id+" "+commentText);
		//return true;
		return new SqlLikescommentshare().putComment(post_id, user_id, commentText);
	}
}
