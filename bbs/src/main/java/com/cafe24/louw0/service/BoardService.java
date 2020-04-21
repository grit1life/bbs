package com.cafe24.louw0.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.louw0.mapper.BoardMapper;
import com.cafe24.louw0.vo.Board;
import com.cafe24.louw0.vo.BoardComment;

@Service
@Transactional
public class BoardService {
	
	@Autowired
	private BoardMapper boardMapper;
	
	public List<Board> getBoardList(){
		return boardMapper.getBoardList();
	}
	
	public List<BoardComment> getBoard(int no){
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
}
