package com.admin.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.admin.entity.CustomerOrder;


@Repository
public class CustomerOrderDao {
	@Autowired
	SessionFactory sessionFactory;

	public List<CustomerOrder> saveCustomerOrder(List<CustomerOrder> customerOrders) {
		List<CustomerOrder> savedOrders = new ArrayList<>();
		Transaction tx = null;

		try (Session session = sessionFactory.openSession()) {
			tx = session.beginTransaction();

			if (customerOrders != null && !customerOrders.isEmpty()) {
				for (CustomerOrder order : customerOrders) {
					session.save(order);
					savedOrders.add(order);
				}
				tx.commit();
			} else {
				if (tx != null) {
					tx.rollback();
				}
			}
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}

		return savedOrders;
	}


	public CustomerOrder getCustomerById(Long customerId) {
		try (Session session = sessionFactory.openSession()) {
			Query<CustomerOrder> query = session.createQuery("From CustomerOrder Where custid =:customerId ").setParameter("custid",
					customerId);
			return query.uniqueResult();
		}
	}

	public List<CustomerOrder> getAllCustomers() {
		try (Session session = sessionFactory.openSession()) {
			Query<CustomerOrder> query = session.createQuery("From CustomerOrder");
			return query.list();
		}
	}

}
