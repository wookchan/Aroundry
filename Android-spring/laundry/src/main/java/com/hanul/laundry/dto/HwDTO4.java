package com.hanul.laundry.dto;
public class HwDTO4 {
	int storeid,machineseq,using;
    String costdate;
    
	public HwDTO4(int storeid, int machineseq, int using, String costdate) {
		super();
		this.storeid = storeid;
		this.machineseq = machineseq;
		this.using = using;
		this.costdate = costdate;
	}

	public int getStoreid() {
		return storeid;
	}

	public void setStoreid(int storeid) {
		this.storeid = storeid;
	}

	public int getMachineseq() {
		return machineseq;
	}

	public void setMachineseq(int machineseq) {
		this.machineseq = machineseq;
	}

	public int getUsing() {
		return using;
	}

	public void setUsing(int using) {
		this.using = using;
	}

	public String getCostdate() {
		return costdate;
	}

	public void setCostdate(String costdate) {
		this.costdate = costdate;
	}
    
    

}
