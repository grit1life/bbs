package com.cafe24.louw0.controller;

import javax.servlet.http.HttpServletRequest;
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
	 public @ResponseBody int login(Member member, HttpServletRequest request) {
		 
		 Member resultMb = memberService.checkLogin(member.getMId());
		 boolean passMatch = passEncoder.matches(member.getMPw(), resultMb.getMPw());
		 int result;
		 HttpSession session = request.getSession();
		 if(resultMb!=null && passMatch) {
			 session.setAttribute("id", resultMb.getMId());
			 session.setAttribute("nickname", resultMb.getMNickname());
			 session.setAttribute("level", resultMb.getMLevel());
			 result = 1;
		 }else {
			 result = 0;
		 }
		 return result;
	 }
	 
	
	@RequestMapping(value = "ajaxSignup", method = RequestMethod.POST)
	public @ResponseBody int ajaxSignup(@ModelAttribute Member member) {
		String pass = passEncoder.encode(member.getMPw());
		member.setMPw(pass);
		return memberService.insertMember(member);
	}
	
	@RequestMapping(value = "myPage", method = RequestMethod.GET)
	public String myPage(Model model, HttpSession session) {
		int page = 1;
		model.addAttribute("nickname", session.getAttribute("nickname"));
		String sId = (String) session.getAttribute("id");
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
		String sId = (String) session.getAttribute("id");
		Paging<Board> paging = memberService.getBookmarkBoard(sId, page);
		model.addAttribute("list", paging.getList());
		model.addAttribute("currentPage", paging.getCurrentPage());
		model.addAttribute("startPage", paging.getStartPage());
		model.addAttribute("endPage", paging.getEndPage());
		model.addAttribute("lastPage", paging.getLastPage());
		return "boardListPage";
	}
}
