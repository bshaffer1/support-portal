package com.palaceflophouse.supportportal.entities;

import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

	@ManyToOne
	@JoinColumn(name = "item_id")
	private SupportItem supportItem;

}
