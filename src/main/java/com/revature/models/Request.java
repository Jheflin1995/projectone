package com.revature.models;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="requests")

public class Request {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="amount")
	private double amount;
	
	@UpdateTimestamp
	@Column(name="date_submited")
	private java.sql.Timestamp REIMB_SUBMITTED;
	
	
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
	@JoinColumn(name="request_status") //rew
	private RequestStatus status;
	
	@ManyToOne
	@JoinColumn(name="rem_type")
	private RemType type;

	public Request() {
		super();
	}

	public Request(double amount, Timestamp rEIMB_SUBMITTED, Date rEIM_RESOLVED, String description, Employee rEIMB_AUTHOR,
			Employee rEIMB_RESOLVER, RequestStatus status, RemType type) {
		super();
		this.amount = amount;
		REIMB_SUBMITTED = rEIMB_SUBMITTED;
		REIM_RESOLVED = rEIM_RESOLVED;
		this.description = description;
		REIMB_AUTHOR = rEIMB_AUTHOR;
		REIMB_RESOLVER = rEIMB_RESOLVER;
		this.status = status;
		this.type = type;
	}

	
	public Request(double amount, Date rEIM_RESOLVED, String description, Employee rEIMB_AUTHOR,
			Employee rEIMB_RESOLVER, RequestStatus status, RemType type) {
		super();
		this.amount = amount;
		REIM_RESOLVED = rEIM_RESOLVED;
		this.description = description;
		REIMB_AUTHOR = rEIMB_AUTHOR;
		REIMB_RESOLVER = rEIMB_RESOLVER;
		this.status = status;
		this.type = type;
	}

	public Request(int id, double amount, Timestamp rEIMB_SUBMITTED, Date rEIM_RESOLVED, String description,
			Employee rEIMB_AUTHOR, Employee rEIMB_RESOLVER, RequestStatus status, RemType type) {
		super();
		this.id = id;
		this.amount = amount;
		REIMB_SUBMITTED = rEIMB_SUBMITTED;
		REIM_RESOLVED = rEIM_RESOLVED;
		this.description = description;
		REIMB_AUTHOR = rEIMB_AUTHOR;
		REIMB_RESOLVER = rEIMB_RESOLVER;
		this.status = status;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Timestamp getREIMB_SUBMITTED() {
		return REIMB_SUBMITTED;
	}

	public void setREIMB_SUBMITTED(Timestamp rEIMB_SUBMITTED) {
		REIMB_SUBMITTED = rEIMB_SUBMITTED;
	}

	public java.sql.Date getREIM_RESOLVED() {
		return REIM_RESOLVED;
	}

	public void setREIM_RESOLVED(java.sql.Date rEIM_RESOLVED) {
		REIM_RESOLVED = rEIM_RESOLVED;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Employee getREIMB_AUTHOR() {
		return REIMB_AUTHOR;
	}

	public void setREIMB_AUTHOR(Employee rEIMB_AUTHOR) {
		REIMB_AUTHOR = rEIMB_AUTHOR;
	}

	public Employee getREIMB_RESOLVER() {
		return REIMB_RESOLVER;
	}

	public void setREIMB_RESOLVER(Employee rEIMB_RESOLVER) {
		REIMB_RESOLVER = rEIMB_RESOLVER;
	}

	public RequestStatus getStatus() {
		return status;
	}

	public void setStatus(RequestStatus status) {
		this.status = status;
	}

	public RemType getType() {
		return type;
	}

	public void setType(RemType type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(REIMB_AUTHOR, REIMB_RESOLVER, REIMB_SUBMITTED, REIM_RESOLVED, amount, description, id,
				status, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Request other = (Request) obj;
		return Objects.equals(REIMB_AUTHOR, other.REIMB_AUTHOR) && Objects.equals(REIMB_RESOLVER, other.REIMB_RESOLVER)
				&& Objects.equals(REIMB_SUBMITTED, other.REIMB_SUBMITTED)
				&& Objects.equals(REIM_RESOLVED, other.REIM_RESOLVED)
				&& Double.doubleToLongBits(amount) == Double.doubleToLongBits(other.amount)
				&& Objects.equals(description, other.description) && id == other.id
				&& Objects.equals(status, other.status) && Objects.equals(type, other.type);
	}

	@Override
	public String toString() {
		return "Requests [id=" + id + ", amount=" + amount + ", REIMB_SUBMITTED=" + REIMB_SUBMITTED + ", REIM_RESOLVED="
				+ REIM_RESOLVED + ", description=" + description + ", REIMB_AUTHOR=" + REIMB_AUTHOR
				+ ", REIMB_RESOLVER=" + REIMB_RESOLVER + ", status=" + status + ", type=" + type + "]";
	}
	
	
	
	
 
	
	

}
