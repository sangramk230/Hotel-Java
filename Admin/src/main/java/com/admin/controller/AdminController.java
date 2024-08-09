package com.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.admin.entity.Admin;
import com.admin.service.AdminService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@CrossOrigin("http://localhost:4201")
@RequestMapping("api/admin/")
public class AdminController {
	@Autowired
	AdminService adminService;
	
	@Autowired
	private HttpServletRequest request;

	static HttpSession httpSession;

	@GetMapping("login/{email}/{password}")
	public ResponseEntity<Boolean> login(@PathVariable String email, @PathVariable String password) {
		httpSession = request.getSession();
		Boolean isAuthenticated = adminService.login(email, password);
		if (isAuthenticated) {
			httpSession.setAttribute("loggedInAdmin", email);
			return ResponseEntity.ok(true);
		} else {
			return ResponseEntity.ok(false);
		}
	}

	@GetMapping("profile")
	public ResponseEntity<Admin> profile() {
		if (httpSession.getAttribute("loggedInAdmin") != null) {
			String email = (String) httpSession.getAttribute("loggedInAdmin");
			Admin admin = adminService.profile(email);
			if (admin != null) {
				return new ResponseEntity<>(admin, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PutMapping("profileUpdate")
	public ResponseEntity<Admin> updateProfile(@RequestBody Admin updatedAdmin) {
		Admin updatedProfile = adminService.updateProfile(updatedAdmin);
		return ResponseEntity.ok(updatedProfile);
	}

	@GetMapping("logout")
	public ResponseEntity<String> logoutUser() {
		HttpSession httpSession = AdminController.httpSession;
		if (httpSession != null) {
			httpSession.setAttribute("loggedInAdmin", null);
			httpSession.invalidate();
		}
		return new ResponseEntity<>(null, HttpStatus.OK);
	}


}
