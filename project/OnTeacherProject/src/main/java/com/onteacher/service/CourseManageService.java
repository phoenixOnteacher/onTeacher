package com.onteacher.service;

import com.onteacher.vo.Homework;

public interface CourseManageService {
	public void setHomework(Homework hw) throws Exception;
	public Homework queryHomework(int id) throws Exception;
}
