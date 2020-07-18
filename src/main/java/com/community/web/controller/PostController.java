package com.community.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.community.web.domain.Post;
import com.community.web.service.PostService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/post")
public class PostController {
	
	@Autowired
    private PostService postService;

	/**
	 * Post List
	 * @return
	 */
	@GetMapping("/list")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public Map<String, Object> userPostList() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Post> postList = postService.findPostList();
		map.put("content", "Post List");
		map.put("posts", postList);
		return map;
	}
}
