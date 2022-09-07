package com.example.my_project.DATA;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

public class HW_CashDTO implements Serializable {
    int cost,storeid,machineid;
    String userid;
    Date costdate;

    public HW_CashDTO( String userid,int machineid,Date costdate, int storeid,int cost) {
        this.cost = cost;
        this.storeid = storeid;
        this.machineid = machineid;
        this.userid = userid;
        this.costdate = costdate;
    }

    public HW_CashDTO(int cost, Date costdate) {
        this.cost = cost;
        this.costdate = costdate;
    }

    public HW_CashDTO(String userid, Date costdate, int cost) {
        this.userid = userid;
        this.costdate = costdate;
        this.cost = cost;
    }


    public Date getCostdate() {
        return costdate;
    }
    public void setCostdate(Date costdate) {
        this.costdate = costdate;
    }

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
}
