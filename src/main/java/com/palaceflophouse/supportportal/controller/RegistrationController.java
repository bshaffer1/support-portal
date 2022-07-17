package com.palaceflophouse.supportportal.controller;

import com.palaceflophouse.supportportal.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * Author: Brandon Shaffer
 * Date: 7/16/2022
 */
@Controller
@RequestMapping("/registration")
@SessionAttributes("user")
public class RegistrationController {

	@GetMapping
	public String showRegistrationForm(){
		return "registration";
	}

	@ModelAttribute(name = "user")
	public User user(){
		return new User();
	}
}
