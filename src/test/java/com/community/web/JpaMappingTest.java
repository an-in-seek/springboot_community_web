package com.community.web;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import java.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.community.web.domain.Board;
import com.community.web.domain.User;
import com.community.web.domain.enums.BoardType;
import com.community.web.domain.enums.SexType;
import com.community.web.repository.BoardRepository;
import com.community.web.repository.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class JpaMappingTest {
    
	// 사용자 정보
	private final String userId = "test";
	private final String userPw = "123456789";
	private final String userName = "seek";
	private final String userBirthDate = "1989-08-28";
	private final SexType userSex = SexType.MALE;
	private final String email = "test@gmail.com";
	
	// 게시글 정보
	private final String boardTitle = "제목";
	private final String boardSubTitle = "부제목";
	private final String boardContent = "내용";
	private final BoardType boardType = BoardType.free;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BoardRepository boardRepository;

    @Before
    public void init() {
        User user = userRepository.save(User.builder()
        		.userId(userId)
        		.password(userPw)
        		.username(userName)
        		.userBirthDate(userBirthDate)
        		.userSex(userSex)
                .email(email)
                .createdDate(LocalDateTime.now())
                .build());

        boardRepository.save(Board.builder()
                .boardTitle(boardTitle)
                .boardSubTitle(boardSubTitle)
                .boardContent(boardContent)
                .boardType(boardType)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .user(user).build());
    }

    @Test
    public void verifyData() {
        User user = userRepository.findByEmail(email);
        assertThat(user.getUserId(), is(userId));
        assertThat(user.getPassword(), is(userPw));
        assertThat(user.getUsername(), is(userName));
        assertThat(user.getUserBirthDate(), is(userBirthDate));
        assertThat(user.getUserSex(), is(userSex));
        assertThat(user.getEmail(), is(email));

        Board board = boardRepository.findByUser(user);
        assertThat(board.getBoardTitle(), is(boardTitle));
        assertThat(board.getBoardSubTitle(), is(boardSubTitle));
        assertThat(board.getBoardContent(), is(boardContent));
        assertThat(board.getBoardType(), is(boardType));
    }
}