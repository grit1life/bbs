package com.cafe24.louw0.vo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Paging<T> {
	private List<T> list;
	private int currentPage;
	private int lastPage;
	private int startPage;
	private int endPage;
	
	public Paging(List<T> list, int currentPage, int cnt) {
		this.list = list;
		this.currentPage = currentPage;
		this.startPage = currentPage - 4;
		if(this.startPage < 1) {
			this.startPage = 1;
		}
		this.endPage = currentPage + 4;
		this.lastPage = cnt/10 + 1;
		if(this.endPage > this.lastPage) {
			this.endPage = this.lastPage;
		}
	}
}
