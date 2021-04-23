package com.Apex.Institute.Repositories;

import org.springframework.stereotype.Repository;

import com.Apex.Institute.Models.Users;

@Repository
public interface UserRepository extends UserBaseRepository<Users> {

		Users findByEmail(String username);
		
		Users findById(int user_id);
}
