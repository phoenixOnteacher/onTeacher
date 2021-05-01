package com.onteacher.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.onteacher.dao.CourseDAO;
import com.onteacher.dao.UserDAO;
import com.onteacher.vo.Course;
import com.onteacher.vo.HighCategory;
import com.onteacher.vo.PagingVO;
import com.onteacher.vo.User;


@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserServiceImpl implements UserService {

	@Autowired

	CourseDAO courseDAO;
	
	@Override
	public List<Course> queryCourseForSearch(Course course) {
		return courseDAO.selectCourseForSearchFilter(course);
	}

	@Override
	public List<HighCategory> highcategoryList() {
		
		return courseDAO.highcategoryList();
	}
	
	@Autowired
	UserDAO userDAO;

	@Override
	public int login(String email, String password) throws Exception {
		User user = userDAO.selectUser(email);
		if(user==null) return 0;
		if(user.getPassword().equals(password)) {
			return user.getId();
		}
		return 0;
	}
	
	public int countBoard() {
		return courseDAO.countBoard();
	}
	public List<Course> selectBoard(PagingVO vo) {
		return courseDAO.selectBoard(vo);
	}
	
}


