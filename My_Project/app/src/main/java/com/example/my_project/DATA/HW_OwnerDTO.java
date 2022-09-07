package com.example.my_project.DATA;

import java.io.Serializable;

public class HW_OwnerDTO implements Serializable {
    String ownerid,password,phone,name,profileurl;

    public HW_OwnerDTO(String ownerid, String password, String phone, String name) {
        this.ownerid = ownerid;
        this.password = password;
        this.phone = phone;
        this.name = name;
    }

    public HW_OwnerDTO(String ownerid, String password, String phone, String name,String profileurl) {
        this.ownerid = ownerid;
        this.password = password;
        this.phone = phone;
        this.name = name;
        this.profileurl = profileurl;
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
