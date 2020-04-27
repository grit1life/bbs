package com.cafe24.louw0.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
public class Bookmark {
	private int bookmarkNo;
	private String mId;
	private int boardNo;
}
