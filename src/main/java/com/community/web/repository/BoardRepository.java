package com.community.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.community.web.domain.Board;
import com.community.web.domain.User;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
	
	List<Board> findAll();
	
	List<Board> findByUser(User user);
}
