package com.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="requests")

public class Requests {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="amount")
	private double amount;
	
	@Column(name="time_submited")
	private java.sql.Date REIMB_SUBMITTED;
	
	@Column(name="time_resolved")
	private java.sql.Date REIM_RESOLVED;
	
	@Column(name="description")
	private String description;
	
	@ManyToOne
    @JoinColumn (name="employee_id")
	private Employee REIMB_AUTHOR;
	
	@ManyToOne
	@JoinColumn(name="manager_id")
	private Employee REIMB_RESOLVER;
	
	@ManyToOne
	@JoinColumn(name="request_status")
	private RequestStatus status;
	
	@ManyToOne
	@JoinColumn(name="rem_type")
	private RemType type;
	
	
	
	
 
	
	

}
