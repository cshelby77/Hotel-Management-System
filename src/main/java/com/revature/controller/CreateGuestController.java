package com.revature.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.GuestDao;

/**
 * Called by: create profile
 */
//@WebServlet("/create")
public class CreateGuestController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String fname = req.getParameter("fname");
		String lname = req.getParameter("lname");
		String email = req.getParameter("email");
		String address = req.getParameter("address");
		String dobstring = req.getParameter("dob");
		
		GuestDao dao = new GuestDao();
		dao.createGuestProfile(username, password, fname, lname, email, address, dobstring);
		
	}
}
