package com.admin.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.admin.entity.MenuItems;

@Repository
public class MenuItemsDao {
	@Autowired
	SessionFactory sessionFactory;

	public MenuItems addMenuItem(MenuItems menuItem) {
		try (Session session = sessionFactory.openSession()) {
			Transaction tx = session.beginTransaction();
			if (menuItem != null) {
				session.save(menuItem);
				tx.commit();
				return menuItem;
			} else {
				tx.rollback();
				return null;
			}
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}

	public MenuItems updateMenuItem(MenuItems menuItem) {
		try (Session session = sessionFactory.openSession()) {
			Transaction tx = session.beginTransaction();
			if (menuItem != null) {
				session.update(menuItem);
				tx.commit();
				return menuItem;
			} else {
				tx.rollback();
				return null;
			}
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public MenuItems removeMenuItem(Long menuItemId) {
		Transaction tx = null;
		MenuItems removedItem = null;

		try (Session session = sessionFactory.openSession()) {
			if (menuItemId == null) {
				throw new IllegalArgumentException("MenuItem ID cannot be null");
			}

			tx = session.beginTransaction();
			MenuItems menuItem = session.get(MenuItems.class, menuItemId);

			if (menuItem != null) {
				session.remove(menuItem);
				tx.commit();
				removedItem = menuItem;
			} else {
				tx.rollback();
			}
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}

		return removedItem;
	}



	public List<MenuItems> getAllMenuItems() {
		try (Session session = sessionFactory.openSession()) {
			Query<MenuItems> query = session.createQuery("From MenuItems");
			return query.list();
			} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public List<MenuItems> getCategory(String category) {
		try (Session session = sessionFactory.openSession()) {
			Query<MenuItems> query = session.createQuery("From MenuItems where category= :category")
					.setParameter("category", category);
			return query.list();
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
}
