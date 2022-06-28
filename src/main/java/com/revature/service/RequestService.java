package com.revature.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.dao.RequestDao;
import com.revature.models.Employee;
import com.revature.models.Request;
import com.revature.models.RequestStatus;
import com.revature.util.HibernateUtil;

public class RequestService {
	
	private RequestDao rdao;
	
	public RequestService(RequestDao rdao) {
		this.rdao=rdao;
	}


	
	
	public List<Request> getAll() {
		
		return rdao.findAll();
		
	}
	
	public List<Request> getPending(){
		
		RequestStatus prs = new RequestStatus(0, "Pending");
		
		List<Request> pendingRequests = (List<Request>) rdao.findAll().stream().filter(r ->(r.getStatus().equals(prs))).toList();
		
		return pendingRequests;
		
	}
	
	public List<Request> getResolved(){
		
		RequestStatus ars = new RequestStatus(1, "Approved");
		RequestStatus drs = new RequestStatus(3, "Deny");
		
		List<Request> pendingRequests = (List<Request>) rdao.findAll().stream()
				.filter(r ->r.getStatus().equals(ars)||r.getStatus().equals(drs)).toList();
		
//			myListOfElms().stream()
//				  .filter(elm -> elm.condition1OK() || elm.condition2OK())
//				  .collect(toList());

		return pendingRequests;
		
	}
	
	
	public List<Request> getEmployeeResolved(Employee e){
		
		RequestStatus ars = new RequestStatus(1, "Approved");
		RequestStatus drs = new RequestStatus(3, "Deny");
		
		
		
		List<Request> pendingRequests = (List<Request>) rdao.findAll().stream()
				.filter(r ->r.getStatus().equals(ars)||r.getStatus().equals(drs)&&r.getREIMB_AUTHOR().equals(e))
				.toList();
		
//			myListOfElms().stream()
//				  .filter(elm -> elm.condition1OK() || elm.condition2OK())
//				  .collect(toList());

		return pendingRequests;
		
	}
	
	public List<Request> getEmployeePending(Employee e) {
		
		RequestStatus prs = new RequestStatus(0, "Pending");
		
		List<Request> pendingRequests = (List<Request>) rdao.findAll().stream()
				.filter(r ->r.getStatus().equals(prs) && r.getREIMB_AUTHOR().equals(e))
				.toList();
		
		return pendingRequests;
	}
	public int submitRequest(Request r) {
		
		return rdao.insert(r);
	}






}
