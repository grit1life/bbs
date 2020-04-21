package com.cafe24.louw0.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cafe24.louw0.service.BoardService;
import com.cafe24.louw0.vo.Board;
import com.cafe24.louw0.vo.BoardComment;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value = "/boardList", method = RequestMethod.GET)
	public String boardList(Model model) {
		model.addAttribute("list", boardService.getBoardList());
		return "boardList";
	}
	
	@RequestMapping(value = "/board", method = RequestMethod.GET)
	public String board( Model model
			,@RequestParam(value = "no", defaultValue = "0") int no
			,RedirectAttributes redirectAttributes) {
		String url;
		if(no < 1) {
			url = "redirect:/boardList";
		}else {
			url = "board";
		}
		model.addAttribute("board", boardService.getBoard(no));
		
		return url;
	}
	
	@RequestMapping(value = "writeComment", method = RequestMethod.POST)
	public String writeComment(BoardComment boardC) {
		
		boardC.setCommentWriter("홍길동"); //임시값 세션
		boardService.insertComment(boardC);
		
		
		return "redirect:/board?no="+boardC.getBoardNo();
	}
	
	@RequestMapping(value = "writeCommentC", method = RequestMethod.POST)
	public String writeCommentC(@ModelAttribute BoardComment boardC) {
		
		System.out.println(boardC);
		System.out.println(boardC.getBoardNo());
		boardC.setCommentWriter("홍길동"); //임시값 세션
		boardService.insertCommentC(boardC);
		
		return "aa";
	}
	
	@RequestMapping(value = "writeBoard", method=RequestMethod.GET)
	public String writeBoard() {
		return "writeBoard";
	}
	
	@RequestMapping(value = "writeBoard", method=RequestMethod.POST)
	public String writeBoard(Board board) {
		System.out.println(board);
		board.setBoardWriter("홍길동"); //임시값 세션
		boardService.insertBoard(board);
		return "redirect:/boardList";
	}

}
