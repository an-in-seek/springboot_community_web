package com.community.web;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.community.web.domain.Board;
import com.community.web.domain.Role;
import com.community.web.domain.User;
import com.community.web.domain.enums.BoardType;
import com.community.web.domain.enums.ERole;
import com.community.web.domain.enums.SexType;
import com.community.web.repository.BoardRepository;
import com.community.web.repository.RoleRepository;
import com.community.web.repository.UserRepository;

@SpringBootApplication
public class SpringBootCommunityWebApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCommunityWebApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(PasswordEncoder encoder, UserRepository userRepository,
			BoardRepository boardRepository, RoleRepository roleRepository) {
		return (args) -> {
			String admin = "admin";
			String moderator = "moderator";
			String user = "user";
			String password = encoder.encode("123456789");
			String birthDate = "1989-08-28";
			LocalDateTime nowDateTime = LocalDateTime.now();

			// Role 기준정보 저장
			Role adminRole = roleRepository.save(new Role(ERole.ROLE_ADMIN));
			Role moderatorRole = roleRepository.save(new Role(ERole.ROLE_MODERATOR));
			Role userRole = roleRepository.save(new Role(ERole.ROLE_USER));

			// admin 정보 저장
			Set<Role> roles = new HashSet<>();
			roles.add(adminRole);
			User adminInfo = new User(admin, password, admin, birthDate, SexType.MALE, admin + "@gmail.com", nowDateTime, nowDateTime, roles);
			userRepository.save(adminInfo);

			// moderator 정보 저장
			roles = new HashSet<>();
			roles.add(moderatorRole);
			User moderatorInfo = new User(moderator, password, moderator, birthDate, SexType.MALE, moderator + "@gmail.com", nowDateTime, nowDateTime, roles);
			userRepository.save(moderatorInfo);

			// user 정보 저장
			roles = new HashSet<>();
			roles.add(userRole);
			User userInfo = new User(user, password, user, birthDate, SexType.MALE, user + "@gmail.com", nowDateTime, nowDateTime, roles);
			userRepository.save(userInfo);

			IntStream.rangeClosed(1, 200).forEach(index -> boardRepository.save(new Board("제목" + index, "부제목" + index,
					"내용" + index, BoardType.FREE, nowDateTime, nowDateTime, userInfo)));
		};
	}
}
