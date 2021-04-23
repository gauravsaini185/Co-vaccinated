package com.Apex.Institute.Services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Apex.Institute.Models.Assignment;
import com.Apex.Institute.Models.Batch;
import com.Apex.Institute.Repositories.AssignmentRepository;

@Service
public class AssignmentService {

	@Autowired
	private AssignmentRepository assignmentRepo;
	
	public void saveAssignment(MultipartFile file, Assignment assignment) throws IOException {
		assignment.setFile(file.getBytes());
		assignment.setType(file.getContentType());
		assignmentRepo.save(assignment);
	}
	
	public List<Assignment> getAssignmentsByBatch(Batch batch){
		return assignmentRepo.findByBatch(batch);
	}

	public List<Assignment> getAllAssignment() {
		return assignmentRepo.findAll();		
	}

	public Assignment getAssignmentById(int assignment_id) {
		return assignmentRepo.getOne(assignment_id);
	}
	
	public void removeAssignment(int assignment_id) {
		assignmentRepo.deleteById(assignment_id);
		
	}
	
}
