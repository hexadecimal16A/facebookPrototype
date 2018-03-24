package com.init.requests;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sql.data.chat;
import com.sql.requests.SqlChatSendAndReceive;

@Path("/chats")
public class ChatSendAndReceive {
		@Path("/{id}")
		@POST
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public boolean postChat(@PathParam("id") String fromTo, @FormParam("chatText") String chatText) throws SQLException{
			long to_id=Long.parseLong(fromTo.split("_")[0]);
			long from_id=Long.parseLong(fromTo.split("_")[1]);
			//System.out.println(chatText+" "+from_id+" "+to_id);
			return new SqlChatSendAndReceive().postChat(chatText, to_id, from_id);
		}
		
		@Path("/{id}")
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public ArrayList<chat> getChat(@PathParam("id") long to_id) throws SQLException{
			return new SqlChatSendAndReceive().getChat(to_id);
		}
		
		@Path("/makeRead/{id}")
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public void makeChatRead(@PathParam("id") long to_id) throws SQLException{
			//System.out.println(to_id);
			new SqlChatSendAndReceive().makeRead(to_id);
		}
		
		@Path("/oldChat/{id}")
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public ArrayList<chat> getOldChat(@PathParam("id") String id) throws SQLException{
			//System.out.println(id);
			long id1=Long.parseLong(id.split("_")[1]);
			long id2=Long.parseLong(id.split("_")[0]);
			return new SqlChatSendAndReceive().getOldChat(id1,id2);
		}
		
}
