package com.Apex.Institute.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int user_id;
	private String name;
	private String phone;
	private String email;
	private String gender;
	private String password;
	private int role;
	private int status;

	public static int ADMIN = 1;
	public static int STUDENT = 2;
	public static int TEACHER = 3;
	public static int active = 1;
	public static int in_active = 2;
	public static int blocked = 3;

	public Users(String name, String phone, String email, String gender, String password, int role, int status) {
		super();
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.gender = gender;
		this.password = password;
		this.role = role;
		this.status = status;
	}
	
	public Users() {
		super();
	}



	public String getRoleAsString() {
		if (role == 1)
			return "ADMIN";
		else if (role == 2)
			return "STUDENT";
		else
			return "TEACHER";
	}

	public String getStatusAsString() {
		if (status == 1)
			return "active";
		else if (status == 2)
			return "in_active";
		else
			return "blocked";
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}