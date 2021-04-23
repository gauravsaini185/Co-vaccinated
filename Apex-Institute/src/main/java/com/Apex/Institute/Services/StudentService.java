package com.Apex.Institute.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import com.Apex.Institute.Models.Batch;
import com.Apex.Institute.Models.Student;
import com.Apex.Institute.Models.Users;
import com.Apex.Institute.Repositories.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private StudentRepository studentRepo;

	public void save(Student student) {
		student.setPassword(encoder.encode(student.getPassword()));
		student.setRole(Users.STUDENT);
		student.setStatus(Users.active);
		studentRepo.save(student);
	}


	public List<Student> getAllStudents() {
		return studentRepo.findAll();
	}
	
	public List<Student> getStudentByBatch(Batch batch){
		return studentRepo.findByBatches(batch);
	}

	public void remove(int student_id) {
		studentRepo.delete(studentRepo.findById(student_id));
	}

	public Student getStudentById(int student_id) {
		return studentRepo.findById(student_id);
	}

	public void updateStudent(Student student){
		Student old = studentRepo.findById(student.getUser_id());
		old.setBatches(student.getBatches());
		old.setFile_path(student.getFile_path());
		old.setPhone(student.getPhone());
		old.setName(student.getName());
		old.setGender(student.getGender());
		old.setEmail(student.getEmail());
		old.setRole(Users.STUDENT);
		old.setStatus(Users.active);
		studentRepo.save(old);
	}
}
