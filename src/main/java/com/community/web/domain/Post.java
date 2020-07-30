package com.community.web.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Post implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String postTitle;
	
	@Column
	private String postContent;
	
	@OneToMany(mappedBy = "post", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<PostImage> images;
	
	@Column
	private int likes;
	
	@Column
	private int dislikes;
	
	@Column
	private LocalDateTime createdDate;

	@Column
	private LocalDateTime updatedDate;
	
	@ManyToOne
    @JoinColumn(name="userId")
	private User user;

	public Post() {
		super();
	}

	public Post(String postTitle, String postContent, int like, int dislike,
			LocalDateTime createdDate, User user) {
		super();
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.likes = like;
		this.dislikes = dislike;
		this.createdDate = createdDate;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
	
	public List<PostImage> getImages() {
		return images;
	}

	public void setImages(List<PostImage> images) {
		this.images = images;
	}
	
	public void addImage(PostImage image) {
		if(images == null) {
			images = new ArrayList<PostImage>();
		}
		images.add(image);
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getDislikes() {
		return dislikes;
	}

	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
