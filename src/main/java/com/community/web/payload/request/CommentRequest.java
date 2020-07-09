package com.community.web.payload.request;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

public class CommentRequest {
	
	@NotBlank
    private String commentNo;
	
	private String boardNo;
	
    private String contents;
    
    private String username;
 
	private LocalDateTime createdDate;
	
	private LocalDateTime updatedDate;

	
	


	public String getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(String commentNo) {
		this.commentNo = commentNo;
	}

	public String getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(String boardNo) {
		this.boardNo = boardNo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
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
