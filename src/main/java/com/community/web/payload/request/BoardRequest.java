package com.community.web.payload.request;

import javax.validation.constraints.NotBlank;

public class BoardRequest {
	
	@NotBlank
    private String boardNo;
	
    private String boardTitle;
 
    private String boardSubTitle;
    
    private String boardContent;
    
    private String boardType;
    
	public String getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(String boardNo) {
		this.boardNo = boardNo;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardSubTitle() {
		return boardSubTitle;
	}

	public void setBoardSubTitle(String boardSubTitle) {
		this.boardSubTitle = boardSubTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getBoardType() {
		return boardType;
	}

	public void setBoardType(String boardType) {
		this.boardType = boardType;
	}
}
