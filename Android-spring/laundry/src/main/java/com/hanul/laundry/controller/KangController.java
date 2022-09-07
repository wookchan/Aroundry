package com.hanul.laundry.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.hanul.laundry.command.AnCommand;
import com.hanul.laundry.dao.KangDao;
import com.hanul.laundry.dao.TimeDao;
import com.hanul.laundry.dao.khjDao;
import com.hanul.laundry.dto.MainMyWashDTO;
import com.hanul.laundry.dto.MainWashAllDTO;
import com.hanul.laundry.dto.UserDTO;
import com.hanul.laundry.dto.khjCleanDTO;


@Controller
public class KangController {
	
	AnCommand command;
	
	@ResponseBody  @RequestMapping(value="/kangJo", method = {RequestMethod.GET, RequestMethod.POST}, produces="text/plain; charset=utf-8")
	public String kangJoin(HttpServletRequest req, Model model) {
		System.out.println("KangJoin");
		
		// 1. 안드로이드에서 보낸 데이터를 req로 받아서 변수에 저장
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		String id = req.getParameter("id");
		String profile = req.getParameter("profile");	
		
		
		// 2. 찍어봅시다
		System.out.println(name + ", " + email + ", " + phone + ", " +  id + ", " + profile );					

		//2022.08.18 소셜로그인시 회원정보 처리 변경 by 조순섭
		KangDao dao = new KangDao();
		UserDTO userdto = dao.kangJoin(id, name, email, phone, profile); 
		
		System.out.println(userdto.getUserid() + ", " + userdto.getName() + ", " + userdto.getEmail() + ", " + userdto.getPhone() + ", " 
				+  userdto.getProfile() + ", " + userdto.getPoint() );
		
		
		model.addAttribute("kangJoin", userdto);		
		
		
		HashMap<String, String> bookmark = new KangDao().bookmark_store(id); //즐겨찾기 store목록 조회		
		HashMap<String, String> datas = new HashMap<String, String>();
		datas.put("user", new Gson().toJson(userdto) );
		datas.put("bookmark", new Gson().toJson(bookmark));
		String result = new Gson().toJson( datas );
		
		
		// 응답을 위해 views에서 anJoin.jsp를 찾아 실행한다.
		return result;
//		return "kangJoin";
		
	}
	
	@ResponseBody  @RequestMapping(value="/moneycharge", method = {RequestMethod.GET, RequestMethod.POST}, produces="text/plain; charset=utf-8")
	public String moneycharge(HttpServletRequest req, Model model) {
		System.out.println("moneycharge");
		
		// 1. 안드로이드에서 보낸 데이터를 req로 받아서 변수에 저장
		String userid = req.getParameter("userid");
		String money = req.getParameter("money");
		
		// 2. 찍어봅시다
		System.out.println(userid + ", " + money );					

		//2022.08.18 소셜로그인시 회원정보 처리 변경 by 조순섭
		KangDao dao = new KangDao();
		UserDTO userdto = dao.moneycharge(userid, money); 
		
		System.out.println(userdto.getUserid() + ", " + userdto.getName() + ", " + userdto.getEmail() + ", " + userdto.getPhone() + ", " 
				+  userdto.getProfile() + ", " + userdto.getPoint() );
		
		
		model.addAttribute("moneycharge", userdto);		
		
		
		HashMap<String, String> bookmark = new KangDao().bookmark_store(userid); //즐겨찾기 store목록 조회		
		HashMap<String, String> datas = new HashMap<String, String>();
		datas.put("user", new Gson().toJson(userdto) );
		datas.put("bookmark", new Gson().toJson(bookmark));
		String result = new Gson().toJson( datas );
		
		
		// 응답을 위해 views에서 anJoin.jsp를 찾아 실행한다.
		return result;
//		return "kangJoin";
		
	}
	
	
	@ResponseBody  @RequestMapping(value="/mainwashall", method = {RequestMethod.GET, RequestMethod.POST}, produces="text/plain; charset=utf-8")
	public String mainwashall(HttpServletRequest req, Model model) {
		System.out.println("mainwashall");
		
		// 1. 안드로이드에서 보낸 데이터를 req로 받아서 변수에 저장
		String storeid = req.getParameter("storeid");
		
		// 2. 찍어봅시다
		System.out.println(storeid);					

		//2022.08.18 소셜로그인시 회원정보 처리 변경 by 조순섭
		KangDao dao = new KangDao();
		ArrayList<MainWashAllDTO> washAllDtos = dao.mainwashall(storeid); 
		
		System.out.println(washAllDtos.get(0).getMachineid());
		
		Gson gson = new Gson();
		String json = gson.toJson(washAllDtos);
		
		return json;
		
		//model.addAttribute("washAllDtos", washAllDtos);		
		
		
		/*
		 * HashMap<String, String> bookmark = new KangDao().bookmark_store(id); //즐겨찾기
		 * store목록 조회 HashMap<String, String> datas = new HashMap<String, String>();
		 * datas.put("user", new Gson().toJson(userdto) ); datas.put("bookmark", new
		 * Gson().toJson(bookmark));
		 */
		//String result = new Gson().toJson( ArrayList<MainWashAllDTO> );
		
		
		// 응답을 위해 views에서 anJoin.jsp를 찾아 실행한다.
		//return result;

		
	}
	
