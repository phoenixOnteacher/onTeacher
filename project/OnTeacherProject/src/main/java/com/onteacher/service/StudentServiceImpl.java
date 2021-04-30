package com.onteacher.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.onteacher.dao.StudentDAO;
import com.onteacher.dao.StudentReviewDAO;
import com.onteacher.vo.Student;
import com.onteacher.vo.StudentReview;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentDAO studentDAO;
	
	@Autowired
	StudentReviewDAO studentReviewDAO;

	@Override
	public Student queryStudentByEmail(String email) {
		return studentDAO.selectStudentByEmail(email);
	}
	
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

	@Override
	public Student studentInfo(int studentId) throws Exception {
		Student student = studentDAO.selectStudentByStudentId(studentId);
		if(student==null) {
			throw new Exception("정보 없음");
		}
		List<StudentReview> reviewList = studentReviewDAO.selectStudentReviewByStudent(studentId);
		student.setStudentReviewList(reviewList);
		return student;
	}
	
}
