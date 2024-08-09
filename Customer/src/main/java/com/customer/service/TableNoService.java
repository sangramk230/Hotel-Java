package com.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.dao.TableNoDao;

@Service
public class TableNoService {
	@Autowired
	TableNoDao tableNoDao;

	public Boolean tableLogin(Long tableid) {
		return tableNoDao.tableLogin(tableid);
	}

}
