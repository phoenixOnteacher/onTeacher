package com.onteacher.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.onteacher.vo.Homework;
import com.onteacher.vo.HomeworkAnswer;

@Mapper
@Repository("homeworkDAO")
public interface HomeworkAnswerDAO {
	public void insertHomeworkAnswer(HomeworkAnswer ha) throws Exception;
//	public Homework selectHomeworkById(int id) throws Exception;
}
