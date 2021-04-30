package com.onteacher.service;

import java.util.List;

import com.onteacher.vo.Course;
import com.onteacher.vo.HighCategory;
import com.onteacher.vo.Homework;
import com.onteacher.vo.LowCategory;
import com.onteacher.vo.Teacher;

public interface CourseService {

	//대기중인 수업 목록 조회
	public List<Course> courseWaitingList(int studentId); //MAP으로 JOIN
	
	//진행중인 수업 목록 조회
	public List<Course> courseStudyingList(int studentId);

	//종료된 수업 목록 조회
	public List<Course> courseEndList(int studentId);

	public Course queryCourseById(int courseId) throws Exception;
	
	public Homework queryHomework(int id) throws Exception;
	
	public List<Homework> queryHomeworkList(int courseId) throws Exception;

	public HighCategory queryHighCategoryById(int highCategoryId);

	public LowCategory queryLowCategoryById(int lowCategoryId);

	public Teacher queryTeacherById(int teacherId);
	
	public List<Course> selectCourseForIndex();
}
