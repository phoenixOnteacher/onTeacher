package com.onteacher.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("course")
public class Course {
	
	private int id; //PK
	private String target;
	private boolean isOneday;
	private String startDate; //Date
	private String endDate; //Date
	private String studyDay;
	private String studyTime;
	private boolean isOnline;
	private String location;
	private boolean isGroup;
	private int minStudent;
	private int maxStudent;
	private String title;
	private String curriculum;
	private String curriculumFile;
	private String status;
	private int teacherId; //FK
	private int highCategoryId; //FK
	private int lowCategoryId; //FK
	
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
	public boolean isOneday() {
		return isOneday;
	}
	public void setOneday(boolean isOneday) {
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
	public boolean isOnline() {
		return isOnline;
	}
	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public boolean isGroup() {
		return isGroup;
	}
	public void setGroup(boolean isGroup) {
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
	
	@Override
	public String toString() {
		return "Course [id=" + id + ", target=" + target + ", isOneday=" + isOneday + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", studyDay=" + studyDay + ", studyTime=" + studyTime + ", isOnline="
				+ isOnline + ", location=" + location + ", isGroup=" + isGroup + ", minStudent=" + minStudent
				+ ", maxStudent=" + maxStudent + ", title=" + title + ", curriculum=" + curriculum + ", curriculumFile="
				+ curriculumFile + ", status=" + status + ", teacherId=" + teacherId + ", highCategoryId="
				+ highCategoryId + ", lowCategoryId=" + lowCategoryId + "]";
	}
}