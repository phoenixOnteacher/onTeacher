package com.onteacher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.onteacher.dao.CourseManageDAO;
import com.onteacher.dao.HomeworkDAO;
import com.onteacher.vo.Homework;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class CourseManageServiceImpl implements CourseManageService {

	@Autowired
	CourseManageDAO courseManageDAO;
	
	@Autowired
	HomeworkDAO homeworkDAO;
	
	@Override
	public void setHomework(Homework hw) throws Exception {
		homeworkDAO.insertHomework(hw);
	}
	
	@Override
	public Homework queryHomework(int id) throws Exception {
		return homeworkDAO.selectHomeworkById(id);
	}
}
