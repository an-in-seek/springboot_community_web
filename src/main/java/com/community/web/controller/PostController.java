package com.community.web.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.community.web.domain.Post;
import com.community.web.domain.User;
import com.community.web.payload.request.PostRequest;
import com.community.web.payload.response.MessageResponse;
import com.community.web.repository.PostRepository;
import com.community.web.repository.UserRepository;
import com.community.web.service.PostService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/post")
public class PostController {
	
	@Autowired
    private PostService postService;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;

	/**
	 * Post List
	 * @return
	 */
	@GetMapping("/list")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public Map<String, Object> userPostList() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Post> postList = postService.findPostList(Sort.by("postNo").descending());
		map.put("content", "Post List");
		map.put("posts", postList);
		return map;
	}
	
	/**
	 * Post 세부
	 * @param postNo
	 * @return
	 */
	@GetMapping("/detail")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Map<String, Object> postDetail(@RequestParam(value = "postNo", defaultValue = "1") Long postNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		Post post = postService.findPostByPostNo(postNo);
		map.put("post", post);
        return map;
    }
	
	/**
	 * Post 등록
	 * @param postRequest
	 * @return
	 */
	@PostMapping("/create")
	public ResponseEntity<?> createPost(@RequestBody PostRequest postRequest) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByUsername(username).get();
		if(user == null) {
			return ResponseEntity.ok(new MessageResponse("Post created fail!"));
		}
		
		String postImage = postRequest.getPostImage();
		if(postImage == null) {
			postImage = "https://s3-us-west-2.amazonaws.com/s.cdpn.io/1211695/downtown.jpg";
		}
		
		Post post = new Post();
		post.setUser(user);
		post.setPostTitle(postRequest.getPostTitle());
		post.setPostContent(postRequest.getPostContent());	
		post.setPostImage(postImage);
		post.setLikes(0);
		post.setDislikes(0);
		post.setCreatedDate(LocalDateTime.now());
		postRepository.save(post);
		
		return ResponseEntity.ok(new MessageResponse("Post created successfully!"));
	}
}
