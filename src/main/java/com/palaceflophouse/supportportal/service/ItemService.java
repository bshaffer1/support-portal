package com.palaceflophouse.supportportal.service;

import com.palaceflophouse.supportportal.entities.SupportItem;

import java.util.Optional;

/**
 * Author: Brandon Shaffer
 * Date: 8/20/2022
 */
public interface ItemService {

	Optional<SupportItem> loadItemById(Long itemId);

	SupportItem save(SupportItem supportItem);
}
