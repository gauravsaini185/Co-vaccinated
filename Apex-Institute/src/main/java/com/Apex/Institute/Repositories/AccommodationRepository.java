package com.Apex.Institute.Repositories;

import com.Apex.Institute.Models.Accommodation;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Integer> {

	public Accommodation findById(int accommodation_id);

}
