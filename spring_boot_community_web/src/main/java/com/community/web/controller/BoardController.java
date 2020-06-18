package com.community.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.community.web.annotation.SocialUser;
import com.community.web.domain.User;
import com.community.web.service.BoardService;


@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
    private BoardService boardService;

    @GetMapping({"", "/"})
    public String board(@RequestParam(value = "boardNo", defaultValue = "0") Long boardNo, Model model) {
        model.addAttribute("board", boardService.findBoardByBoardNo(boardNo));
        return "/board/form";
    }

    @GetMapping("/list")
    public String list(@PageableDefault Pageable pageable, @SocialUser User user, Model model) {
        model.addAttribute("boardList", boardService.findBoardList(pageable));
        return "/board/list";
    }
}
