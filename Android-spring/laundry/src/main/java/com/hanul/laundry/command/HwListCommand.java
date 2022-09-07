package com.hanul.laundry.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.hanul.laundry.dao.HwDAO;
import com.hanul.laundry.dto.HwDTO;

public class HwListCommand implements AnCommand{

	@Override
	public void execute(Model model) {
		String id = (String) model.asMap().get("ownerid");
		
		//System.out.println(model);
		//System.out.println("hwlistcommand: "+id);
		
		HwDAO dao = new HwDAO();
		
		ArrayList<HwDTO> dtos = dao.Hwlist(id);
		//System.out.println(dtos.size());
		model.addAttribute("Hwlist",dtos);
		//System.out.println("command:"+model);
	
	}

}
