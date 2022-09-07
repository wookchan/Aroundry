package com.hanul.laundry.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import common.CommonService;
import member.MemberServiceImpl;
import member.MemberVO;
import store.StoreServiceImpl;
import store.StoreVO;

@Controller
public class MemberController {
	@Autowired private MemberServiceImpl service;
	@Autowired private CommonService common;
	@Autowired private StoreServiceImpl storeService;
	
	//회원가입처리 요청
	@RequestMapping(value="/join", produces="text/html; charset=utf-8") 
	@ResponseBody
	public String join(MemberVO vo, StoreVO store,  HttpSession session, HttpServletRequest req,MultipartFile profile, MultipartFile file[]) {
		String http = req.getRequestURL().toString().replace(req.getServletPath(), "") + "/" ;
		//첨부파일 이미지 5개를 imageurl 필드에 한꺼번에 담는다
		String upload = "";
		for( MultipartFile f : file ) {	
			if( f.isEmpty() ) continue;
			upload += (upload.equals("") ? "" : ",") + http +common.fileUpload("store", f, session, req);			
		}
		store.setImageurl(upload);
		//http://192.168.0.7:8080/project/upload/store/2022/08/20/54e6085b-a184-4c54-9a78-45dadd58239a_bubble.gif
		
		if(!profile.isEmpty()) {
			vo.setProfileurl(http +	common.fileUpload("profile", profile, session, req));
			
		}
		
		
		//화면에서 입력한 정보를 DB에 신규저장한 후
		StringBuffer msg = new StringBuffer("<script>");
		if( service.member_join(vo) ) {
			storeService.store_join(store);
					
			//회원가입 성공여부를 alert으로 띄운 후
			msg.append("alert('회원가입을 축하합니다^^'); location='")
				.append( req.getContextPath() ).append("'; ");
		}else {
			msg.append("alert('회원가입 실패ㅠㅠ'); history.go(-1);");
		}
		msg.append("</script>");
		
		//응답화면 연결 - 웰컴
		return msg.toString();

	}
	
	
	//아이디 중복확인 요청
	@ResponseBody @RequestMapping("/id_check")
	public boolean id_check(String id) {
		//화면에서 입력한 아이디가 DB에 존재하는지 확인하여
		//존재여부를 ajax통신쪽으로 가지고 돌아간다
		//0 ? false(사용가능아이디) : true(이미사용중인아이디)
		return service.member_id_check(id);
	}
	
	
	/*
	 * //회원가입화면 요청
	 * 
	 * @RequestMapping("/member") public String member(HttpSession ss) {
	 * ss.setAttribute("category", "join"); return "member/join"; }
	 */
	
	//로그인처리 요청
			@ResponseBody @RequestMapping("/laundry_login")
			public boolean login(String id, String pw, HttpSession session) {
				//회원id에 해당하는 salt를 조회
				//String salt = service.member_salt(id);
				//salt 를 사용해 로그인시 입력한 비밀번호를 암호화한 후 
				//암호화 한 비밀번호를 사용해 로그인한다
				//pw = common.getEncrypt(pw, salt);
				
				//화면에서 입력한 아이디와 비밀번호가 일치하는 회원정보가 DB에 있는지 조회한 후(비지니스로직)
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("id", id);
				map.put("pw", pw);
				MemberVO vo = service.member_login(map);
				
				//일치하는 회원정보를 세션에 attribute로 담는다
				session.setAttribute("loginInfo", vo);
				
				//로그인여부를 응답한다.
				return vo==null ? false : true;
			}
}
