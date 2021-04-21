package com.onteacher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.onteacher.dao.CourseDAO;
import com.onteacher.dao.HomeworkDAO;
import com.onteacher.dao.StudentReviewDAO;
import com.onteacher.vo.Course;
import com.onteacher.vo.Homework;
import com.onteacher.vo.StudentReview;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class CourseManageServiceImpl implements CourseManageService {

	@Autowired
	HomeworkDAO homeworkDAO;
	
	@Autowired
	StudentReviewDAO studentReviewDAO;
	
	@Autowired
	CourseDAO courseDAO;
	
	@Override
	public void setHomework(Homework hw) throws Exception {
		homeworkDAO.insertHomework(hw);
	}
	
	@Override
	public Homework queryHomework(int id) throws Exception {
		return homeworkDAO.selectHomeworkById(id);
	}
	
	@Override
	public void writeStudentReview(StudentReview sr) throws Exception {
		studentReviewDAO.insertStudentReview(sr);
	}
	
	@Override
	public void startCourse(int courseId) throws Exception {
		Course course = courseDAO.selectCourseById(courseId);
		course.setStatus("studying");
		courseDAO.updateCourseStatus(course);
	}
	
	@Override
	public void extendCourse(int courseId, String extendDate) throws Exception {
		Course course = courseDAO.selectCourseById(courseId);
		course.setEndDate(extendDate);
		courseDAO.updateCourseEndDate(course);
	}
	
	@Override
	public void cancelCourse(Course c) throws Exception {
		Course course = courseDAO.selectCourseById(c.getId());
		// 요청을 보낸 사용자가 해당 수업의 선생님인지 확인
		if (c.getTeacherId()==course.getTeacherId()) {
			courseDAO.deleteCourse(c.getId());
		} else throw new Exception("선생님만 수업 취소 가능");
	}
}
