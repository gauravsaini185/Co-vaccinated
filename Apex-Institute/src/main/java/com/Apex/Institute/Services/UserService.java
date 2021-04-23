package com.Apex.Institute.Services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Apex.Institute.Models.Users;
import com.Apex.Institute.Repositories.BatchRepository;
import com.Apex.Institute.Repositories.StudentRepository;
import com.Apex.Institute.Repositories.TeacherRepository;
import com.Apex.Institute.Repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private StudentRepository studentRepo;

	@Autowired
	private TeacherRepository teacherRepo;

	@Autowired
	private BatchRepository batchRepo;

	public void save(Users user) {
		userRepo.save(user);
	}

	public Map<String, Long> getCount() {

		Map<String, Long> count = new HashMap<String, Long>();
		count.put("students", studentRepo.count());
		count.put("teachers", teacherRepo.count());
		count.put("batches", batchRepo.count());
		return count;
	}
	
	public Users findByEmail(String email) {
		return userRepo.findByEmail(email);
	}
	
	public Users findById(int user_id) {
		return userRepo.findById(user_id);
	}

	public void updateUser(Users newuser) {
		Users old = userRepo.findById(newuser.getUser_id());
		old.setPhone(newuser.getPhone());
		old.setName(newuser.getName());
		old.setGender(newuser.getGender());
		old.setEmail(newuser.getEmail());
		old.setRole(Users.STUDENT);
		old.setStatus(Users.active);
		userRepo.save(old);
		
	}

}
