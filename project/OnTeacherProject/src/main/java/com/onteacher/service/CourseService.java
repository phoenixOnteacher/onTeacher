package com.onteacher.service;

import java.util.List;

import com.onteacher.vo.Course;
import com.onteacher.vo.HighCategory;
import com.onteacher.vo.Homework;
import com.onteacher.vo.HomeworkAnswer;
import com.onteacher.vo.LowCategory;
import com.onteacher.vo.Teacher;

public interface CourseService {

	public Course queryCourseById(int courseId) throws Exception;
	
	public Homework queryHomework(int id) throws Exception;
	
	public List<Homework> queryHomeworkList(int courseId) throws Exception;

	public HighCategory queryHighCategoryById(int highCategoryId);

	public LowCategory queryLowCategoryById(int lowCategoryId);

	public Teacher queryTeacherById(int teacherId);
	
	public List<Course> selectCourseForIndex();

	public List<Course> courseMatchingList(int studentId);

	public HomeworkAnswer queryHomeworkAnswer(int homework_id, int user_id);
	
	public List<Course> selectCourseForSearch();
}
