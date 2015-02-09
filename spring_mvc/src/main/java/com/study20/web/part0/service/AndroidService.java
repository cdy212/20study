package com.study20.web.part0.service;

import java.util.List;

import com.study20.web.part0.dto.BoardDto;

public interface AndroidService {
	List<BoardDto> getBoardList() throws Exception;
	
	long setInsertArticleWrite(BoardDto boardDto) throws Exception;
	
	BoardDto getArticle(Integer articleId) throws Exception;

	long deleteArticle(String getArticleIds) throws Exception;
}
