package com.onteacher.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.onteacher.dao.CourseDAO;
import com.onteacher.dao.MatchingDAO;
import com.onteacher.dao.StudentDAO;
import com.onteacher.vo.Matching;
import com.onteacher.vo.Student;

@Service
@Transactional(propagation = Propagation.REQUIRED)
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
	public void insertMatching(Matching matching) throws Exception {
		matchingDAO.insertMatching(matching);
	}

	@Override
	public Matching selectMatching(Matching matching) throws Exception {
		return matchingDAO.selectMatching(matching);
	}

	@Override
	public List<Matching> selectMatchingListByCourseId(int courseId) throws Exception {
		return matchingDAO.selectMatchingListByCourseId(courseId);
	}
}
