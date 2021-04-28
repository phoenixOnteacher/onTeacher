package com.onteacher.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.onteacher.vo.Article;

@Mapper
@Repository("articleDAO")
public interface ArticleDAO {
	public List<Article> selectAllArticlesList(Article article) throws Exception;
	public void insertArticle(Article article) throws Exception;
	public int selectMaxArticleNO() throws Exception;
	public Article selectArticle(int id) throws Exception;
	public void updateArticle(Article article) throws Exception;
	public void deleteArticle(int id) throws Exception;
}
