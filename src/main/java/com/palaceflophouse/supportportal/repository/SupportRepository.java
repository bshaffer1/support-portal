package com.palaceflophouse.supportportal.repository;

import java.util.Optional;

/**
 * Author: Brandon Shaffer
 * Date: 7/16/2022
 */
public interface SupportRepository<T, Id> {

	Iterable<T> findAll();

	Optional<T> findById(Id id);

	T save(T item);

}
