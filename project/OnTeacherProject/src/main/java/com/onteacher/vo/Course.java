package com.onteacher.vo;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component("course")
public class Course {
	private int id; //PK
	private String target;
	private char isOneday;
	private String startDate; //Date
	private String endDate; //Date
	private String studyDay;
	private String studyTime;
	private char isOnline;
	private String location;
	private char isGroup;
	private int minStudent;
	private int maxStudent;
	private String title;
	private String curriculum;
	private MultipartFile file;
	private String curriculumFile;
	private String status;
	private int teacherId; //FK
	private int highCategoryId; //FK
	private int lowCategoryId; //FK
	private Teacher teacher;
	private List<Student> studentList;
	private int startPage;
	private int endPage;
	private int s_row;
	private int e_row;
	private String lowCategoryName;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public char getIsOneday() {
		return isOneday;
	}
	public void setIsOneday(char isOneday) {
		this.isOneday = isOneday;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getStudyDay() {
		return studyDay;
	}
	public void setStudyDay(String studyDay) {
		this.studyDay = studyDay;
	}
	public String getStudyTime() {
		return studyTime;
	}
	public void setStudyTime(String studyTime) {
		this.studyTime = studyTime;
	}
	public char getIsOnline() {
		return isOnline;
	}
	public void setIsOnline(char isOnline) {
		this.isOnline = isOnline;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public char getIsGroup() {
		return isGroup;
	}
	public void setIsGroup(char isGroup) {
		this.isGroup = isGroup;
	}
	public int getMinStudent() {
		return minStudent;
	}
	public void setMinStudent(int minStudent) {
		this.minStudent = minStudent;
	}
	public int getMaxStudent() {
		return maxStudent;
	}
	public void setMaxStudent(int maxStudent) {
		this.maxStudent = maxStudent;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCurriculum() {
		return curriculum;
	}
	public void setCurriculum(String curriculum) {
		this.curriculum = curriculum;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public String getCurriculumFile() {
		return curriculumFile;
	}
	public void setCurriculumFile(String curriculumFile) {
		this.curriculumFile = curriculumFile;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}
	public int getHighCategoryId() {
		return highCategoryId;
	}
	public void setHighCategoryId(int highCategoryId) {
		this.highCategoryId = highCategoryId;
	}
	public int getLowCategoryId() {
		return lowCategoryId;
	}
	public void setLowCategoryId(int lowCategoryId) {
		this.lowCategoryId = lowCategoryId;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public List<Student> getStudentList() {
		return studentList;
	}
	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getS_row() {
		return s_row;
	}
	public void setS_row(int s_row) {
		this.s_row = s_row;
	}
	public int getE_row() {
		return e_row;
	}
	public void setE_row(int e_row) {
		this.e_row = e_row;
	}
	public String getLowCategoryName() {
		return lowCategoryName;
	}
	public void setLowCategoryName(String lowCategoryName) {
		this.lowCategoryName = lowCategoryName;
	}
	
	@Override
	public String toString() {
		return "Course [id=" + id + ", target=" + target + ", isOneday=" + isOneday + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", studyDay=" + studyDay + ", studyTime=" + studyTime + ", isOnline="
				+ isOnline + ", location=" + location + ", isGroup=" + isGroup + ", minStudent=" + minStudent
				+ ", maxStudent=" + maxStudent + ", title=" + title + ", curriculum=" + curriculum + ", curriculumFile="
				+ curriculumFile + ", status=" + status + ", teacherId=" + teacherId + ", highCategoryId="
				+ highCategoryId + ", lowCategoryId=" + lowCategoryId + ", studentList=" + studentList + ", startPage=" + startPage
				+ ", endPage=" + endPage + ", s_row=" + s_row + ", e_row=" + e_row + "]";
	}

	
}