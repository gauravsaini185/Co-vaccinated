package com.Apex.Institute.Repositories;

import com.Apex.Institute.Models.Teacher;

public interface TeacherRepository extends UserBaseRepository<Teacher> {

    public Teacher findById(int id);

}
