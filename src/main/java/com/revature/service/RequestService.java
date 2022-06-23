package com.revature.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.dao.RequestDao;
import com.revature.models.Employee;
import com.revature.models.Request;
import com.revature.util.HibernateUtil;

public class RequestService {
	
	private RequestDao rdao;
	
	public RequestService(RequestDao rdao) {
		this.rdao=rdao;
	}


	
	
	public List<Request> getAll() {
		
		return rdao.findAll();
		
	}
	
	public int submitRequest(Request r) {
		
		return rdao.insert(r);
	}

}
