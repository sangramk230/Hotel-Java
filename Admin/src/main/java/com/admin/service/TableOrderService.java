package com.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.admin.dao.TableOrderDao;
import com.admin.entity.TableOrder;

@Service
public class TableOrderService {

	@Autowired
	private TableOrderDao tableOrderDao;

	@Transactional
	public TableOrder placeOrder(TableOrder order) {
		return tableOrderDao.placeOrder(order);
	}

	@Transactional
	public TableOrder updateOrder(TableOrder order) {
		return tableOrderDao.updateOrder(order);
	}

	@Transactional
	public void cancelOrder(Long orderId) {
		tableOrderDao.cancelOrder(orderId);
	}

	public TableOrder getOrderById(Long orderId) {
		return tableOrderDao.getOrderById(orderId);
	}

	public List<TableOrder> getAllOrders() {
		return tableOrderDao.getAllOrders();
	}

	public boolean clearTable(Long tableid) {
		return tableOrderDao.clearTable(tableid);
	}

}
