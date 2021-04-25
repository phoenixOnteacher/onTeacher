package com.onteacher.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.onteacher.vo.Student;

@Mapper
@Repository("studentDAO")
public interface StudentDAO {

	public Student selectStdentByEmail(String email);

}
