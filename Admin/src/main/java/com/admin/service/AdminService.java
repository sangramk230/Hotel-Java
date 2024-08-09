package com.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.dao.AdminDao;
import com.admin.entity.Admin;

@Service
public class AdminService {
	@Autowired
	AdminDao adminDao;


	public Boolean login(String email, String password) {

		return adminDao.login(email, password);
	}


	public Admin profile(String email) {
		return adminDao.profile(email);
	}

	public Admin updateProfile(Admin updatedAdmin) {
		return adminDao.updateProfile(updatedAdmin);
	}

}
