package com.admin.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.admin.entity.TableOrder;

@Repository
public class TableOrderDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public TableOrder placeOrder(TableOrder order) {
		try (Session session = sessionFactory.openSession()) {
			Transaction tx = session.beginTransaction();
			Long id = order.getId();
			TableOrder tb = session.get(TableOrder.class, id);
			if (tb != null) {
				tb.setStatus(order.getStatus());
				session.update(tb);
				tx.commit();
				return tb;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public TableOrder updateOrder(TableOrder order) {
		Transaction tx = null;
		TableOrder updatedOrder = null;
		try (Session session = sessionFactory.openSession()) {
			tx = session.beginTransaction();

			Long tableid = order.getTableid();
			String status = order.getStatus();
			String time = order.getTime();

			Query<TableOrder> query = session
					.createQuery("UPDATE TableOrder SET status = :status, time = :time WHERE tableid = :tableid")
					.setParameter("tableid", tableid).setParameter("status", status)
					.setParameter("time", time);

			int result = query.executeUpdate();

			if (result > 0) {
				// Fetch the updated order to return it
				updatedOrder = session.get(TableOrder.class, tableid);
			}

			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}

		return updatedOrder;
	}


	@Transactional
	public void cancelOrder(Long id) {
		try (Session session = sessionFactory.openSession()) {
			TableOrder order = session.get(TableOrder.class, id);
			if (order != null) {
				session.delete(order);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public TableOrder getOrderById(Long tableno) {
		try (Session session = sessionFactory.openSession()) {
			return session.get(TableOrder.class, tableno);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public List<TableOrder> getAllOrders() {
		try (Session session = sessionFactory.openSession()) {
			Query<TableOrder> query = session.createQuery("FROM TableOrder", TableOrder.class);
			return query.list();
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public boolean clearTable(Long tableid) {
		Transaction transaction = null;
		try (Session session = sessionFactory.openSession()) {
			transaction = session.beginTransaction();
			Query query = session.createQuery("delete from TableOrder where tableid = :tableid");
			query.setParameter("tableid", tableid);

			int result = query.executeUpdate();
			transaction.commit();

			return result > 0;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return false;
		}
	}



}
