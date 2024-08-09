package com.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.admin.entity.CustomerOrder;
import com.admin.service.CustomerOrderService;

import jakarta.servlet.http.HttpSession;


@Controller
@CrossOrigin("http://localhost:4201")
@RequestMapping("api/customer/")
public class CustomerOrderController {
	@Autowired
	CustomerOrderService customerOrderService;

	@Autowired
	HttpSession httpSession;

	private boolean isAdminLoggedIn() {
		httpSession = AdminController.httpSession;
		return httpSession != null && httpSession.getAttribute("loggedInAdmin") != null;
	}

	@PostMapping("saveOrder")
	public ResponseEntity<List<CustomerOrder>> saveCustomerOrders(@RequestBody List<CustomerOrder> customerOrders) {

		try {
			List<CustomerOrder> savedOrders = customerOrderService.saveCustomerOrder(customerOrders);
			if (savedOrders != null) {
				return ResponseEntity.ok(savedOrders);
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}


	@GetMapping("getCust/{custid}")
	public ResponseEntity<CustomerOrder> getCustomerById(@PathVariable Long custid) {
		if (isAdminLoggedIn()) {
			return new ResponseEntity<>(customerOrderService.getCustomerById(custid), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("getAllCust")
	public ResponseEntity<List<CustomerOrder>> getAllCustomers() {
		if (isAdminLoggedIn()) {
			return new ResponseEntity<>(customerOrderService.getAllCustomers(), HttpStatus.OK);

		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

}
