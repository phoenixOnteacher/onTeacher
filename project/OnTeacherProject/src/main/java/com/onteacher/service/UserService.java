package com.onteacher.service;

import java.util.List;

import com.onteacher.vo.Course;
import com.onteacher.vo.HighCategory;

public interface UserService {
	public List<Course> queryCourseForSearch(Course course);
	public List<HighCategory> highcategoryList();
}
