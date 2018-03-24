package com.sql.requests;

import static com.sql.requests.SqlInit.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

import com.sql.data.FriendPendingRequestData;

public class SqlFriendRequestDisplayHTMLPage {
	private Connection conn = null;
	private PreparedStatement preparedStatement = null;

	public ArrayList<FriendPendingRequestData> friendRequest(Long to_id)
			throws SQLException {
		conn = makeSQLConn();
		String name;
		String dOB;
		String id;
		String email;
		String picturePath;
		String reqId;
		ArrayList<FriendPendingRequestData> pro = new ArrayList<FriendPendingRequestData>();
		String SqlPending = "select concat(fname,' ',lname) as "
				+ "name,email,dateofBirth,picturepath,id,req_id from "
				+ userTable + " " + "inner join " + pendingRequestTable
				+ " on id=from_id where to_id=? and onhold=0";
		conn = makeSQLConn();
		preparedStatement = conn.prepareStatement(SqlPending);
		preparedStatement.setLong(1, to_id);
		ResultSet rs = preparedStatement.executeQuery();
		if (rs.isBeforeFirst()) {
			while (rs.next()) {
				name = rs.getString("name");
				email = rs.getString("email");
				dOB = rs.getString("dateofBirth");
				picturePath = rs.getString("picturePath");
				id = rs.getString("id");
				reqId = rs.getString("req_id");
				pro.add(new FriendPendingRequestData(name, dOB, id, email,
						picturePath, reqId, "0"));
			}
		}
		conn.close();
		return pro;

	}

	/*
	 * public ArrayList<FriendPendingRequestData> peopleYouMayKnow(Long id)
	 * throws SQLException{ try{ String
	 * getPeople="select * from "+userTable+" where id not in" +
	 * "(select id1 from " +friendTable +" where id2=? union select id2 from "
	 * +friendTable
	 * +" where id1=? union select from_id from "+pendingRequestTable+" " +
	 * " where to_id=? union select to_id from "+
	 * pendingRequestTable+" where from_id=?) " + "and id<>? limit 5"; String
	 * name; String dOB; String email; String picturePath; String Id; String
	 * count; ArrayList<FriendPendingRequestData> pro=new
	 * ArrayList<FriendPendingRequestData>(); conn=makeSQLConn();
	 * preparedStatement=conn.prepareStatement(getPeople);
	 * preparedStatement.setLong(1,id); preparedStatement.setLong(2,id);
	 * preparedStatement.setLong(3,id); preparedStatement.setLong(4,id);
	 * preparedStatement.setLong(5,id); ResultSet
	 * rs=preparedStatement.executeQuery(); if(rs.isBeforeFirst()){
	 * while(rs.next()){ name=rs.getString("fname")+" "+rs.getString("lname");
	 * email=rs.getString("email"); dOB=rs.getString("dateofBirth");
	 * picturePath=rs.getString("picturePath"); Id=rs.getString("id");
	 * 
	 * String sql1="select id2 from friendlist where id1=? ";
	 * preparedStatement=conn.prepareStatement(sql1);
	 * preparedStatement.setLong(1,id); ResultSet
	 * rs1=preparedStatement.executeQuery(); String
	 * sql2="select id1 from friendlist where id2=? ";
	 * preparedStatement=conn.prepareStatement(sql2);
	 * preparedStatement.setLong(1,id); ResultSet
	 * rs2=preparedStatement.executeQuery(); String
	 * sql3="select id2 from friendlist where id1=? ";
	 * preparedStatement=conn.prepareStatement(sql3);
	 * preparedStatement.setLong(1,Long.parseLong(Id)); ResultSet
	 * rs3=preparedStatement.executeQuery(); String
	 * sql4="select id1 from friendlist where id2=? ";
	 * preparedStatement=conn.prepareStatement(sql4);
	 * preparedStatement.setLong(1,Long.parseLong(Id)); ResultSet
	 * rs4=preparedStatement.executeQuery(); ArrayList<String> al1=new
	 * ArrayList<String>(); ArrayList<String> al2=new ArrayList<String>();
	 * //System.out.println("i am here bro"); if(rs1.isBeforeFirst()){
	 * while(rs1.next()){ al1.add(rs1.getString("id2")); } }
	 * if(rs2.isBeforeFirst()){ while(rs2.next()){
	 * al1.add(rs2.getString("id1")); } } if(rs3.isBeforeFirst()){
	 * while(rs3.next()){ al2.add(rs3.getString("id2")); } }
	 * if(rs4.isBeforeFirst()){ while(rs4.next()){
	 * al2.add(rs4.getString("id1")); } } al1.retainAll(al2);
	 * //System.out.println("arraylist="+al1); count=String.valueOf(al1.size());
	 * //System.out.println("count"+count); pro.add(new
	 * FriendPendingRequestData(name,dOB,Id,email,picturePath,null,count)); } }
	 * conn.close(); return pro; } catch(Exception e){ conn.close();
	 * //System.out.println(e.toString()); return null; } }
	 */

