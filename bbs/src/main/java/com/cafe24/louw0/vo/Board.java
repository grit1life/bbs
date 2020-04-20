package com.cafe24.louw0.vo;

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
	private String boardHits;
	private String boardWriter;
	private String commentCount;
}
