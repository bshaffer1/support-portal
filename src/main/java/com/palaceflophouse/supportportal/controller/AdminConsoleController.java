package com.palaceflophouse.supportportal.controller;

import com.palaceflophouse.supportportal.entities.User;
import com.palaceflophouse.supportportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Author: Brandon Shaffer
 * Date: 7/20/2022
 */
@Configuration
@RequestMapping("/admin-console")
public class AdminConsoleController {

	public final UserRepository userRepository;

	@Autowired
	public AdminConsoleController(UserRepository userRepository){
		this.userRepository = userRepository;
	}

	@ModelAttribute("users")
	public Iterable<User> users(){
		return userRepository.findAll();
	}

	@GetMapping
	public String getAdminConsole(){
		return "admin-console";
	}

}
