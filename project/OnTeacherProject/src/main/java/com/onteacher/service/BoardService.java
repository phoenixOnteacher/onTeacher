package com.onteacher.service;

import java.util.List;
import java.util.Map;

import com.onteacher.vo.Article;



public interface BoardService {
	public List<Article> listArticles(Article article) throws Exception;
	public int addArticle(Article article) throws Exception;
	public Article viewArticle(int id) throws Exception;
	public void modArticle(Article article) throws Exception;
	public void removeArticle(int id) throws Exception;
}
