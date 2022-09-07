package com.hanul.laundry.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import common.CommonService;
import store.StoreServiceImpl;
import store.StoreVO;

@Controller
public class StoreController {
	@Autowired private StoreServiceImpl service;
	@Autowired private CommonService common;
	
	//회원가입처리 요청
	@RequestMapping(value="/kjoin", produces="text/html; charset=utf-8")
	@ResponseBody
	public String kjoin(StoreVO store ,  HttpSession session, HttpServletRequest req, MultipartFile file[]) {
		
		//첨부파일 이미지 5개를 imageurl 필드에 한꺼번에 담는다
				String upload = "";
				for( MultipartFile f : file ) {	
					if( f.isEmpty() ) continue;
					upload += (upload.equals("") ? "" : ",") + common.fileUpload("store", f, session, req);			
				}
				store.setImageurl(req.getRequestURL().toString().replace(req.getServletPath(), "") + "/" +upload);
				//http://192.168.0.7:8080/project/upload/store/2022/08/20/54e6085b-a184-4c54-9a78-45dadd58239a_bubble.gif

				//화면에서 입력한 정보를 DB에 신규저장한 후
				StringBuffer msg = new StringBuffer("<script>");
		if( service.store_join(store) ) {
		
			//회원가입 성공여부를 alert으로 띄운 후
			msg.append("alert('사업장 등록에 성공했습니다'); location='")
				.append("detail.cu").append("'; ");
		}else {
			msg.append("alert('사업장 등록 오류'); history.go(-1);");
		}
		msg.append("</script>");
		//응답화면 연결 - 웰컴
//		return "customer/detail";
		return msg.toString();
	}
	
	//회원가입화면 요청
	@RequestMapping("/store")
	public String store(HttpSession ss, int storeid, Model model ) {
		ss.setAttribute("category", "kjoin");
		model.addAttribute("storeid", storeid);
		return "store/kjoin";
	}
	
	//고객정보삭제처리 요청
		@RequestMapping("/delete")
		public String delete(StoreVO store) {
			//선택한 고객정보를 DB에서 삭제한 후(비지니스로직)
			service.store_delete(store);
			//응답할 화면연결: 고객목록화면
			return "redirect:detail.cu";
		}
		
		//고객정보수정저장처리 요청
		@RequestMapping("/storeupdate")
		public String update(StoreVO store , MultipartFile[] file, String[] filename ,HttpSession session ,HttpServletRequest req) {
			StoreVO vo = service.store_info(store.getStoreid());
			String url[] = vo.getImageurl().split(",");			
			String imageurl[] = new String[5];
			
			String http = req.getRequestURL().toString().replace(req.getServletPath(), "") + "/" ;
			
			String upload = "";
			for(int i=0; i<filename.length; i++) {
				imageurl[i] = i < url.length ? url[i] : "";   
				if( !filename[i].isEmpty() && filename[i].equals(imageurl[i])) {
					upload += (upload.equals("") ? "" : ",") + filename[i];
				}else {
					if( ! file[i].isEmpty() ) {
						upload += (upload.equals("") ? "" : ",") + http + common.fileUpload("store", file[i], session, req);
					}
				}
			}
			store.setImageurl(upload);
			//http://192.168.0.7:8080/project/upload/store/2022/08/20/54e6085b-a184-4c54-9a78-45dadd58239a_bubble.gif

			//화면에서 수정입력한 정보를 DB에 변경저장한 후(비지니스로직)
			service.store_update(store);
			//응답할 화면을 연결: 상세화면으로 연결 왜..?
			return "redirect:detail.cu"; 
			
		}
		
		
		//고객정보수정화면 요청
		@RequestMapping("/storeinfo")
		public String modify(int storeid, Model model) {
			//선택한 고객의 정보를 DB에서 조회해와(비지니스로직)
			StoreVO vo = service.store_info(storeid);
			//수정화면에 출력할 수 있도록
			//Model에 데이터를 담는다
			model.addAttribute("store", vo);
			//응답화면은 수정화면으로 연결
			return "/store/storeupdate";
		}
	
	
	
}
