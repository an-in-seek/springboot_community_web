package com.community.web.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Table
@Entity
public class PostImage implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name="postId")
	private Post post;
	
	@Column
	private String postImageUrl;
	
	@Column
	private String postImageFileName;
	
	@Column
	private Long postImageFileSize;
	
	@Column
	private LocalDateTime createdDate;

	@Column
	private LocalDateTime updatedDate;
	
	public PostImage() {
		super();
	}

	public PostImage(Post post, String postImageUrl, String postImageFileName, Long postImageFileSize, LocalDateTime createdDate) {
		super();
		this.post = post;
		this.postImageUrl = postImageUrl;
		this.postImageFileName = postImageFileName;
		this.postImageFileSize = postImageFileSize;
		this.createdDate = createdDate;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPostImageUrl() {
		return postImageUrl;
	}

	public void setPostImageUrl(String postImageUrl) {
		this.postImageUrl = postImageUrl;
	}

	public String getPostImageFileName() {
		return postImageFileName;
	}

	public void setPostImageFileName(String postImageFileName) {
		this.postImageFileName = postImageFileName;
	}

	public Long getPostImageFileSize() {
		return postImageFileSize;
	}

	public void setPostImageFileSize(Long postImageFileSize) {
		this.postImageFileSize = postImageFileSize;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}
}
