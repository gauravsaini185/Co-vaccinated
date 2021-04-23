package com.Apex.Institute.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.Apex.Institute.Models.Users;

@NoRepositoryBean
public interface UserBaseRepository<T extends Users> extends JpaRepository<T, Integer> {
	
}
