package com.palaceflophouse.supportportal.repository;

import com.palaceflophouse.supportportal.entities.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: Brandon Shaffer
 * Date: 7/31/2022
 */
@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
}
