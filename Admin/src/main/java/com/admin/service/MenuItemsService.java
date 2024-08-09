package com.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.dao.MenuItemsDao;
import com.admin.entity.MenuItems;

@Service
public class MenuItemsService {

	@Autowired 
	MenuItemsDao menuItemsDao;

	public MenuItems addMenuItem(MenuItems menuItem) {
		return menuItemsDao.addMenuItem(menuItem);
	}


	public MenuItems updateMenuItem(MenuItems menuItem) {
		return menuItemsDao.updateMenuItem(menuItem);
	}


	public MenuItems removeMenuItem(Long menuItemId) {
		return menuItemsDao.removeMenuItem(menuItemId);
	}


	public List<MenuItems> getAllMenuItems() {
		return menuItemsDao.getAllMenuItems();
	}

	public List<MenuItems> getCategory(String category) {
		return menuItemsDao.getCategory(category);
	}

}
