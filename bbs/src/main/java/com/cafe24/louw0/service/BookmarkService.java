package com.cafe24.louw0.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.louw0.mapper.BookmarkMapper;
import com.cafe24.louw0.vo.Bookmark;

@Service
@Transactional
public class BookmarkService {

	@Autowired
	private BookmarkMapper bookmarkMapper;
	
	public int selectBookmark(Bookmark bookmark) {
		return bookmarkMapper.selectBookmark(bookmark);
	}
	public int insertBookmark(Bookmark bookmark) {
		return bookmarkMapper.insertBookmark(bookmark);
	}
	public int deleteBookmark(Bookmark bookmark) {
		return bookmarkMapper.deleteBookmark(bookmark);
	}
	
}
