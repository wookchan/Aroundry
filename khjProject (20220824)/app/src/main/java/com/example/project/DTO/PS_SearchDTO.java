package com.example.project.DTO;

import java.io.Serializable;

public class PS_SearchDTO implements Serializable {

    /*
    *   dto : location(store), address(store) : 위도 경도로 거리 계산해서 넘어올 예정, imageurl(store)
    *
    *   사용가능 세탁기 개수 = 추가예정
    * */

    private String address, location, imageurl, storeid;
    /* int 사용가능한 세탁기 개수 */

    // recyclerview(example_item 에 뿌릴 시 비밀번호를 제외한 모든정보를 가져오기 위해
    public PS_SearchDTO(String address, String location, String imageurl) {
        this.address = address;
        this.location = location;
        this.imageurl = imageurl;
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
    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getStoreid() {
        return storeid;
    }

    public void setStoreid(String storeid) {
        this.storeid = storeid;
    }

}
