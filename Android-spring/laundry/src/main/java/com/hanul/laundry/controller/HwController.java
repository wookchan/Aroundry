package com.hanul.laundry.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hanul.laundry.command.AnCommand;
import com.hanul.laundry.command.HwListCommand;
import com.hanul.laundry.command.HwmonthcostCommand;
import com.hanul.laundry.command.HwownerloginCommand;
import com.hanul.laundry.command.HwstoreCommand;
import com.hanul.laundry.command.HwweekcostCommand;
import com.hanul.laundry.controller.HwController;

@Controller
public class HwController {
	private static final Logger logger = LoggerFactory.getLogger(HwController.class);
	AnCommand command;
	
	//사업장 오늘자 수입 보여주는곳
	@RequestMapping(value="/Hwstore")
	public String Hwstore(HttpServletRequest req,Model model) {
		System.out.println("connect : Hwstore()");
		String id = (String)req.getParameter("id");
		String storeid = (String)req.getParameter("storeid");
		command = new HwstoreCommand();
		model.addAttribute("ownerid",id);
		model.addAttribute("storeid",storeid);
		command.execute(model);
		return "Hwstore";
	}
	
	//로그인 
	@RequestMapping(value="/Hwownerlogin")
	public String Hwownerlogin(HttpServletRequest req,Model model) {
		System.out.println("connect : Hwownerlogin()");
		String id = (String)req.getParameter("id");
		String pw = (String)req.getParameter("pw");
		command = new HwownerloginCommand();
		model.addAttribute("ownerid",id);
		model.addAttribute("password",pw);
		command.execute(model);
		return "Hwownerlogin";
	}
	
	//목록
	@RequestMapping(value="/Hwlist",method= {RequestMethod.GET,RequestMethod.POST})
	public String Hwlist(HttpServletRequest req,Model model) {
		System.out.println("connect : Hwlist()");
		String id = (String)req.getParameter("id");
		command = new HwListCommand();
		model.addAttribute("ownerid",id);
		command.execute(model);
		//System.out.println(model);
		
		return "Hwlist";
	}
	
	//주간 
	@RequestMapping(value="/Hwweekcost",method= {RequestMethod.GET,RequestMethod.POST})
	public String Hwweekcost(HttpServletRequest req,Model model) {
		System.out.println("connect : hwweekcost");
		String id= (String)req.getParameter("id");
		command = new HwweekcostCommand();
		model.addAttribute("ownerid",id);
		command.execute(model);
		
		return "Hwweekcost";
	}
	
	//월간
	@RequestMapping(value="/Hwmonthcost",method= {RequestMethod.GET,RequestMethod.POST})
	public String Hwmonthcost(HttpServletRequest req,Model model) {
		System.out.println("connect : hwmonthcost");
		String id = (String)req.getParameter("id");
		command = new HwmonthcostCommand();
		model.addAttribute("ownerid",id);
		command.execute(model);
		
		return "Hwmonthcost";
	}
}
