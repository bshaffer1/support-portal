package com.palaceflophouse.supportportal.service;

import com.palaceflophouse.supportportal.entities.Account;
import com.palaceflophouse.supportportal.entities.User;

import java.util.Optional;
import java.util.Set;

/**
 * Author: Brandon Shaffer
 * Date: 8/1/2022
 */
public interface AccountService {

	Optional<Account> loadAccountById(Long id);

	Set<Account> loadAllAccounts();

	Account saveAccount(Account account);

	Account addUserToAccount(Account account, User user);

}
