package com.cafe24.louw0.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cafe24.louw0.vo.Board;

@Repository(value = "boardMapper")
public interface BoardMapper {
	
	public List<Board> getBoardList();
	
	public int insertBoard(Board board);
}
