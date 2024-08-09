package com.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.dao.CustomerOrderDao;
import com.admin.entity.CustomerOrder;

@Service
public class CustomerOrderService {

	@Autowired
	CustomerOrderDao customerOrderDao;

	public List<CustomerOrder> saveCustomerOrder(List<CustomerOrder> customerOrder) {
		return customerOrderDao.saveCustomerOrder(customerOrder);
	}


	public CustomerOrder getCustomerById(Long customerId) {
		return customerOrderDao.getCustomerById(customerId);
	}


	public List<CustomerOrder> getAllCustomers() {
		return customerOrderDao.getAllCustomers();
	}

}
