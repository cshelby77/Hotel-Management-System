package com.revature.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.revature.dao.ReservationDao;
import com.revature.model.Guest;
import com.revature.model.Reservation;

//@WebServlet("/reservation")
public class ReservationController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * Called by Guest and Host Guest: gets all guest's reservation Host:
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		Guest user = (Guest) session.getAttribute("user");
		ReservationDao dao = new ReservationDao();
		List<Reservation> res = null;

		if (user.getType() == "Guest") {
			res = dao.getGuestReservations(user.getGuestid());
		} else if (user.getType() == "Host") {
			res = dao.getPendingReservations();
		} else {
			System.out.println("ERROR: Wrong Type");
		}

		resp.setContentType("application/json;charset=UTF-8");

        ServletOutputStream out = resp.getOutputStream();
        Gson gson = new GsonBuilder().create();
        JsonArray jarray = gson.toJsonTree(res).getAsJsonArray();
        JsonObject json = new JsonObject();
        json.add("reservations", jarray);
        
        String output = json.toString();
        out.print(output);
		out.close();
		
		//TODO
		
	}

	/**
	 * Called by: Guest and Host
	 * Guest: Making a Reservation
	 * Host: Change Reservation Status
	 * 
	 * Guest parameters
	 * @param roomnumber int - room id
	 * @param numofguest int - number of guest
	 * @param checkin    Date - checkin date
	 * @param checkout   Date - checkout date
	 * 
	 * Host Parameters
	 * @param resid		int - reservation id
	 * @param status	String - new status
	 * 
	 * @return nothing
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		Guest user = (Guest) session.getAttribute("user");
		ReservationDao dao = new ReservationDao();

		if (user.getType() == "Guest") {
			String temp1 = req.getParameter("room");
			String temp2 = req.getParameter("numofguest");
			String checkin = req.getParameter("checkin");
			String checkout = req.getParameter("checkout");

			Integer roomid = Integer.valueOf(temp1);
			Integer numofguest = Integer.valueOf(temp2);
			
			dao.createReservation(user.getGuestid(), roomid, numofguest, checkin, checkout);
		}
		else if (user.getType() == "Host") {
			//TODO: EDIT Front end for Host
			int resid = (int) req.getAttribute("resid");
			String value = (String) req.getAttribute("status");
			dao.editReservationStat(resid, value);
		}
	}
}
