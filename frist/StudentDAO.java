package com.onteacher.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.onteacher.vo.Student;

@Mapper
@Repository("studentDAO")
public interface StudentDAO {
	public void insertStudent(Student std) throws Exception;
	public Student selectStudent(String email) throws Exception;
}
