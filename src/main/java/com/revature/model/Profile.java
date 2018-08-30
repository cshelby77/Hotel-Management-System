package com.revature.model;

public class Profile {
	int profileid;
	String fname;
	String lname;
	String type;
	
	// Called when creating empty guest
	public Profile() {}
	
	// called by ProfileDao.GetProfile
	public Profile(int pid, String fn, String ln, String t) {
		this.profileid = pid;
		this.fname = fn;
		this.lname = ln;
		this.type = t;
	}
	
	public int getProfileid() {
		return profileid;
	}

	public void setProfileid(int profileid) {
		this.profileid = profileid;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	@Override
	public String toString() {
		return "Profile [fname=" + fname + ", lname=" + lname + "]";
	}
	
	
}