	@ResponseBody  @RequestMapping(value="/mainwashmy", method = {RequestMethod.GET, RequestMethod.POST}, produces="text/plain; charset=utf-8")
	public String mainwashmy(HttpServletRequest req, Model model) {
		System.out.println("mainwashmy");
		
		// 1. 안드로이드에서 보낸 데이터를 req로 받아서 변수에 저장
		String userid = req.getParameter("userid");
		
		// 2. 찍어봅시다
		System.out.println(userid);					

		//2022.08.18 소셜로그인시 회원정보 처리 변경 by 조순섭
		KangDao dao = new KangDao();
		ArrayList<MainMyWashDTO> washMyDtos = dao.mainwashmy(userid); 
		
		System.out.println(washMyDtos.get(0).getMachineid());
		
		Gson gson = new Gson();
		String json = gson.toJson(washMyDtos);
		
		return json;
		
		//model.addAttribute("washAllDtos", washAllDtos);		
		
		
		/*
		 * HashMap<String, String> bookmark = new KangDao().bookmark_store(id); //즐겨찾기
		 * store목록 조회 HashMap<String, String> datas = new HashMap<String, String>();
		 * datas.put("user", new Gson().toJson(userdto) ); datas.put("bookmark", new
		 * Gson().toJson(bookmark));
		 */
		//String result = new Gson().toJson( ArrayList<MainWashAllDTO> );
		
		
		// 응답을 위해 views에서 anJoin.jsp를 찾아 실행한다.
		//return result;

		
	}
	
	@ResponseBody  @RequestMapping(value="/mainwashiotval", method = {RequestMethod.GET, RequestMethod.POST}, produces="text/plain; charset=utf-8")
	public String mainwashiotval(HttpServletRequest req, Model model) {
		System.out.println("mainwashiotval");
		
		// 1. 안드로이드에서 보낸 데이터를 req로 받아서 변수에 저장
		String userid = req.getParameter("userid");
		String seq = req.getParameter("seq");
		// val은 아두이노에게 넘겨줌
		String val = req.getParameter("val");
		
		// 2. 찍어봅시다
		System.out.println(userid);					
		System.out.println(seq);					
		System.out.println(val);	
		
		TimeDao dao = new TimeDao();
		// userid, seq, restTime은 생성하여 machineState 에 등록함 
		String restTime = "60";
		String money = dao.kangTime(userid, seq, restTime);		
		
		String iotval = "[{seq:" + seq   + ",val:" + val + "}]";
		System.out.println(iotval);
		
		Gson gson = new Gson();
		String json = gson.toJson(iotval);
		
		// 아두이노로 값보내기  	
		 
	    BufferedReader in = null; 
	    try { 
	   	 URL obj = new URL("http://192.168.0.107/bb " + json + "?"); // 호출할 url 
		     HttpURLConnection con = (HttpURLConnection)obj.openConnection(); 
		     con.setRequestMethod("GET"); 
		     con.setReadTimeout(2000);
		     in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8")); 
		     
		     String line; 
		     while((line = in.readLine()) != null) { // response를 차례대로 출력 
		    	 System.out.println(line); 
		     }  
		     con.disconnect();
	    } catch(Exception e) { 
	   	 	e.printStackTrace(); 
		} finally { 
			 if(in != null) {
				 try { 
					 in.close(); 
				 } catch(Exception e) {	 
					 e.printStackTrace(); 
				 } 
			 }
	    }     
		
		return money;

		
	}

	
}
	
