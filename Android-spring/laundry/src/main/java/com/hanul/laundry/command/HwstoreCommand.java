package com.hanul.laundry.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.hanul.laundry.dao.HwDAO;
import com.hanul.laundry.dto.HwDTO2;
import com.hanul.laundry.dto.HwDTO4;

public class HwstoreCommand implements AnCommand {

	@Override
	public void execute(Model model) {

		String id = (String) model.asMap().get("ownerid");
		String storeid = (String) model.asMap().get("storeid");
		HwDAO dao = new HwDAO();
		
		ArrayList<HwDTO4> dtos = dao.Hwstore(id,storeid);
		model.addAttribute("Hwstore", dtos);
	}

}
