package com.revature.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.RoomDao;


//@WebServlet("/room")
public class RoomController extends HttpServlet {
	

	private static final long serialVersionUID = 1L;

	/**
	 * Called by: Host
	 * 
	 * @param roomnumber	int - room id
	 * @param valuename		String - column to be changed
	 * @param value			String - new data
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {

		RoomDao dao = new RoomDao();
		
		// Store values from request
		int roomnum = (int) req.getAttribute("roomnumber");
		String vname = (String) req.getAttribute("valuename");
		String value = (String) req.getAttribute("value");
		
		dao.editRoom(roomnum, vname, value);
	}
}
