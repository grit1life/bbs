package com.cafe24.louw0.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.louw0.mapper.BoardMapper;
import com.cafe24.louw0.vo.Board;
import com.cafe24.louw0.vo.BoardComment;
import com.cafe24.louw0.vo.Bookmark;
import com.cafe24.louw0.vo.Paging;

@Service
@Transactional
public class BoardService {
	
	@Autowired
	private BoardMapper boardMapper;
		
	public Paging<Board> getBoardList(int page){
		int cnt = boardMapper.getBoardLast();
		List<Board> list = boardMapper.getBoardList((page-1)*10);
		Paging<Board> paging = new Paging<Board>(list, page, cnt);
		return paging;
	}
	
	public List<BoardComment> getBoard(int no){
		boardMapper.insertHits(no);
		return boardMapper.getBoard(no);
	}
	
	public void insertComment(BoardComment boardComment) {
		boardMapper.insertComment(boardComment);
	}
	
	public void insertCommentC(BoardComment boardComment) {
		boardMapper.insertCommentC(boardComment);
	}
	
	public int insertBoard(Board board) {
		return boardMapper.insertBoard(board);
	}
	
	public int modiBoard(Board board) {
		return boardMapper.modiBoard(board);
	}
	
	public int deleteBoard(int no) {
		return boardMapper.deleteBoard(no);
	}
	
}
