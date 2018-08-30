package com.revature.model;

import java.sql.Date;

public class Message {

	int id;
	int senderid;
	int receiverid;
	String text;
	Date timesent;
	
	public Message(int id, int senderid, int recieverid, String text, Date timesent) {
		this.id = id;
		this.senderid = senderid;
		this.receiverid = recieverid;
		this.text = text;
		this.timesent = timesent;
	}

	public int getReceiverid() {
		return receiverid;
	}

	public void setReceiverid(int receiverid) {
		this.receiverid = receiverid;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getTimesent() {
		return timesent;
	}

	public void setTimesent(Date timesent) {
		this.timesent = timesent;
	}

	@Override
	public String toString() {
		return "\"id\":" + id + ", \"senderid\":" + senderid + ", \"receiverid\":" + receiverid + ", \"text\":\"" + text
				+ "\", \"timesent\":\"" + timesent + "\"";
	}
	
	
	
	

	


	


}
