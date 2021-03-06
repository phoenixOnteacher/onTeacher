package com.onteacher.service;

import java.util.List;

import com.onteacher.vo.Course;
import com.onteacher.vo.CourseReview;
import com.onteacher.vo.HomeworkAnswer;
import com.onteacher.vo.Teacher;

public interface MyCourseService {
	public void cancelMatching(int studentId, int courseId);
	public List<Course> queryMatchingcourseListByStudentId(int studentId);
	public List<Course> queryMatchedcourseListByStudentId(int studentId);
	public List<Course> queryStudyingcourseListByStudentId(int studentId);
	public List<Course> queryEndcourseListByStudentId(int studentId);
	public Teacher queryMatchingTeacher(int courseId);
	public void writeCourseReview(CourseReview cr);
	public void createHomeworkAnswer(HomeworkAnswer ha) throws Exception;
}
