package com.Apex.Institute.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Apex.Institute.Models.Batch;
import com.Apex.Institute.Models.Teacher;
import com.Apex.Institute.Repositories.BatchRepository;

@Service
public class BatchService {

	@Autowired
	private BatchRepository batchRepo;

	public void saveBatch(Batch batch) {
		batchRepo.save(batch);
	}

	public List<Batch> getAllBatches() {
		return batchRepo.findAll();
	}

	public void remove(int batch_id) {
		batchRepo.delete(batchRepo.findById(batch_id));
	}
	
	public Batch getBatchById(int batch_id) {
		return batchRepo.findById(batch_id);
	}
	
	public List<Batch> getBatchByTeacher(Teacher teacher){
		return batchRepo.findByBatch_teacher(teacher);
	}

	public void updateBatch(Batch batch) {
		Batch old = batchRepo.findById(batch.getBatch_id());
		old.setBatch_class(batch.getBatch_class());
		old.setBatch_fee(batch.getBatch_fee());
		old.setBatch_name(batch.getBatch_name());
		old.setBatch_teacher(batch.getBatch_teacher());
		old.setBatch_time(batch.getBatch_time());
		old.setSchedule(batch.getSchedule());
		old.setSubject(batch.getSubject());
		old.setBatch_fee(batch.getBatch_fee());
		batchRepo.save(old);				
	}

//	public List<Student> getStudentsByBatch(Batch batch){
//		return batchRepo.findByStudent_batches(batch);
//	}
}
