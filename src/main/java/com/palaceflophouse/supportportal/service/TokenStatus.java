package com.palaceflophouse.supportportal.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Author: Brandon Shaffer
 * Date: 7/23/2022
 */
@AllArgsConstructor
@Getter
public enum TokenStatus {

	VALID("Valid"),
	INVALID("Invalid"),
	EXPIRED("EXPIRED");

	private String status;
}
