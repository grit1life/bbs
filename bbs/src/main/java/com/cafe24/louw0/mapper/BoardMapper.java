package com.cafe24.louw0.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cafe24.louw0.vo.Board;
import com.cafe24.louw0.vo.BoardComment;

@Repository(value = "boardMapper")
public interface BoardMapper {
	
	public List<Board> getBoardList();
	
	public List<BoardComment> getBoard(int no);
	
	public int insertComment(BoardComment boardComment);
	
	public int insertCommentC(BoardComment boardComment);
	
	public int insertBoard(Board board);
}
