package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.revature.models.Request;
import com.revature.models.Employee;

import com.revature.util.HibernateUtil;

public class RequestDao {
	
	public int insert(Request r) {
		
		// grab the session object
		Session ses = HibernateUtil.getSession();
		
		// begin a tx
		Transaction tx = ses.beginTransaction();
		
		// capture the pk returned when the session method save() is called
		int pk = (int) ses.save(r);
		
		tx.commit();
		// return the pk
		return pk;
		
	}
	
	// Read
	public List<Request> findAll() {
		
		// grab the session
		Session ses = HibernateUtil.getSession();
		
		// make an HQL -- Hibernate Query Language: odd mix of OOP & native SQL
		 List<Request> reqs = ses.createQuery("from Request", Request.class).list();
		
		 // return the list of employees
		return reqs;
		
	}
	
	public boolean delete(int id) {
		return false;
		
	}
	
	public boolean update(Employee e) {
		return false;
	}

}
