package com.onteacher.service;

import com.onteacher.vo.Student;

public interface StudentService {

	//test 코드 : 로그인한 email로 student갹체를 가져와
	public Student queryStudentByEmail(String email);
	
	public void stjoin(Student student) throws Exception;
	
	public boolean login(String email, String password) throws Exception;
	
	public Student studentInfo(int studentId) throws Exception;
}
