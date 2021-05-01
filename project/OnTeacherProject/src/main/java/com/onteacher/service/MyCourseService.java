package com.onteacher.service;

import java.util.List;

import com.onteacher.vo.Course;
import com.onteacher.vo.Teacher;

public interface MyCourseService {

	public void cancleMatching(int studentId, int courseId);

	public List<Course> queryMatchingcourseListByStudentId(int studentId);

	public List<Course> queryMatchedcourseListByStudentId(int studentId);

	public List<Course> queryStudyingcourseListByStudentId(int studentId);

	public List<Course> queryEndcourseListByStudentId(int studentId);

	public Teacher queryMatchingTeacher(int courseId);
	
}
