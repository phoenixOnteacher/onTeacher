package com.onteacher.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.onteacher.vo.Notification;

@Mapper
@Repository("notificationDAO")
public interface NotificationDAO {
	public void insertNotification(Notification notification) throws Exception;
	public List<Notification> selectNotificationList(int userId) throws Exception;
	public void deleteNotification(int id) throws Exception;
}
