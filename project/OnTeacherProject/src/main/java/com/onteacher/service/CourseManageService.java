package com.onteacher.service;

import java.util.List;

import com.onteacher.vo.Course;
import com.onteacher.vo.HighCategory;
import com.onteacher.vo.Homework;
import com.onteacher.vo.LowCategory;
import com.onteacher.vo.Student;
import com.onteacher.vo.StudentReview;

public interface CourseManageService {
	public void setHomework(Homework hw) throws Exception;
	public Homework queryHomework(int id) throws Exception;
	public void writeStudentReview(StudentReview sr) throws Exception;
	public void startCourse(int courseId) throws Exception;
	public void extendCourse(int courseId, String date) throws Exception;
	public void cancelCourse(Course c) throws Exception;
	public List<Course> queryStudyingCourseList(int teacherId) throws Exception;
	public List<Course> queryMatchingCourseList(int teacherId) throws Exception;
	public List<Course> queryMatchedCourseList(int teacherId) throws Exception;
	public List<Course> queryEndCourseList(int teacherId) throws Exception;
	public List<Student> queryMatchingStudentList(int courseId) throws Exception;
	public List<Homework> queryHomeworkList(int courseId) throws Exception;
	public List<HighCategory> getHighCategory() throws Exception;
	public List<LowCategory> getLowCategory(int high_category_id) throws Exception;
}
