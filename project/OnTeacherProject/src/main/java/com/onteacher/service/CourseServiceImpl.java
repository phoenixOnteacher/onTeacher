package com.onteacher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.onteacher.dao.CourseDAO;
import com.onteacher.vo.Course;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class CourseServiceImpl implements CourseService {
	@Autowired
	CourseDAO courseDAO;
	
	@Override
	public Course queryCourseById(int courseId) throws Exception {
		return courseDAO.selectCourseById(courseId);
	}
}
