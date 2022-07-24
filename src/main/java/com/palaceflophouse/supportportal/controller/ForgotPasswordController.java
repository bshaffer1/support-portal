package com.palaceflophouse.supportportal.controller;

import com.palaceflophouse.supportportal.entities.PasswordResetToken;
import com.palaceflophouse.supportportal.entities.User;
import com.palaceflophouse.supportportal.service.EmailSender;
import com.palaceflophouse.supportportal.service.TokenStatus;
import com.palaceflophouse.supportportal.service.UserRepositoryUserDetailsService;
import com.palaceflophouse.supportportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * Author: Brandon Shaffer
 * Date: 7/21/2022
 */
@Controller
@RequestMapping("/forgot-password")
public class ForgotPasswordController {

	private final UserService userService;
	private final EmailSender emailSender;

	@Autowired
	public ForgotPasswordController(UserService userService, EmailSender emailSender) {
		this.userService = userService;
		this.emailSender = emailSender;
	}

	@GetMapping
	public String forgotPassword(){
		return "forgot-password";
	}

	@PostMapping
	public String submitForgotPassword(ForgotPasswordForm form){
		String userEnteredEmail = form.getUserEnteredEmail();

		User user = userService.loadUserByEmail(userEnteredEmail);

		PasswordResetToken passwordResetToken = userService.generateTokenForUser(user);

		final String baseUrl =
				ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();

		String link = baseUrl + "/forgot-password/reset?token=" + passwordResetToken.getToken();

		String body = StringUtils.replace(MESSAGE_TEMPLATE, "{{link}}", link);

		emailSender.send(userEnteredEmail, body);

		return "redirect:/login";
	}

	@GetMapping("/reset")
	public String resetPassword(Model model, @RequestParam("token") String token){
		PasswordResetToken passwordResetToken = userService.loadToken(token);
		TokenStatus tokenStatus = userService.validateToken(passwordResetToken);

		if(!TokenStatus.VALID.equals(tokenStatus)){
			return "redirect:/login";
		}

		model.addAttribute("token", token);

		return "reset-password";
	}

	@PostMapping("/reset")
	public String processPasswordReset(@RequestParam("token") String token, PasswordResetForm form){

		if(token == null){
			throw new IllegalStateException("Invalid Password Reset Token.");
		}

		PasswordResetToken passwordResetToken = userService.loadToken(token);
		TokenStatus tokenStatus = userService.validateToken(passwordResetToken);

		if(!TokenStatus.VALID.equals(tokenStatus)){
			throw new IllegalStateException("Invalid Password Reset Token.");
		}

		String password = form.getPassword();
		String confirmPassword = form.getConfirmPassword();

		if(!password.equals(confirmPassword)){
			throw new IllegalStateException("Passwords did not match.");
		}

		User user = passwordResetToken.getUser();
		userService.updateUserPassword(user, password);

		return "redirect:/login";
	}

	public static final String MESSAGE_TEMPLATE =
			"<!DOCTYPE html>\n" +
			"<html lang=\"en\">\n" +
			"<head>\n" +
			"    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
			"    <title>Reset Password</title>\n" +
			"</head>\n" +
			"<body>\n" +
			"\n" +
			"    <h1>Reset Your Support Portal Password</h1>\n" +
			"    <h3>Click the following link to reset your support portal password</h3>\n" +
			"    <a href=\"{{link}}\">Reset Password</a>\n" +
			"\n" +
			"</body>\n" +
			"</html>";
}
