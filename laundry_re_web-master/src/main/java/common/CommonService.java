package common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.HtmlEmail;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CommonService {
	
	public Map<String, Object> json_requestAPI(StringBuffer url) {
		JSONObject json = new JSONObject(requestAPI(url));
		json = json.getJSONObject("response");
		json = json.getJSONObject("body");
		int count = 0;
		if( json.has("totalCount"))
			count=json.getInt("totalCount");
		json = json.getJSONObject("items");
		json.put("total", count);
		return json.toMap();
	}
	
	//첨부파일 다운로드처리
	public void fileDownload(String filename, String filepath
							, HttpServletRequest request
							, HttpServletResponse response
							, HttpSession session) {
		//다운로드할 파일객체 생성
		//특정 위치에 있는 경우
		String app = "d://app" + request.getContextPath();
		//application 내부에 있는 경우
		String resources = session.getServletContext()
							.getRealPath("resources");
		File file = new File( app+ "/" + filepath );
		//다운로드할 파일의 마임타입을 지정
		String mime 
			= session.getServletContext().getMimeType(filename);
		response.setContentType(mime);
		
		try {
			filename = URLEncoder.encode(filename, "utf-8");
			response.setHeader("content-disposition"
								, "attachment; filename="+filename);
			
			ServletOutputStream out = response.getOutputStream();
			FileCopyUtils.copy( new FileInputStream(file), out);	
		}catch(Exception e) {
		}
	}
	
	
	//첨부파일 업로드처리
	public String fileUpload(String category, MultipartFile file
							, HttpSession session
							, HttpServletRequest request) {
		//파일을 업로드할 물리적 위치: 특정 폴더를 지정해서
		String app =  "d://app" + request.getContextPath();
		String folder = app 
					+ "/upload/" + category 
					+ new SimpleDateFormat("/yyyy/MM/dd").format(new Date());
		/*
		//파일을 업로드할 물리적 위치: application내부에
		//D:\Study_Spring\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\iot\resources
		String resources 
		= session.getServletContext().getRealPath("resources");
		//..resources/upload/notice/2022/07/05/abc.txt
		//..resources/upload/board/2022/07/06/abc.txt
		//..resources/upload/myinfo/2022/07/05/abc.txt
		String folder 
		= resources + "/upload/" + category 
		+ new SimpleDateFormat("/yyyy/MM/dd").format(new Date()) ;
		 */
		
		File dir = new File( folder );
		if( ! dir.exists() ) dir.mkdirs();
		
		//실제로 서버에 파일을 업로드할때는 id 를 부여하여 업로드한다
		String uuid = UUID.randomUUID().toString() 
					+ "_" + file.getOriginalFilename() ;
		//afgr7834-fgjklr234j_abc.txt
		try {
			file.transferTo( new File(folder, uuid) );
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		//folder: D://..resources/upload/myinfo/2022/07/05
		//resources: D://..resources
		//upload/notice/2022/07/05/afgr7834-fgjklr234j_abc.txt
		//d://app/iot/upload/notice/2022/07/05/afgr7834-fgjklr234j_abc.txt
//		return folder.substring(resources.length()+1) + "/" + uuid;
		return folder.substring(app.length()+1) + "/" + uuid;
	}
	
	
	//RestAPI요청
	public String requestAPI(StringBuffer url, String property) {
		String result = null;
		try {
			HttpURLConnection con 
			= (HttpURLConnection)
			new URL(url.toString()).openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Authorization", property);
			
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if(responseCode==200) { // 정상 호출
				br = new BufferedReader(
						new InputStreamReader(con.getInputStream(), "utf-8"));
			} else {  // 에러 발생
				br = new BufferedReader(
						new InputStreamReader(con.getErrorStream(), "utf-8"));
			}
			String inputLine;
			StringBuffer res = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				res.append(inputLine);
			}
			br.close();
			con.disconnect();
			if(responseCode==200) {
				result = res.toString();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
	//RestAPI요청
	public String requestAPI(StringBuffer url) {
		String result = null;
	    try {
	        HttpURLConnection con 
	        	= (HttpURLConnection)
	        		new URL(url.toString()).openConnection();
	        con.setRequestMethod("GET");
	        int responseCode = con.getResponseCode();
	        BufferedReader br;
	        if(responseCode==200) { // 정상 호출
	          br = new BufferedReader(
	        		  new InputStreamReader(con.getInputStream(), "utf-8"));
	        } else {  // 에러 발생
	          br = new BufferedReader(
	        		  new InputStreamReader(con.getErrorStream(), "utf-8"));
	        }
	        String inputLine;
	        StringBuffer res = new StringBuffer();
	        while ((inputLine = br.readLine()) != null) {
	          res.append(inputLine);
	        }
	        br.close();
	        con.disconnect();
	        if(responseCode==200) {
	          result = res.toString();
	        }
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	    }
	    return result;
	}
	
	//회원가입축하 이메일전송하기(누구에게 어떤파일을 첨부해서 메일전송)
	public void sendWelcomeJoin(String email
							, String name, String attach) {
		HtmlEmail mail = new HtmlEmail();
		mail.setHostName("smtp.naver.com");
		mail.setCharset("utf-8");
		mail.setDebug(true);
		
		//로그인하기위한 아이디,비번 입력하기
		mail.setAuthentication("관리자이메일아이디", "관리자이메일비번");
		mail.setSSLOnConnect(true); //로그인버튼 클릭하기
		
		try {
			mail.setFrom("관리자이메일주소", "IoT과정 관리자");
			mail.addTo(email, name);
			
			mail.setSubject("한울 IoT 융합SW 개발자 과정 가입 축하");
			StringBuffer msg = new StringBuffer();
			msg.append("<html>");
			msg.append("<body>");
			msg.append("<h3><a target='_blank' href='http://hanuledu.co.kr/'>한울</a></h3>");
			msg.append("<hr>");
			msg.append("<p>한울 IoT 융합SW 개발자 과정 가입을 축하합니다!</p>");
			msg.append("<p>프로젝트까지 잘 마무리합시다</p>");
			msg.append("<p>입교시 첨부된 파일을 확인하신후 등교하시기 바랍니다</p>");			
			msg.append("</body>");
			msg.append("</html>");
			mail.setHtmlMsg(msg.toString());
			
			EmailAttachment file = new EmailAttachment();
			file.setPath(attach); //입교관련 첨부할 파일선택
			mail.attach(file); //파일첨부버튼 클릭
			
			mail.send(); //메일전송버튼 클릭
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	
	//재발급 비밀번호전송하기
	public String resetPassword(String id, String name, String email) {
		HtmlEmail mail = new HtmlEmail();
		mail.setHostName("smtp.naver.com");
		mail.setDebug(true);
		mail.setCharset("utf-8");
		
		mail.setAuthentication("ojink2", "");
//		mail.setAuthentication("관리자이메일아이디", "해당이메일아이디의비번");
		mail.setSSLOnConnect(true); //로그인버튼클릭하기
		
		String pw = "";
		try {
			//메일송신자
			mail.setFrom("ojink2@naver.com", "관리자");			
//			mail.setFrom("관리자이메일주소:관리자이메일아이디@naver.com", "관리자");			
			mail.addTo(email, name); //메일수신자
			
			//임시비밀번호로 사용할 랜덤문자열 생성
			pw = UUID.randomUUID().toString();
			//adfaf-ger7798af-adgarae234da
			pw = pw.substring( pw.lastIndexOf("-")+1 );
			
			mail.setSubject(name + " 비밀번호 재발급"); //제목쓰기
			StringBuffer msg = new StringBuffer();
			msg.append("<html>");
			msg.append("<body>");
			msg.append("<h3>임시 비밀번호 발급</h3>");
			msg.append("<p>아이디: ").append(id).append(" 님</p>");
			msg.append("<p>발급된 임시 비밀번호로 로그인후 비밀번호를 변경하세요</p>");
			msg.append("<p><strong>" + pw + "</strong></p>");
			msg.append("</body>");
			msg.append("</html>");			
			mail.setHtmlMsg( msg.toString() ); //내용쓰기
			
			mail.send(); //전송하기버튼 클릭
		}catch(Exception e) {			
		}
		return pw;
	}
	
	//salt를 사용해 비밀번호를 암호화하기
	public String getEncrypt(String pw, String salt) {
		pw = pw + salt;
		//암호화방식 지정
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update( pw.getBytes() );
			byte[] digest = md.digest();
			StringBuffer sb = new StringBuffer();
			for(byte d : digest) {
				sb.append(String.format("%02x", d));
			}
			pw = sb.toString();
		}catch(Exception e) {			
		}
		return pw;
	}
	
	
	
	//비밀번호를 해시 처리하는 단방향 함수의 랜덤데이터만들기
	public String generateSalt() {
		SecureRandom r = new SecureRandom();
		byte[] salt = new byte[24];
		r.nextBytes(salt);
		
		StringBuffer buf = new StringBuffer();
		for(byte s : salt) {
			buf.append(String.format("%02x", s));
		}
		return buf.toString();
	}
	
}
