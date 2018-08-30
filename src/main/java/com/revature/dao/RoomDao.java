package com.revature.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Room;
import com.revature.util.ConnectionUtil;


public class RoomDao {

	/**
	 * Called by:	?
	 * 
	 * @param roomnum	room number  
	 * @return r	Room Object
	 */
	public Room getRoom(int roomnum) {
		// TODO: GetRoom() is not being used
		PreparedStatement ps = null;
		Room r = null;
		ResultSet rs = null;

		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM ROOM WHERE R_NUM = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, roomnum);
			

			rs = ps.executeQuery();
			if (rs.next()) {
				String type = rs.getString("r_type");
				int bnum = rs.getInt("r_bednum");
				String bsize = rs.getString("r_bedsize");
				float rate = rs.getFloat("r_rate");
				String des = rs.getString("r_description");
				String url = rs.getString("r_img");
				
				r = new Room(roomnum, type, bnum, bsize, rate, des, url);
				
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
		return r;
	}

	/**
	 * Called by: HotelControllor.doGet()
	 * 
	 * @return rooms	List of Room Objects
	 */
	public List<Room> getAllRoom() {
		PreparedStatement ps = null;
		Room r = null;
		List<Room> rooms = new ArrayList<Room>();
		ResultSet rs = null;

		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM ROOM";
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			while (rs.next()) {
				int rnum = rs.getInt("r_num");
				String type = rs.getString("r_type");
				int bnum = rs.getInt("r_bednum");
				String bsize = rs.getString("r_bedsize");
				float rate = rs.getFloat("r_rate");
				String des = rs.getString("r_description");
				String url = rs.getString("r_img");
				
				r = new Room(rnum, type, bnum, bsize, rate, des, url);
				rooms.add(r);
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
		return rooms;
	}
	
	/**
	 * Called by: RoomControllor.doPost()
	 * Edits a guest's string value not able to change DOB
	 * @param guestid	id of guest
	 * @param vname		String the SQL column
	 * @param value		String new value for vname
	 * @return nothing
	 */
	public void editRoom(int roomnum, String vname, String value)  {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "UPDATE Room SET  ? = ? WHERE r_num = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, vname);
			ps.setString(2, value);
			ps.setInt(3, roomnum);
		

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