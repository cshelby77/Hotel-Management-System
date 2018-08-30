package com.revature.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import com.revature.dao.ReservationDao;
import com.revature.dao.RoomDao;
import com.revature.model.Guest;
import com.revature.model.Room;


//@WebServlet("/hotel")
public class HotelController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * Called by: Host and Guest
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// DAOs
		RoomDao roomdao = new RoomDao();
		ReservationDao resdao = new ReservationDao();

		HttpSession session = req.getSession(false);

		Guest user = (Guest) session.getAttribute("user");
		List<Room> allrooms = new ArrayList<Room>();
		allrooms = roomdao.getAllRoom();
		System.out.println("GET");
		System.out.println(allrooms);
		
		// TODO: 
		// If user is guest, remove unavailable rooms
		if (user.getType() == "Guest") {
			String checkin = (String) req.getParameter("checkin");
			String checkout = (String) req.getParameter("checkout");

			List<Integer> notavailable = resdao.getBetweenReservations(checkin, checkout);
			System.out.println(notavailable);
			if (!notavailable.isEmpty()) {
				for (Room r : allrooms) {
					for (Integer x : notavailable) {
						if (r.getRoomnum() == x.intValue()) {
							allrooms.remove(r);
						}
					}
				}
			}
		}

		resp.setContentType("application/json;charset=UTF-8");

		ServletOutputStream out = resp.getOutputStream();
		Gson gson = new GsonBuilder().create();
		JsonArray jarray = gson.toJsonTree(allrooms).getAsJsonArray();
		JsonObject json = new JsonObject();
		json.add("rooms", jarray);

		String output = json.toString();
		out.print(output);
		out.close();

	}

}
