package com.revature.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Message;
import com.revature.util.ConnectionUtil;


public class MessageDao {
	
	/**
	 * Called by: MessageControllor.doPost
	 * 
	 * @param senderid 		int - person sending message
	 * @param receiverid	int - person receiving message
	 * @param text			String - message sent
	 * @return none
	 * */
	public void createMessage( int senderid, int receiverid, String text ) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO Message ( send_id, recive_id, text ) VALUES ( ?, ?, ? )";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, senderid);
			ps.setInt(2, receiverid);
			ps.setString(3, text);
			
			rs = ps.executeQuery();

		} catch (Exception ex) {
			System.out.println("sqlexception1");
			ex.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}

				if (rs != null) {
					rs.close();
				}
			} catch (SQLException ex) {
				System.out.println("sqlexception2");
				ex.printStackTrace();
			}
		}
	}

	/**
	 * Called by:	MessageControllor.doGet()
	 * 
	 * @param profileid		int guest's id number  
	 * @return messages		List of Message Objects
	 */
	public List<Message> getGuestMessages(int profileid) {
		PreparedStatement ps = null;
		List<Message> messages = new ArrayList<Message>();
		Message m = null;
		ResultSet rs = null;
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM Message WHERE SEND_ID = ? OR RECIVE_ID = ? ORDER BY date_time";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, profileid);
			ps.setInt(2, profileid);
			

			rs = ps.executeQuery();
			while (rs.next()) {
				int mid = rs.getInt("m_id");
				int sender = rs.getInt("send_id");
				int receiver = rs.getInt("recive_id");
				String text = rs.getString("text");
				Date datetime = rs.getDate("date_time");
								
				m = new Message(mid, sender, receiver, text, datetime);
				messages.add(m);
			}

		} catch (Exception ex) {
			System.out.println("sqlexception1");
			ex.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}

				if (rs != null) {
					rs.close();
				}
			} catch (SQLException ex) {
				System.out.println("sqlexception2");
				ex.printStackTrace();
			}
		}
		return messages;
	}

	/**
	 * Called by:	MessageControllor.doDelete()
	 * 
	 * @param profileid		int guest's profile id number  
	 * @return nothing
	 */
	public void deleteMessages(int profileid) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "DELETE * FROM Message WHERE SEND_ID = ? OR RECIEVE_ID = ? ORDER BY date_time";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, profileid);
			ps.setInt(1, profileid);
			
			rs = ps.executeQuery();


		} catch (Exception ex) {
			System.out.println("sqlexception1");
			ex.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}

				if (rs != null) {
					rs.close();
				}
			} catch (SQLException ex) {
				System.out.println("sqlexception2");
				ex.printStackTrace();
			}
		}
	}

	
}