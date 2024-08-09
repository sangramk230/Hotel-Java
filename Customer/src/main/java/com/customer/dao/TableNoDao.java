package com.customer.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.customer.entity.Tables;

@Repository
public class TableNoDao {
	@Autowired
	SessionFactory sessionFactory;

	public Boolean tableLogin(Long tableid) {
		System.out.println("haoosa" + tableid);
		try (Session session = sessionFactory.openSession()) {
			Query<Tables> query = session.createQuery("FROM Tables WHERE tableid =: tableid",
					Tables.class);
			query.setParameter("tableid", tableid);
			Tables tables = query.uniqueResult();
			return tables != null;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
