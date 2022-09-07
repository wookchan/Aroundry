package com.example.project.DTO;

import java.io.Serializable;

public class MainMyWashDTO implements Serializable {

    String userid, machineseq, resttime, machineid, storeid, address, location;

    public MainMyWashDTO(String userid, String machineseq, String resttime, String machineid, String storeid, String address, String location) {
        this.userid = userid;
        this.machineseq = machineseq;
        this.resttime = resttime;
        this.machineid = machineid;
        this.storeid = storeid;
        this.address = address;
        this.location = location;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getMachineseq() {
        return machineseq;
    }

    public void setMachineseq(String machineseq) {
        this.machineseq = machineseq;
    }

    public String getResttime() {
        return resttime;
    }

    public void setResttime(String resttime) {
        this.resttime = resttime;
    }

    public String getMachineid() {
        return machineid;
    }

    public void setMachineid(String machineid) {
        this.machineid = machineid;
    }

    public String getStoreid() {
        return storeid;
    }

    public void setStoreid(String storeid) {
        this.storeid = storeid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
