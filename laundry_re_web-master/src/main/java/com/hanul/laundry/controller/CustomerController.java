package com.hanul.laundry.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import common.CommonService;
import customer.CustomerInfoServiceImpl;
import customer.CustomerServiceImpl;
import customer.CustomerVO;
import member.MemberVO;
import user.UserServiceImpl;
import user.UserVO;

@Controller
public class CustomerController {
	
	@Autowired private CommonService common;
	@Autowired private CustomerServiceImpl service;	//가맹점주
	@Autowired private UserServiceImpl services; //이용자의 정보	
	

	
	//점주별 고객상세정보 목록 가져오기	
	@RequestMapping("/detail.cu")
	
//	 	public String detail( Model model, HttpSession session,@RequestParam(defaultValue = "0") int storeindex ) {		
		public String detail( Model model, HttpSession session,@RequestParam(defaultValue = "0") int storeid ) {		
			//public String detail(Integer storeid, Model model) { 
		MemberVO owner = (MemberVO)session.getAttribute("loginInfo");
		if( owner==null) return "redirect:login";
		
		String ownerid = owner.getOwnerid();
//		String ownerid = "1";
//		String ownerid = "testtest9";
		
		List<CustomerVO> storelist = service.customer_storelist(ownerid);
		model.addAttribute("store", storelist);
		/* model.addAttribute("profile", profile); */
		
		storeid = storeid==0 ? storelist.get(0).getStoreid() : storeid;
		model.addAttribute("storeid",  storeid);
//		model.addAttribute("storeindex", storeindex);
		
//		int storeid = storelist.get(storeindex).getStoreid();
		/*
		 * model.addAttribute("detail", service.customer_detail(storeid));
		 * model.addAttribute("info", services.customer_info(storeid));
		 */
		  model.addAttribute("detail", service.customer_detail(storeid));
		  model.addAttribute("info", service.customer_store(storeid));
		  
		  //월간매출그래프
		  //{ x: new Date(2022, 05, 1) , y: 15000, label: "6월", indexLabel: '15000', markerType: "triangle", markerColor: "#6B8E23" },
		  model.addAttribute("graph", service.store_monthlycost(storeid));
		  
		   		
		return "customer/detail";
		
	}

	//공지글 상세화면 요청
	@RequestMapping("/profile")
	public String detail(CustomerVO csvo, Model model) {
		
		//선택한 공지글정보를 DB에서 조회해와
		UserVO vo = services.user_detail(csvo);
		//화면에 출력할 수 있도록 Model 에 attribute로 담는다
		model.addAttribute("vo", vo);
		//줄바꿈처리를 할 수 있도록 한다
		model.addAttribute("crlf", "\r\n"); //carriage return + line feed		
		//응답화면연결 - 상세화면
		return "customer/profile";
	}


	
}
