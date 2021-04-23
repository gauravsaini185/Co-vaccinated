package com.Apex.Institute.Models;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

@Entity
public class Assignment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int assignment_id;
	private LocalDate assignment_date;
	private String subject;
	
	@OneToOne
	@JoinColumn(name = "batch")
	private Batch batch;	
	@Lob
	private byte[] file;	
	private String type;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Batch getBatch() {
		return batch;
	}
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public void setBatch(Batch batch) {
		this.batch = batch;
	}
	public byte[] getFile() {
		return file;
	}
	public void setFile(byte[] file) {
		this.file = file;
	}
	public int getAssignment_id() {
		return assignment_id;
	}
	public void setAssignment_id(int assignment_id) {
		this.assignment_id = assignment_id;
	}
	public LocalDate getAssignment_date() {
		return assignment_date;
	}
	public void setAssignment_date(LocalDate assignment_date) {
		this.assignment_date = assignment_date;
	}
	
	
	
	
}
