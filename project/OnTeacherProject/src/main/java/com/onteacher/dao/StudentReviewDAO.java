package com.onteacher.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.onteacher.vo.CourseReview;
import com.onteacher.vo.StudentReview;

@Mapper
@Repository("studentReviewDAO")
public interface StudentReviewDAO {
	public void insertStudentReview(StudentReview sr) throws Exception;
	public StudentReview selectStudentReview(StudentReview sr) throws Exception;
	public List<StudentReview> selectStudentReviewByStudent(int studentId) throws Exception;
	
}
