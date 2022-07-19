package com.palaceflophouse.supportportal.controller;

import com.palaceflophouse.supportportal.entities.User;
import com.palaceflophouse.supportportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

/**
 * Author: Brandon Shaffer
 * Date: 7/18/2022
 */
@Controller
@RequestMapping("/user-homepage")
public class UserHomepageController {

	private final UserRepository userRepo;

	@Autowired
	public UserHomepageController(UserRepository userRepo){
		this.userRepo = userRepo;
	}

	@ModelAttribute(name = "user")
	public User user(Principal principal) {
		String username = principal.getName();
		User user = userRepo.findByUsername(username);
		return user;
	}

	@GetMapping
	public String getUserHomepage(){
		return "userhomepage";
	}

}
