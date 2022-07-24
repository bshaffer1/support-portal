package com.palaceflophouse.supportportal.service;

import com.palaceflophouse.supportportal.entities.PasswordResetToken;
import com.palaceflophouse.supportportal.entities.User;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Author: Brandon Shaffer
 * Date: 7/23/2022
 */
public interface UserService {

	UserDetails loadUserByUsername(String username);

	User loadUserByEmail(String email);

	PasswordResetToken generateTokenForUser(User user);

	TokenStatus validateToken(PasswordResetToken token);

	PasswordResetToken loadToken(String token);

	void updateUserPassword(User user, String password);

	boolean checkUserPassword(User user, String password);
}
