package com.onteacher.service;

import java.util.List;

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
	public List<Course> courseWaitingList(int studentId) {
		return courseDAO.selectCourseWaitingList(studentId);
	}


	@Override
	public List<Course> courseStudyingList(int studentId) {
		return courseDAO.selectCourseStudyingList(studentId);
	}


	@Override
	public List<Course> courseEndList(int studentId) {
		return courseDAO.selectCourseEndList(studentId);
	}
}
