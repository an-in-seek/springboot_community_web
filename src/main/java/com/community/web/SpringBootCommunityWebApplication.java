package com.community.web;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.community.web.domain.Role;
import com.community.web.domain.enums.ERole;
import com.community.web.repository.BoardRepository;
import com.community.web.repository.RoleRepository;
import com.community.web.repository.UserRepository;

@SpringBootApplication
public class SpringBootCommunityWebApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCommunityWebApplication.class, args);
	}
	
	
	@Bean
	public CommandLineRunner runner(UserRepository userRepository, BoardRepository boardRepository, RoleRepository roleRepository) {
		return (args) -> {
			roleRepository.save(Role.builder().name(ERole.ROLE_ADMIN).build());
			roleRepository.save(Role.builder().name(ERole.ROLE_MODERATOR).build());
			roleRepository.save(Role.builder().name(ERole.ROLE_USER).build());
			/*
			User user = userRepository.save(User.builder()
					.username("test")
					.password("123456789")
					.userNickname("seek")
					.userBirthDate("1989-08-28")
					.userSex(SexType.MALE)
					.email("seek@gmail.com")
					.createdDate(LocalDateTime.now())
					.updatedDate(LocalDateTime.now())
					.build());

			IntStream.rangeClosed(1, 200).forEach(index ->
				boardRepository.save(Board.builder()
						.boardTitle("게시글" + index)
						.boardSubTitle("순서" + index)
						.boardContent("내용")
						.boardType(BoardType.free)
						.createdDate(LocalDateTime.now())
						.updatedDate(LocalDateTime.now())
						.user(user).build())
			);
			*/
		};
	}
	

}
