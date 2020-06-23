package com.community.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.community.web.domain.Board;
import com.community.web.repository.BoardRepository;

@Service
public class BoardService {

	@Autowired
    private BoardRepository boardRepository;

	/**
     * 게시판 글 목록 조회
     * @param pageable
     * @return
     */
    public List<Board> findBoardList( ) {
        return boardRepository.findAll();
    }
    
    /**
     * 게시판 글 목록 조회(페이징 처리)
     * @param pageable
     * @return
     */
    public Page<Board> findBoardList(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1, pageable.getPageSize());
        return boardRepository.findAll(pageable);
    }

    /**
     * 게시판 글 내용 조회
     * @param boardNo
     * @return
     */
    public Board findBoardByBoardNo(Long boardNo) {
        return boardRepository.findById(boardNo).orElse(new Board());
    }
}

