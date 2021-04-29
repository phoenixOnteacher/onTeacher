package com.onteacher.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.onteacher.dao.CourseDAO;
import com.onteacher.vo.Course;
import com.onteacher.vo.HighCategory;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserServiceImpl implements UserService {

	@Autowired
	CourseDAO courseDAO;
	
	@Override
	public List<Course> queryCourseForSearch(Course course) {
		return courseDAO.selectCourseForSearchFilter(course);
	}

	@Override
	public List<HighCategory> highcategoryList() {
		
		return courseDAO.highcategoryList();
	}
	
}
