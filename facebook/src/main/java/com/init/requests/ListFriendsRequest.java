package com.init.requests;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sql.data.SqlUserProfileData;
import com.sql.requests.SqlFriendsData;


	@Path("/friends")
	public class ListFriendsRequest {
		@GET
		@Path("/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public ArrayList<SqlUserProfileData> getProfileData(@PathParam("id") String id) throws SQLException{
			SqlFriendsData fl=new SqlFriendsData();
			ArrayList<SqlUserProfileData> friendData=fl.getFriends(Integer.parseInt(id));
			//System.out.println(aa.get(0).getEmail());
			return friendData;
		}
	}

