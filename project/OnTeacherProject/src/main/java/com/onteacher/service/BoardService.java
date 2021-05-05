package com.onteacher.service;

import java.util.List;

import com.onteacher.vo.Article;
import com.onteacher.vo.Comment;

public interface BoardService {
	public List<Article> listArticles(Article article) throws Exception;

	public int addArticle(Article article) throws Exception;

	public Article viewArticle(int id) throws Exception;

	
	public void modArticle(Article article) throws Exception;
	  
	public void deleteArticle(int id) throws Exception;
	 

	public int articleCount() throws Exception;

	public void addComment(Comment comment) throws Exception;

	public List<Comment> commentList(int article_id) throws Exception;
	
	public void plusHits(int id) throws Exception;
	
	
}
