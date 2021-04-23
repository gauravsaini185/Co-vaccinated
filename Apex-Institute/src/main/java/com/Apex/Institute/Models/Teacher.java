package com.Apex.Institute.Models;

import javax.persistence.Entity;

@Entity
public class Teacher extends Users {

	public Teacher(String name, String phone, String email, String gender, String password, int role, int status) {
		super(name, phone, email, gender, password, role, status);
		// TODO Auto-generated constructor stub
	}

	public Teacher() {
		// TODO Auto-generated constructor stub
	}

	private int teacher_salary;

	public int getTeacher_salary() {
		return teacher_salary;
	}

	public void setTeacher_salary(int teacher_salary) {
		this.teacher_salary = teacher_salary;
	}

}
