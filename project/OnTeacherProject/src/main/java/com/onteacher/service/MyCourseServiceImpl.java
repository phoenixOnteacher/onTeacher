package com.onteacher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.onteacher.dao.HomeworkAnswerDAO;
import com.onteacher.vo.HomeworkAnswer;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class MyCourseServiceImpl implements MyCourseService {
	@Autowired
	HomeworkAnswerDAO homeworkAnswerDAO;
	
	@Override
	public void submitHomework(HomeworkAnswer ha) throws Exception {
		homeworkAnswerDAO.insertHomeworkAnswer(ha);
	}
	
//	@Override
//	public Homework queryHomework(int id) throws Exception {
//		return homeworkAnswerDAO.selectHomeworkById(id);
}
