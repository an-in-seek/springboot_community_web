package com.community.web.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.community.web.domain.enums.SexType;
import com.community.web.domain.enums.SocialType;

@Getter
@NoArgsConstructor
@Entity
@Table
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userNo;

	@Column
	private String userId;

	@Column
	private String userPw;

	@Column
	private String userName;

	@Column
	private String userBirthDate;

	@Column
	@Enumerated(EnumType.STRING)
	private SexType userSex;
	
	@Column
	private String email;

	@Column
	private String pincipal;

	@Column
	@Enumerated(EnumType.STRING)
	private SocialType socialType;

	@Column
	private LocalDateTime createdDate;

	@Column
	private LocalDateTime updatedDate;

	@Builder
	public User(String userId, String userPw, String userName, String userBirthDate, SexType userSex, String email,
			String pincipal, SocialType socialType, LocalDateTime createdDate, LocalDateTime updatedDate) {
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.userBirthDate = userBirthDate;
		this.userSex = userSex;
		this.email = email;
		this.pincipal = pincipal;
		this.socialType = socialType;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}
}
