package com.Apex.Institute.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Apex.Institute.Models.Attendance;
import com.Apex.Institute.Models.Student;
import com.Apex.Institute.Repositories.AttendanceRepository;

@Service
public class AttendanceService {

	@Autowired
	private AttendanceRepository attendanceRepo;
	
	public void save(Attendance attendance) {
		attendanceRepo.save(attendance);
	}
	
	public Attendance getAttendanceById(int attendance_id) {
		return attendanceRepo.getOne(attendance_id);
	}
	
	public void update(Attendance attendance) {
		attendanceRepo.save(attendance);
	}
	
	public void saveAll(List<Attendance> attendance) {
		attendanceRepo.saveAll(attendance);
	}

	public List<Attendance> getAttendanceByStudent(Student student) {
		return attendanceRepo.findByStudent(student);
		
	}
}
