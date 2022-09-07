package store;

public class StoreVO {
	private String location, imageurl, address, latitude, longitude, ownerid;
	private int f_cctv, f_game, f_toilet, f_concent, f_wifi, f_coin, cost, machine , storeid;
	
	public int getStoreid() {
		return storeid;
	}
	public void setStoreid(int storeid) {
		this.storeid = storeid;
	}
	public String getOwnerid() {
		return ownerid;
	}
	public void setOwnerid(String ownerid) {
		this.ownerid = ownerid;
	}
	public int getMachine() {
		return machine;
	}
	public void setMachine(int machine) {
		this.machine = machine;
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
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
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
	public int getF_wifi() {
		return f_wifi;
	}
	public void setF_wifi(int f_wifi) {
		this.f_wifi = f_wifi;
	}
	public int getF_coin() {
		return f_coin;
	}
	public void setF_coin(int f_coin) {
		this.f_coin = f_coin;
	}

}