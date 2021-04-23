package com.Apex.Institute.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Apex.Institute.Models.Attendance;
import com.Apex.Institute.Models.Student;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

	public List<Attendance> findByStudent(Student student);


}
