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
	public int addArticle(Article article) throws Exception {
		int no = articleDAO.selectMaxArticleNO() + 1;
		article.setId(no);
		articleDAO.insertArticle(article);
		return 0;
	}

	@Override
	public Article viewArticle(int id) throws Exception {
		return articleDAO.selectArticle(id);
	}
	//아직 수정 삭제가 덜됨
	@Override
	public void modArticle(Article article) throws Exception {
		// TODO Auto-generated method stub
		
	}
	//아직 수정 삭제가 덜됨
	@Override
	public void removeArticle(int id) throws Exception {
		// TODO Auto-generated method stub

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
		return commentDAO.selectComment(article_id);
	}

}
