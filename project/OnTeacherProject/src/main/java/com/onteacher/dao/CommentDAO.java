package com.onteacher.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.onteacher.vo.Comment;

@Mapper
@Repository("commentDAO")
public interface CommentDAO {

	public List<Comment> selectAllCommentsList() throws Exception;

	public void insertComment(Comment comment) throws Exception;

	public void updateComment(Comment comment) throws Exception;

	public void deleteComment(int id) throws Exception;

	public List<Comment> selectComment(int article_id) throws Exception;
}
