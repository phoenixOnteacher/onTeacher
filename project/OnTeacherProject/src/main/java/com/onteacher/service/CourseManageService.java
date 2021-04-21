package com.onteacher.service;

import com.onteacher.vo.Course;
import com.onteacher.vo.Homework;
import com.onteacher.vo.StudentReview;

public interface CourseManageService {
	public void setHomework(Homework hw) throws Exception;
	public Homework queryHomework(int id) throws Exception;
	public void writeStudentReview(StudentReview sr) throws Exception;
	public void startCourse(int courseId) throws Exception;
	public void extendCourse(int courseId, String date) throws Exception;
	public void cancelCourse(Course c) throws Exception;
}
