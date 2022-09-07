package com.example.project.DTO;

import java.io.Serializable;

public class MemberDTO implements Serializable {


    private String name, email, phonenumber, userid, profileimage;
    ////////////////////////////////////////////////


    private String address, location, imageurl, lc, storeid , point;

    int num;



    public MemberDTO(String name, String point) {
        this.name = name;
        this.point = point;
    }



    public MemberDTO(String userid, String storeid, String lc, int num) {
        this.userid = userid;
        this.storeid = storeid;
        this.lc = lc;
    }


    public MemberDTO(String address, String location , String imageurl) {
        this.address = address;
        this.location = location;
        this.imageurl = imageurl;
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

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getLc() {
        return lc;
    }

    public void setLc(String lc) {
        this.lc = lc;
    }

    public String getStoreid() {
        return storeid;
    }

    public void setStoreid(String storeid) {
        this.storeid = storeid;
    }

    //////////////////////////////////////

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getProfileimage() {
        return profileimage;
    }

    public void setProfileimage(String profileimage) {
        this.profileimage = profileimage;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public MemberDTO(String name, String email, String phonenumber, String userid, String profileimage) {
        this.name = name;
        this.email = email;
        this.phonenumber = phonenumber;
        this.userid = userid;
        this.profileimage = profileimage;
    }

    public MemberDTO(String userid, String name, String email, String phone, String profile, String point) {
        this.userid = userid;
        this.name = name;
        this.email = email;
        this.phonenumber = phone;
        this.profileimage = profile;
        this.point = point;
    }

}
