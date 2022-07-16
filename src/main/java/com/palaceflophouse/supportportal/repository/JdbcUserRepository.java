package com.palaceflophouse.supportportal.repository;

import com.palaceflophouse.supportportal.entities.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Author: Brandon Shaffer
 * Date: 7/16/2022
 */
@Repository
public class JdbcUserRepository implements SupportRepository<User, String> {

	@Override
	public Iterable<User> findAll() {
		return null;
	}

	@Override
	public Optional<User> findById(String s) {
		return Optional.empty();
	}

	@Override
	public User save(User item) {
		return null;
	}
}
