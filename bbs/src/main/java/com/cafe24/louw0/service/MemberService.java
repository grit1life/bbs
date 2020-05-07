package com.cafe24.louw0.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.louw0.captcha.ApiCaptchaNkeyResult;
import com.cafe24.louw0.mapper.BoardMapper;
import com.cafe24.louw0.mapper.MemberMapper;
import com.cafe24.louw0.vo.Board;
import com.cafe24.louw0.vo.Member;
import com.cafe24.louw0.vo.Paging;
import com.google.gson.Gson;

@Service
@Transactional
public class MemberService {

	@Autowired
	BCryptPasswordEncoder passEncoder;
	
	@Autowired
	private MemberMapper memberMapper;

	@Autowired
	private BoardMapper boardMapper;
	
	public int insertMember(Member member) {
		int idCondition = memberMapper.checkId(member.getMId());
		int pwCondition = memberMapper.checkNickname(member.getMNickname());
		if(idCondition == 0 && pwCondition==0) {
			memberMapper.insertMember(member);
		}
		if(idCondition == 0 && pwCondition != 0) {
			idCondition = 2;
		}
		return idCondition;
	}
	
	public int checkLogin(Member member, HttpServletRequest request, HttpServletResponse response) {
		 Member resultMb = memberMapper.checkLogin(member.getMId());
		 boolean passMatch = passEncoder.matches(member.getMPw(), resultMb.getMPw());
		 int result;
		 Gson gson = new Gson();
		 Map map = new HashMap();
		 HttpSession session = request.getSession();
		 ApiCaptchaNkeyResult captchaResult = new ApiCaptchaNkeyResult();
		 
		 
		 String cResult = captchaResult.captchakeyResult(member.getCaptchaKey(), member.getCaptchaValue());
		 map = gson.fromJson(cResult, Map.class);

		 if(resultMb!=null && passMatch && map.get("errorMessage")==null) {
			 String sId = resultMb.getMId();
			 String nickname = resultMb.getMNickname();
			 String level = resultMb.getMLevel();
			 
			 //세션 설정
			 session.setAttribute("sId", sId);
			 session.setAttribute("nickname", nickname);
			 session.setAttribute("level", level);
			 if("on".equals(member.getLoginKeep())) {
				 //쿠키 설정
				 List<Cookie> cookies = new ArrayList<Cookie>();
				 cookies.add(new Cookie("sId", sId));
				 cookies.add(new Cookie("nickname", nickname));
				 cookies.add(new Cookie("level", level));
				 
				 for(int i=0; i<cookies.size(); i++) {
					 cookies.get(i).setMaxAge(24*60*60);
					 cookies.get(i).setPath("/");
					 response.addCookie(cookies.get(i));
				 }
			 }
			 result = 1;

		 }else {
			 result = 0;
		 }
		 
		return result;
	}
	
	//북마크 게시판 목록
	public Paging<Board> getBookmarkBoard(String mId, int page){
		int column = (page-1)*10; 
		Member member = new Member();
		member.setMId(mId);
		member.setColumn(column);
		int cnt = boardMapper.getBookmarkBoardCnt(mId);
		List<Board> list = boardMapper.getBookmarkBoard(member);
		Paging<Board> paging = new Paging<Board>(list, page, cnt);
		return paging;
	}
}
