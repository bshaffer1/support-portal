package com.palaceflophouse.supportportal.entities;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Author: Brandon Shaffer
 * Date: 7/16/2022
 */
@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class User implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private final String username;
	private String password;

	private String firstName;
	private String lastName;

	private String email;

	private final LocalDate dateCreated;
	private Boolean isAdmin = false;

	@ManyToOne(targetEntity = Account.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "account_id")
	private Account account;

	@Builder
	public User(String username, String password, String firstName, String lastName, String email, LocalDate dateCreated){
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.dateCreated = dateCreated;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority[] auths;
		if(isAdmin){
			auths = new SimpleGrantedAuthority[2];
			auths[1] = new SimpleGrantedAuthority("ROLE_ADMIN");
		}
		else{
			auths = new SimpleGrantedAuthority[1];
		}
		auths[0] = new SimpleGrantedAuthority("ROLE_USER");

		List<SimpleGrantedAuthority> grantedAuthorities = Arrays.asList(auths);

		return grantedAuthorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
