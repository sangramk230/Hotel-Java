package com.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.customer.service.TableNoService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@CrossOrigin("http://localhost:4200")
@RequestMapping("api/cust")
public class TableNoController {
@Autowired
TableNoService tableNoService;
@Autowired
private HttpServletRequest request;

static HttpSession httpSession;

@GetMapping("tableLogin/{tableid}")
public ResponseEntity<Boolean> tableLogin (@PathVariable Long tableid) {
	httpSession = request.getSession();
	Boolean isAuthenticated = tableNoService.tableLogin(tableid);
	if (isAuthenticated) {
		httpSession.setAttribute("loggedTable", tableid);
		return ResponseEntity.ok(true);
	} else {
		return ResponseEntity.ok(false);
	}
}
}
