package com.onteacher.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.onteacher.dao.CourseDAO;
import com.onteacher.dao.MatchingDAO;
import com.onteacher.dao.TeacherDAO;
import com.onteacher.vo.Course;
import com.onteacher.vo.Teacher;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class MyCourseServiceImpl implements MyCourseService {

	@Autowired
	MatchingDAO matchingDAO;
	
	@Autowired
	CourseDAO courseDAO;
	
	@Autowired
	TeacherDAO teacherDAO;

	@Override
	public void cancleMatching(int studentId, int courseId) {
		matchingDAO.deleteMatchingData(studentId,courseId);
	}


	@Override
	public List<Course> queryMatchingcourseListByStudentId(int studentId) {
		return courseDAO.selectCourseMatchingListByStudentId(studentId);
	}


	@Override
	public List<Course> queryMatchedcourseListByStudentId(int studentId) {
		return courseDAO.selectCourseMatchedListByStudentId(studentId);
	}


	@Override
	public List<Course> queryStudyingcourseListByStudentId(int studentId) {
		return courseDAO.selectCourseStudyingListByStudentId(studentId);
	}


	@Override
	public List<Course> queryEndcourseListByStudentId(int studentId) {
		return courseDAO.selectCourseEndListByStudentId(studentId);
	}


	@Override
	public Teacher queryMatchingTeacher(int courseId) {
		System.out.println("서비스임플 courseId:"+courseId);
		return teacherDAO.selectMatchingTeacherByCourseId(courseId);
	}

}
