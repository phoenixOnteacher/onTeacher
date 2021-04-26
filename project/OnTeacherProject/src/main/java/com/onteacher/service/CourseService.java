package com.onteacher.service;

import java.util.List;

import com.onteacher.vo.Course;
import com.onteacher.vo.Homework;

public interface CourseService {

	//대기중인 수업 목록 조회
	public List<Course> courseWaitingList(int studentId); //MAP으로 JOIN
	
	//진행중인 수업 목록 조회
	public List<Course> courseStudyingList(int studentId);

	//종료된 수업 목록 조회
	public List<Course> courseEndList(int studentId);

	public Course queryCourseById(int courseId) throws Exception;
	
	public void cancleMatching(int studentId, int courseId);
	
	public Homework queryHomework(int id) throws Exception;
	
	public List<Homework> queryHomeworkList(int courseId) throws Exception;
}
