package com.onteacher.service;

import com.onteacher.vo.Student;

public interface StudentService {
//	public Student queryStudentByEmail(String email);
	public void stjoin(Student student) throws Exception;
	public boolean login(String email, String password) throws Exception;
	public Student studentInfo(int studentId) throws Exception;
	public String studentName(int id) throws Exception;
}
