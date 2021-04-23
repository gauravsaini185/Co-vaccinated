package com.Apex.Institute.Services;

import java.util.List;





import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import com.Apex.Institute.Models.Accommodation;
import com.Apex.Institute.Models.Batch;
import com.Apex.Institute.Repositories.AccommodationRepository;

@Service
public class AccommodationService {

	
	@Autowired
	private AccommodationRepository accRepo;

	
	public void saveAccommodatin(Accommodation accommodation) {
		accRepo.save(accommodation);
	}

	public List<Accommodation> getAllAccomodation() {
		return accRepo.findAll();
	}

	public Accommodation getAccommodationById(int accommodation_id) {
		return accRepo.findById(accommodation_id);
	}
	
	
	public void remove(int accommodation_id) {
		accRepo.delete(accRepo.findById(accommodation_id));
	}

	public void updateAccomodation(Accommodation accommodation) {
		Accommodation old = accRepo.findById(accommodation.getAccommodation_id());
		old.setName(accommodation.getName());
		old.setEmail(accommodation.getEmail());
		old.setCollegeName(accommodation.getCollegeName());
		old.setCollegeRollNo(accommodation.getCollegeRollNo());
		old.setCollegeAddress(accommodation.getCollegeAddress());
		old.setRoomType(accommodation.getRoomType());
		old.setArrivalDateTime(accommodation.getArrivalDateTime());
		old.setDepartureDate(accommodation.getDepartureDate());
		old.setFreePickup(accommodation.getFreePickup());
		old.setFlightNo(accommodation.getFlightNo());
		old.setSpecialRequest(accommodation.getSpecialRequest());
		old.setStatus(accommodation.getStatus());
		
		accRepo.save(old);				
	}

	
	

}
