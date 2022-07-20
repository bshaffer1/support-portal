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
		return args -> userRepository.save(admin);
	}

}
