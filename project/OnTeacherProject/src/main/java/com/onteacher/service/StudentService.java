package com.onteacher.service;

import com.onteacher.vo.Student;

public interface StudentService {

	//test 肄붾뱶 : 濡쒓렇�씤�븳 email濡� student媛뱀껜瑜� 媛��졇��
	public Student queryStudentByEmail(String email);
	
	public void stjoin(Student student) throws Exception;
	
	public boolean login(String email, String password) throws Exception;
	

	public Student studentInfo(int studentId) throws Exception;
	
	public String studentName(int id) throws Exception;
	
	
}
