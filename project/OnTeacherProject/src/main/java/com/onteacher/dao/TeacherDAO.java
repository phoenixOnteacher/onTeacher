package com.onteacher.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.onteacher.vo.Teacher;

@Mapper
@Repository("teacherDAO")
public interface TeacherDAO {

	public Teacher selectTeacherById(int teacherId);
	public void insertTeacher(Teacher tea) throws Exception;
	public Teacher selectTeacher(String email) throws Exception;
	public Teacher selectTeacherByEmail(String email) throws Exception;
	public Teacher selectMatchingTeacherByCourseId(int courseId);
	public List<Teacher> baseCertTeacher() throws Exception;
	public void updateActive(String email);
	public void updateApproved(String email);
	public void updateRejected(String email);
	public void updateCert(Teacher teacher);
	public Teacher selectTeacherByTeacherId(int teacherId) throws Exception;
	public void updateTeacherCert(Teacher teacher);
}

