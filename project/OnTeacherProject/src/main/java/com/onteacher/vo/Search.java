package com.onteacher.vo;

import org.springframework.stereotype.Component;

@Component("search")
public class Search {
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
	private int highCategoryId; //FK
	private int lowCategoryId; //FK
	
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public char getIsGroup() {
		return isGroup;
	}
	public void setIsGroup(char isGroup) {
		this.isGroup = isGroup;
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
	public char getIsOneday() {
		return isOneday;
	}
	public void setIsOneday(char isOneday) {
		this.isOneday = isOneday;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public char getIsOnline() {
		return isOnline;
	}
	public void setIsOnline(char isOnline) {
		this.isOnline = isOnline;
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
		return "Search [target=" + target + ", isOneday=" + isOneday + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", studyDay=" + studyDay + ", studyTime=" + studyTime + ", isOnline="
				+ isOnline + ", location=" + location + ", isGroup=" + isGroup + ", minStudent=" + minStudent
				+ ", maxStudent=" + maxStudent + ", highCategoryId="+ highCategoryId + ", lowCategoryId=" + lowCategoryId  ;
	}
}