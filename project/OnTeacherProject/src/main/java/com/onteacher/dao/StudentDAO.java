package com.onteacher.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.onteacher.vo.Student;

@Mapper
@Repository("studentDAO")
public interface StudentDAO {

	public Student selectStudentByEmail(String email);

	public List<Student> selectMatchingStudentByCourseId(int courseId) throws Exception;
	public void insertStudent(Student std) throws Exception;
	public Student selectStudent(String email) throws Exception;

	public Student selectqueryStudentById(int studentId);
}
