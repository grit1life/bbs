package com.cafe24.louw0.mapper;

import org.springframework.stereotype.Repository;

import com.cafe24.louw0.vo.Board;

@Repository(value = "boardMapper")
public interface BoardMapper {
	
	public int insertBoard(Board board);
}
