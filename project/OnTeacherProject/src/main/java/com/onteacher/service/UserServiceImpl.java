package com.onteacher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.onteacher.dao.UserDAO;
import com.onteacher.vo.User;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDAO;

	@Override
	public int login(String email, String password) throws Exception {
		User user = userDAO.selectUser(email);
		if(user==null) throw new Exception("이메일 오류");
		if(user.getPassword().equals(password)) {
			return user.getId();
		}
		return 0;
	}
}
