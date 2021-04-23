package com.Apex.Institute.Repositories;

import java.util.List;

import com.Apex.Institute.Models.Batch;
import com.Apex.Institute.Models.Student;

public interface StudentRepository extends UserBaseRepository<Student>{

	public List<Student> findByBatches(Batch batch);


	public Student findById(int id);

}
