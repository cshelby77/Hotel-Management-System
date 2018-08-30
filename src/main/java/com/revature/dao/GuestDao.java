package com.revature.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.model.Guest;
import com.revature.model.Profile;
import com.revature.util.ConnectionUtil;

public class GuestDao {

	/**
	 * Called by: LoginControllor
	 * 
	 * @param username String
	 * @param password String
	 * @return g Guest object - if user is host guest portion will not be filled
	 */
	public Guest getGuest(String username, String password) {
		PreparedStatement ps = null;
		ProfileDao pdao = new ProfileDao();
		Profile p = null;
		Guest g = new Guest();
		ResultSet rs = null;

		p = pdao.getProfile(username, password);

		// if is a guest then get guest info
		if (p.getType() == "Guest") {

			try (Connection conn = ConnectionUtil.getConnection()) {
				String sql = "SELECT * FROM GUEST WHERE P_ID = ?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, p.getProfileid());

				rs = ps.executeQuery();
				if (rs.next()) {
					g.setGuestid(rs.getInt("g_id"));
					g.setEmail(rs.getString("g_email"));
					g.setAddress(rs.getString("g_address"));
					g.setDob(rs.getDate("g_dob"));
				}
			} catch (

			Exception ex) {
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

		// Transferring p to g
		g.setProfileid(p.getProfileid());
		g.setFname(p.getFname());
		g.setLname(p.getLname());
		g.setType(p.getType());

		return g;
	}

	/**
	 * Called by: Guest(String username, String password, String firstname, String
	 * lastname, String email, Date dob) Calls a plsql stored procedure that creates
	 * new profile and guest
	 * 
	 * @param username String of guest's username
	 * @param password String of guest's encrypted password
	 * @param fname    String of guest'ss first name
	 * @param lname    String of guest's last name
	 * @param email    String of guest's email
	 * @param dob      Date object of guest's date-of-birth
	 * @return nothing
	 */
	public void createGuestProfile(String username, String password, String fname, String lname, String email,
			String address, String dob) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "CALL SP_CREATEGUEST(?, ?, ?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, fname);
			ps.setString(4, lname);
			ps.setString(5, email);
			ps.setString(6, address);
			ps.setString(7, dob );

			rs = ps.executeQuery();

		} catch (Exception ex) {
			System.out.println("createGuestsqlexception1");
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
	 * Called by: GuestControllor.doPost()
	 * Edits a guest's string value not able to change DOB
	 * 
	 * @param guestid id of guest
	 * @param vname   String - the SQL column
	 * @param value   String - new value for vname
	 * @return g	  Guest - updated guest
	 */
	public Guest editGuest(int guestid, String vname, String value) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Guest g = null;

		if (isColumnName(vname)) {
			try (Connection conn = ConnectionUtil.getConnection()) {
				String sql = "UPDATE Guest SET  ? = ? WHERE g_id = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, vname);
				ps.setString(2, value);
				ps.setInt(3, guestid);

				rs = ps.executeQuery();
				g = getGuestForEdit(guestid);

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
		
		return g;
	}

	/**
	 * Called by: this.EditGuest() 
	 * Determines if column is valid
	 * 
	 * @param vname String - column name
	 * @return incolumn boolean - true if column exists
	 */
	private boolean isColumnName(String vname) {
		String[] columns = { "g_email", "G_EMAIL", "g_address", "G_ADDRESS" };
		boolean incolumn = false;
		for (String x : columns) {
			if (x == vname) {
				incolumn = true;
			}
		}
		return incolumn;
	}

	/**
	 * Called by: this.EditGuest() 
	 * Determines if column is valid
	 * 
	 * @param guestid	int - guest id
	 * @return g		Guest - true if column exists
	 */
	private Guest getGuestForEdit(int guestid) {
		PreparedStatement ps = null;
		Guest g = new Guest();
		ResultSet rs = null;

		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM GUEST WHERE G_ID = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, guestid);

			rs = ps.executeQuery();
			if (rs.next()) {
				g.setGuestid(rs.getInt("g_id"));
				g.setEmail(rs.getString("g_email"));
				g.setAddress(rs.getString("g_address"));
				g.setDob(rs.getDate("g_dob"));
			}
		} catch (

		Exception ex) {
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

		return g;
	}

}