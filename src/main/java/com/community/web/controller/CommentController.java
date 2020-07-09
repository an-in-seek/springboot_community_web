package com.community.web.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.community.web.domain.Board;
import com.community.web.domain.Comment;
import com.community.web.domain.User;
import com.community.web.payload.request.CommentRequest;
import com.community.web.payload.response.MessageResponse;
import com.community.web.repository.BoardRepository;
import com.community.web.repository.CommentRepository;
import com.community.web.repository.UserRepository;
import com.community.web.service.BoardService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/comment")
public class CommentController {

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	UserRepository userRepository;	
	
	@Autowired
    private BoardService boardService;

	@GetMapping("/commentList")
	public Map<String, Object> boardCommentList(@RequestParam(value = "boardNo", defaultValue = "1") Long boardNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		Board board = boardRepository.findByBoardNo(boardNo);
		List<Comment> commentList = boardService.findCommentList(board);
		map.put("content", "Board Comments.");
		map.put("items", commentList);
		return map;
	}	
	
	
	/**
	 * 게시판 등록
	 * @param boardRequest
	 * @return
	 */
	@PostMapping("/create")
	public ResponseEntity<?> createComment(@RequestBody CommentRequest commentRequest) {
		String username = commentRequest.getUsername();
		User user = userRepository.findByUsername(username).get();
		if(user == null) {
			return ResponseEntity.ok(new MessageResponse("Board created fail!"));
		}
		
		Long boardNo = Long.parseLong(commentRequest.getBoardNo());
		Board board = boardRepository.findByBoardNo(boardNo);		

		Comment comment = new Comment();
		comment.setBoard(board);
		comment.setContents(commentRequest.getContents());
		comment.setCreatedDate(LocalDateTime.now());
		comment.setUser(user);
		
		commentRepository.save(comment);
		
		return ResponseEntity.ok(new MessageResponse("Comment created successfully!"));
	}
	
	/**
	 * 게시판 수정
	 * @param boardRequest
	 * @return
	 */
	@PostMapping("/update")
	public ResponseEntity<?> updateComment(@Valid @RequestBody CommentRequest commentRequest) {
		Long commentNo = Long.parseLong(commentRequest.getCommentNo());
		Comment comment =  commentRepository.findByCommentNo(commentNo);
		comment.setContents(commentRequest.getContents());
		comment.setUpdatedDate(LocalDateTime.now());
		
		commentRepository.save(comment);		
		
		return ResponseEntity.ok(new MessageResponse("Comment updated successfully!"));
	}
	
	/**
	 * 게시판 삭제
	 * @param boardRequest
	 * @return
	 */
	@PostMapping("/delete")
	public ResponseEntity<?> deleteComment(@Valid @RequestBody CommentRequest commentRequest) {
		Long commentNo = Long.parseLong(commentRequest.getCommentNo());
		commentRepository.delete(commentRepository.findByCommentNo(commentNo));
		return ResponseEntity.ok(new MessageResponse("Comment deleted successfully!"));
	}
}
