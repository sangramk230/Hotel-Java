package com.customer.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.customer.entity.MenuItems;
import com.customer.entity.TableOrder;

@Repository
public class OrderFoodDao {
	@Autowired
	private SessionFactory sessionFactory;

	// Add a new order
	public TableOrder addOrder(TableOrder order) {
		Transaction transaction = null;
		try (Session session = sessionFactory.openSession()) {
			transaction = session.beginTransaction();
			session.save(order);
			transaction.commit();
			return order;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return null;
		}
	}


	// Update an order for a customer
	public List<TableOrder> updateOrder(List<TableOrder> updatedOrders) {
		Transaction tx = null;
		List<TableOrder> updatedList = new ArrayList<>();
		try (Session session = sessionFactory.openSession()) {
			tx = session.beginTransaction();

			for (TableOrder updatedOrder : updatedOrders) {
				Long id = updatedOrder.getId();
				TableOrder existingOrder = session.get(TableOrder.class, id);

				if (existingOrder != null) {
					existingOrder.setQuantity(updatedOrder.getQuantity());
					existingOrder.setPlaceorder("Confirm");
					session.update(existingOrder);
					updatedList.add(existingOrder);
				}
			}

			tx.commit();
			return updatedList;
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		return null;
	}


	// Delete an order
	public TableOrder deleteOrder(Long id) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			TableOrder order = session.get(TableOrder.class, id);
			if (order != null) {
				session.delete(order);
				transaction.commit();
				return order;
			} else {
				return null;
			}
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
			return null;
		} finally {
			if (session != null)
				session.close();
		}
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

	public TableOrder placeOrder(TableOrder order) {
		try (Session session = sessionFactory.openSession()) {
			Transaction tx = session.beginTransaction();
			session.save(order);
				tx.commit();
			return order;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public List<TableOrder> getOrderItems(Long tableid) {
		try (Session session = sessionFactory.openSession()) {
			Query<TableOrder> query = session.createQuery("From TableOrder where tableid=: tableid")
					.setParameter("tableid", tableid);
			return query.list();
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
}
