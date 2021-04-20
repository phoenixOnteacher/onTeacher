package com.onteacher.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.onteacher.vo.Homework;

@Mapper
@Repository("homeworkDAO")
public interface HomeworkDAO {
	public void insertHomework(Homework hw) throws Exception;
	public Homework selectHomeworkById(int id) throws Exception;
}
