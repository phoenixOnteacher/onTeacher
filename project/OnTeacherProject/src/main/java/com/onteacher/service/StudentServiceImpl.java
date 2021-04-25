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
	public Student queryStudentByEmail(String email) {
		return studentDAO.selectStdentByEmail(email);
	}
}
