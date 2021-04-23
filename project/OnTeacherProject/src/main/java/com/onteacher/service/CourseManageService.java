package com.onteacher.service;

import java.util.List;

import com.onteacher.vo.Course;
import com.onteacher.vo.HighCategory;
import com.onteacher.vo.LowCategory;

public interface CourseManageService {
	public List<HighCategory> getHighCategory() throws Exception;
	public List<LowCategory> getLowCategory(int high_category_id) throws Exception;
	public void registerCourse(Course course) throws Exception;
}
