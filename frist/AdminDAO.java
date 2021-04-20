package com.onteacher.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.onteacher.vo.Admin;

@Mapper
@Repository("adminDAO")
public interface AdminDAO {
	public void insertAdmin(Admin adm) throws Exception;
	public Admin selectAdmin(String email) throws Exception;
}
