package com.revature.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.GuestDao;
import com.revature.model.Guest;

//@WebServlet("/guest")
public class GuestController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * Called by: Host and Guest
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//TODO : json with one object
		HttpSession session = req.getSession();
		Guest user = (Guest) session.getAttribute("user");

		String jsonString = "{ \"name\": \" \"" + user.getFname() + " " + user.getLname() + "\"," 
								+ " \"address\": \"" + user.getAddress() + "\"," 
								+ " \"email\": \"" + user.getEmail() + "\"," 
								+ " \"dob\": \"" + user.getDob() + "\" " + "}";
		
//		// Mapper takes incoming json string and parsing it into an object
//		ObjectMapper mapper = new ObjectMapper();
//		Object obj = mapper.readValue(jsonString, Object.class);
//		
//		//Mapper here writes the Java object back to json
//		mapper.writeValue(resp.getOutputStream(), obj);

	}

	/**
	 * Called by: Guest
	 * 
	 * @param valuename Column name we want to change
	 * @param value     New data value
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		Guest user = (Guest) session.getAttribute("us er");
		GuestDao dao = new GuestDao();

		// Store values from request
		String vname = (String) req.getAttribute("valuename");
		String value = (String) req.getAttribute("value");

		Guest temp = dao.editGuest(user.getGuestid(), vname, value);

		// Updating user object
		user.setAddress(temp.getAddress());
		user.setEmail(temp.getEmail());
		user.setDob(temp.getDob());

		// Updating user within session
		session.setAttribute("user", user);

	}
}
