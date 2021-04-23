package com.Apex.Institute.Services;

import java.util.List;

import com.Apex.Institute.Models.Teacher;
import com.Apex.Institute.Models.Users;
import com.Apex.Institute.Repositories.TeacherRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TeacherService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private TeacherRepository teacherRepo;

    public List<Teacher> getAllTeachers() {
        return teacherRepo.findAll();
    }

    public void save(Teacher teacher) {
        teacher.setPassword(encoder.encode(teacher.getPassword()));
        teacher.setRole(Users.TEACHER);
        teacher.setStatus(Users.active);
        teacherRepo.save(teacher);
    }

    public void remove(int teacher_id) {
        teacherRepo.delete(teacherRepo.findById(teacher_id));
    }

	public Teacher getTeacherById(int teacher_id) {
		return teacherRepo.findById(teacher_id);
	}

	public void updateTeacher(Teacher teacher) {
		Teacher old = teacherRepo.findById(teacher.getUser_id());
		old.setPhone(teacher.getPhone());
		old.setName(teacher.getName());
		old.setGender(teacher.getGender());
		old.setEmail(teacher.getEmail());
		old.setTeacher_salary(teacher.getTeacher_salary());
		old.setPassword(teacher.getPassword());
		old.setRole(Users.TEACHER);
		old.setStatus(Users.active);
		teacherRepo.save(old);
		
	}

}