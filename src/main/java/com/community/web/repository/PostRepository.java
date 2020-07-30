package com.community.web.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.community.web.domain.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

	List<Post> findAll();
	
	Optional<Post> findById(Long id);
	
}
