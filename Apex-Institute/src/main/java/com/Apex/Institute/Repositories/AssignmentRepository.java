package com.Apex.Institute.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Apex.Institute.Models.Assignment;
import com.Apex.Institute.Models.Batch;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Integer>{

	List<Assignment> findByBatch(Batch batch);

}
