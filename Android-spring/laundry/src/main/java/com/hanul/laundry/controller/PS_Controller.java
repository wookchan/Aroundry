package com.hanul.laundry.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.hanul.laundry.command.AnCommand;
import com.hanul.laundry.command.PS_QRCommand;
import com.hanul.laundry.command.PS_SearchCommand;
import com.hanul.laundry.command.PS_SearchCommand3;

@Controller
public class PS_Controller {
	
	AnCommand command;	

	@RequestMapping(value="/anSearch", method = {RequestMethod.GET, RequestMethod.POST})
	public String psSearch(HttpServletRequest req, Model model) {
		System.out.println("anSearch");
		
		// 1. 안드로이드에서 보낸 데이터를 req로 받아서 변수에 저장	
				// 2. 찍어봅시다
				// 3. 안드로이드에서 보낸 파일 받기 : 파일을 보낸 경우에만 실행
				// 파일이름만 저장해 놓고 안드로이드에서 받아서 전체 경로를 완성한다	
				// 4. AnJoinCommand 에게 model에 담아서 데이터를 보내준다.
				// 5. AnJoinCommand를 생성해서 데이터가 담긴 model을 넘겨준다.
		command = new PS_SearchCommand();
		command.execute(model);				
		
		// 응답을 위해 views에서 anLogin.jsp를 찾아 실행한다.
		return "anSearch";
		
	}

	
	@RequestMapping(value="/psQRCode", method = {RequestMethod.GET, RequestMethod.POST})
	public String psQRCode(HttpServletRequest req, Model model) {
		System.out.println("psQRCode");
		
		// 1. 안드로이드에서 보낸 데이터를 req로 받아서 변수에 저장
		String name = req.getParameter("name");
		//String name = "강민재";
		// 2. 찍어봅시다
		System.out.println("name: " + name );
					
		// 3. 안드로이드에서 보낸 파일 받기 : 파일을 보낸 경우에만 실행
		// 파일이름만 저장해 놓고 안드로이드에서 받아서 전체 경로를 완성한다		
			
		
		// 4. AnJoinCommand 에게 model에 담아서 데이터를 보내준다.
		model.addAttribute("name", name);
		
		// 5. AnJoinCommand를 생성해서 데이터가 담긴 model을 넘겨준다.
		command = new PS_QRCommand();
		command.execute(model);				
		
		// 응답을 위해 views에서 anLogin.jsp를 찾아 실행한다.
		return "psQRCode";
		
	}
	
	@RequestMapping(value="/anSearch3", method = {RequestMethod.GET, RequestMethod.POST} )
	public String anBookmark(HttpServletRequest req, Model model, PS_SearchCommand3 command) {
		System.out.println("anSearch3()");
		
		//1. 안드로이드에서 보낸 값을 req로 받아서 변수에 저장
		String userid = (String) req.getParameter("userid");	
		
		//2. 한번 찍어본다
		System.out.println(userid);	
		
		//3. command 에서 사용하기 위해 model에 저장
		model.addAttribute("userid", userid);
		
		System.out.println("check1");
		//4. AnJoinCommand를 생성해서 모델에 담긴 데이터를 넘겨준다
		command = new PS_SearchCommand3();
		System.out.println("check2");
		command.execute(model);
		System.out.println("check3");
		
		//11. 응답을 위해 views에서 anJoin.jsp를 찾아 실행한다.
		return "anSearch3";
				 
		
	}
	
}
