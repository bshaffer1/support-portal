package com.palaceflophouse.supportportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Author: Brandon Shaffer
 * Date: 7/16/2022
 */
@Controller
@RequestMapping("/login")
public class LoginController {

	@GetMapping
	public String loginForm(){
		return "login";
	}

	@PostMapping
	public String loginUser(){
		return "logged in";
	}

}
