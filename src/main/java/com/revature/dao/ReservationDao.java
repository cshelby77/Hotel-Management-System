package com.revature.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Reservation;
import com.revature.util.ConnectionUtil;

public class ReservationDao {

	/**
	 * Called by: ReservationControllor.doPost()
	 * 
	 * @param guestid    int - guest making reservation
	 * @param roomid     int - the room to reserve
	 * @param numofguest int - how many guests are staying
	 * @param checkin    Date - checkin date
	 * @param checkout   Date - checkout date
	 * @return none
	 */
	public void createReservation(int guestid, int roomid, int numofguest, String checkin, String checkout) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO Reservation ( g_id, r_id, res_guestnum, res_checkin, res_checkout ) VALUES ( ?, ?, ?, TO_DATE(?, 'yyyy-mm-dd'), TO_DATE(?, 'yyyy-mm-dd') )";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, guestid);
			ps.setInt(2, roomid);
			ps.setInt(3, numofguest);
			ps.setString(4, checkin);
			ps.setString(5, checkout);

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
	 * Called by: ReservationControllor.doPost()
	 * 
	 * @param guestid int guest's id number
	 * @return reservations List of Reservation Objects
	 */
	public List<Reservation> getGuestReservations(int guestid) {
		PreparedStatement ps = null;
		List<Reservation> reservations = new ArrayList<Reservation>();
		Reservation r = null;
		ResultSet rs = null;

		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM RESERVATION WHERE G_ID = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, guestid);

			rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("res_id");
				int roomid = rs.getInt("r_id");
				int numofguest = rs.getInt("res_guestnum");
				Date checkin = rs.getDate("res_checkin");
				Date checkout = rs.getDate("res_checkout");
				String status = rs.getString("res_stat");

				r = new Reservation(id, guestid, roomid, numofguest, checkin, checkout, status);
				reservations.add(r);
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
		return reservations;
	}

	/**
	 * Called by: HotelControllor.doGet()
	 * 
	 * @return reservations List of Reservation Objects
	 */
	public List<Reservation> getAllReservations() {
		PreparedStatement ps = null;
		List<Reservation> reservations = new ArrayList<Reservation>();
		Reservation r = null;
		ResultSet rs = null;

		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM RESERVATION";
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("res_id");
				int guestid = rs.getInt("g_id");
				int roomid = rs.getInt("r_id");
				int numofguest = rs.getInt("res_guestnum");
				Date checkin = rs.getDate("res_checkin");
				Date checkout = rs.getDate("res_checkout");
				String status = rs.getString("res_stat");

				r = new Reservation(id, guestid, roomid, numofguest, checkin, checkout, status);
				reservations.add(r);
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
		return reservations;
	}

	/**
	 * Called by: HotelControllor.doGet()
	 * 
	 * @return reservations List of Reservation Objects
	 */
	public List<Integer> getBetweenReservations(String in, String out) {
		PreparedStatement ps = null;
		List<Integer> temp = new ArrayList<Integer>();

		ResultSet rs = null;
		int roomid = -1;
		System.out.println("getbetweenres");
		System.out.println(in);
		System.out.println(out);
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT r_id FROM RESERVATION WHERE RES_CHECKIN BETWEEN TO_DATE(?, 'yyyy-mm-dd') AND TO_DATE(?, 'yyyy-mm-dd')";
			sql += " UNION ";
			sql += "SELECT r_id FROM RESERVATION WHERE RES_CHECKOUT BETWEEN TO_DATE(?, 'yyyy-mm-dd') AND TO_DATE(?, 'yyyy-mm-dd')";
			ps = conn.prepareStatement(sql);
			ps.setString(1, in);
			ps.setString(2, out);
			ps.setString(3, in);
			ps.setString(4, out);

			rs = ps.executeQuery();
			while (rs.next()) {
				roomid = rs.getInt("r_id");
				temp.add(roomid);
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
		return temp;
	}

	
	/**
	 * Called by: Reservation.doGet()
	 * 
	 * @return reservations List of Reservation Objects
	 */
	public List<Reservation> getPendingReservations() {
		PreparedStatement ps = null;
		List<Reservation> reservations = new ArrayList<Reservation>();
		Reservation r = null;
		ResultSet rs = null;

		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM RESERVATION WHERE RES_STAT = 'P'";
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("res_id");
				int guestid = rs.getInt("g_id");
				int roomid = rs.getInt("r_id");
				int numofguest = rs.getInt("res_guestnum");
				Date checkin = rs.getDate("res_checkin");
				Date checkout = rs.getDate("res_checkout");
				String status = rs.getString("res_stat");

				r = new Reservation(id, guestid, roomid, numofguest, checkin, checkout, status);
				reservations.add(r);
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
		return reservations;
	}

	/**
	 * Called by: Reservation.doPost()
	 * Used to change reservation status
	 * 
	 * @param resid 	id of reservation
	 * @param value   	String with status
	 * @return nothing
	 */
	public void editReservationStat(int resid, String value) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "UPDATE Reservation SET res_stat = ? WHERE res_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, value);
			ps.setInt(2, resid);

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