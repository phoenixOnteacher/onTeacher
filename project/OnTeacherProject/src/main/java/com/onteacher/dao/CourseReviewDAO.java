package com.onteacher.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.onteacher.vo.CourseReview;
import com.onteacher.vo.StudentReview;

@Mapper
@Repository("courseReviewDAO")
public interface CourseReviewDAO {
	public void insertCourseReview(CourseReview cr);
	public CourseReview selectCourseReview(CourseReview cr);

}
