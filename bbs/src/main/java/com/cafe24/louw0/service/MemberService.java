package com.cafe24.louw0.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.louw0.mapper.BoardMapper;
import com.cafe24.louw0.mapper.MemberMapper;
import com.cafe24.louw0.vo.Board;
import com.cafe24.louw0.vo.Member;
import com.cafe24.louw0.vo.Paging;

@Service
@Transactional
public class MemberService {

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
	
	public Member checkLogin(String mId) {
		return memberMapper.checkLogin(mId);
	}
	

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
