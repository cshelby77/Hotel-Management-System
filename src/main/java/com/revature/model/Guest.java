package com.revature.model;

import java.sql.Date;

public class Guest extends Profile {

	int guestid;
	String address;
	Date dob;
	String email;

	// Called by GuestDOA
	public Guest() {
		super();
	}

	public int getGuestid() {
		return guestid;
	}

	public void setGuestid(int guestid) {
		this.guestid = guestid;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Guest [guestid=" + guestid + ", address=" + address + ", dob=" + dob + ", email=" + email
				+ ", profileid=" + profileid + ", fname=" + fname + ", lname=" + lname + ", type=" + type + "]";
	}
	
	

	
}
