package com.onteacher.service;

import com.onteacher.vo.Student;

public interface StudentService {

	//test 코드 : 로그인한 email로 student갹체를 가져와
	public Student queryStudentByEmail(String email);
	
}
