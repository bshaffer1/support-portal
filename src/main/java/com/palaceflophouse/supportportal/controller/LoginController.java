package com.palaceflophouse.supportportal.controller;

import com.palaceflophouse.supportportal.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Author: Brandon Shaffer
 * Date: 7/16/2022
 */
@Controller
@RequestMapping("/login")
@SessionAttributes("user")
public class LoginController {

	@GetMapping
	public String loginForm(){
		return "login";
	}

	@ModelAttribute(name = "user")
	public User user(){
		return new User();
	}

	@PostMapping
	public String loginUser(User user){
		return "redirect:/login/loggedIn";
	}

	@GetMapping("loggedIn")
	public String loggedIn(){
		return "loggedIn";
	}

}
