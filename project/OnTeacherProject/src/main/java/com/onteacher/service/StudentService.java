package com.onteacher.service;

import com.onteacher.vo.Student;

public interface StudentService {
	public void stjoin(Student student) throws Exception;
	public boolean login(String email, String password) throws Exception;
}
