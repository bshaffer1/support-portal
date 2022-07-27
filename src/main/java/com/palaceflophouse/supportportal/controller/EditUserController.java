package com.palaceflophouse.supportportal.controller;

import com.palaceflophouse.supportportal.entities.User;
import com.palaceflophouse.supportportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import java.util.Optional;

/**
 * Author: Brandon Shaffer
 * Date: 7/26/2022
 */
@Controller
@RequestMapping("/admin/edit-user")
public class EditUserController {

	private final UserService userService;

	@Autowired
	public EditUserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public String editUser(@RequestParam Long userId, Model model){

		User user = userService.loadUserById(userId).orElseThrow(()
				-> new IllegalStateException("No user found matching ID."));

		model.addAttribute("user", user);
		//TODO Add the accounts for this user to the model.
		return "edit-user";
	}

	@PostMapping
	public String processEditUser(@RequestParam Long userId, EditUserForm form){

		User user = userService.loadUserById(userId).orElseThrow();

		String password = form.getPassword();
		String confirmPassword = form.getConfirmPassword();

		if(!StringUtils.isEmpty(password)) {
			if(!password.equals(confirmPassword)){
				throw new IllegalStateException("Passwords do not match!");
			}

			boolean passwordIsSame = userService.checkUserPassword(user, password);

			if (!passwordIsSame) {
				user = userService.updateUserPassword(user, password);
			}
		}

		user.setFirstName(form.getFirstName());
		user.setLastName(form.getLastName());
		user.setEmail(form.getEmail());

		userService.saveUser(user);
		return "redirect:/admin/admin-console";
	}
}
