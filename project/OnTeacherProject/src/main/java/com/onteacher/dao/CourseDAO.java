package com.onteacher.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.onteacher.vo.Course;

@Mapper
@Repository("courseDAO")
public interface CourseDAO {
	public void insertCourse(Course course) throws Exception;
}
