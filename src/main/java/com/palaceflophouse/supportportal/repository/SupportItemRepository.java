package com.palaceflophouse.supportportal.repository;

import com.palaceflophouse.supportportal.entities.SupportItem;

import java.util.Optional;

/**
 * Author: Brandon Shaffer
 * Date: 7/16/2022
 */
public class SupportItemRepository implements SupportRepository<SupportItem, Long>{
	@Override
	public Iterable<SupportItem> findAll() {
		return null;
	}

	@Override
	public Optional<SupportItem> findById(Long aLong) {
		return Optional.empty();
	}

	@Override
	public SupportItem save(SupportItem item) {
		return null;
	}
}
