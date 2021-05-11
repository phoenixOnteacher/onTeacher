package com.onteacher.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.onteacher.dao.CourseDAO;
import com.onteacher.dao.HighCategoryDAO;
import com.onteacher.dao.HomeworkAnswerDAO;
import com.onteacher.dao.HomeworkDAO;
import com.onteacher.dao.LowCategoryDAO;
import com.onteacher.dao.MatchingDAO;
import com.onteacher.dao.TeacherDAO;
import com.onteacher.prop.UploadPath;
import com.onteacher.vo.Course;
import com.onteacher.vo.HighCategory;
import com.onteacher.vo.Homework;
import com.onteacher.vo.HomeworkAnswer;
import com.onteacher.vo.LowCategory;
import com.onteacher.vo.Teacher;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class CourseServiceImpl implements CourseService {
	
	@Autowired
	CourseDAO courseDAO;

	@Autowired
	HomeworkDAO homeworkDAO;
	
	@Autowired
	HomeworkAnswerDAO homeworkAnswerDAO;
	
	@Autowired
	MatchingDAO matchingDAO;
	
	@Autowired
	HighCategoryDAO highCategoryDAO;
	
	@Autowired
	LowCategoryDAO lowCategoryDAO;
	
	@Autowired
	TeacherDAO teacherDAO;
	
	@Autowired
	private UploadPath uploadPath;

	@Override
	public Course queryCourseById(int courseId) throws Exception {
		Course course = courseDAO.selectCourseById(courseId);
		course.setStartDate(course.getStartDate().substring(0,10));
		course.setEndDate(course.getEndDate().substring(0,10));
		return course;
	}
	
	@Override
	public Homework queryHomework(int id) throws Exception {
		Homework homework = homeworkDAO.selectHomeworkById(id);
		homework.setDeadline(homework.getDeadline().substring(0,10));
		return homework;
		
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

	@Override
	public HomeworkAnswer queryHomeworkAnswer(int homework_id, int user_id) {
		return homeworkAnswerDAO.selectHomeworkAnswerById(homework_id,user_id);
	}

	@Override
	public List<Course> selectCourseForSearch() {
		return courseDAO.selectCourseForSearch();
	}
	
	@Override
	public List<Course> queryCourseListByStartDate(String today) throws Exception {
		return courseDAO.selectCourseListByStartDate(today);
	}
	
	@Override
	public List<Course> queryCourseListByEndDate(String today) throws Exception {
		return courseDAO.selectCourseListByEndDate(today);
	}
}
