package com.onteacher.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.onteacher.vo.Course;
import com.onteacher.vo.HighCategory;
import com.onteacher.vo.PagingVO;

@Mapper
@Repository("courseDAO")
public interface CourseDAO {

	public List<Course> selectCourseWaitingList(int studentId);

	public List<Course> selectCourseStudyingList(int studentId);

	public List<Course> selectCourseEndList(int studentId);

	public void insertCourse(Course course) throws Exception;
	public Course selectCourseById(int id) throws Exception;
	public void updateCourseStatus(Course c) throws Exception;
	public void deleteCourse(int id) throws Exception;
	public void updateCourseEndDate(Course c) throws Exception;
	public List<Course> selectStudyingCourseList(int teacherId) throws Exception;
	public List<Course> selectMatchingCourseList(int teacherId) throws Exception;
	public List<Course> selectMatchedCourseList(int teacherId) throws Exception;
	public List<Course> selectEndCourseList(int teacherId) throws Exception;
	public List<Course> selectCourseForSearch();
	public List<Course> selectCourseForSearchFilter(Course course);
	public List<HighCategory> highcategoryList();

	public List<Course> selectCourseForIndex();
	
	
	// 게시물 총 갯수
	public int countBoard();
	// 페이징 처리 게시글 조회
	public List<Course> selectBoard(PagingVO vo);

	public Course selectqueryCourseById(int courseId);

}