	public ArrayList<FriendPendingRequestData> peopleYouMayKnow(Long id)
			throws SQLException {
		try {
			/*
			 * String getPeople="select * from "+userTable+" where id not in" +
			 * "(select id1 from " +friendTable
			 * +" where id2=? union select id2 from "
			 * +friendTable+" where id1=? union select from_id from "
			 * +pendingRequestTable+" " +
			 * " where to_id=? union select to_id from "+
			 * pendingRequestTable+" where from_id=?) " + "and id<>? limit 5";
			 */
			conn = makeSQLConn();
			String sql5 = "select id2 from friendlist where id1=?";
			preparedStatement = conn.prepareStatement(sql5);
			preparedStatement.setLong(1, id);
			ResultSet rs5 = preparedStatement.executeQuery();
			String sql6 = "select id1 from friendlist where id2=? ";
			preparedStatement = conn.prepareStatement(sql6);
			preparedStatement.setLong(1, id);
			ResultSet rs6 = preparedStatement.executeQuery();
			HashSet<String> al3 = new HashSet<String>();
			if (rs5.isBeforeFirst()) {
				while (rs5.next()) {
					al3.add(rs5.getString("id2"));
				}
			}
			if (rs6.isBeforeFirst()) {
				while (rs6.next()) {
					al3.add(rs6.getString("id1"));
				}
			}
			HashSet<String> al4 = new HashSet<String>();
			for (String st : al3) {
				int x = Integer.parseInt(st);
				String sql7 = "select id2 from friendlist where id1=? ";
				preparedStatement = conn.prepareStatement(sql7);
				preparedStatement.setLong(1, x);
				ResultSet rs7 = preparedStatement.executeQuery();
				String sql8 = "select id1 from friendlist where id2=? ";
				preparedStatement = conn.prepareStatement(sql8);
				preparedStatement.setLong(1, x);
				ResultSet rs8 = preparedStatement.executeQuery();
				// ArrayList<String> al3=new ArrayList<String>();

				if (rs7.isBeforeFirst()) {
					while (rs7.next()) {
						if (!al3.contains(rs7.getString("id2")))
							al4.add(rs7.getString("id2"));
					}
				}
				if (rs8.isBeforeFirst()) {
					while (rs8.next()) {
						if (!al3.contains(rs8.getString("id1")))
							al4.add(rs8.getString("id1"));
					}
				}
			}
			String sql9 = "select user_id from suggestion where to_id=? ";
			preparedStatement = conn.prepareStatement(sql9);
			preparedStatement.setLong(1, id);
			ResultSet rs9 = preparedStatement.executeQuery();
			/*String sql10 = "select to_id from suggestion where user_id=? ";
			preparedStatement = conn.prepareStatement(sql10);
			preparedStatement.setLong(1, id);
			ResultSet rs10 = preparedStatement.executeQuery();*/
			// ArrayList<String> al3=new ArrayList<String>();

			if (rs9.isBeforeFirst()) {
				while (rs9.next()) {
					if (al4.contains(rs9.getString("user_id")))
						al4.remove(rs9.getString("user_id"));
				}
			}
			
			sql9 = "select from_id from pending_request where to_id=? ";
			preparedStatement = conn.prepareStatement(sql9);
			preparedStatement.setLong(1, id);
			rs9 = preparedStatement.executeQuery();
			String sql10 = "select to_id from pending_request where from_id=? ";
			preparedStatement = conn.prepareStatement(sql10);
			preparedStatement.setLong(1, id);
			ResultSet rs10 = preparedStatement.executeQuery();
			// ArrayList<String> al3=new ArrayList<String>();

			if (rs9.isBeforeFirst()) {
				while (rs9.next()) {
					if (al4.contains(rs9.getString("from_id")))
						al4.remove(rs9.getString("from_id"));
				}
			}
			if (rs10.isBeforeFirst()) {
				while (rs10.next()) {
					if (al4.contains(rs10.getString("to_id")))
						al4.remove(rs10.getString("to_id"));
				}
			}
			
			al4.remove(String.valueOf(id));
			String name;
			String dOB;
			String email;
			String picturePath;
			String Id;
			String count;
			ArrayList<FriendPendingRequestData> pro = new ArrayList<FriendPendingRequestData>();
			String getPeople = "";
			if (al4.size() >= 1)
				getPeople = "select * from userprofile where id=?;";// or id=?
																	// or id=?
																	// or id=?
																	// or id=?";
			if (al4.size() >= 2)
				getPeople = "select * from userprofile where id=? or id=?;";// or
																			// id=?
																			// or
																			// id=?
																			// or
																			// id=?";
			if (al4.size() >= 3)
				getPeople = "select * from userprofile where id=? or id=? or id=?;";// or
																					// id=?
																					// or
																					// id=?";
			if (al4.size() >= 4)
				getPeople = "select * from userprofile where id=? or id=? or id=? or id=?;";// or
																							// id=?";
			if (al4.size() >= 5)
				getPeople = "select * from userprofile where id=? or id=? or id=? or id=? or id=?;";
			preparedStatement = conn.prepareStatement(getPeople);
			ArrayList<String> al0 = new ArrayList<String>(al4);
			if (al0.size() >= 1)
				preparedStatement.setLong(1, Integer.parseInt(al0.get(0)));
			if (al0.size() >= 2)
				preparedStatement.setLong(2, Integer.parseInt(al0.get(1)));
			if (al0.size() >= 3)
				preparedStatement.setLong(3, Integer.parseInt(al0.get(2)));
			if (al0.size() >= 4)
				preparedStatement.setLong(4, Integer.parseInt(al0.get(3)));
			if (al0.size() >= 5)
				preparedStatement.setLong(5, Integer.parseInt(al0.get(4)));
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					name = rs.getString("fname") + " " + rs.getString("lname");
					email = rs.getString("email");
					dOB = rs.getString("dateofBirth");
					picturePath = rs.getString("picturePath");
					Id = rs.getString("id");
					String sql1 = "select id2 from friendlist where id1=? ";
					preparedStatement = conn.prepareStatement(sql1);
					preparedStatement.setLong(1, id);
					ResultSet rs1 = preparedStatement.executeQuery();
					String sql2 = "select id1 from friendlist where id2=? ";
					preparedStatement = conn.prepareStatement(sql2);
					preparedStatement.setLong(1, id);
					ResultSet rs2 = preparedStatement.executeQuery();
					String sql3 = "select id2 from friendlist where id1=? ";
					preparedStatement = conn.prepareStatement(sql3);
					preparedStatement.setLong(1, Long.parseLong(Id));
					ResultSet rs3 = preparedStatement.executeQuery();
					String sql4 = "select id1 from friendlist where id2=? ";
					preparedStatement = conn.prepareStatement(sql4);
					preparedStatement.setLong(1, Long.parseLong(Id));
					ResultSet rs4 = preparedStatement.executeQuery();
					ArrayList<String> al1 = new ArrayList<String>();
					ArrayList<String> al2 = new ArrayList<String>();
					//System.out.println("i am here bro");
					if (rs1.isBeforeFirst()) {
						while (rs1.next()) {
							al1.add(rs1.getString("id2"));
						}
					}
					if (rs2.isBeforeFirst()) {
						while (rs2.next()) {
							al1.add(rs2.getString("id1"));
						}
					}
					if (rs3.isBeforeFirst()) {
						while (rs3.next()) {
							al2.add(rs3.getString("id2"));
						}
					}
					if (rs4.isBeforeFirst()) {
						while (rs4.next()) {
							al2.add(rs4.getString("id1"));
						}
					}
					al1.retainAll(al2);
					//System.out.println("arraylist=" + al1);
					count = String.valueOf(al1.size());
					//System.out.println("count" + count);
					pro.add(new FriendPendingRequestData(name, dOB, Id, email,
							picturePath, null, count));
				}
			}
			conn.close();
			return pro;
		} catch (Exception e) {
			conn.close();
			System.out.println(e.toString());
			return null;
		}
	}

}
