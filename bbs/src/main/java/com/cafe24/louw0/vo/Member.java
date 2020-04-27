package com.cafe24.louw0.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
public class Member {
	private String mId;
	private String mPw;
	private String mEmail;
	private String mName;
	private String mNickname;
	private String mLevel;
	private int column;
}