package com.onteacher.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.onteacher.dao.ArticleDAO;
import com.onteacher.dao.CommentDAO;
import com.onteacher.vo.Article;
import com.onteacher.vo.Comment;

@Service("boardService")
@Transactional(propagation = Propagation.REQUIRED)
public class BoardServiceImpl implements BoardService {
	@Autowired
	ArticleDAO articleDAO;

	@Autowired
	CommentDAO commentDAO;

	@Override
	public List<Article> listArticles(Article article) throws Exception {

		return articleDAO.selectAllArticlesList(article);
	}

	@Override
	public void addArticle(Article article) throws Exception {
		articleDAO.insertArticle(article);
	}

	@Override
	public Article viewArticle(int id) throws Exception {
		return articleDAO.selectArticle(id);
	}
	
	
	  @Override 
	  public void modArticle(Article article) throws Exception {
	  articleDAO.updateArticle(article);
	  
	  }
	  
	  @Override 
	  public void deleteArticle(int id) throws Exception { 
	  articleDAO.deleteArticle(id);
	  
	  }
	 

	@Override
	public int articleCount() throws Exception {
		return articleDAO.selectArticleCount();
	}

	@Override
	public void addComment(Comment comment) throws Exception {
		commentDAO.insertComment(comment);

	}

	@Override
	public List<Comment> commentList(int article_id) throws Exception {
		List<Comment> list= commentDAO.selectCommentList(article_id);

		return list;
	}

	@Override
	public void plusHits(int id) throws Exception {
		// TODO Auto-generated method stub
		articleDAO.updateHits(id);
	}

	
	

	
	

}
