package com.onteacher.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("commentDAO")
public interface CommentDAO {

	

public interface BoardDAO {
	public List selectAllCommentsList() throws DataAccessException;
	public int insertNewComment(Map articleMap) throws DataAccessException;
	public void updateComment(Map articleMap) throws DataAccessException;
	public void deleteComment(int articleNO) throws DataAccessException;
	
}

}
