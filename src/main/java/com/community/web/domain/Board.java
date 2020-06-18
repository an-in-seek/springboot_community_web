package com.community.web.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.community.web.domain.enums.BoardType;

@Getter
@NoArgsConstructor
@Entity
@Table
public class Board implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long boardNo;

	@Column
	private String boardTitle;

	@Column
	private String boardSubTitle;

	@Column
	private String boardContent;

	@Column
	@Enumerated(EnumType.STRING)
	private BoardType boardType;

	@Column
	private LocalDateTime createdDate;

	@Column
	private LocalDateTime updatedDate;

	@OneToOne(fetch = FetchType.LAZY)
	private User user;

	@Builder
	public Board(String boardTitle, String boardSubTitle, String boardContent, BoardType boardType,
			LocalDateTime createdDate, LocalDateTime updatedDate, User user) {
		this.boardTitle = boardTitle;
		this.boardSubTitle = boardSubTitle;
		this.boardContent = boardContent;
		this.boardType = boardType;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.user = user;
	}
}