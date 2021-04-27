package com.onteacher.dao;

//import java.util.List; 

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.onteacher.vo.Search;

@Mapper
@Repository("searchDAO")
public interface SearchDAO {
	public void search(Search search) throws Exception;
//	public List<search> selectsearchList() throws Exception;

}
