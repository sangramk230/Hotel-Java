package com.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.customer.entity.MenuItems;
import com.customer.entity.TableOrder;
import com.customer.service.OrderFoodService;

import jakarta.servlet.http.HttpSession;

@Controller
@CrossOrigin("http://localhost:4200")
@RequestMapping("api/order/")
public class OrderFoodController {

	@Autowired
	private OrderFoodService orderFoodService;

	@Autowired
	private HttpSession httpSession;

	private Boolean isLoggedTable() {
		httpSession = TableNoController.httpSession;
		return httpSession != null && httpSession.getAttribute("loggedTable") != null;
	}

	// Add an order for a customer
	@PostMapping("add")
	public ResponseEntity<TableOrder> addOrder(@RequestBody TableOrder order) {
		if (!isLoggedTable()) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		try {
			Long tableId = (Long) httpSession.getAttribute("loggedTable");
			TableOrder createdOrder = orderFoodService.addOrder(tableId, order);
			return ResponseEntity.ok(createdOrder);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(500).body(null);
		}
	}

	// Update an order for a customer
	@PutMapping("update")
	public ResponseEntity<List<TableOrder>> updateOrder(@RequestBody List<TableOrder> updatedOrders) {
		if (!isLoggedTable()) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		try {
			List<TableOrder> updated = orderFoodService.updateOrder(updatedOrders);
			System.out.println("vevdvdvdv" + updated);
			if (updated != null) {
				return ResponseEntity.ok(updated);
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}


	// Delete an order
	@DeleteMapping("delete/{id}")
	public ResponseEntity<TableOrder> deleteOrder(@PathVariable Long id) {
		if (!isLoggedTable()) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		try {
			TableOrder deleted = orderFoodService.deleteOrder(id);
			if (deleted != null) {
				return ResponseEntity.ok(deleted);
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(500).body(null);
		}
	}

	@GetMapping("getAllMenuItems")
	public ResponseEntity<List<MenuItems>> getAllMenuItems() {
		if (!isLoggedTable()) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
			try {
				List<MenuItems> orders = orderFoodService.getAllMenuItems();
				return new ResponseEntity<>(orders, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}

	@GetMapping("getCategory/{category}")
	public ResponseEntity<List<MenuItems>> getCategory(@PathVariable String category) {

		List<MenuItems> menuItems = orderFoodService.getCategory(category);
		if (menuItems != null && !menuItems.isEmpty()) {
			return ResponseEntity.ok(menuItems);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@PostMapping("placeOrder")
	public ResponseEntity<TableOrder> placeOrder(@RequestBody TableOrder order) {
		if (!isLoggedTable()) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		try {
			Long tableId = (Long) httpSession.getAttribute("loggedTable");

			return new ResponseEntity<>(orderFoodService.placeOrder(tableId, order), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("getOrderItems")
	public ResponseEntity<List<TableOrder>> getOrderItems() {
		if (!isLoggedTable()) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		try {
			Long tableId = (Long) httpSession.getAttribute("loggedTable");

			List<TableOrder> orders = orderFoodService.getOrderItems(tableId);
			return new ResponseEntity<>(orders, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
