package com.palaceflophouse.supportportal.entities;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * Author: Brandon Shaffer
 * Date: 7/16/2022
 */
@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(unique = true)
	private final String name;
	private final LocalDate dateCreated;

	@OneToMany(targetEntity = User.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "users")
	private Set<User> users;

	@Builder
	public Account(String name, LocalDate dateCreated){
		this.name = name;
		this.dateCreated = dateCreated;
	}
}
