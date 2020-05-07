package com.cafe24.louw0.mapper;

import org.springframework.stereotype.Repository;

import com.cafe24.louw0.vo.Bookmark;

@Repository(value = "bookmarkMapper")
public interface BookmarkMapper {

	public int selectBookmark(Bookmark bookmark);
	
	public int insertBookmark(Bookmark bookmark);
	
	public int deleteBookmark(Bookmark bookmark);
}
