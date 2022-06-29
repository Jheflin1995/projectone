package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.EmployeeDao;
import com.revature.dao.RequestDao;
import com.revature.models.Employee;
import com.revature.models.RemType;
import com.revature.models.Request;
import com.revature.models.RequestStatus;
import com.revature.models.Role;
import com.revature.models.UserRole;
import com.revature.service.EmployeeService;
import com.revature.service.RequestService;

public class RequestHelper {

	// employeeservice
	private static EmployeeService eserv = new EmployeeService(new EmployeeDao());

	private static RequestService rserv = new RequestService(new RequestDao());
	// object mapper (for frontend)
	private static ObjectMapper om = new ObjectMapper();



	public static void processEmployees(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//http://localhost:8080/employee-servlet-app/employees
		//will return me an entire list of all the employees in JSON
		// 1. set the content type to be application/json
		response.setContentType("text/html");
	//	response.setContentType("application/json");

		//2. Call the findAll() method from the employee service
		List<Employee> emps = eserv.getAll();


		//3. transfrom the list to a String

		String jsonString = om.writeValueAsString(emps);

		//write it out

		PrintWriter out = response.getWriter();
		out.write(jsonString); // write the String to the response body
	}

	public static void processRegistration(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		1. extract all values from the params

		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String username = request.getParameter("username");
		String password = request.getParameter("password");

//
		UserRole ur = new UserRole(2, Role.Employee);

//		2. construct a new employee object

		Employee e = new Employee(firstname, lastname, username, password, ur);

		int pk = eserv.register(e);
//
//		3. call the register() method from the service layer
//
		if(pk>0) {

			e.setId(pk);

			//add the user to the session
			HttpSession session = request.getSession();
			session.setAttribute("the-user", e);


			request.getRequestDispatcher("welcome.html").forward(request, response);
			//using the request dispatcher, forward the request and response to a new resource...
			// send the user to a ne page -- welcome.html

		} else {
//		4. check it's ID...if its > 0 it's successfull
//				using the request dispatcher, forward the request and response to a new resource....
//				send the user to a new page -- welcome.html

			PrintWriter out = response.getWriter();
			response.setContentType("text/html");

			out.println("<h1>Registration failed...Username already exists</h1>");
			out.println("<a href=\"index.html\">Back</a>");


//				if its -1 that means the registration method failed ( that might be a duplicate )

		}
	}

	/**
	 * What does this method do?
	 *
	 * It extracts the parameters from a request (username and password) from the UI
	 * It will call the confirmLogin() method from the EmployeeService and
	 * see if a user with that username and password exists
	 *
	 * Who will provide the method with the HttpRequest? The UI
	 * We need to build an html doc with a form that will send these prameters to the method
	 */


public static void processManagerRegistration(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		1. extract all values from the params

		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		int managerKey = Integer.parseInt(request.getParameter("managerkey"));
//
		int pk=0;
		Employee e = new Employee();
		if(managerKey==1) {
		UserRole ur = new UserRole(1, Role.Admin);

//		2. construct a new employee object
		e.setFirstName(firstname);
		e.setLastName(lastname);
		e.setUsername(username);
		e.setPassword(password);
		e.setUser_role_id(ur);


		 pk = eserv.register(e);
//
//		3. call the register() method from the service layer

		}else {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.println("Incorrect managerkey");
		}
//
		if(pk>0) {

			e.setId(pk);

			//add the user to the session
			HttpSession session = request.getSession();
			session.setAttribute("the-user", e);

			request.getRequestDispatcher("welcome.html").forward(request, response);
			//using the request dispatcher, forward the request and response to a new resource...
			// send the user to a ne page -- welcome.html

		} else {
//		4. check it's ID...if its > 0 it's successfull
//				using the request dispatcher, forward the request and response to a new resource....
//				send the user to a new page -- welcome.html

			PrintWriter out = response.getWriter();
			response.setContentType("text/html");

			out.println("<h1>Registration failed...Username already exists</h1>");
			out.println("<a href=\"index.html\">Back</a>");


//				if its -1 that means the registration method failed ( that might be a duplicate )

		}
	}
	public static void processLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		UserRole eur = new UserRole(2, Role.Employee);
		UserRole aur = new UserRole(1, Role.Admin);
		// 1. Extract the parameters from the request (username & password)
		String username = request.getParameter("username");
		String password = request.getParameter("password"); // use fn + arrow key < or > to get to the beginning or end of a line of code
		// use ctrl + arrow key to go from word to word

