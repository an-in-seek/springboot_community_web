package com.community.web;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.community.web.domain.Board;
import com.community.web.domain.Post;
import com.community.web.domain.PostImage;
import com.community.web.domain.Role;
import com.community.web.domain.User;
import com.community.web.domain.enums.BoardType;
import com.community.web.domain.enums.ERole;
import com.community.web.domain.enums.SexType;
import com.community.web.repository.BoardRepository;
import com.community.web.repository.PostImageRepository;
import com.community.web.repository.PostRepository;
import com.community.web.repository.RoleRepository;
import com.community.web.repository.UserRepository;

@SpringBootApplication
public class SpringBootCommunityWebApplication implements WebMvcConfigurer {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootCommunityWebApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(UserRepository userRepository, RoleRepository roleRepository,
			BoardRepository boardRepository, PostRepository postRepository, PostImageRepository postImageRepository) {
		return (args) -> {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String admin = "admin";
			String moderator = "moderator";
			String user = "user";
			String password = encoder.encode("123456789");
			String birthDate = "1989-08-28";
			String profileImage = "profileImage";
			String postImageUrl = "https://s3-us-west-2.amazonaws.com/s.cdpn.io/1211695/tropical_beach.jpg";
			String postImageFileName = "test";
			LocalDateTime nowDateTime = LocalDateTime.now();

			// Role 기준정보 저장
			Role adminRole = roleRepository.save(new Role(ERole.ROLE_ADMIN));
			Role moderatorRole = roleRepository.save(new Role(ERole.ROLE_MODERATOR));
			Role userRole = roleRepository.save(new Role(ERole.ROLE_USER));

			// admin 데이터 생성
			Set<Role> roles = new HashSet<>();
			roles.add(adminRole);
			User adminInfo = new User(admin, password, admin, birthDate, SexType.MALE, admin + "@gmail.com",
					profileImage, nowDateTime, nowDateTime, roles);
			userRepository.save(adminInfo);

			// moderator 데이터 생성
			roles = new HashSet<>();
			roles.add(moderatorRole);
			User moderatorInfo = new User(moderator, password, moderator, birthDate, SexType.MALE,
					moderator + "@gmail.com", profileImage, nowDateTime, nowDateTime, roles);
			userRepository.save(moderatorInfo);

			// user 데이터 생성
			roles = new HashSet<>();
			roles.add(userRole);
			User userInfo = new User(user, password, user, birthDate, SexType.MALE, user + "@gmail.com", profileImage,
					nowDateTime, nowDateTime, roles);
			userRepository.save(userInfo);

			// 게시글 200개 데이터 생성
			IntStream.rangeClosed(1, 200).forEach(index -> boardRepository.save(new Board("제목" + index, "부제목" + index,
					"내용" + index, BoardType.FREE, nowDateTime, nowDateTime, userInfo)));

			for (int index = 1; index < 6; index++) {
				// Vuestagram 데이터 생성
				Post post = new Post("제목" + index, "Views from the six..." + index, index, 0, nowDateTime, userInfo);
				postRepository.save(post);

				// Vuestagram 이미지 생성
				PostImage postImage = new PostImage(post, postImageUrl, postImageFileName, 1L, nowDateTime);
				postImageRepository.save(postImage);
			}
		};
	}
}
