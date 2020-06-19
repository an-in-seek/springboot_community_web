package com.community.web.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.community.web.domain.enums.SexType;
import com.community.web.domain.enums.SocialType;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "USER_NAME"), @UniqueConstraint(columnNames = "EMAIL") })
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String userId;

	@Column
	private String password;

	@Column
	private String username;

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
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	@Builder
	public User(String userId, String password, String username, String userBirthDate, SexType userSex, String email,
			String pincipal, SocialType socialType, LocalDateTime createdDate, LocalDateTime updatedDate) {
		this.userId = userId;
		this.password = password;
		this.username = username;
		this.userBirthDate = userBirthDate;
		this.userSex = userSex;
		this.email = email;
		this.pincipal = pincipal;
		this.socialType = socialType;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}
}
