package com.palaceflophouse.supportportal.repository;

import com.palaceflophouse.supportportal.entities.PasswordResetToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: Brandon Shaffer
 * Date: 7/23/2022
 */
@Repository
public interface PasswordResetTokenRepository extends CrudRepository<PasswordResetToken, Long> {

	PasswordResetToken findByToken(String token);
}
