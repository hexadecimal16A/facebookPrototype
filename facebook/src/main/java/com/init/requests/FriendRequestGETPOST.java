package com.init.requests;

import com.sql.requests.SqlFriendSuggestion;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.sql.requests.SqlFriendRequestDisplayHTMLPage;
import com.sql.requests.SqlFriendRequestGETPOST;
import com.sql.requests.SqlMutualFriend;
import com.sql.data.FriendPendingRequestData;
import com.sql.data.SqlUserProfileData;

@Path("/friendRequest")
public class FriendRequestGETPOST {
	@POST
	public boolean sendFriendRequest(@QueryParam("friendId") Long to_id,
			@QueryParam("userId") Long from_id, @QueryParam("extra") Long extra)
			throws SQLException {

		if ((Long) extra == null) {
			extra = (long) 0;
		}
		// System.out.println(extra);
		return new SqlFriendRequestGETPOST().pendingRequest(from_id, to_id,
				extra);
	}

	@POST
	@Path("/friendCheck")
	public String sendFriendRequest2(@QueryParam("friendId") Long id1,
			@QueryParam("userId") Long id2) throws SQLException {
		// System.out.println(id1+" "+id2);
		return new SqlFriendRequestGETPOST().friendCheck(id1, id2);
	}

	@GET
	@Path("/pendingRequest")
	public ArrayList<FriendPendingRequestData> pendingRequest(
			@QueryParam("userId") Long id2) throws SQLException {
		// System.out.println(id2);
		return new SqlFriendRequestDisplayHTMLPage().friendRequest(id2);
	}

	@POST
	@Path("/response")
	public boolean responseToRequest(@QueryParam("reqId") Long reqId,
			@QueryParam("response") String response) throws SQLException {
		// System.out.println(reqId+" "+response);
		return new SqlFriendRequestGETPOST().responseToRequest(reqId, response);
	}

	@POST
	@Path("/unfriend")
	public boolean unfriend(@QueryParam("id1") Long id1,
			@QueryParam("id2") Long id2) throws SQLException {
		// System.out.println(id1+" "+id2);
		return new SqlFriendRequestGETPOST().unfriend(id1, id2);
	}

	@GET
	@Path("/peopleyoumayknow/{id}")
	public ArrayList<FriendPendingRequestData> peopleYouMayKnow(
			@PathParam("id") Long id) throws SQLException {
		// System.out.println(id2);
		return new SqlFriendRequestDisplayHTMLPage().peopleYouMayKnow(id);
	}

	@GET
	@Path("/suggestion")
	public ArrayList<FriendPendingRequestData> suggestFriend(
			@QueryParam("from") Long from, @QueryParam("to") Long to)
			throws SQLException {
		// System.out.println(from+" "+to);
		return new SqlFriendSuggestion().friendSuggestion(from, to);
	}

	@PUT
	@Path("/suggest")
	public boolean/* ArrayList<FriendPendingRequestData> */suggested(
			@QueryParam("from") Long from, @QueryParam("to") Long to,
			@QueryParam("this") Long id) throws SQLException {
		// System.out.println(from+" "+to+" "+id);
		return new SqlFriendSuggestion().suggest(from, to, id);
	}

	@GET
	@Path("/getsuggestion/{id}")
	public ArrayList<FriendPendingRequestData> getSuggestion(
			@PathParam("id") Long id) throws SQLException {
		// System.out.println(id);
		return new SqlFriendSuggestion().getSuggestion(id);
	}

	@POST
	@Path("/removesuggestion/{id}")
	public boolean removeSuggestion(@PathParam("id") Long id)
			throws SQLException {
		// System.out.println(id);
		return new SqlFriendSuggestion().suggestRemove(id);
	}

	@Path("/MutualFriend/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<SqlUserProfileData> MutualFriend(@PathParam("id") String id)
			throws SQLException {
		Long id1=Long.parseLong(id.split("_")[0]);
		Long id2=Long.parseLong(id.split("_")[1]);
		return new SqlMutualFriend().MutualFriend(id1, id2);
	}

}
