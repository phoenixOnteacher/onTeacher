package com.onteacher.service;

import java.util.List;


import com.onteacher.vo.Teacher;

public interface TeacherService {
	public void thjoin(Teacher thacher) throws Exception;
	public boolean login(String email, String password) throws Exception;
	public List<Teacher> certConfirm() throws Exception;
	public void certApproved(String email) throws Exception;
	public void certRejected(String email) throws Exception;
	public Teacher teacherInfo(int teacherId) throws Exception;
	public void updateTeacherCert(Teacher teacher);

}
