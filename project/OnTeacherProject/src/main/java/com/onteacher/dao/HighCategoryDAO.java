package com.onteacher.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.onteacher.vo.HighCategory;

@Mapper
@Repository("highCategoryDAO")
public interface HighCategoryDAO {
	public List<HighCategory> selectHighCategory() throws Exception;
}
