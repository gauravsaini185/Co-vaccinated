package com.Apex.Institute.Components;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.Apex.Institute.Models.Student;
import com.Apex.Institute.Models.Teacher;
import com.Apex.Institute.Models.Users;
import com.Apex.Institute.Repositories.UserRepository;
import com.Apex.Institute.Services.StudentService;
import com.Apex.Institute.Services.TeacherService;

@Component
public class UserCreator {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private StudentService sService;

	@Autowired
	private TeacherService tService;

	@Autowired
	private PasswordEncoder encoder;

	@PostConstruct
	public void init() {
		if (userRepository.count() == 0) {
			userRepository.save(new Users("admin", "4234234", "admin@gmail.com", "male", encoder.encode("admin"), 1, 1));
			sService.save(new Student("student 1", "4234234", "student1@gmail.com", "male", "student1", 2, 1));
			sService.save(new Student("student 2", "4234234", "student2@gmail.com", "male", "student2", 2, 1));
			sService.save(new Student("student 3", "4234234", "student3@gmail.com", "male", "student3", 2, 1));
			sService.save(new Student("student 4", "4234234", "student4@gmail.com", "male", "student4", 2, 1));
			tService.save(new Teacher("teacher 1", "4234234", "teacher1@gmail.com", "male", "teacher1", 3, 1));
			tService.save(new Teacher("teacher 2", "4234234", "teacher2@gmail.com", "male", "teacher2", 3, 1));
			tService.save(new Teacher("teacher 3", "4234234", "teacher3@gmail.com", "male", "teacher3", 3, 1));
			tService.save(new Teacher("teacher 4", "4234234", "teacher4@gmail.com", "male", "teacher4", 3, 1));
		}
	}
}

