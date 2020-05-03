package com.cafe24.louw0.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cafe24.louw0.vo.Board;
import com.cafe24.louw0.vo.BoardComment;
import com.cafe24.louw0.vo.Bookmark;
import com.cafe24.louw0.vo.Member;

@Repository(value = "boardMapper")
public interface BoardMapper {
	
	public List<Board> getBoardList(int column);
	public int getBoardLast();
	
	public List<BoardComment> getBoard(int no);
	public int insertHits(int no);
	
	public int insertComment(BoardComment boardComment);
	
	public int insertCommentC(BoardComment boardComment);
	
	public int insertBoard(Board board);
	
	public List<Board> getBookmarkBoard(Member member);
	public int getBookmarkBoardCnt(String mId);
	
	public int modiBoard(Board board);
	
	public int deleteBoard(Board board);
}
