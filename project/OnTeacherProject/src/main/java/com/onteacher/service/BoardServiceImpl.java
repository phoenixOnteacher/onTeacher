package com.onteacher.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.onteacher.dao.ArticleDAO;
import com.onteacher.dao.CommentDAO;
import com.onteacher.dao.NotificationDAO;
import com.onteacher.vo.Article;
import com.onteacher.vo.Comment;
import com.onteacher.vo.Notification;

@Service("boardService")
@Transactional(propagation = Propagation.REQUIRED)
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private ArticleDAO articleDAO;

	@Autowired
	private CommentDAO commentDAO;
	
	@Autowired
	private NotificationDAO notificationDAO;

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
		// 게시글 작성자에게 알림 보냄
		Article article = articleDAO.selectArticle(comment.getArticle_id());
		Notification notification = new Notification();
		notification.setContent("[" + article.getTitle() +"] 게시글에 댓글이 달렸습니다.");
		notification.setToId(article.getUser_id());
		notificationDAO.insertNotification(notification);
	}

	@Override
	public List<Comment> commentList(int article_id) throws Exception {
		List<Comment> list= commentDAO.selectCommentList(article_id);
		return list;
	}

	@Override
	public void plusHits(int id) throws Exception {
		articleDAO.updateHits(id);
	}
}
