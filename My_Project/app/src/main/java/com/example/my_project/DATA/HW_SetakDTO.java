package com.example.my_project.DATA;

import java.io.Serializable;

public class HW_SetakDTO implements Serializable {
    String titlelocation,location,imgpath,conven,ownerid;
    int machine,storeid,f_cctv,f_game,f_toilet,f_concent,f_wifi,f_coin;

    public HW_SetakDTO(String titlelocation, String location, String imgpath, int machine, int storeid, int f_cctv, int f_game, int f_toilet, int f_concent, int f_wifi, int f_coin) {
        this.titlelocation = titlelocation;
        this.location = location;
        this.imgpath = imgpath;
        this.machine = machine;
        this.storeid = storeid;
        this.f_cctv = f_cctv;
        this.f_game = f_game;
        this.f_toilet = f_toilet;
        this.f_concent = f_concent;
        this.f_wifi = f_wifi;
        this.f_coin = f_coin;
    }

    public  HW_SetakDTO(String titlelocation, String location, String imgpath, String conven, int machine, int storeid){
        this.titlelocation = titlelocation;
        this.location = location;
        this.imgpath = imgpath;
        this.conven = conven;
        this.machine = machine;
        this.storeid = storeid;
    }

    public HW_SetakDTO(String titlelocation, String location, String imgpath, String conven, String ownerid, int machine,	int storeid) {
        this.titlelocation = titlelocation;
        this.location = location;
        this.imgpath = imgpath;
        this.conven = conven;
        this.ownerid = ownerid;
        this.machine = machine;
        this.storeid = storeid;
    }



    public String getOwnerid() {
        return ownerid;
    }

    public void setOwnerid(String ownerid) {
        this.ownerid = ownerid;
    }

    public int getStoreid() {
        return storeid;
    }

    public void setStoreid(int storeid) {
        this.storeid = storeid;
    }

    public String getTitlelocation() {
        return titlelocation;
    }

    public void setTitlelocation(String titlelocation) {
        this.titlelocation = titlelocation;
    }

    public String getConven() {
        return conven;
    }

    public void setConven(String conven) {
        this.conven = conven;
    }

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getMachine() {
        return machine;
    }

    public void setMachine(int machine) {
        this.machine = machine;
    }

    public int getF_cctv() {
        return f_cctv;
    }

    public void setF_cctv(int f_cctv) {
        this.f_cctv = f_cctv;
    }

    public int getF_game() {
        return f_game;
    }

    public void setF_game(int f_game) {
        this.f_game = f_game;
    }

    public int getF_toilet() {
        return f_toilet;
    }

    public void setF_toilet(int f_toilet) {
        this.f_toilet = f_toilet;
    }

    public int getF_concent() {
        return f_concent;
    }

    public void setF_concent(int f_concent) {
        this.f_concent = f_concent;
    }

    public int getF_coin() {
        return f_coin;
    }

    public void setF_coin(int f_coin) {
        this.f_coin = f_coin;
    }

    public int getF_wifi() {
        return f_wifi;
    }

    public void setF_wifi(int f_wifi) {
        this.f_wifi = f_wifi;
    }
}
