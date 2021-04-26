package com.onteacher.service;

import java.util.List;
import java.util.Map;

import com.onteacher.vo.Article;



public interface BoardService {
	public List<Article> listArticles() throws Exception;
	public int addNewArticle(Map articleMap) throws Exception;
	public Article viewArticle(int articleNO) throws Exception;
	public void modArticle(Map articleMap) throws Exception;
	public void removeArticle(int articleNO) throws Exception;
}
