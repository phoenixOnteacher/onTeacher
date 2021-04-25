package com.onteacher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.onteacher.dao.MatchingDAO;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class MyCourseServiceImpl implements MyCourseService {

	@Autowired
	MatchingDAO matchingDAO;
	
	@Override
	public void applyCancle(int studentId, int courseId) {
		matchingDAO.deleteMatchingData(studentId,courseId);
	}

}
