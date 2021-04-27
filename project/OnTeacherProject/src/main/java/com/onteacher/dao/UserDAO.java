package com.onteacher.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.onteacher.vo.User;

@Mapper
@Repository("userDAO")
public interface UserDAO {
	public User selectUser(String email) throws Exception;
}
