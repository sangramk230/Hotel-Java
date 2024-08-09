package com.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.dao.OrderFoodDao;
import com.customer.entity.MenuItems;
import com.customer.entity.TableOrder;

@Service
public class OrderFoodService {

	@Autowired
	private OrderFoodDao orderFoodDao;

	// Add an order for a customer
	public TableOrder addOrder(Long tableId, TableOrder order) {
		System.out.println("sdcsdvsdvv" + tableId);
		order.setTableid(tableId);
		return orderFoodDao.addOrder(order);
	}

	// Update an order for a customer
	public List<TableOrder> updateOrder(List<TableOrder> updatedOrder) {

		return orderFoodDao.updateOrder(updatedOrder);
	}

	// Delete an order
	public TableOrder deleteOrder(Long id) {
		return orderFoodDao.deleteOrder(id);

	}

	public List<MenuItems> getAllMenuItems() {
		return orderFoodDao.getAllMenuItems();
	}

	public List<MenuItems> getCategory(String category) {
		return orderFoodDao.getCategory(category);
	}

	public TableOrder placeOrder(Long tableid, TableOrder order) {
		order.setTableid(tableid);
		return orderFoodDao.placeOrder(order);
	}

	public List<TableOrder> getOrderItems(Long tableId) {
		return orderFoodDao.getOrderItems(tableId);
	}
}
