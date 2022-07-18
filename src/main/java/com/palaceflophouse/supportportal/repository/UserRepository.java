package com.palaceflophouse.supportportal.repository;

import com.palaceflophouse.supportportal.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Author: Brandon Shaffer
 * Date: 7/16/2022
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	User findByUsername(String username);

}
