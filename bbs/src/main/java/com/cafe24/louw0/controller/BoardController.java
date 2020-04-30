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
	public String boardList(Model model, HttpSession session) {
		int page = 1;
		model.addAttribute("sId", session.getAttribute("sId"));
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
		
		String sId = (String) session.getAttribute("sId");
		model.addAttribute("bookmark", bookmarkService.selectBookmark(Bookmark.builder() 
																			.boardNo(no)
																			.mId(sId).build()));
		model.addAttribute("board", boardService.getBoard(no));
		model.addAttribute("sId", sId);
		return url;
	}
	
	@RequestMapping(value = "changeBookmark", method = RequestMethod.POST)
	public @ResponseBody int changeBookmark(@RequestParam(value = "boardNo") int boardNo 
			, @RequestParam(value = "boolBookmark") int boolBookmark
			, HttpSession session) {
		String sId = (String) session.getAttribute("sId");
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
		
		String mId = (String)session.getAttribute("sId");
		boardC.setMId(mId); 
		boardService.insertComment(boardC);
		
		return "redirect:/board?no="+boardC.getBoardNo();
	}
	
	@RequestMapping(value = "writeCommentC", method = RequestMethod.POST)
	public String writeCommentC(Model model,
			@ModelAttribute BoardComment boardC, HttpSession session) {

		String mId = (String)session.getAttribute("sId");
		boardC.setMId(mId); 
		boardService.insertCommentC(boardC);
		model.addAttribute("board", boardService.getBoard(boardC.getBoardNo()));
		return "commentC";
	}
	
	@RequestMapping(value = "writeBoard", method=RequestMethod.GET)
	public String writeBoard(Model model, HttpSession session) {
		model.addAttribute("sId", session.getAttribute("sId"));
		return "writeBoard";
	}
	
	@RequestMapping(value = "writeBoard", method=RequestMethod.POST)
	public String writeBoard(Board board, HttpSession session) {
		String mId = (String)session.getAttribute("sId");
		board.setMId(mId);  
		boardService.insertBoard(board);
		return "redirect:/boardList";
	}
	
	@RequestMapping(value = "modiBoard", method=RequestMethod.POST)
	public @ResponseBody int modiBoard(Board board) {
		return boardService.modiBoard(board);
	}
	
	@RequestMapping(value = "deleteBoard", method=RequestMethod.GET)
	public String deleteBoard(@RequestParam (value = "boardNo") int boardNo) {
		boardService.deleteBoard(boardNo);
		return "redirect:/boardList";
	}
}
