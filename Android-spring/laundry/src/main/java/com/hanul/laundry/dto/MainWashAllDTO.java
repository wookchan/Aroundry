package com.hanul.laundry.dto;

public class MainWashAllDTO {
	
	String storeid, machineid, machineseq, cost, resttime, userid, name, location;
	
	public MainWashAllDTO(String storeid, String machineid, String machineseq, String cost, String resttime,
			String userid) {
		super();
		this.storeid = storeid;
		this.machineid = machineid;
		this.machineseq = machineseq;
		this.cost = cost;
		this.resttime = resttime;
		this.userid = userid;
	}

	public MainWashAllDTO(String storeid, String machineid, String machineseq, String cost, String resttime,
			String userid, String name, String location) {
		super();
		this.storeid = storeid;
		this.machineid = machineid;
		this.machineseq = machineseq;
		this.cost = cost;
		this.resttime = resttime;
		this.userid = userid;
		this.name = name;
		this.location = location;
	}

	public String getStoreid() {
		return storeid;
	}

	public void setStoreid(String storeid) {
		this.storeid = storeid;
	}

	public String getMachineid() {
		return machineid;
	}

	public void setMachineid(String machineid) {
		this.machineid = machineid;
	}

	public String getMachineseq() {
		return machineseq;
	}

	public void setMachineseq(String machineseq) {
		this.machineseq = machineseq;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getResttime() {
		return resttime;
	}

	public void setResttime(String resttime) {
		this.resttime = resttime;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	
	
	

}
