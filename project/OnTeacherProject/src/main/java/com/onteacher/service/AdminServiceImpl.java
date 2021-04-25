package com.onteacher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.onteacher.dao.AdminDAO;
import com.onteacher.vo.Admin;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminDAO adminDAO;

	@Override
	public void adjoin(Admin admin) throws Exception {
		Admin sadmin = adminDAO.selectAdmin(admin.getEmail());
		if(sadmin!=null) throw new Exception("이메일 중복");
		adminDAO.insertAdmin(admin);
	}

	@Override
	public boolean login(String email, String password) throws Exception {
		Admin admin = adminDAO.selectAdmin(email);
		if(admin==null) throw new Exception("이메일 오류");
		if(admin.getPassword().equals(password)) {
			return true;
		}
		return false;
	}
	
}
