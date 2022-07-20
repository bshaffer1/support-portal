package com.palaceflophouse.supportportal.controller;

import lombok.Data;

/**
 * Author: Brandon Shaffer
 * Date: 7/19/2022
 */
@Data
public class ChangePasswordForm {
	private String oldPassword;
	private String newPassword;
	private String confirmPassword;
}
