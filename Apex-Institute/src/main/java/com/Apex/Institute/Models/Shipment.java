package com.Apex.Institute.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Shipment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int shipment_id;
	private String college;
	private String contact_Person;
	private String email;
	private String phone;
	private String collegeRollNo;
	private String address;
	private String delivery_Time_From;
	private String delivery_Time_To;
	private String your_Belongings;
	private String shipstatus = "Under Process";
	private String name;
	private String coll_address;
	
	
	public String getColl_address() {
		return coll_address;
	}
	public void setColl_address(String coll_address) {
		this.coll_address = coll_address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShipstatus() {
		return shipstatus;
	}
	public void setShipstatus(String shipstatus) {
		this.shipstatus = shipstatus;
	}
	public int getShipment_id() {
		return shipment_id;
	}
	public void setShipment_id(int shipment_id) {
		this.shipment_id = shipment_id;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public String getContact_Person() {
		return contact_Person;
	}
	public void setContact_Person(String contact_Person) {
		this.contact_Person = contact_Person;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCollegeRollNo() {
		return collegeRollNo;
	}
	public void setCollegeRollNo(String collegeRollNo) {
		this.collegeRollNo = collegeRollNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDelivery_Time_From() {
		return delivery_Time_From;
	}
	public void setDelivery_Time_From(String delivery_Time_From) {
		this.delivery_Time_From = delivery_Time_From;
	}
	public String getDelivery_Time_To() {
		return delivery_Time_To;
	}
	public void setDelivery_Time_To(String delivery_Time_To) {
		this.delivery_Time_To = delivery_Time_To;
	}
	public String getYour_Belongings() {
		return your_Belongings;
	}
	public void setYour_Belongings(String your_Belongings) {
		this.your_Belongings = your_Belongings;
	}
	
}
