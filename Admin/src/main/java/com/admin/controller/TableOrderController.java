package com.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.admin.entity.TableOrder;
import com.admin.service.TableOrderService;

import jakarta.servlet.http.HttpSession;

@Controller
@CrossOrigin("http://localhost:4201")
@RequestMapping("api/table/")
public class TableOrderController {

	@Autowired
	private TableOrderService tableOrderService;

	@Autowired
	private HttpSession httpSession;

	private boolean isAdminLoggedIn() {
		httpSession = AdminController.httpSession;
		return httpSession != null && httpSession.getAttribute("loggedInAdmin") != null;
	}

	@PutMapping("placeOrder")
	public ResponseEntity<TableOrder> placeOrder(@RequestBody TableOrder order) {
		if (!isAdminLoggedIn()) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		try {
			return new ResponseEntity<>(tableOrderService.placeOrder(order), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("updateOrder")
	public ResponseEntity<TableOrder> updateOrder(@RequestBody TableOrder order) {
		System.out.println("sdvvevvvvvvv" + order);
		if (!isAdminLoggedIn()) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		try {
			return new ResponseEntity<>(tableOrderService.updateOrder(order), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("cancelOrder/{id}")
	public ResponseEntity<String> cancelOrder(@PathVariable Long id) {
		if (!isAdminLoggedIn()) {
			return new ResponseEntity<>("Unauthorized access", HttpStatus.UNAUTHORIZED);
		}
		try {
			tableOrderService.cancelOrder(id);
			return new ResponseEntity<>("Order canceled successfully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Failed to cancel order", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("getOrderById/{orderId}")
	public ResponseEntity<TableOrder> getOrderById(@PathVariable Long orderId) {
		if (!isAdminLoggedIn()) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		try {
			TableOrder order = tableOrderService.getOrderById(orderId);
			if (order != null) {
				return new ResponseEntity<>(order, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("getAllOrders")
	public ResponseEntity<List<TableOrder>> getAllOrders() {
		if (!isAdminLoggedIn()) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		} else {
		try {
			List<TableOrder> orders = tableOrderService.getAllOrders();
			return new ResponseEntity<>(orders, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		}
	}
	
	@DeleteMapping("clearTable/{tableid}")
	public ResponseEntity<Void> clearTable(@PathVariable Long tableid) {
		if (!isAdminLoggedIn()) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		try {
			boolean isCleared = tableOrderService.clearTable(tableid);
			if (isCleared) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


}
