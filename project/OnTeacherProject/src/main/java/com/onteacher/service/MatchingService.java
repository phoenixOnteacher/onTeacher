package com.onteacher.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.onteacher.vo.Course;
import com.onteacher.vo.Matching;
import com.onteacher.vo.Student;

public interface MatchingService {
	
	public Student queryStudentById(int studentId);
//	public Course queryCourseById(int courseId) throws Exception;	
	public void insertMatching(Matching matching) throws Exception;
	public Matching selectMatching(Matching matching) throws Exception;
	public void deleteMatching(Matching matching) throws Exception;
	public List<Matching> selectMatchingListByCourseId(int courseId) throws Exception;
	public void deleteMatchingData(@Param("studentId") int studentId,@Param("courseId")  int courseId);
	
	
}
