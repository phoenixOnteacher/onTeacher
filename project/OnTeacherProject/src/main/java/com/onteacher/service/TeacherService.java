package com.onteacher.service;

import java.util.List;


import com.onteacher.vo.Teacher;

public interface TeacherService {
	public void thjoin(Teacher thacher) throws Exception;
	public boolean login(String email, String password) throws Exception;
	public List<Teacher> certConfirm() throws Exception;
}
