package com.palaceflophouse.supportportal.service;

import com.palaceflophouse.supportportal.entities.PasswordResetToken;
import com.palaceflophouse.supportportal.entities.User;
import com.palaceflophouse.supportportal.repository.PasswordResetTokenRepository;
import com.palaceflophouse.supportportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Author: Brandon Shaffer
 * Date: 7/23/2022
 */
@Service
public class AppUserService implements UserService{

	private final UserRepository userRepo;
	private final PasswordResetTokenRepository passwordResetTokenRepo;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public AppUserService(UserRepository userRepo, PasswordResetTokenRepository passwordResetTokenRepository, PasswordEncoder passwordEncoder){
		this.userRepo = userRepo;
		this.passwordResetTokenRepo = passwordResetTokenRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Optional<User> loadUserById(Long userId) {
		return userRepo.findById(userId);
	}

	@Override
	public User saveUser(User user) {
		User save = userRepo.save(user);
		return save;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepo.findByUsername(username);

		if(user == null){
			throw new UsernameNotFoundException("User '" + username + "' not found");
		}

		return user;
	}

	public User loadUserByEmail(String email){
		User user = userRepo.findByEmail(email);

		if(user == null){
			throw new UsernameNotFoundException("User with email '" + email + "' not found");
		}

		return user;
	}

	public PasswordResetToken generateTokenForUser(User user){
		PasswordResetToken passwordResetToken = new PasswordResetToken(user);
		passwordResetTokenRepo.save(passwordResetToken);

		return passwordResetToken;
	}

	public TokenStatus validateToken(PasswordResetToken token){

		if(token == null){
			return TokenStatus.INVALID;
		}
		Long expirationDate = token.getExpiryDate();
		if(expirationDate < System.currentTimeMillis()){
			return TokenStatus.EXPIRED;
		}

		return TokenStatus.VALID;
	}

	public PasswordResetToken loadToken(String token){
		return passwordResetTokenRepo.findByToken(token);
	}

	public User updateUserPassword(User user, String password){
		String encodedPassword = passwordEncoder.encode(password);
		user.setPassword(encodedPassword);
		return userRepo.save(user);
	}

	public boolean checkUserPassword(User user, String password){
		String currentPassword = user.getPassword();
		if(!passwordEncoder.matches(password, currentPassword)){
			return false;
		}

		return true;
	}
}
