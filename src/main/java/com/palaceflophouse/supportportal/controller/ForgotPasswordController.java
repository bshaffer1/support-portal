package com.palaceflophouse.supportportal.controller;

import com.palaceflophouse.supportportal.entities.User;
import com.palaceflophouse.supportportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Author: Brandon Shaffer
 * Date: 7/21/2022
 */
@Controller
@RequestMapping("/forgot-password")
public class ForgotPasswordController {

	private final UserRepository userRepository;

	@Autowired
	public ForgotPasswordController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping
	public String forgotPassword(){
		return "forgot-password";
	}

	@PostMapping
	public String submitForgotPassword(ForgotPasswordForm form){
		String userEnteredEmail = form.getUserEnteredEmail();

		User user = userRepository.findByEmail(userEnteredEmail);

		return "redirect:/login";
	}
}
