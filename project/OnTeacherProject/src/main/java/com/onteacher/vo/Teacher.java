package com.onteacher.vo;



import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component("teacher")
public class Teacher {
	
	private int id; //PK
	private String email;
	private String name;
	private String password;
	private String phoneNumber;
	private String address;
	private String birthday; //Date
	private String gender;
	private String profileImg;
	private String introduction;
	private boolean active;
	private String fileName;
	private String description;
	private String status;
	private String message;
	private MultipartFile file;
	private CourseReview courseReview;
	private List<CourseReview> courseReviewList;
	
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
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public CourseReview getCourseReview() {
		return courseReview;
	}
	public void setCourseReview(CourseReview courseReview) {
		this.courseReview = courseReview;
	}
	public List<CourseReview> getCourseReviewList() {
		return courseReviewList;
	}
	public void setCourseReviewList(List<CourseReview> courseReviewList) {
		this.courseReviewList = courseReviewList;
	}
	
	@Override
	public String toString() {
		return "Teacher [id=" + id + ", email=" + email + ", name=" + name + ", phoneNumber="
				+ phoneNumber + ", address=" + address + ", birthday=" + birthday + ", gender=" + gender
				+ ", profileImg=" + profileImg + ", introduction=" + introduction + ", active=" + active + ", fileName="
				+ fileName + ", description=" + description + ", status=" + status + ", message=" + message + ", file="
				+ file + ", courseReview=" + courseReview + ", courseReviewList=" + courseReviewList + "]";
	}
	
}
