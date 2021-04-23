package com.Apex.Institute.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.Apex.Institute.Models.Batch;
import com.Apex.Institute.Models.Teacher;
import com.Apex.Institute.Services.BatchService;
import com.Apex.Institute.Services.TeacherService;

@Controller
public class TeacherController {

	@Autowired
	TeacherService teacherService;
	
	@Autowired
	BatchService batchService;
	
    @GetMapping("/addTeacher")
	public String teacherForm(Model model) {
	    model.addAttribute("teacher", new Teacher());
	    List<Batch> batches = batchService.getAllBatches();
	    model.addAttribute("batches", batches);
	    return "admin/teacherForm";
	}

	@PostMapping(value = "/addTeacher")
	public String addTeacher(@ModelAttribute("teacher") Teacher teacher, Model model) {
	    teacherService.save(teacher);
	    model.addAttribute("teachers", teacherService.getAllTeachers());
	    model.addAttribute("message", "Teacher Added");
	    return "admin/allTeachers";
	}

	@GetMapping("/allTeachers")
	public String allTeachers(Model model) {
	    model.addAttribute("teachers", teacherService.getAllTeachers());
	    return "admin/allTeachers";
	}

	@GetMapping("/updateTeacher")
    public String updateTeacher(@RequestParam int teacher_id, Model model){
        model.addAttribute("teacher", teacherService.getTeacherById(teacher_id));
        return "admin/updateTeacher";
    }

    @RequestMapping(value="/updateTeacher", method=RequestMethod.POST)
    public RedirectView updateTeacher(@ModelAttribute Teacher teacher, Model model){
        teacherService.updateTeacher(teacher);
        return new RedirectView("/allTeachers");
    }
    
    @GetMapping("/removeTeacher/{teacher_id}")
    public RedirectView removeTeacher(@PathVariable("teacher_id") int teacher_id, RedirectAttributes model) {
    	try {
        teacherService.remove(teacher_id);
        model.addFlashAttribute("message", "Teacher Removed");
    	}
    	catch (DataIntegrityViolationException e) {
			model.addFlashAttribute("error", "Teacher is assigned to a batch. First change the batch teacher");
		}
        return new RedirectView("/allTeachers");
    }

}
