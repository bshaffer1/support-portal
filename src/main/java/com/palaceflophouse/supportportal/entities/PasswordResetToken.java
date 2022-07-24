package com.palaceflophouse.supportportal.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

/**
 * Author: Brandon Shaffer
 * Date: 7/22/2022
 */
@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class PasswordResetToken {

	private static final int EXPIRATION = 24 * 60 * 60 * 1000;

	@Id
	@GeneratedValue
	private Long id;

	private String token;

	@OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "user_id")
	private User user;

	private Long expiryDate;

	public PasswordResetToken(User user){
		this.user = user;
		this.token = generateRandomToken();
		this.expiryDate = calculateExpiryDate();
	}

	public static String generateRandomToken() {
		return UUID.randomUUID().toString();
	}

	private Long calculateExpiryDate() {
		return System.currentTimeMillis() + EXPIRATION;
	}
}
