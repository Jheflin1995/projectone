package com.revature.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrontController
 */
public class FrontController extends HttpServlet {


	/**
	 * This method will be responsible for determining what resource the client is requesting
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1. URI rewriting
		// http://localhost:8080/employee-servlet-app/login -- we want to capture login
		// http://localhost:8080/employee-servlet-app/employees -- if they go here it returns all employees in the DB
		final String URI = request.getRequestURI().replace("/employee-servlet-app/", "");
		// we're capturing the very last part of the URI

		// set up a switch case statement in which we call the appropriate functionality based on the URI returned
		switch(URI) {

		case "login":

			// invoke some function from the RequestHelper
			RequestHelper.processLogin(request, response);
			break;

		case "employees":

			RequestHelper.processEmployees(request,response);
			// invoke some functionality from the request helper which would return all employees
			break;

		case "register":
			RequestHelper.processRegistration(request,response);

			break;

		case "managerregister" :
			RequestHelper.processManagerRegistration(request,response);

			break;

		case "submit":
			RequestHelper.processSubmition(request,response);

			break;

		case "reviewall" :
			RequestHelper.processAllRequests(request, response);

			break;

		case "reviewpending" :
			RequestHelper.processPendingRequests(request, response);

			break;

		case "reviewresolved" :
			RequestHelper.processResolvedRequests(request, response);

			break;

		case "viewresolvedemployee" :
			RequestHelper.processResolvedEmployeeRequests(request, response);

			break;

		case "viewpendingemployee" :
			RequestHelper.processPendingEmployeeRequests(request,response);

			break;
		case "viewmyinfo" :

			RequestHelper.viewMyInfo(request,response);

			break;

		case "changename" :

			RequestHelper.updateEmployeeName(request, response);

			break;

		case "changeusername" :

			RequestHelper.updateEmployeeUsername(request, response);

			break;

		case "changepassword" :

			RequestHelper.updateEmployeePassword(request, response);

			break;

		case "approvedeny":
  			RequestHelper.updateApproveDeny(request,response);
  			break;
  			
		case "viewrequestbyemployee":
  			RequestHelper.vewRequestByEmployee(request,response);
  			break;
  			
  			

		default:
			// custom error page
			break;
		}


	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}