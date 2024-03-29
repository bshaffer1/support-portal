package com.palaceflophouse.supportportal;

import com.palaceflophouse.supportportal.entities.Account;
import com.palaceflophouse.supportportal.entities.User;
import com.palaceflophouse.supportportal.repository.AccountRepository;
import com.palaceflophouse.supportportal.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SupportPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(SupportPortalApplication.class, args);
	}

	@Bean
	public CommandLineRunner addAdminUserOnStartup(UserRepository userRepository,
												   AccountRepository accountRepository,
												   PasswordEncoder encoder){
		String encodedPassword = encoder.encode("kims2008");
		User admin = User.builder()
				.username("admin")
				.password(encodedPassword)
				.firstName("admin")
				.lastName("user")
				.email("admin@admin.com")
				.build();
		admin.setIsAdmin(true);
		User user1 = User.builder()
				.username("bshaffer")
				.password(encodedPassword)
				.firstName("Brandon")
				.lastName("Shaffer")
				.email("brandonrussellshaffer@gmail.com")
				.build();
		User user2 = User.builder()
				.username("ashaffer")
				.password(encodedPassword)
				.firstName("Amber")
				.lastName("Shaffer")
				.email("amberlaurenshaffer@gmail.com")
				.build();
		User user3 = User.builder()
				.username("cshaffer")
				.password(encodedPassword)
				.firstName("Cormac")
				.lastName("Shaffer")
				.email("cormacrussellshaffer@gmail.com")
				.build();

		Account account1 = Account.builder()
				.name("Bologna Technologies")
				.build();

		Account account2 = Account.builder()
				.name("Mud Pharma")
				.build();

		Account account3 = Account.builder()
				.name("69DT")
				.build();

		return args -> {
			userRepository.save(admin);
			userRepository.save(user1);
			userRepository.save(user2);
			userRepository.save(user3);
			accountRepository.save(account1);
			accountRepository.save(account2);
			accountRepository.save(account3);
		};
	}

}
