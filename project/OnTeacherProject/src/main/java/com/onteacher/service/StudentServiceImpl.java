package com.onteacher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.onteacher.dao.StudentDAO;
import com.onteacher.vo.Student;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentDAO studentDAO;
	
	@Override
	public void stjoin(Student student) throws Exception {
		Student sstudent = studentDAO.selectStudent(student.getEmail());
		if(sstudent!=null) throw new Exception("이메일 중복");
		studentDAO.insertStudent(student);
	}

	@Override
	public boolean login(String email, String password) throws Exception {
		Student student = studentDAO.selectStudent(email);
		if(student==null) throw new Exception("아이디 오류");
		if(student.getPassword().equals(password)) {
			return true;
		}
		return false;
	}
}
