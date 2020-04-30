package com.cafe24.louw0.mapper;

import org.springframework.stereotype.Repository;

import com.cafe24.louw0.vo.Member;

@Repository(value = "memberMapper")
public interface MemberMapper {
	//회원가입
	public int checkId(String mId);
	public int checkNickname(String nickname);
	public int insertMember(Member member);
	
	//로그인
	public Member checkLogin(String mId);
	
}