		// 2. call the confirm login(0 method from the employeeService and see what it returns
		Employee e = eserv.confirmLogin(username, password);

		// 3. If the user exists, lets print their info to the screen
		if (e.getId() > 0) {

			// grab the session
			HttpSession session = request.getSession();

			// add the user to the session
			session.setAttribute("the-user", e);

			// alternatively you can redirect to another resource instead of printing out dynamically

			// print out the user's data with the print writer
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");

			out.println("<h1>Welcome " + e.getFirstName() + "!</h1>");
			out.println("<h3>You have successfully logged in!</h3>");
			out.println("userroleid is " + e.getUser_role_id());

			// you COULD print the object out as a JSON string
			String jsonString = om.writeValueAsString(e);
			out.println(jsonString);

			if(e.getUser_role_id().equals(eur)) {
			request.getRequestDispatcher("welcome.html").forward(request, response);

			}else if(e.getUser_role_id().equals(aur)) {
			request.getRequestDispatcher("managerhomepage.html").forward(request, response);
			}


		} else {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.println("No user found, sorry");

			// Shout out to Gavin for figuring this out -- 204 doesn't return a response body
//			response.setStatus(204); // 204 meants successful connection to the server, but no content found
		}
	}
		public static void processSubmition(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

			HttpSession session = request.getSession();

			Employee e = (Employee) session.getAttribute("the-user");

			RequestStatus rs = new RequestStatus(0, "Pending");

			RemType rt = new RemType();


			// 1. Extract the parameters from the request (user name & password)

			double amount = Double.parseDouble(request.getParameter("amount"));
			String description = request.getParameter("description");
			String type = request.getParameter("request_type");// use fn + arrow key < or > to get to the beginning or end of a line of code
			// use ctrl + arrow key to go from word to word
			System.out.println(type);



			if(type.equals("LODGING")) {
				rt.setId(1);
				rt.setType("LODGING");

			}if(type.equals("TRAVEL")) {
				rt.setId(2);
				rt.setType("TRAVEL");

			}if(type.equals("FOOD")) {
				rt.setId(3);
				rt.setType("FOOD");

			}if(type.equals("OTHER")) {
				rt.setId(4);
				rt.setType("OTHER");

			}



			Request r = new Request(amount, null, description, e, null, rs, rt);
			// 2. call the confirm login(0 method from the employeeService and see what it returns
			int pk = rserv.submitRequest(r);


			// 3. If the user exists, lets print their info to the screen
			if (r.getId() > 0) {

				// grab the session



				// add the user to the session
				session.setAttribute("the-request", r);

				// alternatively you can redirect to another resource instead of printing out dynamically

				// print out the user's data with the print writer
				PrintWriter out = response.getWriter();
				response.setContentType("text/html");

				out.println("<h1>Your request for " + r.getAmount() + " has been proccessed!</h1>");


				// you COULD print the object out as a JSON string
				String jsonString = om.writeValueAsString(r);
				out.println(jsonString);



			} else {
				PrintWriter out = response.getWriter();
				response.setContentType("text/html");
				out.println("Request not processed, sorry");

				// Shout out to Gavin for figuring this out -- 204 doesn't return a response body
//				response.setStatus(204); // 204 meants successful connection to the server, but no content found
			}




	}

		public static void processAllRequests(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			//http://localhost:8080/employee-servlet-app/employees
			//will return me an entire list of all the employees in JSON
			// 1. set the content type to be application/json
		//	response.setContentType("text/html");
			response.setContentType("application/json");

			//2. Call the findAll() method from the employee service
			List<Request> req = rserv.getAll();


			//3. transfrom the list to a String

			String jsonString = om.writeValueAsString(req);

			//write it out

			PrintWriter out = response.getWriter();
			out.write(jsonString); // write the String to the response body
		}

		public static void processPendingRequests(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			//http://localhost:8080/employee-servlet-app/employees
			//will return me an entire list of all the employees in JSON
			// 1. set the content type to be application/json
		//	response.setContentType("text/html");
			response.setContentType("application/json");

			//2. Call the findAll() method from the employee service
			List<Request> req = rserv.getPending();


			//3. transfrom the list to a String

			String jsonString = om.writeValueAsString(req);

			//write it out

			PrintWriter out = response.getWriter();
			out.write(jsonString); // write the String to the response body
		}

		public static void processResolvedRequests(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			//http://localhost:8080/employee-servlet-app/employees
			//will return me an entire list of all the employees in JSON
			// 1. set the content type to be application/json
		//	response.setContentType("text/html");
			response.setContentType("application/json");

			//2. Call the findAll() method from the employee service
			List<Request> req = rserv.getResolved();


			//3. transfrom the list to a String

			String jsonString = om.writeValueAsString(req);

			//write it out

			PrintWriter out = response.getWriter();
			out.write(jsonString); // write the String to the response body
		}

		public static void processResolvedEmployeeRequests(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			HttpSession session = request.getSession();

			Employee e = (Employee) session.getAttribute("the-user");

			//http://localhost:8080/employee-servlet-app/employees
			//will return me an entire list of all the employees in JSON
			// 1. set the content type to be application/json
		//	response.setContentType("text/html");


			response.setContentType("application/json");



			//2. Call the findAll() method from the employee service
			List<Request> req = rserv.getEmployeeResolved(e);


			//3. transfrom the list to a String

			String jsonString = om.writeValueAsString(req);

			//write it out

			PrintWriter out = response.getWriter();
			out.write(jsonString); // write the String to the response body
		}

		public static void processPendingEmployeeRequests(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			HttpSession session = request.getSession();

			Employee e = (Employee) session.getAttribute("the-user");


			response.setContentType("application/json");


			List<Request> req = rserv.getEmployeePending(e);


			String jsonString = om.writeValueAsString(req);

			PrintWriter out = response.getWriter();
			out.write(jsonString); // write the String to the response body

		}

		public static void viewMyInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			HttpSession session = request.getSession();

			Employee e = (Employee) session.getAttribute("the-user");


			response.setContentType("application/json");


			String jsonString = om.writeValueAsString(e);

			PrintWriter out = response.getWriter();
			out.write(jsonString); // write the String to the response body

		}

		public static void updateEmployeeName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			HttpSession session = request.getSession();

			Employee e = (Employee) session.getAttribute("the-user");

			String newFirstName = request.getParameter("firstname");
			String newLastName = request.getParameter("lastname");

			e.setFirstName(newFirstName);
			e.setLastName(newLastName);

			eserv.updateEmployeeInfo(e);

			session.setAttribute("the-user", e);

			request.getRequestDispatcher("welcome.html").forward(request, response);
		}

		public static void updateEmployeeUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			HttpSession session = request.getSession();

			Employee e = (Employee) session.getAttribute("the-user");

			String newUsername = request.getParameter("username");

			e.setUsername(newUsername);

			eserv.updateEmployeeInfo(e);

			session.setAttribute("the-user", e);

			request.getRequestDispatcher("welcome.html").forward(request, response);
		}

		public static void updateEmployeePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			HttpSession session = request.getSession();

			Employee e = (Employee) session.getAttribute("the-user");

			String newPassword = request.getParameter("password");

			e.setPassword(newPassword);

			eserv.updateEmployeeInfo(e);

			session.setAttribute("the-user", e);

			request.getRequestDispatcher("welcome.html").forward(request, response);

		}

		//	public static void updateApproveDeny(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//
		//		HttpSession session = request.getSession();
		//
		//		Employee e = (Employee) session.getAttribute("the-user");
		//		Request r = (Request)session.getAttribute(status);
		//
		//
		//		String newStat = request.getParameter("status");
		//
		//
		//
		//
		//
		//
		//
		//
		//
		//
		//		}

}
