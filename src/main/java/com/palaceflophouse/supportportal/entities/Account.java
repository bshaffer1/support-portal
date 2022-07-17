package com.palaceflophouse.supportportal.entities;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * Author: Brandon Shaffer
 * Date: 7/16/2022
 */
@Data
public class Account {

	String accountName;
	LocalDate dateCreated;

	List<String> users;

}
