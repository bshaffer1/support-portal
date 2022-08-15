package com.palaceflophouse.supportportal.service;

import com.palaceflophouse.supportportal.entities.Account;
import com.palaceflophouse.supportportal.entities.User;
import com.palaceflophouse.supportportal.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

/**
 * Author: Brandon Shaffer
 * Date: 8/1/2022
 */
@Service
public class AppAccountService implements AccountService {

	private final AccountRepository accountRepo;

	public AppAccountService(AccountRepository accountRepo) {
		this.accountRepo = accountRepo;
	}

	@Override
	public Optional<Account> loadAccountById(Long id) {
		return accountRepo.findById(id);
	}

	@Override
	public Set<Account> loadAllAccounts() {

		Set<Account> accounts = new HashSet<>();

		Iterable<Account> allAccounts = accountRepo.findAll();
		Iterator<Account> iterator = allAccounts.iterator();
		while(iterator.hasNext()){
			accounts.add(iterator.next());
		}

		return accounts;
	}

	@Override
	public Account saveAccount(Account account) {
		return accountRepo.save(account);
	}

	@Override
	public Account addUserToAccount(Account account, User user){
		Set<User> users = account.getUsers();
		users.add(user);
		return saveAccount(account);
	}

	@Override
	public Optional<Account> loadAccountByName(String name) {
		return accountRepo.findByName(name);
	}
}
