package com.onteacher.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("matchingDAO")
public interface MatchingDAO {

	public void deleteMatchingData(@Param("studentId") int studentId,@Param("courseId")  int courseId);

}
