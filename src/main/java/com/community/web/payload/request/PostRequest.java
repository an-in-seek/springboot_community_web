package com.community.web.payload.request;

import javax.validation.constraints.NotBlank;

public class PostRequest {
	
	@NotBlank
    private String postNo;
	
    private String postTitle;
 
    private String postContent;
    
    private String postImage;
    
	public String getPostNo() {
		return postNo;
	}

	public void setPostNo(String postNo) {
		this.postNo = postNo;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public String getPostImage() {
		return postImage;
	}

	public void setPostImage(String postImage) {
		this.postImage = postImage;
	}
}
