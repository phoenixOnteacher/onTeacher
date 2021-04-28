package com.onteacher.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.onteacher.vo.Teacher;

@Mapper
@Repository("teacherDAO")
public interface TeacherDAO {

	public Teacher selectTeacherById(int teacherId);

}