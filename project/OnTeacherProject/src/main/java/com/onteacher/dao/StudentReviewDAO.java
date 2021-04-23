package com.onteacher.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.onteacher.vo.StudentReview;

@Mapper
@Repository("studentReviewDAO")
public interface StudentReviewDAO {
	public void insertStudentReview(StudentReview sr) throws Exception;
}
