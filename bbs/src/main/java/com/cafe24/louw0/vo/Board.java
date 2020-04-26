package com.cafe24.louw0.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Board {
	private String boardNo;
	private String boardTitle;
	private String boardContent;
	private int boardHits;
	private String boardWriter;
	private String boardDate;
	private String boardModiDate;
	private int commentCount;
}
