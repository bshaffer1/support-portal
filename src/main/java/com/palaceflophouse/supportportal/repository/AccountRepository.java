package com.palaceflophouse.supportportal.repository;

import com.palaceflophouse.supportportal.entities.Account;

import java.util.Optional;

/**
 * Author: Brandon Shaffer
 * Date: 7/16/2022
 */
public class AccountRepository implements SupportRepository<Account, String>{
	@Override
	public Iterable<Account> findAll() {
		return null;
	}

	@Override
	public Optional<Account> findById(String s) {
		return Optional.empty();
	}

	@Override
	public Account save(Account item) {
		return null;
	}
}
