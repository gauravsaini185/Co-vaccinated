package com.Apex.Institute.Services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.Apex.Institute.Models.Accommodation;
import com.Apex.Institute.Models.Shipment;

import com.Apex.Institute.Repositories.ShipmentRepository;

@Service
public class ShipmentService {

	@Autowired
	private ShipmentRepository shipRepo;

	
	public void saveShipment(Shipment shipment) {
		shipRepo.save(shipment);
	}
	
	
	
	public List<Shipment> getAllShipment() {
		return shipRepo.findAll();
	}

	public Shipment getShipmentById(int shipment_id) {
		return shipRepo.findById(shipment_id);
	}
	
	
	public void remove(int shipment_id) {
		shipRepo.delete(shipRepo.findById(shipment_id));
	}
	
	
	public void updateShipment(Shipment shipment) {
		Shipment old = shipRepo.findById(shipment.getShipment_id());
		old.setCollege(shipment.getCollege());
		old.setCollegeRollNo(shipment.getCollegeRollNo());
		old.setContact_Person(shipment.getContact_Person());
		old.setPhone(shipment.getPhone());
		old.setAddress(shipment.getAddress());
		old.setDelivery_Time_From(shipment.getDelivery_Time_From());
		old.setDelivery_Time_To(shipment.getDelivery_Time_To());
		old.setEmail(shipment.getEmail());
		old.setYour_Belongings(shipment.getYour_Belongings());
		old.setShipstatus(shipment.getShipstatus());
		
		shipRepo.save(old);				
	}
	
	
	
	
	
}
