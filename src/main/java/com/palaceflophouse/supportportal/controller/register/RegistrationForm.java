package com.palaceflophouse.supportportal.controller.register;

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

	private String email;

	public User toUser(PasswordEncoder passwordEncoder){
		User user = User.builder()
				.username(username)
				.password(passwordEncoder.encode(password))
				.firstName(firstName)
				.lastName(lastName)
				.email(email)
				.dateCreated(LocalDate.now())
				.build();

		return user;
	}
}
