package com.forum_post_like.model;

import java.io.Serializable;

public class ForumPostLikeVO implements Serializable {
	private Integer forum_post_like_id;
	private Integer forum_post_id;
	private Integer member_id;
	
	public ForumPostLikeVO() {
		super();
	}

	public ForumPostLikeVO(Integer forum_post_like_id, Integer forum_post_id, Integer member_id) {
		super();
		this.forum_post_like_id = forum_post_like_id;
		this.forum_post_id = forum_post_id;
		this.member_id = member_id;
	}

	public Integer getForum_post_like_id() {
		return forum_post_like_id;
	}

	public void setForum_post_like_id(Integer forum_post_like_id) {
		this.forum_post_like_id = forum_post_like_id;
	}

	public Integer getForum_post_id() {
		return forum_post_id;
	}

	public void setForum_post_id(Integer forum_post_id) {
		this.forum_post_id = forum_post_id;
	}

	public Integer getMember_id() {
		return member_id;
	}

	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
	}
	
}

