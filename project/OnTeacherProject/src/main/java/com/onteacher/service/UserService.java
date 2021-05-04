package com.onteacher.service;


import java.util.List;

import com.onteacher.vo.Course;
import com.onteacher.vo.HighCategory;


public interface UserService<CourseList> {
	public List<Course> queryCourseForSearch(Course course);
	public List<HighCategory> highcategoryList();
	public int login(String email, String password) throws Exception;

	public List<Course> selectMaxCourseNO();
	public List<Course> selectAllCourseList();
	public List<Course> CourseList(Course courseList);
	public int Course();	

}
