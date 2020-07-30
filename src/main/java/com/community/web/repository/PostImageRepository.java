package com.community.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.community.web.domain.PostImage;

@Repository
public interface PostImageRepository extends JpaRepository<PostImage, Long> {

	
}
