package com.community.web.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Comment implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long commentNo;

	@Column
	private String comment;

	@Column
	private LocalDateTime createdDate;

	@Column
	private LocalDateTime updatedDate;
	
	@ManyToOne
    @JoinColumn(name="user_id")
	private User user;	
	
	@ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_board_boardNo"))
	private Board board;

	public Comment() {
		super();
	}
	
	public Comment(LocalDateTime createdDate, LocalDateTime updatedDate, Board board) {
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.board = board;
	}


	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(Long commentNo) {
		this.commentNo = commentNo;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}