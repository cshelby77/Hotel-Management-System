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
import com.revature.dao.MessageDao;
import com.revature.model.Guest;
import com.revature.model.Message;

//@WebServlet("/message")
public class MessageController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * Called by: Host and Guest
	 * Gets messages sent between guest and host
	 * 
	 * @return list of messages
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {

		HttpSession session = req.getSession();

		Guest user = (Guest) session.getAttribute("user");
		MessageDao dao = new MessageDao();

		List<Message> messages = dao.getGuestMessages(user.getProfileid());
				
		resp.setContentType("application/json;charset=UTF-8");

        ServletOutputStream out = resp.getOutputStream();
        Gson gson = new GsonBuilder().create();
        JsonArray jarray = gson.toJsonTree(messages).getAsJsonArray();
        JsonObject json = new JsonObject();
        json.add("messages", jarray);
        
        String output = json.toString();
        out.print(output);
		out.close();
		
	}
	
	/**
	 * Called by: Host and Guest 
	 * Sends message
	 * 
	 * @param receiver 		receiver id
	 * @param text			the actual message
	 * @return nothing
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {

		MessageDao dao = new MessageDao();
		HttpSession session = req.getSession();
		//TODO Fix front end to accept host data
		int receiver;
		Guest user = (Guest) session.getAttribute("user");
		if(user.getType() == "Guest") {
			receiver = 1;
		} else {
			String temp = req.getParameter("receiver");
			Integer itemp = Integer.valueOf(temp);
			receiver = itemp.intValue();
		}

		String text = (String)req.getParameter("text");
		dao.createMessage(user.getProfileid(), receiver, text);
	}
	
	/**
	 * Called by: Host
	 * Deletes all messages between guest and host
	 * 
	 * @param receiver		guest's profile id
	 * @return nothing
	 */
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		
		MessageDao dao = new MessageDao();
		
		// This is the id of the guest not the host 
		int receiver = (int)req.getAttribute("receiver");
		dao.deleteMessages(receiver);
			
	}
}
