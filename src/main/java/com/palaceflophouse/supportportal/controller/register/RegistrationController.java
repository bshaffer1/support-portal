package com.palaceflophouse.supportportal.controller.register;

import com.palaceflophouse.supportportal.entities.User;
import com.palaceflophouse.supportportal.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Author: Brandon Shaffer
 * Date: 7/16/2022
 */
@Controller
@RequestMapping("/registration")
public class RegistrationController {

	private UserRepository userRepo;
	private PasswordEncoder passwordEncoder;

	public RegistrationController(UserRepository userRepository, PasswordEncoder passwordEncoder){
		this.userRepo = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@GetMapping
	public String showRegistrationForm(){
		return "registration";
	}

	@PostMapping
	public String processRegistration(RegistrationForm form){
		User user = form.toUser(passwordEncoder);
		User savedUser = userRepo.save(user);
		return "redirect:/login";
	}
}
