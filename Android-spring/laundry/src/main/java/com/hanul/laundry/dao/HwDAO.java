package com.hanul.laundry.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.springframework.format.annotation.DateTimeFormat;

import com.google.gson.JsonObject;
import com.hanul.laundry.dto.HwDTO;
import com.hanul.laundry.dto.HwDTO2;
import com.hanul.laundry.dto.HwDTO3;
import com.hanul.laundry.dto.HwDTO4;

public class HwDAO {
	
	DataSource ds;
	PreparedStatement ps;
	Connection conn;
	ResultSet rs;
	
	//connect
	public HwDAO() {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:/comp/env/ateam");	
		}
		catch(Exception e) {
			e.getMessage();
		}
	}
	//connect end
	
	//disconnect
	public void disconnect() {
		try {
			if(rs != null) {
				rs.close();
			}
			if(ps != null) {
				ps.close();
			}
			if(conn != null) {
				conn.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	//end of disconnect
	
	
//	String titlelocation,location,imgpath,conven,ownerid;
//	int machine,storeid;
//	(String titlelocation, String location, String imgpath, String conven, String ownerid, int machine,int storeid)
	public ArrayList<HwDTO> Hwlist(String id) {
		System.out.println("HWlist 진입");
		ArrayList<HwDTO> list = new ArrayList<HwDTO>();
		String titlelocation,location,imgpath,conven,ownerid;
		int machine,storeid,f_cctv,f_game,f_toilet,f_concent,f_wifi,f_coin;
		try {
			conn = ds.getConnection();
			String sql ="select * "
					+	"from store "
					+	"where ownerid= '"+id+"'\n " 
					+ 	"order by storeid ";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				titlelocation = rs.getString("location");
				location = rs.getString("address");
				if(rs.getString("imageurl")!=null) {
					imgpath = rs.getString("imageurl");

				}
				else {
					imgpath = "NoImage";

				}
				/* conven = rs.getString("conven"); */
				ownerid = rs.getString("ownerid");
				machine = rs.getInt("machine"); // db에 없음 없음
				storeid = rs.getInt("storeid");
				f_cctv = rs.getInt("f_cctv");
				f_game = rs.getInt("f_game");
				f_toilet = rs.getInt("f_toilet");
				f_concent = rs.getInt("f_concent");
				f_wifi = rs.getInt("f_wifi");
				f_coin = rs.getInt("f_coin");
				// String titlelocation, String location, String imgpath, String ownerid, int machine, int storeid, int f_cctv, int f_game, int f_toilet, int f_concent, int f_f_wifi, int f_coin
				//list.add(new HwDTO(titlelocation,location,imgpath,conven,ownerid,machine,storeid));
				list.add(new HwDTO(titlelocation,location,imgpath,ownerid,machine,storeid,f_cctv,f_game,f_toilet,f_concent,f_wifi,f_coin));
			}
			System.out.println("list.size : " + list.size());
		}
		catch (Exception e) {
			e.getMessage();
		}
		finally {
			disconnect();
		}
		
		
		return list;
	}

//	int cost,storeid,machineid;
//	String userid;
//	Date costdate;
	public ArrayList<HwDTO2> Hwweekcost(String id) {
		System.out.println("Hwweekcost 진입");
		ArrayList<HwDTO2> list = new ArrayList<HwDTO2>();
		JsonObject list2 =new JsonObject();
		try {
			conn = ds.getConnection();
			/*		
			String sql = "select a.*,b.ownerid,to_date(to_char(costdate,'YY/MM/DD HH24:MI:SS'),'YY/MM/DD HH24:MI:SS') costdate2 "
					+ "from storehistory a left join ownerstore b "
					+ "on a.storeid = b.storeid "
					+ "where ownerid= '"+id+"'"
					+ "order by a.costdate ";
			 */
			
			String sql = "select   nvl(cost,0) cost , dd "     
					+ "from (select sum(cost) cost,  to_char(costdate, 'rrrr/mm/dd') dd , "
			       + "decode(to_char(costdate, 'rrrr/mm/dd'),   "
			       				+ "trunc(sysdate, 'day')+1, 'd1', "
			                    + "trunc(sysdate, 'day')+2 , 'd2', "
			                    + "trunc(sysdate, 'day')+3 , 'd3', "
			                    + "trunc(sysdate, 'day')+4 , 'd4', "
			                    + "trunc(sysdate, 'day')+5 , 'd5', "
			                    + "trunc(sysdate, 'day')+6 , 'd6', "
			                    + "trunc(sysdate, 'day')+7 , 'd7' "
			                    + ") no  from storehistory  "
			        + "where storeid in(select storeid from store where ownerid='"+id+"') "
			        + "and to_char(costdate, 'rrrrmmdd') between trunc(sysdate, 'day') and trunc(sysdate, 'day')+6 "
			        + "group by  to_char(costdate, 'rrrr/mm/dd') ) s         "
			                    + "right outer join ( "
			                   + "select to_char(dd, 'rrrr/mm/dd') dd from ( "
			                            + "select  trunc(sysdate, 'day')+1 dd from dual "
			                            + "union select  trunc(sysdate, 'day')+2 dd from dual "
			                            + "union select  trunc(sysdate, 'day')+3 dd from dual "
			                            + "union select  trunc(sysdate, 'day')+4 dd from dual "
			                            + "union select  trunc(sysdate, 'day')+5 dd from dual "
			                            + "union select  trunc(sysdate, 'day')+6 dd from dual "
			                            + "union select  trunc(sysdate, 'day')+7 dd from dual ) ) d using(dd) " 
			        + "order by dd ";
		        
		        
			ps=conn.prepareStatement(sql);
//			ps.setString(1, id);
			rs=ps.executeQuery();
			while(rs.next()) {
//				String userid = rs.getString("userid");
//				int machineid = rs.getInt("machineid");
				String costdate = rs.getString("dd");
//				int storeid = rs.getInt("storeid");
				int cost= rs.getInt("cost");
				
				list.add(new HwDTO2(cost,costdate));
//				list.add(new HwDTO2(userid,machineid,costdate,storeid,cost));
				
			}
			System.out.println("list.size : " + list.size());
			
		}
		catch (Exception e){
			e.getMessage();
		}
		finally {
			disconnect();
		}
		
		return list;
	}
	
	public ArrayList<HwDTO2> Hwmonthcost(String id) {
		System.out.println("hwmonthcost 진입");
		ArrayList<HwDTO2> list = new ArrayList<HwDTO2>();
		JsonObject list2 =new JsonObject();
		
		try {
			conn= ds.getConnection();
			String sql = "select   nvl(cost,0) cost , dd "
					+ "from (select sum(cost) cost,  to_char(costdate, 'rrrr/mm/dd') dd, "
					+ "       decode(to_char(costdate, 'rrrr/mm/dd'), "
					+ "                    add_months(trunc(sysdate,'MM'),-2),'-2m d1', "
					+ "                    add_months(trunc(sysdate,'MM'),-2)+1,'-2m d2', "
					+ "                    add_months(trunc(sysdate,'MM'),-2)+2,'-2m d3', "
					+ "                    add_months(trunc(sysdate,'MM'),-2)+3,'-2m d4', "
					+ "                    add_months(trunc(sysdate,'MM'),-2)+4,'-2m d5', "
					+ "                    add_months(trunc(sysdate,'MM'),-2)+5,'-2m d6', "
					+ "                    add_months(trunc(sysdate,'MM'),-2)+6,'-2m d7', "
					+ "                    add_months(trunc(sysdate,'MM'),-2)+7,'-2m d8', "
					+ "                    add_months(trunc(sysdate,'MM'),-2)+8,'-2m d9', "
					+ "                    add_months(trunc(sysdate,'MM'),-2)+9,'-2m d10', "
					+ "                    add_months(trunc(sysdate,'MM'),-2)+10,'-2m d11', "
					+ "                    add_months(trunc(sysdate,'MM'),-2)+11,'-2m d12', "
					+ "                    add_months(trunc(sysdate,'MM'),-2)+12,'-2m d13', "
					+ "                    add_months(trunc(sysdate,'MM'),-2)+13,'-2m d14', "
					+ "                    add_months(trunc(sysdate,'MM'),-2)+14,'-2m d15', "
					+ "                    add_months(trunc(sysdate,'MM'),-2)+15,'-2m d16', "
					+ "                    add_months(trunc(sysdate,'MM'),-2)+16,'-2m d17', "
					+ "                    add_months(trunc(sysdate,'MM'),-2)+17,'-2m d18', "
					+ "                    add_months(trunc(sysdate,'MM'),-2)+18,'-2m d19', "
					+ "                    add_months(trunc(sysdate,'MM'),-2)+19,'-2m d20', "
					+ "                    add_months(trunc(sysdate,'MM'),-2)+20,'-2m d21', "
					+ "                    add_months(trunc(sysdate,'MM'),-2)+21,'-2m d22', "
					+ "                    add_months(trunc(sysdate,'MM'),-2)+22,'-2m d23', "
					+ "                    add_months(trunc(sysdate,'MM'),-2)+23,'-2m d24', "
					+ "                    add_months(trunc(sysdate,'MM'),-2)+24,'-2m d25', "
					+ "                    add_months(trunc(sysdate,'MM'),-2)+25,'-2m d26', "
					+ "                    add_months(trunc(sysdate,'MM'),-2)+26,'-2m d27', "
					+ "                    add_months(trunc(sysdate,'MM'),-2)+27,'-2m d28', "
					+ "                    add_months(trunc(sysdate,'MM'),-2)+28,'-2m d29', "
					+ "                    add_months(trunc(sysdate,'MM'),-2)+29,'-2m d30', "
					+ "                    add_months(trunc(sysdate,'MM'),-2)+30,'-2m d31', "
					+ "                    add_months(trunc(sysdate,'MM'),-1),'-1m d1', "
					+ "                    add_months(trunc(sysdate,'MM'),-1)+1,'-1m d2', "
					+ "                    add_months(trunc(sysdate,'MM'),-1)+2,'-1m d3', "
					+ "                    add_months(trunc(sysdate,'MM'),-1)+3,'-1m d4', "
					+ "                    add_months(trunc(sysdate,'MM'),-1)+4,'-1m d5', "
					+ "                    add_months(trunc(sysdate,'MM'),-1)+5,'-1m d6', "
					+ "                    add_months(trunc(sysdate,'MM'),-1)+6,'-1m d7', "
					+ "                    add_months(trunc(sysdate,'MM'),-1)+7,'-1m d8', "
					+ "                    add_months(trunc(sysdate,'MM'),-1)+8,'-1m d9', "
					+ "                    add_months(trunc(sysdate,'MM'),-1)+9,'-1m d10', "
					+ "                    add_months(trunc(sysdate,'MM'),-1)+10,'-1m d11', "
					+ "                    add_months(trunc(sysdate,'MM'),-1)+11,'-1m d12', "
					+ "                    add_months(trunc(sysdate,'MM'),-1)+12,'-1m d13', "
					+ "                    add_months(trunc(sysdate,'MM'),-1)+13,'-1m d14', "
					+ "                    add_months(trunc(sysdate,'MM'),-1)+14,'-1m d15', "
					+ "                    add_months(trunc(sysdate,'MM'),-1)+15,'-1m d16', "
					+ "                    add_months(trunc(sysdate,'MM'),-1)+16,'-1m d17', "
					+ "                    add_months(trunc(sysdate,'MM'),-1)+17,'-1m d18', "
					+ "                    add_months(trunc(sysdate,'MM'),-1)+18,'-1m d19', "
					+ "                    add_months(trunc(sysdate,'MM'),-1)+19,'-1m d20', "
					+ "                    add_months(trunc(sysdate,'MM'),-1)+20,'-1m d21', "
					+ "                    add_months(trunc(sysdate,'MM'),-1)+21,'-1m d22', "
					+ "                    add_months(trunc(sysdate,'MM'),-1)+22,'-1m d23', "
					+ "                    add_months(trunc(sysdate,'MM'),-1)+23,'-1m d24', "
					+ "                    add_months(trunc(sysdate,'MM'),-1)+24,'-1m d25', "
					+ "                    add_months(trunc(sysdate,'MM'),-1)+25,'-1m d26', "
					+ "                    add_months(trunc(sysdate,'MM'),-1)+26,'-1m d27', "
					+ "                    add_months(trunc(sysdate,'MM'),-1)+27,'-1m d28', "
					+ "                    add_months(trunc(sysdate,'MM'),-1)+28,'-1m d29', "
					+ "                    add_months(trunc(sysdate,'MM'),-1)+29,'-1m d30', "
					+ "                    add_months(trunc(sysdate,'MM'),-1)+30,'-1m d31', "
					+ "                    trunc(sysdate, 'MM') , 'd1', "
					+ "                    trunc(sysdate, 'MM')+1 , 'd2', "
					+ "                    trunc(sysdate, 'MM')+2 , 'd3', "
					+ "                    trunc(sysdate, 'MM')+3 , 'd4', "
					+ "                    trunc(sysdate, 'MM')+4 , 'd5', "
					+ "                    trunc(sysdate, 'MM')+5 , 'd6', "
					+ "                    trunc(sysdate, 'MM')+6 , 'd7', "
					+ "                    trunc(sysdate, 'MM')+7 , 'd8', "
					+ "                    trunc(sysdate, 'MM')+8 , 'd9', "
					+ "                    trunc(sysdate, 'MM')+9 , 'd10', "
					+ "                    trunc(sysdate, 'MM')+10 , 'd11', "
					+ "                    trunc(sysdate, 'MM')+11 , 'd12', "
					+ "                    trunc(sysdate, 'MM')+12 , 'd13', "
					+ "                    trunc(sysdate, 'MM')+13 , 'd14', "
					+ "                    trunc(sysdate, 'MM')+14 , 'd15', "
					+ "                    trunc(sysdate, 'MM')+15 , 'd16', "
					+ "                    trunc(sysdate, 'MM')+16 , 'd17', "
					+ "                    trunc(sysdate, 'MM')+17 , 'd18', "
					+ "                    trunc(sysdate, 'MM')+18 , 'd19', "
					+ "                    trunc(sysdate, 'MM')+19 , 'd20', "
					+ "                    trunc(sysdate, 'MM')+20 , 'd21', "
					+ "                    trunc(sysdate, 'MM')+21 , 'd22', "
					+ "                    trunc(sysdate, 'MM')+22 , 'd23', "
					+ "                    trunc(sysdate, 'MM')+23 , 'd24', "
					+ "                    trunc(sysdate, 'MM')+24 , 'd25', "
					+ "                    trunc(sysdate, 'MM')+25 , 'd26', "
					+ "                    trunc(sysdate, 'MM')+26 , 'd27', "
					+ "                    trunc(sysdate, 'MM')+27 , 'd28', "
					+ "                    trunc(sysdate, 'MM')+28 , 'd29', "
					+ "                    trunc(sysdate, 'MM')+29 , 'd30', "
					+ "                    trunc(sysdate, 'MM')+30 , 'd31' "
					+ "                    ) no  from storehistory "
					+ "        where storeid in(select storeid from store where ownerid='"+id+"') "
					+ "        and to_char(costdate, 'rrrrmmdd') between add_months(trunc(sysdate, 'MM'),-2) and TRUNC(LAST_DAY(SYSDATE)) "
					+ "        group by  to_char(costdate, 'rrrr/mm/dd') ) s          "
					+ "                    right outer join ( "
					+ "                   select to_char(dd, 'rrrr/mm/dd') dd from ( "
					+ "                            select add_months(trunc(sysdate,'MM'),-2) dd from dual "
					+ "                            union "
					+ "                            select add_months(trunc(sysdate,'MM'),-2)+1 dd from dual "
					+ "                            union "
					+ "                            select add_months(trunc(sysdate,'MM'),-2)+2 dd from dual "
					+ "                            union "
					+ "                            select add_months(trunc(sysdate,'MM'),-2)+3 dd from dual "
					+ "                            union "
					+ "                            select add_months(trunc(sysdate,'MM'),-2)+4 dd from dual "
					+ "                            union "
					+ "                            select add_months(trunc(sysdate,'MM'),-2)+5 dd from dual "
					+ "                            union "
					+ "                            select add_months(trunc(sysdate,'MM'),-2)+6 dd from dual "
					+ "                            union "
					+ "                            select add_months(trunc(sysdate,'MM'),-2)+7 dd from dual "
					+ "                            union "
					+ "                            select add_months(trunc(sysdate,'MM'),-2)+8 dd from dual "
					+ "                            union "
					+ "                            select add_months(trunc(sysdate,'MM'),-2)+9 dd from dual "
					+ "                            union "
					+ "                            select add_months(trunc(sysdate,'MM'),-2)+10 dd from dual "
					+ "                            union "
					+ "                            select add_months(trunc(sysdate,'MM'),-2)+11 dd from dual "
					+ "                            union "
					+ "                            select add_months(trunc(sysdate,'MM'),-2)+12 dd from dual "
					+ "                            union "
					+ "                            select add_months(trunc(sysdate,'MM'),-2)+13 dd from dual "
					+ "                            union "
					+ "                            select add_months(trunc(sysdate,'MM'),-2)+14 dd from dual "
					+ "                            union "
					+ "                            select add_months(trunc(sysdate,'MM'),-2)+15 dd from dual "
					+ "                            union "
					+ "                            select add_months(trunc(sysdate,'MM'),-2)+16 dd from dual "
					+ "                            union "
					+ "                            select add_months(trunc(sysdate,'MM'),-2)+17 dd from dual "
					+ "                            union "
					+ "                            select add_months(trunc(sysdate,'MM'),-2)+18 dd from dual "
					+ "                            union "
					+ "                            select add_months(trunc(sysdate,'MM'),-2)+19 dd from dual "
					+ "                            union "
					+ "                            select add_months(trunc(sysdate,'MM'),-2)+20 dd from dual "
					+ "                            union "
					+ "                            select add_months(trunc(sysdate,'MM'),-2)+21 dd from dual "
					+ "                            union "
					+ "                            select add_months(trunc(sysdate,'MM'),-2)+22 dd from dual "
					+ "                            union "
					+ "                            select add_months(trunc(sysdate,'MM'),-2)+23 dd from dual "
					+ "                            union "
					+ "                            select add_months(trunc(sysdate,'MM'),-2)+24 dd from dual "
					+ "                            union "
					+ "                            select add_months(trunc(sysdate,'MM'),-2)+25 dd from dual "
					+ "                            union "
					+ "                            select add_months(trunc(sysdate,'MM'),-2)+26 dd from dual "
					+ "                            union "
					+ "                            select add_months(trunc(sysdate,'MM'),-2)+27 dd from dual "
					+ "                            union "
					+ "                            select add_months(trunc(sysdate,'MM'),-2)+28 dd from dual "
					+ "                            union "
					+ "                            select add_months(trunc(sysdate,'MM'),-2)+29 dd from dual "
					+ "                            union "
					+ "                            select add_months(trunc(sysdate,'MM'),-2)+30 dd from dual "
					+ "                            union "
					+ "                            select add_months(trunc(sysdate,'MM'),-1) dd from dual "
					+ "                            union "
					+ "                            select add_months(trunc(sysdate,'MM'),-1)+1 dd from dual "
					+ "                            union "
					+ "                            select add_months(trunc(sysdate,'MM'),-1)+2 dd from dual "
					+ "                            union "
					+ "                            select add_months(trunc(sysdate,'MM'),-1)+3 dd from dual "
					+ "                            union "
					+ "                            select add_months(trunc(sysdate,'MM'),-1)+4 dd from dual "
					+ "                            union "
					+ "                            select add_months(trunc(sysdate,'MM'),-1)+5 dd from dual "
					+ "                            union "
					+ "                            select add_months(trunc(sysdate,'MM'),-1)+6 dd from dual "
					+ "                            union "
					+ "                            select add_months(trunc(sysdate,'MM'),-1)+7 dd from dual "
					+ "                            union "
					+ "                            select add_months(trunc(sysdate,'MM'),-1)+8 dd from dual "
					+ "                            union "
					+ "                            select add_months(trunc(sysdate,'MM'),-1)+9 dd from dual "
					+ "                            union "
					+ "                            select add_months(trunc(sysdate,'MM'),-1)+10 dd from dual "
					+ "                            union "
					+ "                            select add_months(trunc(sysdate,'MM'),-1)+11 dd from dual "
					+ "                            union "
					+ "                            select add_months(trunc(sysdate,'MM'),-1)+12 dd from dual "
					+ "                            union "
					+ "                            select add_months(trunc(sysdate,'MM'),-1)+13 dd from dual "
					+ "                            union "
					+ "                            select add_months(trunc(sysdate,'MM'),-1)+14 dd from dual "
					+ "                            union "
					+ "                            select add_months(trunc(sysdate,'MM'),-1)+15 dd from dual "
					+ "                            union "
					+ "                            select add_months(trunc(sysdate,'MM'),-1)+16 dd from dual "
					+ "                            union "
					+ "                            select add_months(trunc(sysdate,'MM'),-1)+17 dd from dual "
					+ "                            union "
					+ "                            select add_months(trunc(sysdate,'MM'),-1)+18 dd from dual "
					+ "                            union "
					+ "                            select add_months(trunc(sysdate,'MM'),-1)+19 dd from dual "
					+ "                            union "
					+ "                            select add_months(trunc(sysdate,'MM'),-1)+20 dd from dual "
					+ "                            union "
					+ "                            select add_months(trunc(sysdate,'MM'),-1)+21 dd from dual "
					+ "                            union "
					+ "                            select add_months(trunc(sysdate,'MM'),-1)+22 dd from dual "
					+ "                            union "
					+ "                            select add_months(trunc(sysdate,'MM'),-1)+23 dd from dual "
					+ "                            union "
					+ "                            select add_months(trunc(sysdate,'MM'),-1)+24 dd from dual "
					+ "                            union "
					+ "                            select add_months(trunc(sysdate,'MM'),-1)+25 dd from dual "
					+ "                            union "
					+ "                            select add_months(trunc(sysdate,'MM'),-1)+26 dd from dual "
					+ "                            union "
					+ "                            select add_months(trunc(sysdate,'MM'),-1)+27 dd from dual "
					+ "                            union "
					+ "                            select add_months(trunc(sysdate,'MM'),-1)+28 dd from dual "
					+ "                            union "
					+ "                            select add_months(trunc(sysdate,'MM'),-1)+29 dd from dual "
					+ "                            union "
					+ "                            select add_months(trunc(sysdate,'MM'),-1)+30 dd from dual "
					+ "                            union "
					+ "                             select  trunc(sysdate, 'MM') dd from dual "
					+ "                            union "
					+ "                            select  trunc(sysdate, 'MM')+1 dd from dual "
					+ "                            union "
					+ "                            select  trunc(sysdate, 'MM')+2 dd from dual "
					+ "                            union "
					+ "                            select  trunc(sysdate, 'MM')+3 dd from dual "
					+ "                            union "
					+ "                            select  trunc(sysdate, 'MM')+4 dd from dual "
					+ "                            union "
					+ "                            select  trunc(sysdate, 'MM')+5 dd from dual  "
					+ "                            union "
					+ "                            select  trunc(sysdate, 'MM')+6 dd from dual  "
					+ "                            union "
					+ "                            select  trunc(sysdate, 'MM')+7 dd from dual  "
					+ "                            union "
					+ "                            select  trunc(sysdate, 'MM')+8 dd from dual  "
					+ "                            union "
					+ "                            select  trunc(sysdate, 'MM')+9 dd from dual  "
					+ "                            union "
					+ "                            select  trunc(sysdate, 'MM')+10 dd from dual "
					+ "                            union "
					+ "                            select  trunc(sysdate, 'MM')+11 dd from dual "
					+ "                            union "
					+ "                            select  trunc(sysdate, 'MM')+12 dd from dual "
					+ "                            union "
					+ "                            select  trunc(sysdate, 'MM')+13 dd from dual "
					+ "                            union "
					+ "                            select  trunc(sysdate, 'MM')+14 dd from dual "
					+ "                            union "
					+ "                            select  trunc(sysdate, 'MM')+15 dd from dual "
					+ "                            union "
					+ "                            select  trunc(sysdate, 'MM')+16 dd from dual "
					+ "                            union "
					+ "                            select  trunc(sysdate, 'MM')+17 dd from dual "
					+ "                            union "
					+ "                            select  trunc(sysdate, 'MM')+18 dd from dual "
					+ "                            union "
					+ "                            select  trunc(sysdate, 'MM')+19 dd from dual "
					+ "                            union "
					+ "                            select  trunc(sysdate, 'MM')+20 dd from dual "
					+ "                            union "
					+ "                            select  trunc(sysdate, 'MM')+21 dd from dual "
					+ "                            union "
					+ "                            select  trunc(sysdate, 'MM')+22 dd from dual "
					+ "                            union "
					+ "                            select  trunc(sysdate, 'MM')+23 dd from dual "
					+ "                            union "
					+ "                            select  trunc(sysdate, 'MM')+24 dd from dual "
					+ "                            union "
					+ "                            select  trunc(sysdate, 'MM')+25 dd from dual "
					+ "                            union "
					+ "                            select  trunc(sysdate, 'MM')+26 dd from dual "
					+ "                            union "
					+ "                            select  trunc(sysdate, 'MM')+27 dd from dual "
					+ "                            union "
					+ "                            select  trunc(sysdate, 'MM')+28 dd from dual "
					+ "                            union "
					+ "                            select  trunc(sysdate, 'MM')+29 dd from dual "
					+ "                            union "
					+ "                            select  trunc(sysdate, 'MM')+30 dd from dual) ) d using(dd)  "
					+ "order by dd ";
			ps= conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()) {
				String costdate = rs.getString("dd");
				int cost = rs.getInt("cost");
				
				list.add(new HwDTO2(cost,costdate));
			}
			System.out.println("list.size : "+list.size());
			
		} 
		catch (Exception e) {
			e.getMessage();
		}
		
		finally {
			disconnect();
		}
		
		return list;
		
	}

	public HwDTO3 Hwownerlogin(String id,String pw) {
		System.out.println("Hwownerlogin 진입");
		HwDTO3 list = null;
		String ownerid,password,phone,name,profileurl;
		System.out.println("select * "
				+ "					 from ownerinfo "
				+ "					 where ownerid = '"+id+"' and password='"+pw+"'");
		try {
			conn = ds.getConnection();
			String sql ="select * "
					+ " from ownerinfo "
					+ " where ownerid = '"+id+"' and password='"+pw+"'";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				ownerid = rs.getString("ownerid");
				password = rs.getString("password");
				
				if(rs.getString("phone")!=null) {
					phone = rs.getString("phone");

				}
				else {
					phone = "null";

				}
				name = rs.getString("name");
				
				if(rs.getString("profileurl")!=null) {
					profileurl = rs.getString("profileurl");
				}
				else {
					profileurl = "NoImage";
				}
				
				list = new HwDTO3(ownerid,password,phone,name,profileurl);
				
			}
		}
		catch (Exception e) {
			e.getMessage();
		}
		finally {
			disconnect();
		}
		
		
		return list;
	}

	public ArrayList<HwDTO4> Hwstore(String id, String storeid) {
		System.out.println("Hwstore 진입");
		ArrayList<HwDTO4> list = new ArrayList<HwDTO4>();
		int storeid2; int machineseq; int using; String costdate;
		/*
		 * System.out.
		 * println("select a.storeid,to_char(costdate,'yy/mm/dd') costdate,machineseq,count(*) using"
		 * + " from storehistory c,ownerinfo b,store a " +
		 * " where  a.ownerid = b.ownerid and " +
		 * "           a.storeid = c.storeid    and " +
		 * "           a.ownerid = '"+id+"'         and " +
		 * "           to_char(costdate,'yy/mm/dd') =  to_char(sysdate,'yy/mm/dd')    and "
		 * + "           a.storeid = "+storeid+ "" +
		 * " group by machineseq,a.storeid,to_char(costdate,'yy/mm/dd')       " +
		 * " order by machineseq") ;
		 */
		try {
			conn = ds.getConnection();
			String sql = "select a.storeid,to_char(costdate,'yy/mm/dd') costdate,machineseq,count(*) using "
					+ " from storehistory c,ownerinfo b,store a "
					+ " where  a.ownerid = b.ownerid and "
					+ "           a.storeid = c.storeid    and "
					+ "           a.ownerid = '" +id+"'         and "
					+ "           to_char(costdate,'yy/mm/dd') =  to_char(sysdate,'yy/mm/dd')    and "
					+ "           a.storeid = "+storeid+ ""
					+ " group by machineseq,a.storeid,to_char(costdate,'yy/mm/dd') "
					+ " order by machineseq ";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				storeid2 = rs.getInt("storeid");
				System.out.println("storeid :" + storeid2);
				machineseq = rs.getInt("machineseq");
				System.out.println("machineseq :" + machineseq);
				using = rs.getInt("using");
				System.out.println("using : " + using);
				costdate = rs.getString("costdate");
				System.out.println("costdate : " + costdate);
				//HwDTO4(int storeid, int machineseq, int using, String costdate)
				list.add(new HwDTO4(storeid2,machineseq,using,costdate));
				System.out.println("안 list.size : " + list.size());
			}
			System.out.println("나온 list.size : " + list.size());
		} 
		catch (Exception e) {
			e.getMessage();
		}
		finally {
			disconnect();
		}
		
		return list;
	}
}
