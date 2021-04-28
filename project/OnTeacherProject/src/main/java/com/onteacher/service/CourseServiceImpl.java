package com.onteacher.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.onteacher.dao.CourseDAO;
import com.onteacher.dao.HomeworkDAO;
import com.onteacher.vo.Course;
import com.onteacher.vo.Homework;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class CourseServiceImpl implements CourseService {
	
	@Autowired
	CourseDAO courseDAO;

	@Autowired
	private HomeworkDAO homeworkDAO;
	
	@Override
	public Course queryCourseById(int courseId) throws Exception {
		return courseDAO.selectCourseById(courseId);
	}

	@Override
	public Homework queryHomework(int id) throws Exception {
		return homeworkDAO.selectHomeworkById(id);
	}

	@Override
	public List<Homework> queryHomeworkList(int courseId) throws Exception {
		List<Homework> homeworkList = homeworkDAO.selectHomeworkListByCourseId(courseId);
		for (Homework homework : homeworkList) {
			homework.setDeadline(homework.getDeadline().substring(0,10));
		}
		return homeworkList;
	}

	@Override
	public List<Course> selectCourseForIndex() {
		return courseDAO.selectCourseForIndex();
	}
}
