package com.palaceflophouse.supportportal.controller;

import com.palaceflophouse.supportportal.entities.Account;
import com.palaceflophouse.supportportal.entities.SupportItem;
import com.palaceflophouse.supportportal.entities.User;
import com.palaceflophouse.supportportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

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

	@ModelAttribute(name = "accountItems")
	public List<SupportItem> accountItems(Principal principal){
		String username = principal.getName();
		User user = userRepo.findByUsername(username);

		Account account = user.getAccount();
		if(account == null){
			return new ArrayList<>();
		}

		List<SupportItem> supportItems = account.getSupportItems();
		return supportItems;
	}

	@GetMapping
	public String getUserHomepage(){
		return "userhomepage";
	}
}
