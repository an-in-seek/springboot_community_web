package com.community.web.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.community.web.domain.enums.SexType;
import com.community.web.domain.enums.SocialType;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "username"), @UniqueConstraint(columnNames = "email") })
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String username;

	@Column
	private String password;

	@Column
	private String userNickname;

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
	
	public User() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	public String getUserBirthDate() {
		return userBirthDate;
	}

	public void setUserBirthDate(String userBirthDate) {
		this.userBirthDate = userBirthDate;
	}

	public SexType getUserSex() {
		return userSex;
	}

	public void setUserSex(SexType userSex) {
		this.userSex = userSex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPincipal() {
		return pincipal;
	}

	public void setPincipal(String pincipal) {
		this.pincipal = pincipal;
	}

	public SocialType getSocialType() {
		return socialType;
	}

	public void setSocialType(SocialType socialType) {
		this.socialType = socialType;
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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public User(String username, String password, String userNickname, String userBirthDate, SexType userSex, String email,
			String pincipal, SocialType socialType, LocalDateTime createdDate, LocalDateTime updatedDate) {
		this.username = username;
		this.password = password;
		this.userNickname = userNickname;
		this.userBirthDate = userBirthDate;
		this.userSex = userSex;
		this.email = email;
		this.pincipal = pincipal;
		this.socialType = socialType;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}
}
