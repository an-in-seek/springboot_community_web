package com.community.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.community.web.domain.Post;
import com.community.web.repository.PostRepository;

/**
 * @author dktmr
 *
 */
@Service
public class PostService {
	
	@Autowired
    private PostRepository postRepository;
	
	/**
     * Post 목록 조회
     * @return
     */
    public List<Post> findPostList(Sort sort) {
        return postRepository.findAll(sort);
    }
    
    /**
     * Post 내용 조회
     * @param postNo
     * @return
     */
    public Post findPostByPostNo(Long postNo) {
        return postRepository.findById(postNo).orElse(new Post());
    }
    
}
