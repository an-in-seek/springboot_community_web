package com.community.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.community.web.domain.Board;
import com.community.web.domain.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	
	List<Comment> findAll();
	
	List<Comment> findByBoard(Board board);
	
	Comment findByCommentNo(Long commentNo);
}
