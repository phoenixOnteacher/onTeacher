package com.onteacher.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.onteacher.vo.Matching;

@Mapper
@Repository("matchingDAO")
public interface MatchingDAO {
	public void insertMatching(Matching matching) throws Exception;
	public Matching selectMatching(Matching matching) throws Exception;
	public void deleteMatching(Matching matching) throws Exception;
	public List<Matching> selectMatchingListByCourseId(int courseId) throws Exception;
}
