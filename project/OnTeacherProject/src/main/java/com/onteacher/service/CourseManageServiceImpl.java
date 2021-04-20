package com.onteacher.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.onteacher.dao.CourseManageDAO;
import com.onteacher.vo.HighCategory;
import com.onteacher.vo.LowCategory;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class CourseManageServiceImpl implements CourseManageService {

	@Autowired
	CourseManageDAO courseManageDAO;

	@Override
	public List<HighCategory> getHighCategory() throws Exception {
		return courseManageDAO.selectHighCategory();
	}

	@Override
	public List<LowCategory> getLowCategory(int high_category_id) throws Exception {
		return courseManageDAO.selectLowCategory(high_category_id);
	}
}
