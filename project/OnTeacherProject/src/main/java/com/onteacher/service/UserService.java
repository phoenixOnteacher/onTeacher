package com.onteacher.service;

import java.util.List;

import com.onteacher.vo.Notification;
import com.onteacher.vo.Course;
import com.onteacher.vo.HighCategory;

public interface UserService {
	public List<Course> queryCourseForSearch(Course course);
	public List<HighCategory> highcategoryList();
	public int login(String email, String password) throws Exception;
	public List<Notification> queryNotificationList(int userId) throws Exception;
	public void deleteNotification(int id) throws Exception;
	public List<Course> selectMaxCourseNO();
	public List<Course> selectAllCourseList();
	public List<Course> CourseList(Course courseList);
}
