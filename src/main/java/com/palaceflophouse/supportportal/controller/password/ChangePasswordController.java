package com.palaceflophouse.supportportal.controller.password;

import com.palaceflophouse.supportportal.entities.User;
import com.palaceflophouse.supportportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

	private final UserService userService;
	private String username;

	@Autowired
	public ChangePasswordController(UserService userService){
		this.userService = userService;
	}

	@GetMapping
	public String getChangePassword(){
		return "change-password";
	}

	@ModelAttribute(name = "user")
	public User user(Principal principal){
		String username = principal.getName();
		this.username = username;
		User user = (User) userService.loadUserByUsername(username);
		return user;
	}

	@PostMapping
	public String processChangePassword(ChangePasswordForm changePasswordForm){
		User thisUser = (User) userService.loadUserByUsername(username);

		String oldPassword = changePasswordForm.getOldPassword();
		boolean isValid = userService.checkUserPassword(thisUser, oldPassword);
		if(!isValid){
			throw new IllegalStateException("Old Password was incorrect!");
		}

		String newPassword = changePasswordForm.getNewPassword();
		String confirmPassword = changePasswordForm.getConfirmPassword();
		if(!newPassword.equals(confirmPassword)){
			throw new IllegalStateException("Passwords did not match!");
		}

		userService.updateUserPassword(thisUser, newPassword);

		return "redirect:/user-homepage";
	}
}
