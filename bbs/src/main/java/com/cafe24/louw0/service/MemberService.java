package com.cafe24.louw0.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.louw0.mapper.MemberMapper;
import com.cafe24.louw0.vo.Member;

@Service
@Transactional
public class MemberService {

	@Autowired
	private MemberMapper memberMapper;
	
	public int insertMember(Member member) {
		int idCondition = memberMapper.checkId(member.getMId());
		System.out.println(idCondition);
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
	
}
