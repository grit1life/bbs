package com.cafe24.louw0.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.louw0.service.MemberService;
import com.cafe24.louw0.vo.Board;
import com.cafe24.louw0.vo.Member;
import com.cafe24.louw0.vo.Paging;

@Controller
public class MemberController {

	@Autowired
	BCryptPasswordEncoder passEncoder;
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value = "signup", method = RequestMethod.GET)
	public String signup() {
		return "signup";
	}
	@RequestMapping(value = "signup", method = RequestMethod.POST)
	public String signup(Member member){
		memberService.insertMember(member);
		return "signup";
	}
	
	
	 @RequestMapping(value = "login", method = RequestMethod.POST) 
	 public @ResponseBody int login(Member member, HttpServletRequest request, HttpServletResponse response) {
		 return memberService.checkLogin(member, request, response);
	 }
	 @RequestMapping(value = "logout", method = RequestMethod.GET)
	 public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		 session.invalidate();
		 Cookie[] cookies = request.getCookies();
		 for(int i=0; i<cookies.length; i++) {
			 cookies[i].setMaxAge(0);
			 cookies[i].setPath("/");
			 response.addCookie(cookies[i]);
		 }
		 
		 return "home";
	 }
	
	@RequestMapping(value = "ajaxSignup", method = RequestMethod.POST)
	public @ResponseBody int ajaxSignup(@ModelAttribute Member member, HttpSession session ) {
		String pass = passEncoder.encode(member.getMPw());
		member.setMPw(pass);
		int signupSuccess = memberService.insertMember(member);
		if(signupSuccess == 0) {
			session.setAttribute("sId", member.getMId());
			session.setAttribute("nickname", member.getMNickname());
		}
		return signupSuccess;
	}
	
	@RequestMapping(value = "myPage", method = RequestMethod.GET)
	public String myPage(Model model, HttpSession session) {
		int page = 1;
		String sId = (String) session.getAttribute("sId");
		model.addAttribute("nickname", session.getAttribute("nickname"));
		model.addAttribute("sId", session.getAttribute("sId"));
		
		Paging<Board> paging = memberService.getBookmarkBoard(sId, page);
		model.addAttribute("list", paging.getList());
		model.addAttribute("currentPage", paging.getCurrentPage());
		model.addAttribute("startPage", paging.getStartPage());
		model.addAttribute("endPage", paging.getEndPage());
		model.addAttribute("lastPage", paging.getLastPage());
		return "myPage";
	}
	@RequestMapping(value = "myPage", method = RequestMethod.POST)
	public String myPage(Model model, HttpSession session,
			@RequestParam (value = "page") int page) {
		model.addAttribute("nickname", session.getAttribute("nickname"));
		String sId = (String) session.getAttribute("sId");
		Paging<Board> paging = memberService.getBookmarkBoard(sId, page);
		model.addAttribute("list", paging.getList());
		model.addAttribute("currentPage", paging.getCurrentPage());
		model.addAttribute("startPage", paging.getStartPage());
		model.addAttribute("endPage", paging.getEndPage());
		model.addAttribute("lastPage", paging.getLastPage());
		return "boardListPage";
	}
}
