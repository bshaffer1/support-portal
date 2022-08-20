package com.palaceflophouse.supportportal.controller.admin;

import com.palaceflophouse.supportportal.entities.Account;
import com.palaceflophouse.supportportal.entities.User;
import com.palaceflophouse.supportportal.service.AccountService;
import com.palaceflophouse.supportportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import java.util.Set;

/**
 * Author: Brandon Shaffer
 * Date: 7/26/2022
 */
@Controller
@RequestMapping("/admin/edit-user")
public class EditUserController {

	private final UserService userService;
	private final AccountService accountService;

	@Autowired
	public EditUserController(UserService userService, AccountService accountService) {
		this.userService = userService;
		this.accountService = accountService;
	}

	@GetMapping
	public String editUser(@RequestParam Long userId, Model model){

		User user = userService.loadUserById(userId).orElseThrow(()
				-> new IllegalStateException("No user found matching ID."));

		model.addAttribute("user", user);
		Account currentAccount = user.getAccount();
		model.addAttribute("currentAccount", currentAccount);

		Set<Account> accounts = accountService.loadAllAccounts();
		model.addAttribute("accounts", accounts);

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

		//Set the user's account if it's new
		String newAccountName = form.getNewAccount();
		if(newAccountName != null){
			if(!newAccountName.equals(form.getCurrentAccount())){
				Account account = accountService.loadAccountByName(newAccountName)
						.orElseThrow(() -> new IllegalStateException());
				user.setAccount(account);
			}
		}

		userService.saveUser(user);
		return "redirect:/admin/admin-console";
	}
}
