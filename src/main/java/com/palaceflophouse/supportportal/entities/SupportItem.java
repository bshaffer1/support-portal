package com.palaceflophouse.supportportal.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Author: Brandon Shaffer
 * Date: 7/16/2022
 */
@Getter
@Setter
@Entity
public class SupportItem {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Long dateCreated;
	private Long dateResolved;

	private ItemStatus status;
	private String createdBy;

	@OneToMany(mappedBy = "supportitem")
	private List<ItemComment> comments;

	public void addComment(ItemComment itemComment){
		comments.add(itemComment);
	}
}
