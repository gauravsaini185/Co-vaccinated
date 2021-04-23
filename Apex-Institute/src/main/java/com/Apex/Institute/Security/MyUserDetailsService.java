package com.Apex.Institute.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Apex.Institute.Models.Users;
import com.Apex.Institute.Repositories.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepo;
	
	public MyUserDetailsService(UserRepository userRepo) {
		userRepo = this.userRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = this.userRepo.findByEmail(username);
		if(user.getEmail() == null){
	        throw new UsernameNotFoundException("User not authorized.");
	    }
		MyUserDetails myUserDetails = new MyUserDetails(user);
		return myUserDetails;
	}
	
	

}
