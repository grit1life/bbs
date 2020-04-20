package com.cafe24.louw0.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.louw0.mapper.BoardMapper;
import com.cafe24.louw0.vo.Board;

@Service
@Transactional
public class BoardService {
	
	@Autowired
	private BoardMapper boardMapper;
	
	public List<Board> getBoardList(){
		return boardMapper.getBoardList();
	}
	
	public int insertBoard(Board board) {
		return boardMapper.insertBoard(board);
	}
}
