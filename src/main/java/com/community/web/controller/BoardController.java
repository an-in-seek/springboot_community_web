package com.community.web.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.community.web.domain.Board;
import com.community.web.domain.enums.BoardType;
import com.community.web.payload.request.BoardRequest;
import com.community.web.payload.response.MessageResponse;
import com.community.web.repository.BoardRepository;
import com.community.web.service.BoardService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/board")
public class BoardController {
	
	@Autowired
    private BoardService boardService;
	
	@Autowired
	private BoardRepository boardRepository;

	/**
	 * 사용자 게시판
	 * @return
	 */
	@GetMapping("/user")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public String userAccess() {
		return "User Content.";
	}
	
	/**
	 * 운영자 게시판
	 * @return
	 */
	@GetMapping("/mod")
	@PreAuthorize("hasRole('MODERATOR')")
	public String moderatorAccess() {
		return "Moderator Board.";
	}

	/**
	 * 관리자 게시판
	 * @return
	 */
	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public Map<String, Object> adminBoardList() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Board> boardList = boardService.findBoardList();
		map.put("content", "Admin Board.");
		map.put("items", boardList);
		return map;
	}
	
	/**
	 * 관리자 게시판 세부
	 * @param boardNo
	 * @return
	 */
	@GetMapping("/admin/detail")
	@PreAuthorize("hasRole('ADMIN')")
    public Board adminBoardDetail(@RequestParam(value = "boardNo", defaultValue = "1") Long boardNo) {
		Board board = boardService.findBoardByBoardNo(boardNo);
        return board;
    }
	
	/**
	 * 관리자 게시판 수정
	 * @param boardRequest
	 * @return
	 */
	@PostMapping("/admin/update")
	public ResponseEntity<?> updateBoard(@Valid @RequestBody BoardRequest boardRequest) {
		Long boardNo = Long.parseLong(boardRequest.getBoardNo());
		
		BoardType boardTye = null;
		if(boardRequest.getBoardType().equals(BoardType.FREE.name())) {
			boardTye = BoardType.FREE;
		} else if(boardRequest.getBoardType().equals(BoardType.NOTICE.name())) {
			boardTye = BoardType.NOTICE;
		}
		Board board = boardRepository.findByBoardNo(boardNo);
		board.setBoardTitle(boardRequest.getBoardTitle());
		board.setBoardSubTitle(boardRequest.getBoardSubTitle());
		board.setBoardContent(boardRequest.getBoardContent());	
		board.setBoardType(boardTye);
		board.setUpdatedDate(LocalDateTime.now());
		boardRepository.save(board);
		return ResponseEntity.ok(new MessageResponse("Board updated successfully!"));
	}
	
	/**
	 * 관리자 게시판 삭제
	 * @param boardRequest
	 * @return
	 */
	@PostMapping("/admin/delete")
	public ResponseEntity<?> deleteBoard(@Valid @RequestBody BoardRequest boardRequest) {
		Long boardNo = Long.parseLong(boardRequest.getBoardNo());
		Board board = boardRepository.findByBoardNo(boardNo);
		boardRepository.delete(board);
		return ResponseEntity.ok(new MessageResponse("Board deleted successfully!"));
	}
}
