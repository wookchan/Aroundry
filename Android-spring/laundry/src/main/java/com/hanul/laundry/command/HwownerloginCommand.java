package com.hanul.laundry.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.hanul.laundry.dao.HwDAO;
import com.hanul.laundry.dto.HwDTO;
import com.hanul.laundry.dto.HwDTO3;

public class HwownerloginCommand implements AnCommand {

	@Override
	public void execute(Model model) {
		String id = (String) model.asMap().get("ownerid");
		String pw = (String) model.asMap().get("password");
		HwDAO dao = new HwDAO();
		
		HwDTO3 dtos = dao.Hwownerlogin(id,pw);
		
		model.addAttribute("Hwownerlogin",dtos);
	}

}
