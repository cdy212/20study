package com.study20.web.part0.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.study20.web.part0.dto.BoardDto;

@Repository("AndroidDao")
public class AndroidDao {
	
	@Resource(name = "userSqlSessionFactory")
	SqlSessionTemplate userSqlSessionFactory;
	
	public List<BoardDto> getBoardList() {
		return (List<BoardDto>) userSqlSessionFactory.selectList("sql.android.board.select.selectBoardList");
	}
	
	public long insertArticle(BoardDto boardDto) {
		userSqlSessionFactory.insert("sql.android.board.insert.insertArticle", boardDto);
		
		return boardDto.getArticleId();
	}

	public BoardDto getArticle(Integer articleId) {
		return (BoardDto) userSqlSessionFactory.selectOne("sql.android.board.select.selectArticle", articleId);
	}

	public long deleteArticle(String articleIds) {
		return userSqlSessionFactory.delete("sql.android.board.delete.deleteArticle", articleIds);
	}

	public void updateArticle(BoardDto boardDto) {
		userSqlSessionFactory.update("sql.android.board.insert.updateArticle", boardDto);
	}
}
