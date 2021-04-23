package com.Apex.Institute.Controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.Apex.Institute.Models.Assignment;
import com.Apex.Institute.Models.Batch;
import com.Apex.Institute.Models.Student;
import com.Apex.Institute.Services.AssignmentService;
import com.Apex.Institute.Services.BatchService;

@Controller
public class AssignmentController {

	@Autowired
	private AssignmentService assignmentService;

	@Autowired
	private BatchService batchService;
	
	@Autowired
	private UserController userController;

	@GetMapping("/addAssignment")
	public String assignmentForm(Model model) {
		model.addAttribute("assignment", new Assignment());
		model.addAttribute("batches", batchService.getAllBatches());
		return "teacher/assignmentForm";
	}

	@PostMapping("/addAssignment")
	public RedirectView addAssignment(@RequestParam("file") MultipartFile file, @RequestParam("batch") Batch batch,
			RedirectAttributes redirectAttr,@RequestParam String date, @RequestParam String subject) throws IOException {
		Assignment assignment = new Assignment();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-dd-yyyy");
		assignment.setAssignment_date(LocalDate.parse(date, formatter));
		assignment.setSubject(subject);
		assignment.setBatch(batch);
		assignmentService.saveAssignment(file, assignment);
		redirectAttr.addFlashAttribute("message", "Assignment added");
		return new RedirectView("/allAssignments");
	}

	@GetMapping("/allAssignments")
	public String allAssignments(Model model) {
		model.addAttribute("assignments", assignmentService.getAllAssignment());
		return "teacher/allAssignments";
	}
	
	@GetMapping("/studentsAssignments")
	public String studentsAssignments(Model model) {
		Student user = (Student) userController.getCurrentUser();
		Collection<Batch> userBatches = user.getBatches();
		Iterator<Batch> itr = userBatches.iterator();
		List<Assignment> userAssignments = new ArrayList<>();
		while (itr.hasNext()) {
			userAssignments.addAll(assignmentService.getAssignmentsByBatch(itr.next()));
		}
		model.addAttribute("assignments", userAssignments);
		model.addAttribute("user", user);
		return "student/allAssignments";
	}
	
	@GetMapping("/downloadAssignment/{assignment_id}")
	public ResponseEntity<ByteArrayResource> downloadAssignment(@PathVariable ("assignment_id") int assignment_id) {
		Assignment assignment = assignmentService.getAssignmentById(assignment_id);
		String name = assignment.getBatch().getBatch_name()+"/"+assignment.getAssignment_date()+".pdf";
	    return ResponseEntity.ok()
	    		.contentType(MediaType.APPLICATION_OCTET_STREAM)
	            .header(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=\""+name+"\"")
	            .body(new ByteArrayResource(assignment.getFile()));
	}
	
	@GetMapping("/removeAssignment/{assignment_id}")
	public RedirectView removeAssignment(@PathVariable("assignment_id") int assignment_id, RedirectAttributes redirectAttr) {
		assignmentService.removeAssignment(assignment_id);
		redirectAttr.addFlashAttribute("message", "Assignment deleted");
		return new RedirectView("/allAssignments");
	}

}
