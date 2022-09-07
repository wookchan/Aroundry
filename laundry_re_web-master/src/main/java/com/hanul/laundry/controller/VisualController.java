package com.hanul.laundry.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class VisualController {
	
		//시각화 화면요청
		@RequestMapping("/chart.ch")
		public String chart(HttpSession session) {
			session.setAttribute("category", "ch");
			return "visual/chart";
		}
		
}
