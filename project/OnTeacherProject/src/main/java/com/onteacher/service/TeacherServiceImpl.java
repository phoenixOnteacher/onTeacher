package com.onteacher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.onteacher.dao.TeacherDAO;
import com.onteacher.vo.Teacher;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	TeacherDAO teacherDAO;
	
	@Override
	public void thjoin(Teacher thacher) throws Exception {
		Teacher steacher = teacherDAO.selectTeacher(thacher.getEmail());
		if(steacher!=null) throw new Exception("이메일 중복");
		teacherDAO.insertTeacher(thacher);
	}

	@Override
	public boolean login(String email, String password) throws Exception {
		Teacher teacher = teacherDAO.selectTeacher(email);
		if(teacher==null) throw new Exception("이메일 오류");
		if(teacher.getPassword().equals(password)) {
			return true;
		}
		return false;
	}
}
