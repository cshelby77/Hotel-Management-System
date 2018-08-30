package com.revature.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;

//import javax.servlet.ServletException;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import com.revature.dao.GuestDao;
import com.revature.model.Guest;

//@WebServlet("/login")
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Called by: index.html
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		Guest user = null;
		
		try {
			GuestDao dao = new GuestDao();
			user = dao.getGuest(username, password);
		} catch(Exception ex) {
			System.out.println("dao.getGuest ERROR");
			ex.printStackTrace();
		}
		

		HttpSession session = req.getSession(false);
		if (session!=null && !session.isNew()) {
		    session.invalidate();
		}
		
		HttpSession session1 = req.getSession(true);
		session1.setMaxInactiveInterval(20*60);
		session1.setAttribute("user", user);

		//Save cookie for front end
		Cookie ck = new Cookie("type", user.getType());
		resp.addCookie(ck);		

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		out.append("success");
		out.close();

	}
}
