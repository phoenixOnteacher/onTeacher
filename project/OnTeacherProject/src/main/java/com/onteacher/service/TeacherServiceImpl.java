package com.onteacher.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.onteacher.dao.CourseReviewDAO;
import com.onteacher.dao.NotificationDAO;
import com.onteacher.dao.TeacherDAO;
import com.onteacher.vo.CourseReview;
import com.onteacher.vo.Notification;
import com.onteacher.vo.Teacher;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	TeacherDAO teacherDAO;
	
	@Autowired
	CourseReviewDAO couresReviewDAO;
	
	@Autowired
	NotificationDAO notificationDAO;
	
	@Override
	public void thjoin(Teacher thacher) throws Exception {
		Teacher steacher = teacherDAO.selectTeacher(thacher.getEmail());
		if(steacher!=null) throw new Exception("이메일 중복");
		teacherDAO.insertTeacher(thacher);
	}

	@Override
	public boolean login(String email, String password) throws Exception {
		Teacher teacher = teacherDAO.selectTeacher(email);
		if(teacher==null) throw new Exception("이메일 오류");
		if(teacher.getPassword().equals(password)) {
			return true;
		}
		return false;
	}

	
	@Override
	public List<Teacher> certConfirm() throws Exception {
		return teacherDAO.baseCertTeacher();
	}

	@Override
	public void certApproved(String email) throws Exception {
		Teacher teacher = teacherDAO.selectTeacherByEmail(email);
		teacher.setMessage("승인됨");
		teacher.setStatus("approved");
		teacherDAO.updateActive(email);
		teacherDAO.updateCert(teacher);
		Notification notification = new Notification();
		notification.setContent("[자격 증명(승인)] 지금부터 온티처의 서비스를 이용하실 수 있어요.");
		notification.setToId(teacher.getId());
		notificationDAO.insertNotification(notification);
	}
	
	@Override
	public void certRejected(String email, String message) throws Exception {
//		teacherDAO.updateActive(email); 반려된 경우 ACTIVE는 0으로 변하지 않음 (수정:김다니엘)
		Teacher teacher = teacherDAO.selectTeacherByEmail(email);
		teacher.setMessage(message);
		teacher.setStatus("rejected");
		teacherDAO.updateCert(teacher);
		Notification notification = new Notification();
		notification.setContent("[자격 증명(반려)] 사유 : " + message);
		notification.setToId(teacher.getId());
		notificationDAO.insertNotification(notification);
	}

	@Override
	public Teacher teacherInfo(int teacherId) throws Exception {
		Teacher teacher = teacherDAO.selectTeacherByTeacherId(teacherId);
		if(teacher==null) {
			throw new Exception("정보 없음");
		}
		List<CourseReview> reviewList = couresReviewDAO.selectCourseReviewByTeacher(teacherId);
		teacher.setCourseReviewList(reviewList);
		return teacher;
	}

	@Override
	public void updateTeacherCert(Teacher teacher) {
		teacherDAO.updateTeacherCert(teacher);
	}
	
}

