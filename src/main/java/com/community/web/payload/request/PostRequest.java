package com.community.web.payload.request;

import javax.validation.constraints.NotBlank;

public class PostRequest{

	@NotBlank
    private String id;
	
    private String postTitle;
 
    private String postContent;
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
}
