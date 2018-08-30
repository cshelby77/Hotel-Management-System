package com.revature.model;


public class Room {

	private int roomnum;
	private String type;
	private int bednum;
	private String bedsize;
	private float rentrate;
	private String description;
	private String imgurl;
	

	//
	public Room(int rnum, String rtype, int bnum, String bsize, float rate, String des, String url) {
		this.roomnum = rnum;
		this.type = rtype;
		this.bednum = bnum;
		this.bedsize = bsize;
		this.rentrate = rate;
		this.description = des;
		this.imgurl = url;		
	}


	public int getRoomnum() {
		return roomnum;
	}


	public void setRoomnum(int roomnum) {
		this.roomnum = roomnum;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public int getBednum() {
		return bednum;
	}


	public void setBednum(int bednum) {
		this.bednum = bednum;
	}


	public String getBedsize() {
		return bedsize;
	}


	public void setBedsize(String bedsize) {
		this.bedsize = bedsize;
	}


	public float getRentrate() {
		return rentrate;
	}


	public void setRentrate(float rentrate) {
		this.rentrate = rentrate;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getImgurl() {
		return imgurl;
	}


	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}


	@Override
	public String toString() {
		return "\"roomnum\":" + roomnum + ", \"type\":\"" + type + "\", \"bednum\":\"" + bednum + "\", \"bedsize\":\"" + bedsize
				+ "\", \"rentrate\":\"" + rentrate + "\", \"description\":\"" + description + "\"";
	}
	


}
