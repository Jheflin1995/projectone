package com.revature.service;

import java.util.List;
import java.util.Optional;

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
		
		System.out.println("get employee resolved triggered");

		RequestStatus ars = new RequestStatus(1, "Approved");
		RequestStatus drs = new RequestStatus(3, "Deny");



		List<Request> pendingRequests = (List<Request>) rdao.findAll().stream()
				.filter(r ->r.getREIMB_AUTHOR().equals(e)&&(r.getStatus().equals(ars)||r.getStatus().equals(drs)))
				.toList();

//			myListOfElms().stream()
//				  .filter(elm -> elm.condition1OK() || elm.condition2OK())
//				  .collect(toList());
		System.out.println(pendingRequests);
		return pendingRequests;

	}

	public List<Request> getEmployeePending(Employee e) {

		RequestStatus prs = new RequestStatus(0, "Pending");

		List<Request> pendingRequests = (List<Request>) rdao.findAll().stream()
				.filter(r ->r.getStatus().equals(prs) && r.getREIMB_AUTHOR().equals(e))
				.toList();

		return pendingRequests;
	}
	
	public List<Request> getRequestByEmployeeId(Employee e) {

		List<Request> employeeRequests = (List<Request>) rdao.findAll().stream()
				.filter(r -> r.getREIMB_AUTHOR().equals(e))
				.toList();

		return employeeRequests;
		
	}



	public int submitRequest(Request r) {

		return rdao.insert(r);
	}

	
	public Request findId(int id) {

		Optional<Request> possibleReq = rdao.findAll().stream()
				.filter(e -> (e.getId()==(id)))
				.findFirst();


		return(possibleReq.isPresent() ? possibleReq.get() : new Request());
	}



public boolean updateRequestStatus(Request r) {


		return rdao.update(r);
	}









}