package customer;

import java.util.Date;

public class CustomerVO {
	
	private Integer storeid, f_cctv, f_game, f_toilet, f_concent, f_wifi, f_coin, cost;
	private String ownerid, location, imageurl, address, userid, profile;
	private Date costdate;
	
	
	
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getOwnerid() {
		return ownerid;
	}
	public void setOwnerid(String ownerid) {
		this.ownerid = ownerid;
	}
	public Integer getF_cctv() {
		return f_cctv;
	}
	public void setF_cctv(Integer f_cctv) {
		this.f_cctv = f_cctv;
	}
	public Integer getF_game() {
		return f_game;
	}
	public void setF_game(Integer f_game) {
		this.f_game = f_game;
	}
	public Integer getF_toilet() {
		return f_toilet;
	}
	public void setF_toilet(Integer f_toilet) {
		this.f_toilet = f_toilet;
	}
	public Integer getF_concent() {
		return f_concent;
	}
	public void setF_concent(Integer f_concent) {
		this.f_concent = f_concent;
	}
	public Integer getF_wifi() {
		return f_wifi;
	}
	public void setF_wifi(Integer f_wifi) {
		this.f_wifi = f_wifi;
	}
	public Integer getF_coin() {
		return f_coin;
	}
	public void setF_coin(Integer f_coin) {
		this.f_coin = f_coin;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getStoreid() {
		return storeid;
	}
	public void setStoreid(Integer storeid) {
		this.storeid = storeid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public Integer getCost() {
		return cost;
	}
	public void setCost(Integer cost) {
		this.cost = cost;
	}

	public Date getCostdate() {
		return costdate;
	}
	public void setCostdate(Date costdate) {
		this.costdate = costdate;
	}
	

}
