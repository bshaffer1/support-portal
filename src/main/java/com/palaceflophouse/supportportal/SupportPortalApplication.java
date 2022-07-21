package com.palaceflophouse.supportportal;

import com.palaceflophouse.supportportal.entities.User;
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
	public CommandLineRunner addAdminUserOnStartup(UserRepository userRepository, PasswordEncoder encoder){
		String encodedPassword = encoder.encode("password1");
		User admin = User.builder()
				.username("admin")
				.password(encodedPassword)
				.firstName("admin")
				.lastName("user")
				.build();
		admin.setIsAdmin(true);
		User user1 = User.builder()
				.username("bshaffer")
				.password(encodedPassword)
				.firstName("Brandon")
				.lastName("Shaffer")
				.build();
		User user2 = User.builder()
				.username("ashaffer")
				.password(encodedPassword)
				.firstName("Amber")
				.lastName("Shaffer")
				.build();
		User user3 = User.builder()
				.username("cshaffer")
				.password(encodedPassword)
				.firstName("Cormac")
				.lastName("Shaffer")
				.build();

		return args -> {
			userRepository.save(admin);
			userRepository.save(user1);
			userRepository.save(user2);
			userRepository.save(user3);

		};
	}

}
