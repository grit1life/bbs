package com.cafe24.louw0.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardComment extends Board {
	private int commentNo;
	private int commentSort;
	private int commentDepth;
	private String commentContent;
	private String cMId;
	private String cMNickname;
	private String commentDate;
	private String commentModiDate;
}
