package com.revature.dao;

import java.sql.*;

import com.revature.model.Profile;
import com.revature.util.ConnectionUtil;


public class ProfileDao {

	/**
	 * Called by: GuestDao()
	 * 
	 * @param username	String
	 * @param password	String
	 * @return p 		Profile object - if user is host guest type = host
	 */
	public Profile getProfile(String username, String password) {
		PreparedStatement ps = null;
		Profile p = null;
		ResultSet rs = null;

		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM PROFILE WHERE P_NAME = ? AND P_PASS = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);

			rs = ps.executeQuery();
			if(rs.next()) {
				int pid = rs.getInt("p_id");
				String fname = rs.getString("f_name");
				String lname = rs.getString("l_name");
				String type = whatTypeIsProfile(pid);
				p = new Profile(pid, fname, lname, type);
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
		return p;
	}

	/**
	 * Called by: GetProfile()
	 * 
	 * @param pid		int - profile id
	 * @return type		String - if user is host type = host
	 */
	public int getProfileID(String username, String password) {
		PreparedStatement ps = null;
		int pid = -1;
		ResultSet rs = null;

		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT p_id FROM PROFILE WHERE P_USERNAME = ? AND P_PASSWORD = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);

			rs = ps.executeQuery();
			if(rs.next()) {
				pid = rs.getInt("p_id");
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
		return pid;
	}

	/**
	 * Called by: ?
	 * Edits a profile's string value
	 * 
	 * @param profileid	 id of profile
	 * @param vname   	 String the SQL column we want to change
	 * @param value   	 String new value for vname
	 * @return nothing
	 */
	public void editProfile(int profileid, String vname, String value) {
		// TODO: EditProfile() is not being used
		PreparedStatement ps = null;
		ResultSet rs = null;

		if (isColumnName(vname)) {
			try (Connection conn = ConnectionUtil.getConnection()) {
				String sql = "UPDATE Profile SET  ? = ? WHERE p_id = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, vname);
				ps.setString(2, value);
				ps.setInt(3, profileid);

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

	/**
	 * Called by: GetProfile()
	 * Determines whether or not the profile is a guest or host
	 * 
	 * @param profileid	 int - id of profile
	 * @return type		 String - 'Host' or 'Guest'
	 */
	public String whatTypeIsProfile(int pid) {
		PreparedStatement ps = null;
		String type = "";
		ResultSet rs = null;

		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM HOST WHERE P_ID = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, pid);
			

			rs = ps.executeQuery();
			if(rs.next()) {
				type = "Host";				
			} else {
				type = "Guest";
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
		return type;
	}

	/**
	 * Called by: EditProfile()
	 * Determines if column is valid
	 * 
	 * @param vname			String - column name
	 * @return incolumn		boolean - true if column exists
	 */
	private boolean isColumnName(String vname) {
		String[] columns = { "p_name", "P_NAME", "p_pass", "P_PASS", 
							 "f_name", "F_NAME", "l_name", "L_NAME" };
		boolean incolumn = false;
		for (String x : columns) {
			if (x == vname) {
				incolumn = true;
			}
		}
		return incolumn;
	}
}