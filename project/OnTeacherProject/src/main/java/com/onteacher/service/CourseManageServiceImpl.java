package com.onteacher.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.onteacher.dao.CourseDAO;
import com.onteacher.dao.HighCategoryDAO;
import com.onteacher.dao.HomeworkDAO;
import com.onteacher.dao.LowCategoryDAO;

import com.onteacher.dao.StudentDAO;
import com.onteacher.dao.StudentReviewDAO;
import com.onteacher.vo.Course;
import com.onteacher.vo.HighCategory;
import com.onteacher.vo.Homework;
import com.onteacher.vo.LowCategory;
import com.onteacher.vo.Student;
import com.onteacher.vo.StudentReview;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class CourseManageServiceImpl implements CourseManageService {

	@Autowired
	private HomeworkDAO homeworkDAO;
	
	@Autowired
	private StudentReviewDAO studentReviewDAO;
	
	@Autowired
	private CourseDAO courseDAO;
	
	@Autowired
	private HighCategoryDAO highCategoryDAO;

	@Autowired
	private LowCategoryDAO lowCategoryDAO;

	@Autowired
	private StudentDAO studentDAO;
	
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
		if (c.getTeacherId()==course.getTeacherId()) {
			courseDAO.deleteCourse(c.getId());
		} else throw new Exception("해당 수업의 선생님만 취소 가능");
	}
	
	@Override
	public List<Course> queryStudyingCourseList(int teacherId) throws Exception {
		List<Course> courseList = courseDAO.selectStudyingCourseList(teacherId);
		for (Course course : courseList) {
			course.setStudentList(studentDAO.selectMatchingStudentByCourseId(course.getId()));
			course.setStartDate(course.getStartDate().substring(0,10));
			course.setEndDate(course.getEndDate().substring(0,10));
		}
		return courseList;
	}

	@Override
	public List<Course> queryMatchingCourseList(int teacherId) throws Exception {
		List<Course> courseList = courseDAO.selectMatchingCourseList(teacherId);
		for (Course course : courseList) {
			course.setStudentList(studentDAO.selectMatchingStudentByCourseId(course.getId()));
			course.setStartDate(course.getStartDate().substring(0,10));
			course.setEndDate(course.getEndDate().substring(0,10));
		}
		return courseList;
	}

	@Override
	public List<Course> queryMatchedCourseList(int teacherId) throws Exception {
		List<Course> courseList = courseDAO.selectMatchedCourseList(teacherId);
		for (Course course : courseList) {
			course.setStudentList(studentDAO.selectMatchingStudentByCourseId(course.getId()));
			course.setStartDate(course.getStartDate().substring(0,10));
			course.setEndDate(course.getEndDate().substring(0,10));
		}
		return courseList;
	}

	@Override
	public List<Course> queryEndCourseList(int teacherId) throws Exception {
		List<Course> courseList = courseDAO.selectEndCourseList(teacherId);
		for (Course course : courseList) {
			course.setStudentList(studentDAO.selectMatchingStudentByCourseId(course.getId()));
			course.setStartDate(course.getStartDate().substring(0,10));
			course.setEndDate(course.getEndDate().substring(0,10));
		}
		return courseList;
	}
	
	@Override
	public List<Student> queryMatchingStudentList(int courseId) throws Exception {
		List<Student> studentList = studentDAO.selectMatchingStudentByCourseId(courseId);
		for (Student student : studentList) {
			String phoneNum = student.getPhoneNumber();
			student.setPhoneNumber(phoneNum.substring(0,3)+"-"+phoneNum.substring(3,7)+"-"+phoneNum.substring(7,11));
			student.setBirthday(student.getBirthday().substring(0,10));
		}
		return studentList;
	}
	
	@Override
	public List<Homework> queryHomeworkList(int courseId) throws Exception {
		return homeworkDAO.selectHomeworkListByCourseId(courseId);
	}
	
	@Override
	public List<HighCategory> getHighCategory() throws Exception {
		return highCategoryDAO.selectHighCategory();
	}

	@Override
	public List<LowCategory> getLowCategory(int high_category_id) throws Exception {
		return lowCategoryDAO.selectLowCategory(high_category_id);
	}

	@Override
	public void registerCourse(Course course) throws Exception {
		if (course.getCurriculumFile() == null)
			course.setCurriculumFile("");
		courseDAO.insertCourse(course);
	}
}
