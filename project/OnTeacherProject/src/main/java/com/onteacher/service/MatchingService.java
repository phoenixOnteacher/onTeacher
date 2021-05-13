package com.onteacher.service;

import java.util.List;

import com.onteacher.vo.Matching;
import com.onteacher.vo.Student;

public interface MatchingService {
	public Student queryStudentById(int studentId);
	public void insertMatching(Matching matching) throws Exception;
	public Matching selectMatching(Matching matching) throws Exception;
	public List<Matching> selectMatchingListByCourseId(int courseId) throws Exception;
}
