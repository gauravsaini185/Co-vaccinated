package com.Apex.Institute.Controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.Apex.Institute.Models.Assignment;
import com.Apex.Institute.Models.Batch;
import com.Apex.Institute.Models.Student;
import com.Apex.Institute.Models.Teacher;
import com.Apex.Institute.Models.Users;
import com.Apex.Institute.Services.AssignmentService;
import com.Apex.Institute.Services.BatchService;
import com.Apex.Institute.Services.StudentService;
import com.Apex.Institute.Services.TeacherService;
import com.Apex.Institute.Services.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private BatchService batchService;
    
    @Autowired
    private AssignmentService assignmentService;
    
    

    @GetMapping("/student")
    public String studentHome(Model model) {
    	Student user = (Student) getCurrentUser();
        model.addAttribute("user", user);
        Collection<Batch> userBatches = user.getBatches();
        Iterator<Batch> itr = userBatches.iterator();
        List<Assignment> userAssignments = new ArrayList<>();
        while(itr.hasNext()) {
        userAssignments.addAll(assignmentService.getAssignmentsByBatch(itr.next()));
        }
        model.addAttribute("assignments",userAssignments);
        return "student/studentHome";
    }
    
    public Users getCurrentUser() {
    	Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        Users user = userService.findByEmail(loggedInUser.getName());
        return user;
    }

    @GetMapping("/admin")
    public String adminHome(Model model) { 
        model.addAttribute("studentCount", userService.getCount().get("students"));
        model.addAttribute("teacherCount", userService.getCount().get("teachers"));
        model.addAttribute("batchCount", userService.getCount().get("batches"));
        model.addAttribute("teachers", teacherService.getAllTeachers());
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("batches", batchService.getAllBatches());
        return "admin/adminHome";
    }
    
    @GetMapping("/teacher")
    public String teacherHome(Model model) {
    	Teacher teacher = (Teacher) getCurrentUser();
    	  model.addAttribute("studentCount", userService.getCount().get("students"));
          model.addAttribute("teacherCount", userService.getCount().get("teachers"));
          model.addAttribute("batchCount", userService.getCount().get("batches"));
          model.addAttribute("teachers", teacherService.getAllTeachers());
          model.addAttribute("students", studentService.getAllStudents());
          model.addAttribute("batches", batchService.getBatchByTeacher(teacher));
          return "teacher/teacherHome";
    }
    
    @GetMapping("/viewProfile")
    public String viewProfile(Model model) {
    	Users user = getCurrentUser();
    	model.addAttribute("user",user);
    	return "/profile";
    }
    
    @PostMapping("/updateProfile")
    public RedirectView updateProfile(@ModelAttribute Users user) {
    	userService.updateUser(user);
    	return new RedirectView("/student");
    }
    
}
