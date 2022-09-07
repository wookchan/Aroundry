package com.example.project.DTO;

import java.io.Serializable;

public class UserDTO implements Serializable {

    private String userid, name, email, phone,  profile, point;
    public UserDTO(){

    }
    public UserDTO(String name, String point) {
        this.name = name;
        this.point = point;
    }

    public UserDTO(String userid, String name, String email, String phone, String profile) {
        this.userid = userid;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.profile = profile;
    }

    public UserDTO(String userid, String name, String email, String phone, String profile, String point) {
        this.userid = userid;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.profile = profile;
        this.point = point;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }
}
