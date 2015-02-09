package com.study20.web.part0.service.impl;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.study20.web.part0.dao.AndroidDao;
import com.study20.web.part0.dto.BoardDto;
import com.study20.web.part0.service.AndroidService;

@Service(value = "AndroidService")
public class AndroidServiceImpl implements AndroidService {
	
	@Resource(name = "AndroidDao")
	private AndroidDao androidDao;
	
	@Value("${common.upload.dir}")
	private String uploadDir;
	
	public List<BoardDto> getBoardList() throws Exception {
		return androidDao.getBoardList();
	}
	
	public BoardDto getArticle(Integer articleId) throws Exception {
		return androidDao.getArticle(articleId);
	}
	
	@Transactional
	public long setInsertArticleWrite(BoardDto boardDto) throws Exception {
		long articleId = 0;
		
		if ( 0 == boardDto.getArticleId() ) {
			articleId = androidDao.insertArticle(boardDto);
		}
		else {
			androidDao.updateArticle(boardDto);
			articleId = boardDto.getArticleId();
		}
		
		if ( boardDto.getFile() != null && (int) boardDto.getFile().getSize() > 0 ) {
			moveUploadFile(boardDto.getFile(), articleId);
		}
		
		return articleId;
	}
	
	private void moveUploadFile(MultipartFile file, long articleId) throws Exception {
		File tempFileName = new File(uploadDir, String.valueOf(articleId));
		file.transferTo(tempFileName);
	}

	@Override
	@Transactional
	public long deleteArticle(String getArticleIds) throws Exception {
		String[] articleIdArr = getArticleIds.split(",");
		
		File tempFileName = null;
		for ( String articleId : articleIdArr ) {
			tempFileName = new File(uploadDir, articleId);
			tempFileName.delete();
		}
		
		return androidDao.deleteArticle(getArticleIds);
	}
}
