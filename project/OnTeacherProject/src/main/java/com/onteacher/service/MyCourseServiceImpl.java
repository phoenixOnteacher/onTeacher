package com.onteacher.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.onteacher.dao.CourseDAO;
import com.onteacher.dao.CourseReviewDAO;
import com.onteacher.dao.HomeworkAnswerDAO;
import com.onteacher.dao.MatchingDAO;
import com.onteacher.dao.TeacherDAO;
import com.onteacher.vo.Course;
import com.onteacher.vo.CourseReview;
import com.onteacher.vo.HomeworkAnswer;
import com.onteacher.vo.Teacher;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class MyCourseServiceImpl implements MyCourseService {

	@Autowired
	MatchingDAO matchingDAO;
	
	@Autowired
	CourseDAO courseDAO;
	
	@Autowired
	TeacherDAO teacherDAO;
	
	@Autowired
	CourseReviewDAO courseReviewDAO;
	
	@Autowired
	HomeworkAnswerDAO homeworkAnswerDAO;

	@Override
	public void cancelMatching(int studentId, int courseId) {
		matchingDAO.deleteMatchingData(studentId,courseId);
	}

	@Override
	public List<Course> queryMatchingcourseListByStudentId(int studentId) {
		return courseDAO.selectCourseMatchingListByStudentId(studentId);
	}

	@Override
	public List<Course> queryMatchedcourseListByStudentId(int studentId) {
		return courseDAO.selectCourseMatchedListByStudentId(studentId);
	}

	@Override
	public List<Course> queryStudyingcourseListByStudentId(int studentId) {
		return courseDAO.selectCourseStudyingListByStudentId(studentId);
	}

	@Override
	public List<Course> queryEndcourseListByStudentId(int studentId) {
		List<Course> courseList = courseDAO.selectCourseEndListByStudentId(studentId);
		for (Course course : courseList) {
			Teacher teacher = course.getTeacher();
			CourseReview cr = new CourseReview();
			cr.setCourseId(course.getId());
			cr.setStudentId(studentId);
			cr.setTeacherId(teacher.getId());
			teacher.setCourseReview(courseReviewDAO.selectCourseReview(cr));
		}
		return courseList;
	}

	@Override
	public Teacher queryMatchingTeacher(int courseId) {
		Teacher teacher = teacherDAO.selectMatchingTeacherByCourseId(courseId);
		teacher.setBirthday(teacher.getBirthday().substring(0, 10));
		return teacher;
	}

	@Override
	public void writeCourseReview(CourseReview cr) {
		courseReviewDAO.insertCourseReview(cr);
	}

	@Override
	public void createHomeworkAnswer(HomeworkAnswer ha) throws Exception {
		homeworkAnswerDAO.insertHomeworkAnswer(ha);
	}
}
