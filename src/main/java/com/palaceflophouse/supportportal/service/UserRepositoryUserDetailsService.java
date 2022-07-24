package com.palaceflophouse.supportportal.service;

import com.palaceflophouse.supportportal.entities.User;
import com.palaceflophouse.supportportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Author: Brandon Shaffer
 * Date: 7/17/2022
 */
@Service
public class UserRepositoryUserDetailsService implements UserDetailsService {

	private final UserRepository userRepo;

	@Autowired
	public UserRepositoryUserDetailsService(UserRepository userRepo){
		this.userRepo = userRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepo.findByUsername(username);

		if(user == null){
			throw new UsernameNotFoundException("User '" + username + "' not found");
		}

		return user;
	}
}
