package com.Apex.Institute.Repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Apex.Institute.Models.Shipment;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Integer>{

	public Shipment findById(int shipment_id);
	
}
