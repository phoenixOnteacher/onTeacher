package com.onteacher.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.onteacher.dao.CourseDAO;
import com.onteacher.dao.MatchingDAO;
import com.onteacher.dao.StudentDAO;
import com.onteacher.vo.Course;
import com.onteacher.vo.Matching;
import com.onteacher.vo.Student;

public class MatchingServiceImpl implements MatchingService{
	
	@Autowired
	CourseDAO courseDAO;
	
	@Autowired
	MatchingDAO matchingDAO;
	
	@Autowired
	StudentDAO studentDAO;

	@Override
	public Student queryStudentById(int studentId) {
		return studentDAO.selectqueryStudentById(studentId);
	}

	@Override
	public Course queryCourseById(int courseId) throws Exception {
		return courseDAO.selectqueryCourseById(courseId);
	}

	@Override
	public void insertMatching(Matching matching) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Matching selectMatching(Matching matching) throws Exception {
		// TODO Auto-generated method stub
		return matchingDAO.selectMatching(matching);
	}

	@Override
	public void deleteMatching(Matching matching) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Matching> selectMatchingListByCourseId(int courseId) throws Exception {
		// TODO Auto-generated method stub
		return matchingDAO.selectMatchingListByCourseId(courseId);
	}

	@Override
	public void deleteMatchingData(int studentId, int courseId) {
		// TODO Auto-generated method stub
		
	}
	

}
