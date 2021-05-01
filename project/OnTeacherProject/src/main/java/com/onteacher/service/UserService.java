package com.onteacher.service;


import java.util.List;

import com.onteacher.vo.Course;
import com.onteacher.vo.HighCategory;
import com.onteacher.vo.PagingVO;

public interface UserService {
	public List<Course> queryCourseForSearch(Course course);
	public List<HighCategory> highcategoryList();
	public int login(String email, String password) throws Exception;
	
	// 게시물 총 갯수
	public static int countBoard() {
		// TODO Auto-generated method stub
		return 0;
	}

	// 페이징 처리 게시글 조회
	public static List<Course> selectBoard(PagingVO vo) {
		// TODO Auto-generated method stub
		return null;
	}
}
