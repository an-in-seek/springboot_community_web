package com.community.web.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.transaction.SystemException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.community.web.domain.Post;
import com.community.web.domain.PostImage;
import com.community.web.domain.User;
import com.community.web.payload.response.MessageResponse;
import com.community.web.repository.PostRepository;
import com.community.web.repository.UserRepository;
import com.community.web.service.PostService;
import com.community.web.util.CommonUtil;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/post")
public class PostController {

	@Autowired
	private PostService postService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	/**
	 * Post List
	 * 
	 * @return
	 */
	@GetMapping("/list")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public Map<String, Object> userPostList() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Post> postList = postService.findPostList(Sort.by("id").descending());
		map.put("posts", postList);
		return map;
	}

	/**
	 * Post 세부
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/detail")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public Map<String, Object> postDetail(@RequestParam(defaultValue = "1") Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		Post post = postService.findPostById(id);
		map.put("post", post);
		return map;
	}

	/**
	 * Post 등록
	 * 
	 * @param postRequest
	 * @return
	 */
	@PostMapping("/create")
	public ResponseEntity<?> createPost(@RequestParam String postTitle
			, @RequestParam String postContent
			, @RequestParam List<MultipartFile> images
			, @Value("${upload.path.image.default.post}") String uploadPostImagePath
			, @Value("${upload.path.image.windows.post}") String uploadPostImagePathForWindows
			, @Value("${upload.path.image.linux.post}") String uploadPostImagePathForLinux) throws Throwable {
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByUsername(username).get();

		// Post 저장
		Post post = new Post();
		post.setPostTitle(postTitle);
		post.setPostContent(postContent);
		post.setLikes(0);
		post.setDislikes(0);
		post.setCreatedDate(LocalDateTime.now());
		post.setUser(user);
		for (MultipartFile multipartFile : images) {
			String realPostImageUrl = "";
			Resource resource = null;
			
			// 서버 로컬 경로에 이미지 저장
			String originFileName = multipartFile.getOriginalFilename();
			long postImageFileSize = multipartFile.getSize();
			String postImageUrl = uploadPostImagePath + originFileName;
			if(CommonUtil.isWindows()) {
				realPostImageUrl = uploadPostImagePathForWindows + originFileName;
				resource = new PathResource(postImageUrl);
			} else if (CommonUtil.isUnix()) {
				realPostImageUrl = uploadPostImagePathForLinux + originFileName;
				resource = new PathResource(realPostImageUrl);
			}
			multipartFile.transferTo(new File(realPostImageUrl));
			
			// 이미지 해상도 조정해서 로컬에 다시 저장
			String[] splitName = originFileName.split("\\.");
			int splitLength = splitName.length;
			if(splitLength <= 0) {
				throw new SystemException();
			}
			String formatName = splitName[splitLength - 1];
			BufferedImage resizedImage = CommonUtil.resizeImage(resource.getInputStream(), 1024, 768);
			ImageIO.write(resizedImage, formatName, new File(realPostImageUrl));
			
			// 이미지 저장
			PostImage postImage = new PostImage();
			postImage.setPost(post);
			postImage.setPostImageUrl(postImageUrl);
			postImage.setPostImageFileName(originFileName);
			postImage.setPostImageFileSize(postImageFileSize);
			postImage.setCreatedDate(LocalDateTime.now());
			post.addImage(postImage);
		}
		postRepository.save(post);

		/*
		// Post Image 저장
		PostImage postImage = null;
		List<PostImage> postImageList = new ArrayList<PostImage>();
		for (MultipartFile multipartFile : images) {
			String originFileName = multipartFile.getOriginalFilename();
			long postImageFileSize = multipartFile.getSize();
			String postImageUrl = uploadPath + originFileName;
			multipartFile.transferTo(new File(postImageUrl));
			postImage = new PostImage();
			postImage.setPost(post);
			postImage.setPostImageUrl(postImageUrl);
			postImage.setPostImageFileName(originFileName);
			postImage.setPostImageFileSize(postImageFileSize);
			postImage.setCreatedDate(LocalDateTime.now());
			postImage.setUser(user);
			postImageList.add(postImage);
		}
		postImageRepository.saveAll(postImageList);
		*/

		return ResponseEntity.ok(new MessageResponse("Post created successfully!"));
	}
}
