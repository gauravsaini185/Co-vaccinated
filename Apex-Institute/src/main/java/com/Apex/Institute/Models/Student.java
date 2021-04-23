package com.Apex.Institute.Models;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

@Entity
public class Student extends Users {

	public Student(String name, String phone, String email, String gender, String password, int role, int status) {
		super(name, phone, email, gender, password, role, status);
		// TODO Auto-generated constructor stub
	}

	public Student() {
		// TODO Auto-generated constructor stub
	}
	
	private String file_path;

	@ManyToMany(cascade =CascadeType.PERSIST)
	@JoinTable(name = "Student_batch" , joinColumns = @JoinColumn(name="student_id"), inverseJoinColumns = @JoinColumn(name = "batch_id"))
	private Collection<Batch> batches = new ArrayList<Batch>();

	public Collection<Batch> getBatches() {
		return batches;
	}

	public void setBatches(Collection<Batch> batches) {
		this.batches = batches;
	}

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	
	
	
}
