package com.onteacher.service;

import java.util.List;

import com.onteacher.vo.Course;
import com.onteacher.vo.Homework;

public interface CourseService {
	public Course queryCourseById(int courseId) throws Exception;
	public Homework queryHomework(int id) throws Exception;
	public List<Homework> queryHomeworkList(int courseId) throws Exception;
	public List<Course> selectCourseForIndex();
}
