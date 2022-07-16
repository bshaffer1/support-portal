package com.palaceflophouse.supportportal.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Author: Brandon Shaffer
 * Date: 7/16/2022
 */
@AllArgsConstructor
@Getter
public enum ItemStatus {

	NEW("New"),
	IN_PROGRESS("In Progress"),
	COMPLETE("Complete");

	private String statusKey;
}
