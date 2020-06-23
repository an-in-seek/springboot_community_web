package com.community.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.community.web.annotation.SocialUser;
import com.community.web.domain.Board;
import com.community.web.domain.User;
import com.community.web.service.BoardService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/board")
public class BoardController {
	
	@Autowired
    private BoardService boardService;

	@GetMapping("/user")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public String userAccess() {
		return "User Content.";
	}
	
	@GetMapping("/mod")
	@PreAuthorize("hasRole('MODERATOR')")
	public String moderatorAccess() {
		return "Moderator Board.";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public Map<String, Object> adminAccess() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Board> boardList = boardService.findBoardList();
		map.put("content", "Admin Board.");
		map.put("items", boardList);
		return map;
	}
	
	@GetMapping("/admin/list")
    public String list(@PageableDefault Pageable pageable, @SocialUser User user, Model model) {
        model.addAttribute("boardList", boardService.findBoardList(pageable));
        return "/admin";
    }
	
    @GetMapping({"", "/"})
    public String board(@RequestParam(value = "boardNo", defaultValue = "0") Long boardNo, Model model) {
        model.addAttribute("board", boardService.findBoardByBoardNo(boardNo));
        return "/board/form";
    }
}
