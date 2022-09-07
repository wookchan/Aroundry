package com.hanul.laundry.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.hanul.laundry.dao.HwDAO;
import com.hanul.laundry.dto.HwDTO2;

public class HwmonthcostCommand implements AnCommand {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		String id = (String) model.asMap().get("ownerid");
		//System.out.println("Hwmonthcost id : " + id);
		
		HwDAO dao = new HwDAO();
		
		ArrayList<HwDTO2> dtos = dao.Hwmonthcost(id);
		System.out.println("HwmonthcostCommand: "+ dtos.size());
		model.addAttribute("Hwmonthcost",dtos);
		
	}

}
