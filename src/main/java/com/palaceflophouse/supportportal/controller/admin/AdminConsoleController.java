package com.palaceflophouse.supportportal.controller.admin;

import com.palaceflophouse.supportportal.entities.Account;
import com.palaceflophouse.supportportal.entities.User;
import com.palaceflophouse.supportportal.repository.AccountRepository;
import com.palaceflophouse.supportportal.repository.UserRepository;
import com.palaceflophouse.supportportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Author: Brandon Shaffer
 * Date: 7/20/2022
 */
@Configuration
@RequestMapping("/admin/admin-console")
public class AdminConsoleController {

	public final UserRepository userRepository;
	public final AccountRepository accountRepository;

	@Autowired
	public AdminConsoleController(UserRepository userRepository, UserService userService, AccountRepository accountRepository){
		this.userRepository = userRepository;
		this.accountRepository = accountRepository;
	}

	@ModelAttribute("users")
	public Iterable<User> users(){
		return userRepository.findAll();
	}

	@ModelAttribute("accounts")
	public Iterable<Account> accounts(){
		return accountRepository.findAll();
	}

	@GetMapping
	public String getAdminConsole(){
		return "admin-console";
	}

}
