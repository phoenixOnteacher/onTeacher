package com.onteacher.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

<<<<<<< HEAD
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
=======
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class MyCourseServiceImpl implements MyCourseService {

>>>>>>> fec50bb813172fc77c1091e13a438be5b47a126c
}
