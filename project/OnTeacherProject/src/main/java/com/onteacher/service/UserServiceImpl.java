package com.onteacher.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.onteacher.dao.NotificationDAO;
import com.onteacher.dao.UserDAO;
import com.onteacher.vo.Notification;
import com.onteacher.vo.User;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDAO;

	@Autowired
	NotificationDAO notificationDAO;
	
	@Override
	public int login(String email, String password) throws Exception {
		User user = userDAO.selectUser(email);
		if(user==null) return 0;
		if(user.getPassword().equals(password)) {
			return user.getId();
		}
		return 0;
	}
	
	@Override
	public List<Notification> queryNotificationList(int userId) throws Exception {
		return notificationDAO.selectNotificationList(userId);
	}
}
