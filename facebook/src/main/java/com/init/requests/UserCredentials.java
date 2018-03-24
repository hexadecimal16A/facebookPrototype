package com.init.requests;


import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import java.sql.PreparedStatement;
import com.sql.requests.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/UsersInfo")
public class UserCredentials {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<UsersCredentials> getPeople() throws SQLException{
		
		SqlUsersCredentialRequest Credentials=new  SqlUsersCredentialRequest();
		 
		 List<UsersCredentials> list=  Credentials.getCredentials();
		 return list;
	}


	
}

class UsersCredentials {
private String email;
private String password;

public UsersCredentials() {

}

public UsersCredentials(String email, String password) {

	this.email = email;
	this.password = password;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}

}
 
class SqlUsersCredentialRequest {
		private Connection conn=null;
		private PreparedStatement preparedStatement=null;
		public List<UsersCredentials> getCredentials() throws SQLException
		{
			//System.out.println("I reached here1");
			List<UsersCredentials> list=new ArrayList<UsersCredentials>();	
			try{
				conn=SqlInit.makeSQLConn();
				String sql="select email,password from userprofile";
				preparedStatement=conn.prepareStatement(sql);
				ResultSet rs=preparedStatement.executeQuery();
				if(rs.isBeforeFirst()){
					while(rs.next()){
						list.add(new UsersCredentials(rs.getString("email"),rs.getString("password")));
					}
				}
				conn.close();
				return list;
				}
				catch(Exception e){
					System.out.println(e.toString());
					return null;
				}		
		}
	}


