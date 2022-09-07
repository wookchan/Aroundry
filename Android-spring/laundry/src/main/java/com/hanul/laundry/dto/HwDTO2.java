package com.hanul.laundry.dto;

import java.sql.Date;


public class HwDTO2 {
	int cost,storeid,machineid;
	String userid;
	String costdate;
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public int getStoreid() {
		return storeid;
	}
	public void setStoreid(int storeid) {
		this.storeid = storeid;
	}
	public int getMachineid() {
		return machineid;
	}
	public void setMachineid(int machineid) {
		this.machineid = machineid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getCostdate() {
		return costdate;
	}
	public void setCostdate(String costdate) {
		this.costdate = costdate;
	}
	
	
	
	public HwDTO2(int cost, String costdate) {
		super();
		this.cost = cost;
		this.costdate = costdate;
	}
	public HwDTO2(String userid, int machineid, String costdate, int storeid, int cost) {
		super();
		this.cost = cost;
		this.storeid = storeid;
		this.machineid = machineid;
		this.userid = userid;
		this.costdate = costdate;
	}

	
	
	
}
