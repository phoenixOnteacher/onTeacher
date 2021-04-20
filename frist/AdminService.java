package com.onteacher.service;

import com.onteacher.vo.Admin;

public interface AdminService {
	public void adjoin(Admin admin) throws Exception;
	public boolean login(String email, String password) throws Exception;
}
