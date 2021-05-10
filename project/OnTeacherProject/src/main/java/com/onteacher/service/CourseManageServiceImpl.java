package com.onteacher.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.onteacher.dao.CourseDAO;
import com.onteacher.dao.HighCategoryDAO;
import com.onteacher.dao.HomeworkAnswerDAO;
import com.onteacher.dao.HomeworkDAO;
import com.onteacher.dao.LowCategoryDAO;
import com.onteacher.dao.MatchingDAO;
import com.onteacher.dao.NotificationDAO;
import com.onteacher.dao.StudentDAO;
import com.onteacher.dao.StudentReviewDAO;
import com.onteacher.prop.UploadPath;
import com.onteacher.vo.Course;
import com.onteacher.vo.HighCategory;
import com.onteacher.vo.Homework;
import com.onteacher.vo.HomeworkAnswer;
import com.onteacher.vo.LowCategory;
import com.onteacher.vo.Matching;
import com.onteacher.vo.Notification;
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

	@Autowired
	private MatchingDAO matchingDAO;

	@Autowired
	private HomeworkAnswerDAO homeworkAnswerDAO;
	
	@Autowired
	private NotificationDAO notificationDAO;

	@Autowired
	private UploadPath uploadPath;
	
	@Override
	public void setHomework(Homework hw) throws Exception {
		homeworkDAO.insertHomework(hw);
	}
	
	@Override
	public void writeStudentReview(StudentReview sr) throws Exception {
		studentReviewDAO.insertStudentReview(sr);
	}
	
	@Override
	public void startCourse(int courseId) throws Exception {
		Course course = courseDAO.selectCourseById(courseId);
		course.setStatus("studying");
		Notification notification = new Notification();
		notification.setContent("[" + course.getTitle() +"] 수업이 시작되었습니다.");
		List<Matching> matchings = matchingDAO.selectMatchingListByCourseId(course.getId());
		for (Matching matching : matchings) {
			notification.setToId(matching.getStudentId());
			notificationDAO.insertNotification(notification);
		}
		notificationDAO.insertNotification(notification);
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
			Notification notification = new Notification();
			notification.setContent("[" + course.getTitle() +"] 수업이 취소되었습니다.");
			List<Matching> matchings = matchingDAO.selectMatchingListByCourseId(course.getId());
			for (Matching matching : matchings) {
				notification.setToId(matching.getStudentId());
				notificationDAO.insertNotification(notification);
			}
			courseDAO.deleteCourse(c.getId());
		} else throw new Exception("해당 수업의 선생님만 취소 가능");
	}
	
	// 매칭하기
	@Override
	public void match(int courseId, List<String> selectedStudents) throws Exception {
		List<Matching> matchingList = matchingDAO.selectMatchingListByCourseId(courseId);
		Course course = courseDAO.selectCourseById(courseId);
		Notification notification = new Notification();
		notification.setContent("[" + course.getTitle() +"] 수업 매칭이 성사되지 않았습니다.");
		for (Matching matching : matchingList) {
			int matchingStudentId = matching.getStudentId();
			boolean isSelected = false; // 선택된 학생인지 확인하기 위한 변수
			for (String selectedStudent_id : selectedStudents) {
				int selectedStudentId = Integer.parseInt(selectedStudent_id);
				if (matchingStudentId==selectedStudentId) {
					isSelected = true;
				}
			}
			// 선택되지 않은 학생들의 matching 데이터는 삭제됨
			if (!isSelected) {
				matchingDAO.deleteMatching(matching);
				notification.setToId(matching.getStudentId());
				notificationDAO.insertNotification(notification);
			}
		}
		course.setStatus("matched");
		courseDAO.updateCourseStatus(course);
	}
	
	@Override
	public void cancelMatching(Matching matching) throws Exception {
		matchingDAO.deleteMatching(matching);
		int courseId = matching.getCourseId();
		Course course = courseDAO.selectCourseById(courseId);
		Notification notification = new Notification();
		notification.setContent("[" + course.getTitle() +"] 수업 매칭이 취소되었습니다.");
		notification.setToId(matching.getStudentId());
		notificationDAO.insertNotification(notification);
		List<Matching> remain = matchingDAO.selectMatchingListByCourseId(courseId);
		if (remain.size()<course.getMinStudent()) { // 최소 인원보다 적어지면 수업 취소
			// 남아있는 학생들에게 매칭 취소 알림
			for (Matching m : remain) {
				notification.setToId(m.getStudentId());
				notificationDAO.insertNotification(notification);	
			}
			courseDAO.deleteCourse(courseId);
		}
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
	public List<Student> queryMatchingStudentList(int courseId, int teacherId) throws Exception {
		List<Student> studentList = studentDAO.selectMatchingStudentByCourseId(courseId);
		for (Student student : studentList) {
			student.setProfileImg(uploadPath.getStprofilePath()+student.getProfileImg());
			student.setBirthday(student.getBirthday().substring(0,10));
			StudentReview sr = new StudentReview();
			sr.setCourseId(courseId);
			sr.setStudentId(student.getId());
			sr.setTeacherId(teacherId);
			student.setStudentReview(studentReviewDAO.selectStudentReview(sr));
		}
		return studentList;
	}
	
	@Override
	public List<Student> queryStudentListAndHomeworkAnswer(int homeworkId) throws Exception {
		int courseId = homeworkDAO.selectHomeworkById(homeworkId).getCourseId();
		List<Student> studentList = studentDAO.selectMatchingStudentByCourseId(courseId);
		for (Student student : studentList) {
			String phoneNum = student.getPhoneNumber();
			student.setPhoneNumber(phoneNum.substring(0,3)+"-"+phoneNum.substring(3,7)+"-"+phoneNum.substring(7,11));
			student.setBirthday(student.getBirthday().substring(0,10));
			HomeworkAnswer ha = new HomeworkAnswer();
			ha.setStudentId(student.getId());
			ha.setHomeworkId(homeworkId);
			student.setHomeworkAnswer(homeworkAnswerDAO.selectHomeworkAnswer(ha));
		}
		return studentList;
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
