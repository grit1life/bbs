package com.cafe24.louw0.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cafe24.louw0.service.BoardService;
import com.cafe24.louw0.service.BookmarkService;
import com.cafe24.louw0.vo.Board;
import com.cafe24.louw0.vo.BoardComment;
import com.cafe24.louw0.vo.Bookmark;
import com.cafe24.louw0.vo.Paging;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private BookmarkService bookmarkService;
	
	@RequestMapping(value = "/boardList", method = RequestMethod.GET)
	public String boardList(Model model) {
		int page = 1;
		Paging<Board> paging = boardService.getBoardList(page);
		model.addAttribute("list", paging.getList());
		model.addAttribute("currentPage", paging.getCurrentPage());
		model.addAttribute("startPage", paging.getStartPage());
		model.addAttribute("endPage", paging.getEndPage());
		model.addAttribute("lastPage", paging.getLastPage());
		return "boardList";
	}
	
	@RequestMapping(value = "/boardList", method = RequestMethod.POST)
	public String boardList(Model model
				,@RequestParam(value="page") int page){
		Paging<Board> paging = boardService.getBoardList(page);
		model.addAttribute("list", paging.getList());
		model.addAttribute("currentPage", paging.getCurrentPage());
		model.addAttribute("startPage", paging.getStartPage());
		model.addAttribute("endPage", paging.getEndPage());
		model.addAttribute("lastPage", paging.getLastPage());
		return "boardListPage";
	}
	
	@RequestMapping(value = "/board", method = RequestMethod.GET)
	public String board( Model model
			,@RequestParam(value = "no", defaultValue = "0") int no
			,RedirectAttributes redirectAttributes
			,HttpSession session) {
		String url;
		if(no < 1) {
			url = "redirect:/boardList";
		}else {
			url = "board";
		}
		
		String sId = (String) session.getAttribute("id");
		model.addAttribute("bookmark", bookmarkService.selectBookmark(Bookmark.builder() 
																			.mId(sId)
																			.boardNo(no).build()));
		model.addAttribute("board", boardService.getBoard(no));
		
		return url;
	}
	
	@RequestMapping(value = "changeBookmark", method = RequestMethod.POST)
	public @ResponseBody int changeBookmark(@RequestParam(value = "boardNo") int boardNo 
			, @RequestParam(value = "boolBookmark") int boolBookmark
			, HttpSession session) {
		String sId = (String) session.getAttribute("id");
		Bookmark bookmark = Bookmark.builder()
									.mId(sId)
									.boardNo(boardNo).build();
		int result = 0;
		if(boolBookmark==1) {
			bookmarkService.deleteBookmark(bookmark);
		}else {
			result = bookmarkService.insertBookmark(bookmark);
		}
		return result;
	}
	
	@RequestMapping(value = "writeComment", method = RequestMethod.POST)
	public String writeComment(BoardComment boardC, HttpSession session) {
		
		String nickname = (String)session.getAttribute("nickname");
		boardC.setCommentWriter(nickname); 
		boardService.insertComment(boardC);
		
		return "redirect:/board?no="+boardC.getBoardNo();
	}
	
	@RequestMapping(value = "writeCommentC", method = RequestMethod.POST)
	public @ResponseBody int writeCommentC(@ModelAttribute BoardComment boardC, HttpSession session) {
		String nickname = (String)session.getAttribute("nickname");
		boardC.setCommentWriter(nickname); 
		boardService.insertCommentC(boardC);
		
		return 1;
	}
	
	@RequestMapping(value = "writeBoard", method=RequestMethod.GET)
	public String writeBoard() {
		return "writeBoard";
	}
	
	@RequestMapping(value = "writeBoard", method=RequestMethod.POST)
	public String writeBoard(Board board, HttpSession session) {
		String nickname = (String)session.getAttribute("nickname");
		board.setBoardWriter(nickname); 
		boardService.insertBoard(board);
		return "redirect:/boardList";
	}

}
