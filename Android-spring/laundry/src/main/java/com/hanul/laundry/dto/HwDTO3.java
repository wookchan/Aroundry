package com.hanul.laundry.dto;

public class HwDTO3 {
	String ownerid,password,phone,name,profileurl;

	public HwDTO3(String ownerid, String password, String phone, String name) {
		super();
		this.ownerid = ownerid;
		this.password = password;
		this.phone = phone;
		this.name = name;
	}

	public HwDTO3(String ownerid, String password, String phone, String name,String profileurl) {
		super();
		this.profileurl = profileurl;
		this.ownerid = ownerid;
		this.password = password;
		this.phone = phone;
		this.name = name;
	}
	
	
	public String getProfileurl() {
		return profileurl;
	}

	public void setProfileurl(String profileurl) {
		this.profileurl = profileurl;
	}

	public String getOwnerid() {
		return ownerid;
	}

	public void setOwnerid(String ownerid) {
		this.ownerid = ownerid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
