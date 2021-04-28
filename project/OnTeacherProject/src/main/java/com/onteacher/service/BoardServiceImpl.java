package com.onteacher.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.onteacher.dao.ArticleDAO;
import com.onteacher.vo.Article;


@Service("boardService")
@Transactional(propagation = Propagation.REQUIRED)
public class BoardServiceImpl  implements BoardService{
	@Autowired
	ArticleDAO articleDAO;

	@Override
	public List<Article> listArticles(Article article) throws Exception {
		
		return articleDAO.selectAllArticlesList(article);
	}

	@Override
	public int addArticle(Article article) throws Exception {
		int no=articleDAO.selectMaxArticleNO()+1;
		article.setId(no);
		articleDAO.insertArticle(article);
		return 0;
	}

	@Override
	public Article viewArticle(int id) throws Exception {
		// TODO Auto-generated method stub
		return articleDAO.selectArticle(id);
	}

	@Override
	public void modArticle(Article article) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeArticle(int id) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
}
