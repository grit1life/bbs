package com.cafe24.louw0.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Board {
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private int boardHits;
	private String mId;
	private String mNickname;
	private String boardDate;
	private String boardModiDate;
	private int commentCount;
}
