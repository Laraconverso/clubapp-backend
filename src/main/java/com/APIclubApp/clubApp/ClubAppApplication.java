package com.APIclubApp.clubApp;

import com.APIclubApp.clubApp.model.UserSecurityModels.ERole;
import com.APIclubApp.clubApp.model.UserSecurityModels.RoleEntity;
import com.APIclubApp.clubApp.model.UserSecurityModels.UserEntity;
import com.APIclubApp.clubApp.repository.UserSecurityRepositories.UserSecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Set;

@SpringBootApplication
public class ClubAppApplication {

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST","PUT", "DELETE");
			}
		};
	}

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserSecurityRepository userSecurityRepository;

	@Bean
	CommandLineRunner init(){

		return args -> {
			UserEntity adminUser = UserEntity.builder()
					.email("admin@mail.com")
					.username("admin")
					.password(passwordEncoder.encode("1234"))
					.roles(Set.of(RoleEntity.builder()
							.name(ERole.valueOf(ERole.ADMIN.name()))
							.build()))
					.build();

			userSecurityRepository.save(adminUser);
		};
	}


	public static void main(String[] args) {
		SpringApplication.run(ClubAppApplication.class, args);
	}

}
