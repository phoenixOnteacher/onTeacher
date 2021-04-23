package com.onteacher.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.onteacher.dao.CourseDAO;
import com.onteacher.dao.HighCategoryDAO;
import com.onteacher.dao.LowCategoryDAO;
import com.onteacher.vo.Course;
import com.onteacher.vo.HighCategory;
import com.onteacher.vo.LowCategory;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class CourseManageServiceImpl implements CourseManageService {

	@Autowired
	private HighCategoryDAO highCategoryDAO;

	@Autowired
	private LowCategoryDAO lowCategoryDAO;

	@Autowired
	private CourseDAO courseDAO;

	@Override
	public List<HighCategory> getHighCategory() throws Exception {
		return highCategoryDAO.selectHighCategory();
	}

	@Override
	public List<LowCategory> getLowCategory(int high_category_id) throws Exception {
		return lowCategoryDAO.selectLowCategory(high_category_id);
	}

	@Override
	public void registerCourse(Course course) throws Exception {
		if (course.getCurriculumFile() == null)
			course.setCurriculumFile("");
		courseDAO.insertCourse(course);
	}
}
