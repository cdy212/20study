package com.study20.web.part0.controller;

import java.io.File;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.CookieGenerator;

import com.study20.web.cmm.util.MessageCode;
import com.study20.web.cmm.util.MessageSetter;
import com.study20.web.part0.dto.BoardDto;

@Controller
public class RestLoginController {
	
	@Resource(name="MessageSetter")
	private MessageSetter messageSetter;
	
//	@Resource(name="LoginService")
//	private LoginService loginService;
//	
//	@Resource(name = "BoardService")
//	private BoardService boardService;
	
	

	@RequestMapping(value="/file.do")
	public @ResponseBody String file(MultipartFile images,HttpServletRequest request) throws Exception {
		System.out.println(images.getName());
		System.out.println(images.getOriginalFilename());
		  //String path ="/Users/choedaeyeol/git/SpringArchitecture/src/main/webapp/resources/fileUpload/"+images.getOriginalFilename();
		String path ="/data/upload/"+images.getOriginalFilename();
		  //업로드 될 디렉토리
			
		  File dir    = new File(path);
		  
		  if(!dir.exists()){
		   //업로드 디렉 토리가 없을 경우 만듦
		   dir.mkdirs();
		  }
		  
		images.transferTo(dir);
		return images.getOriginalFilename();
		
	}
	
	
	@RequestMapping(value="/gelery.do")
	public String gelery(HttpServletRequest request) throws Exception {
		return "android/gelery";
	}
	
	@RequestMapping(value="/test.do")
	public @ResponseBody String test(HttpServletRequest request,HttpServletResponse response) throws Exception {
		CookieGenerator cg = new CookieGenerator();

		cg.setCookieName("cookieName");
		cg.addCookie(response, "value");
		
		cg.setCookieName("name2");
		cg.addCookie(response, "haha value");
		
		System.out.println(request.getParameter("loginId"));
		System.out.println(request.getParameter("loginPw"));
		return "1";
	}
	
	
	@RequestMapping(value="/rest_login.do")
	public @ResponseBody String login(HttpServletRequest request,HttpServletResponse response) throws Exception {
		CookieGenerator cg = new CookieGenerator();

		cg.setCookieName("cookieName");
		cg.addCookie(response, "value");
		
		cg.setCookieName("name2");
		cg.addCookie(response, "haha value");
		
		System.out.println(request.getParameter("loginId"));
		System.out.println(request.getParameter("loginPw"));
		return "1";
	}

	
	
	@RequestMapping(value="/authorization.do")
	public @ResponseBody String authorization( @RequestHeader(value = "Authorization") String credentials, HttpServletRequest request,HttpServletResponse response) throws Exception {
		System.out.println(credentials+"<<=============================>>");
		CookieGenerator cg = new CookieGenerator();

		cg.setCookieName("cookieName");
		cg.addCookie(response, "id");
		
		cg.setCookieName("name2");
		cg.addCookie(response, "haha");
		
		System.out.println(request.getParameter("loginId"));
		System.out.println(request.getParameter("loginPw"));
		return "1";
	}

	
	@RequestMapping(value="/pushRegid.do")
	public @ResponseBody String pushRegid(@RequestParam String regId,@RequestParam String userInfo) throws Exception {
		System.out.println(URLDecoder.decode(userInfo,"UTF-8"));
		System.out.println(regId);
		return "1";
	}

	public static List<BoardDto> arlBoardDtos = new ArrayList<BoardDto>();
	
	@RequestMapping(value="/smsInfo.do")
	public String pushRegid(@RequestParam String regId,@RequestParam String message,@RequestParam String from,Model model) throws Exception {
		System.out.println(from);
		System.out.println(URLDecoder.decode(message,"UTF-8"));
		System.out.println(regId);
		
		BoardDto boardDto = new BoardDto();
		boardDto.setName(from);
		boardDto.setTitle(URLDecoder.decode(message,"UTF-8"));
		boardDto.setContent(regId);
		arlBoardDtos.add(boardDto);
		model.addAttribute("boardList", arlBoardDtos);
		
		return "android/boardList";
	}
	
	
	@RequestMapping(value="/android/{a}/{b}/map.do")
	public String map(@PathVariable String a,@PathVariable String b,Model model) throws Exception {
		model.addAttribute("a",a);
		model.addAttribute("b",b);
		
		return "android/pushMap";
	}


	
	
//	@RequestMapping(value="/mobile_login.json", method=RequestMethod.POST)
//	public String getLoginValid(HttpServletRequest request, Model model) throws Exception {
//		String getUserId = request.getParameter("loginId");
//		String getUserPassword = request.getParameter("loginPw");
//		
//		HashMap<String,String> loginIdPw = new HashMap<String,String>();
//		loginIdPw.put("userId", getUserId);
//		loginIdPw.put("userPw", getUserPassword);
//		
//		boolean isValidLogin = loginService.validLogin(loginIdPw);
//		if ( isValidLogin ) {
//			SessionUtil.set(request);
//			messageSetter.message0000(model, "");
//		}
//		else {
//			messageSetter.messageSet(model, MessageCode.result1000, null, "");
//		}
//		
//		return "jsonViewer";
//	}
}
