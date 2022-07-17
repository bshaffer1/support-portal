package com.palaceflophouse.supportportal.entities;

import lombok.Data;

import java.time.LocalDate;

/**
 * Author: Brandon Shaffer
 * Date: 7/16/2022
 */
@Data
public class ItemComment {

	private String itemComment;
	private String createdBy;
	private LocalDate dateCreated;

}
