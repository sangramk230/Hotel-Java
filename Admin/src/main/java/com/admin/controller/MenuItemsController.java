package com.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admin.entity.MenuItems;
import com.admin.service.MenuItemsService;

import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin("http://localhost:4201")
@RequestMapping("api/menu/")
public class MenuItemsController {

	@Autowired
	private MenuItemsService menuItemsService;

	@Autowired
	private HttpSession httpSession;

	private boolean isAdminLoggedIn() {
		httpSession = AdminController.httpSession;
		return httpSession != null && httpSession.getAttribute("loggedInAdmin") != null;
	}

	@PostMapping("addMenu")
	public ResponseEntity<MenuItems> addMenuItem(@RequestBody MenuItems menuItem) {
		if (!isAdminLoggedIn()) {
			return ResponseEntity.status(403).build();
		}

		MenuItems addedItem = menuItemsService.addMenuItem(menuItem);
		System.out.println();
		if (addedItem != null) {
			return ResponseEntity.ok(addedItem);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping("update")
	public ResponseEntity<MenuItems> updateMenuItem(@RequestBody MenuItems menuItem) {
		if (!isAdminLoggedIn()) {
			return ResponseEntity.status(403).build();
		}

		MenuItems updatedItem = menuItemsService.updateMenuItem(menuItem);
		if (updatedItem != null) {
			return ResponseEntity.ok(updatedItem);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	@DeleteMapping("remove/{id}")
	public ResponseEntity<Void> removeMenuItem(@PathVariable Long id) {
		if (!isAdminLoggedIn()) {
			return ResponseEntity.status(403).build();
		}

		MenuItems removedItem = menuItemsService.removeMenuItem(id);
		if (removedItem != null) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("all")
	public ResponseEntity<List<MenuItems>> getAllMenuItems() {
		if (!isAdminLoggedIn()) {
			return ResponseEntity.status(403).build();
		}

		List<MenuItems> menuItems = menuItemsService.getAllMenuItems();
		if (menuItems != null && !menuItems.isEmpty()) {
			return ResponseEntity.ok(menuItems);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@GetMapping("getCategory/{category}")
	public ResponseEntity<List<MenuItems>> getCategory(@PathVariable String category) {
		if (!isAdminLoggedIn()) {
			return ResponseEntity.status(403).build();
		}

		List<MenuItems> menuItems = menuItemsService.getCategory(category);
		if (menuItems != null && !menuItems.isEmpty()) {
			return ResponseEntity.ok(menuItems);
		} else {
			return ResponseEntity.noContent().build();
		}
	}
}
