package com.example.project.DTO;

import java.io.Serializable;

public class TimeDTO implements Serializable {

    /*
    *   dto : location(store), address(store) : 위도 경도로 거리 계산해서 넘어올 예정, imageurl(store)
    *
    *   사용가능 세탁기 개수 = 추가예정
    * */

    private String userid, machineid, enddate, storeid;
    /* int 사용가능한 세탁기 개수 */

    // recyclerview(example_item 에 뿌릴 시 비밀번호를 제외한 모든정보를 가져오기 위해
    public TimeDTO(String address, String location, String imageurl) {
        this.userid = userid;
        this.machineid = machineid;
        this.enddate = enddate;
        this.storeid = storeid;
    }


    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getMachineid() {
        return machineid;
    }
    public void setMachineid(String machineid) {
        this.machineid = machineid;
    }
    public String getEnddate() {
        return enddate;
    }

    public void setImageurl(String enddate) {
        this.enddate = enddate;
    }

    public String getStoreid() {
        return storeid;
    }

    public void setStoreid(String storeid) {
        this.storeid = storeid;
    }

}
