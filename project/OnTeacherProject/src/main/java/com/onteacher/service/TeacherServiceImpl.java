package com.onteacher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.onteacher.dao.TeacherDAO;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	TeacherDAO teacherDAO;
}
