package com.onteacher.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.onteacher.dao.CourseDAO;
import com.onteacher.dao.HighCategoryDAO;
import com.onteacher.dao.MatchingDAO;
import com.onteacher.dao.TeacherDAO;
import com.onteacher.dao.HomeworkDAO;
import com.onteacher.dao.LowCategoryDAO;
import com.onteacher.vo.Course;
import com.onteacher.vo.HighCategory;
import com.onteacher.vo.Homework;
import com.onteacher.vo.LowCategory;
import com.onteacher.vo.Teacher;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class CourseServiceImpl implements CourseService {
	
	@Autowired
	CourseDAO courseDAO;

	@Autowired
	private HomeworkDAO homeworkDAO;
	
	@Autowired
	MatchingDAO matchingDAO;
	
	@Autowired
	HighCategoryDAO highCategoryDAO;
	
	@Autowired
	LowCategoryDAO lowCategoryDAO;
	
	@Autowired
	TeacherDAO teacherDAO;

	@Override
	public Course queryCourseById(int courseId) throws Exception {
		Course course = courseDAO.selectCourseById(courseId);
		System.out.println("서비스 문제없다");
		System.out.println(course);
		System.out.println(course.getId());
		course.setStartDate(course.getStartDate().substring(0,10));
		course.setEndDate(course.getEndDate().substring(0,10));
		return course;
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
	public HighCategory queryHighCategoryById(int highCategoryId) {
		return highCategoryDAO.selectHighCategoryById(highCategoryId);
	}


	@Override
	public LowCategory queryLowCategoryById(int lowCategoryId) {
		return lowCategoryDAO.selectLowCategoryById(lowCategoryId);
	}


	@Override
	public Teacher queryTeacherById(int teacherId) {
		// TODO Auto-generated method stub
		return teacherDAO.selectTeacherById(teacherId);
	}

	@Override
	public List<Course> selectCourseForIndex() {
		return courseDAO.selectCourseForIndex();
	}


	@Override
	public List<Course> courseMatchingList(int studentId) {
		return null;
	}
}
