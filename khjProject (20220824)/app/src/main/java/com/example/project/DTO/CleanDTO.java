package com.example.project.DTO;

import java.io.Serializable;

public class CleanDTO implements Serializable {

    private String location;
    private String latitude;
    private String longitude;
    private String address;
    private String imageurl;


    public CleanDTO(String location, String latitude, String longitude, String address, String imageurl) {
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.imageurl = imageurl;

    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImageurl() { return imageurl; }

    public void setImageurl(String imageurl) { this.imageurl = imageurl; }
}

