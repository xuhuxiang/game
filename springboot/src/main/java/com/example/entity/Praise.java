package com.example.entity;

import javax.persistence.*;

/**
 * 点赞
 */
@Table(name = "praise")
public class Praise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	/** 点赞人id */
	@Column(name = "userId")
	private Integer userId;
	/** 点赞人角色 */
	@Column(name = "role")
	private Integer role;
	/** 关联id */
	@Column(name = "relId")
	private Integer relId;
	@Transient
	private String userName;
	@Transient
	private String avatar;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Integer getRelId() {
		return relId;
	}

	public void setRelId(Integer relId) {
		this.relId = relId;
	}
}
