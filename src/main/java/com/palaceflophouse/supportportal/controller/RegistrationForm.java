package com.palaceflophouse.supportportal.controller;

import com.palaceflophouse.supportportal.entities.User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Author: Brandon Shaffer
 * Date: 7/17/2022
 */
@Data
public class RegistrationForm {

	private String username;
	private String password;

	private String firstName;
	private String lastName;

	public User toUser(PasswordEncoder passwordEncoder){
		User user = new User(username, passwordEncoder.encode(password), firstName, lastName, LocalDate.now());

		return user;
	}
}
