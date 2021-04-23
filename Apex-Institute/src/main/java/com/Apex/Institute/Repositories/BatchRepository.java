package com.Apex.Institute.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Apex.Institute.Models.Batch;
import com.Apex.Institute.Models.Teacher;

@Repository
public interface BatchRepository extends JpaRepository<Batch, Integer> {

    public Batch findById(int id);

    @Query("FROM Batch WHERE batch_teacher = ?1")
	public List<Batch> findByBatch_teacher(Teacher teacher);

    
//    public List<Student> findByStudent_batches(Batch batch);
    

}
