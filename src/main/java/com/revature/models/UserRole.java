package com.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_role")
public class UserRole {
	
	@Id
	@Column(name= "user_role_id")
	private int id;
	
	
	@Column(name= "role")
	private String role;

}
