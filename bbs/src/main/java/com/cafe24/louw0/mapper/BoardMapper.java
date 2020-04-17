package com.cafe24.louw0.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.louw0.vo.Board;

//@Mapper
public interface BoardMapper {
	
	public int insertBoard(Board board);
}
