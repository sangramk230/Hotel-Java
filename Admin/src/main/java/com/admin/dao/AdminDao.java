package com.admin.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.admin.entity.Admin;

@Repository
public class AdminDao {
	@Autowired
	private SessionFactory sessionFactory;

	public Boolean login(String email, String password) {
		try (Session session = sessionFactory.openSession()) {
			Query<Admin> query = session.createQuery("FROM Admin WHERE email = :email AND password = :password",
					Admin.class);
			query.setParameter("email", email);
			query.setParameter("password", password);
			Admin admin = query.uniqueResult();
			return admin != null;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Admin profile(String email) {
		try (Session session = sessionFactory.openSession()) {
			Query<Admin> query = session.createQuery("FROM Admin WHERE email = :email", Admin.class);
			query.setParameter("email", email);
			return query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Admin updateProfile(Admin updatedAdmin) {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.update(updatedAdmin);
			session.getTransaction().commit();
			return updatedAdmin;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
