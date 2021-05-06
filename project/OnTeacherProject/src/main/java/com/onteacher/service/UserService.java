package com.onteacher.service;

import java.util.List;

import com.onteacher.vo.Notification;

public interface UserService {
	public int login(String email, String password) throws Exception;
	public List<Notification> queryNotificationList(int userId) throws Exception;
}
