package com.onteacher.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.onteacher.vo.HighCategory;
import com.onteacher.vo.LowCategory;

@Mapper
@Repository("courseManageDAO")
public interface CourseManageDAO {
	public List<HighCategory> selectHighCategory() throws Exception;
	public List<LowCategory> selectLowCategory(int high_category_id) throws Exception;
}
