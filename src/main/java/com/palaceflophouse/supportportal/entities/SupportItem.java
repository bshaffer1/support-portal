package com.palaceflophouse.supportportal.entities;

import java.time.LocalDate;
import java.util.List;

/**
 * Author: Brandon Shaffer
 * Date: 7/16/2022
 */
public class SupportItem {

	private Long id;
	private LocalDate dateCreated;
	private LocalDate dateResolved;
	private ItemStatus status;
	private String createdBy;

	private List<ItemComment> comments;

}
