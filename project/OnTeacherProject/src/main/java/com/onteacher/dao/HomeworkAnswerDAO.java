package com.onteacher.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.onteacher.vo.HomeworkAnswer;

@Mapper
@Repository("homeworkAnswerDAO")
public interface HomeworkAnswerDAO {
	public void insertHomeworkAnswer(HomeworkAnswer ha) throws Exception;
	public HomeworkAnswer selectHomeworkAnswer(HomeworkAnswer ha) throws Exception;
}
