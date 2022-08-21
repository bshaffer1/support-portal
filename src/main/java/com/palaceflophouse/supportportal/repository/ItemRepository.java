package com.palaceflophouse.supportportal.repository;

import com.palaceflophouse.supportportal.entities.SupportItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: Brandon Shaffer
 * Date: 8/20/2022
 */
@Repository
public interface ItemRepository extends CrudRepository<SupportItem, Long> {
}
