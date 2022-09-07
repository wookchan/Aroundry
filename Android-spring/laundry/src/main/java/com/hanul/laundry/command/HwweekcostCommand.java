package com.hanul.laundry.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.hanul.laundry.dao.HwDAO;
import com.hanul.laundry.dto.HwDTO2;

public class HwweekcostCommand implements AnCommand {

	@Override
	public void execute(Model model) {
		// ownerid를 넘겨받음
		String id = (String) model.asMap().get("ownerid");

		//System.out.println(model);
		//System.out.println("Hwweekcostcommand: "+id);
		
		HwDAO dao = new HwDAO();
		
		ArrayList<HwDTO2> dtos = dao.Hwweekcost(id);
		//System.out.println(dtos.size());
		model.addAttribute("Hwweekcost",dtos);
		//System.out.println("command:"+model);
	}

}
