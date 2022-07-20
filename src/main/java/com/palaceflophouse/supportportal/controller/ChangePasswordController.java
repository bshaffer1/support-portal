package com.palaceflophouse.supportportal.controller;

import com.palaceflophouse.supportportal.entities.User;
import com.palaceflophouse.supportportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

/**
 * Author: Brandon Shaffer
 * Date: 7/19/2022
 */
@Controller
@RequestMapping("/change-password")
public class ChangePasswordController {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private String username;

	@Autowired
	public ChangePasswordController(UserRepository userRepository, PasswordEncoder passwordEncoder){
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@GetMapping
	public String getChangePassword(){
		return "change-password";
	}

	@ModelAttribute(name = "user")
	public User user(Principal principal){
		String username = principal.getName();
		this.username = username;
		User user = userRepository.findByUsername(username);
		return user;
	}

	@PostMapping
	public String processChangePassword(ChangePasswordForm changePasswordForm){
		User thisUser = userRepository.findByUsername(username);

		String oldPassword = changePasswordForm.getOldPassword();
		String currentPassword = thisUser.getPassword();
		if(!passwordEncoder.matches(oldPassword, currentPassword)){
			throw new IllegalStateException("Old Password was incorrect!");
		}

		String newPassword = changePasswordForm.getNewPassword();
		String confirmPassword = changePasswordForm.getConfirmPassword();
		if(!newPassword.equals(confirmPassword)){
			throw new IllegalStateException("Passwords did not match!");
		}

		String encodedNewPassword = passwordEncoder.encode(newPassword);
		thisUser.setPassword(encodedNewPassword);

		this.userRepository.save(thisUser);

		return "redirect:/user-homepage";
	}
}
