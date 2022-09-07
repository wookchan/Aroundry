package com.example.my_project.DATA;

import java.io.Serializable;
import java.sql.Date;

public class HW_StoreDTO implements Serializable {
    int storeid,machineseq,using;
    Date costdate;

    public HW_StoreDTO(int storeid, int machineseq, int using, Date costdate) {
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

    public Date getCostdate() {
        return costdate;
    }

    public void setCostdate(Date costdate) {
        this.costdate = costdate;
    }
}
