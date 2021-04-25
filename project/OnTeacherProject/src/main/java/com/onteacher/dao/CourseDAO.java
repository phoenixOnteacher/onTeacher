package com.onteacher.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.onteacher.vo.Course;

@Mapper
@Repository("courseDAO")
public interface CourseDAO {

	public List<Course> selectCourseWaitingList(int studentId);

	public List<Course> selectCourseStudyingList(int studentId);

	public List<Course> selectCourseEndList(int studentId);

	

}
