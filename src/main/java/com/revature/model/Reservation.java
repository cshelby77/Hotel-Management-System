package com.revature.model;

import java.sql.Date;

public class Reservation {

	int id;
	int guestid;
	int roomid;
	int numofguest;
	Date checkin;
	Date checkout;
	String status;
	
	
	public Reservation(int id, int guestid, int roomid, int numofguest, Date checkin, Date checkout, String status) {
		this.id = id;
		this.guestid = guestid;
		this.roomid = roomid;
		this.numofguest = numofguest;
		this.checkin = checkin;
		this.checkout = checkout;
		this.status = status;
	}


	public int getRoomid() {
		return roomid;
	}


	public void setRoomid(int roomid) {
		this.roomid = roomid;
	}


	public int getNumofguest() {
		return numofguest;
	}


	public void setNumofguest(int numofguest) {
		this.numofguest = numofguest;
	}


	public Date getCheckin() {
		return checkin;
	}


	public void setCheckin(Date checkin) {
		this.checkin = checkin;
	}


	public Date getCheckout() {
		return checkout;
	}


	public void setCheckout(Date checkout) {
		this.checkout = checkout;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "\"id\":\" + id + \", \"guestid\":" + guestid + ", \"roomnum\":" + roomid + ", \"numofguest\":" + numofguest + ", \"checkin\":\""
				+ checkin + "\", \"checkout\":\"" + checkout + "\", \"status\":\"" + status + "\"";
	}

}
