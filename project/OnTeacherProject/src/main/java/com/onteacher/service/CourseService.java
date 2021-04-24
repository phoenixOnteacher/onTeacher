package com.onteacher.service;

import com.onteacher.vo.Course;

public interface CourseService {
	public Course queryCourseById(int courseId) throws Exception;
}
