package com.palaceflophouse.supportportal.service;

import com.palaceflophouse.supportportal.entities.SupportItem;
import com.palaceflophouse.supportportal.repository.ItemRepository;

import java.util.Optional;

/**
 * Author: Brandon Shaffer
 * Date: 8/20/2022
 */
public class AppItemService implements ItemService{

	private final ItemRepository itemRepository;

	public AppItemService(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}

	@Override
	public Optional<SupportItem> loadItemById(Long itemId) {
		return itemRepository.findById(itemId);
	}

	@Override
	public SupportItem save(SupportItem supportItem) {
		return itemRepository.save(supportItem);
	}
}
