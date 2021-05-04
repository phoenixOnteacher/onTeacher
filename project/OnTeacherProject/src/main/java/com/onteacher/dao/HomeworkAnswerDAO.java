package com.onteacher.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.onteacher.vo.HomeworkAnswer;

@Mapper
@Repository("homeworkAnswerDAO")
public interface HomeworkAnswerDAO {
	public void insertHomeworkAnswer(HomeworkAnswer ha) throws Exception;
//	public Homework selectHomeworkById(int id) throws Exception;

	public HomeworkAnswer selectHomeworkAnswer(@Param("homework_id")int homework_id, @Param("user_id")int user_id);
}
