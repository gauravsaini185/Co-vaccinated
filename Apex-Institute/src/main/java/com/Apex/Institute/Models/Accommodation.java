package com.Apex.Institute.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Accommodation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int accommodation_id;
	private String name;
	private String email;
	private String collegeName;
	private String collegeRollNo;
	private String collegeAddress;
	private String roomType;
	private String arrivalDateTime;
	private String departureDate;
	private String freePickup;
	private String flightNo;
	private String specialRequest;
	private String status = "Under Process";
	
	
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getAccommodation_id() {
		return accommodation_id;
	}
	public void setAccommodation_id(int accommodation_id) {
		this.accommodation_id = accommodation_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCollegeName() {
		return collegeName;
	}
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
	public String getCollegeRollNo() {
		return collegeRollNo;
	}
	public void setCollegeRollNo(String collegeRollNo) {
		this.collegeRollNo = collegeRollNo;
	}
	public String getCollegeAddress() {
		return collegeAddress;
	}
	public void setCollegeAddress(String collegeAddress) {
		this.collegeAddress = collegeAddress;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public String getArrivalDateTime() {
		return arrivalDateTime;
	}
	public void setArrivalDateTime(String arrivalDateTime) {
		this.arrivalDateTime = arrivalDateTime;
	}
	public String getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}
	public String getFreePickup() {
		return freePickup;
	}
	public void setFreePickup(String freePickup) {
		this.freePickup = freePickup;
	}
	public String getFlightNo() {
		return flightNo;
	}
	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}
	public String getSpecialRequest() {
		return specialRequest;
	}
	public void setSpecialRequest(String specialRequest) {
		this.specialRequest = specialRequest;
	}
	
	

	
	

}
