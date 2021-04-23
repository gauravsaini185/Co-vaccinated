package com.Apex.Institute.Models;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Batch {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int batch_id;
	private String batch_name;
	private String subject;
	private String batch_class;
	private int batch_fee;
	private String batch_time;
	private String schedule;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Teacher batch_teacher;
	
	@ManyToMany(mappedBy = "batches")
	private Collection<Student> students = new ArrayList<Student>();

	public Teacher getBatch_teacher() {
		return batch_teacher;
	}

	public void setBatch_teacher(Teacher batch_teacher) {
		this.batch_teacher = batch_teacher;
	}

	public String getBatch_time() {
		return this.batch_time;
	}

	public void setBatch_time(String batch_time) {
		this.batch_time = batch_time;
	}

	public String getSchedule() {
		return this.schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public String getBatch_name() {
		return batch_name;
	}

	public void setBatch_name(String batch_name) {
		this.batch_name = batch_name;
	}

	public int getBatch_id() {
		return batch_id;
	}

	public void setBatch_id(int batch_id) {
		this.batch_id = batch_id;
	}

	public String getBatch_class() {
		return batch_class;
	}

	public void setBatch_class(String batch_class) {
		this.batch_class = batch_class;
	}

	public int getBatch_fee() {
		return batch_fee;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setBatch_fee(int batch_fee) {
		this.batch_fee = batch_fee;
	}

	public Collection<Student> getStudents() {
		return students;
	}

	public void setStudents(Collection<Student> students) {
		this.students = students;
	}	
}
