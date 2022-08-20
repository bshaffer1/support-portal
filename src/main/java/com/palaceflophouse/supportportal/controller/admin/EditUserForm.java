package com.palaceflophouse.supportportal.controller.admin;

import lombok.Data;

/**
 * Author: Brandon Shaffer
 * Date: 7/26/2022
 */
@Data
public class EditUserForm {

	private String username;
	private String password;
	private String confirmPassword;

	private String firstName;
	private String lastName;

	private String email;

	private String currentAccount;
	private String newAccount;
}
