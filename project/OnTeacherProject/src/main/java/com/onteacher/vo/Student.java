package com.onteacher.vo;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component("student")
public class Student {
	
	private int id; //PK
	private String email;
	private String name;
	private String password;
	private String phoneNumber;
	private String grade;
	private String address;
	private String birthday; //Date
	private String gender;
	private MultipartFile file;
	private String profileImg;
	private String introduction;
	private StudentReview studentReview;
	private List<StudentReview> studentReviewList;
	private HomeworkAnswer homeworkAnswer;
	
	public StudentReview getStudentReview() {
		return studentReview;
	}
	public void setStudentReview(StudentReview studentReview) {
		this.studentReview = studentReview;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public String getProfileImg() {
		return profileImg;
	}
	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public List<StudentReview> getStudentReviewList() {
		return studentReviewList;
	}
	public void setStudentReviewList(List<StudentReview> studentReviewList) {
		this.studentReviewList = studentReviewList;
	}
	public HomeworkAnswer getHomeworkAnswer() {
		return homeworkAnswer;
	}
	public void setHomeworkAnswer(HomeworkAnswer homeworkAnswer) {
		this.homeworkAnswer = homeworkAnswer;
	}
	
}
