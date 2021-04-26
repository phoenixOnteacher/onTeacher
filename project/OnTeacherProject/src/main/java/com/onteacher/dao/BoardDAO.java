package com.onteacher.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.onteacher.vo.Article;




public interface BoardDAO {
	public List selectAllArticlesList() throws DataAccessException;
	public int insertNewArticle(Map articleMap) throws DataAccessException;
	public Article selectArticle(int articleNO) throws DataAccessException;
	public void updateArticle(Map articleMap) throws DataAccessException;
	public void deleteArticle(int articleNO) throws DataAccessException;
	
}
