package com.palaceflophouse.supportportal.controller;

import com.palaceflophouse.supportportal.entities.Account;
import com.palaceflophouse.supportportal.entities.User;
import com.palaceflophouse.supportportal.service.AccountService;
import com.palaceflophouse.supportportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Author: Brandon Shaffer
 * Date: 8/14/2022
 */
@Controller
@RequestMapping("/admin/edit-account")
public class EditAccountController {

	private final AccountService accountService;
	private final UserService userService;

	@Autowired
	public EditAccountController(AccountService accountService, UserService userService) {
		this.accountService = accountService;
		this.userService = userService;
	}

	@GetMapping
	public String editAccount(@RequestParam Long accountId, Model model){
		Account account = accountService.loadAccountById(accountId)
				.orElseThrow(EntityNotFoundException::new);
		model.addAttribute("account", account);

		Set<User> users = account.getUsers();
		model.addAttribute("users", users);

		return "edit-account";
	}

	@GetMapping("/remove")
	public String remove(@RequestParam Long userId, Long accountId){
		User user = userService.loadUserById(userId).
				orElseThrow(() -> new IllegalStateException("User not found!"));

		Account account = accountService.loadAccountById(accountId)
				.orElseThrow(() -> new IllegalStateException("Account not found!"));

		Set<User> users = account.getUsers();
		users.remove(user);

		user.setAccount(null);

		accountService.saveAccount(account);
		userService.saveUser(user);

		return "redirect:/admin/edit-account?accountId=" + accountId;
	}
}
