package com.palaceflophouse.supportportal.entities;

import lombok.Data;

import java.time.LocalDate;

/**
 * Author: Brandon Shaffer
 * Date: 7/16/2022
 */
@Data
public class User {

	private Long id;

	private String username;
	private String password;

	private String firstName;
	private String lastName;

	private LocalDate dateCreated;
	private Boolean isAdmin;
}
