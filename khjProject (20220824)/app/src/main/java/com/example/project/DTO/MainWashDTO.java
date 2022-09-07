package com.example.project.DTO;

import java.io.Serializable;

public class MainWashDTO implements Serializable {
    String storeid, machineid, machineseq, cost, resttime, userid, name;

    public MainWashDTO(String storeid, String machineid, String machineseq, String cost, String resttime, String userid) {
        this.storeid = storeid;
        this.machineid = machineid;
        this.machineseq = machineseq;
        this.cost = cost;
        this.resttime = resttime;
        this.userid = userid;
    }

    public MainWashDTO(String storeid, String machineid, String machineseq, String cost, String resttime, String userid, String name) {
        this.storeid = storeid;
        this.machineid = machineid;
        this.machineseq = machineseq;
        this.cost = cost;
        this.resttime = resttime;
        this.userid = userid;
        this.name = name;
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
}
