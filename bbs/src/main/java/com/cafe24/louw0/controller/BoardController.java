package com.cafe24.louw0.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cafe24.louw0.service.BoardService;
import com.cafe24.louw0.vo.Board;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value = "/board", method = RequestMethod.GET)
	public String board() {
		Board board = Board.builder()
						.title("title")
						.build();
		boardService.insertBoard(board);
		return "NewFile";
	}
}
