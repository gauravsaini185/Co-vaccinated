package com.Apex.Institute.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.Apex.Institute.Models.Student;
import com.Apex.Institute.Services.StudentService;

@Controller
public class HomeController {

	@Autowired
	private StudentService studentService;

	@GetMapping("/")
	public String home() {
		return "index";
	}

	@GetMapping("login")
	public String login() {
		return "login";
	}

	@GetMapping("logout")
	public String logout() {
		return "logout";
	}

	@GetMapping("/register")
	public String registerPage(Model model) {
		model.addAttribute("user", new Student());
		return "register";
	}

	@PostMapping("/register")
	public String register(Model model, @ModelAttribute("user") Student user) {
		user.setFile_path("profileImages/default.png");
		studentService.save(user);
		model.addAttribute("message", "registered successfully");
		return "login";
	}

}
