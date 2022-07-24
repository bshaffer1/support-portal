package com.palaceflophouse.supportportal.controller;

import lombok.Data;

/**
 * Author: Brandon Shaffer
 * Date: 7/23/2022
 */
@Data
public class PasswordResetForm {

	private String password;
	private String confirmPassword;
}
