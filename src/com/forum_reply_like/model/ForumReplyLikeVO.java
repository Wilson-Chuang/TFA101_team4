package com.forum_reply_like.model;

import java.io.Serializable;

public class ForumReplyLikeVO implements Serializable{
	private Integer forum_reply_like_id;
	private Integer forum_reply_id;
	private Integer member_id;
	
	public ForumReplyLikeVO() {
		super();
	}

	public ForumReplyLikeVO(Integer forum_reply_like_id, Integer forum_reply_id, Integer member_id) {
		super();
		this.forum_reply_like_id = forum_reply_like_id;
		this.forum_reply_id = forum_reply_id;
		this.member_id = member_id;
	}

	public Integer getForum_reply_like_id() {
		return forum_reply_like_id;
	}

	public void setForum_reply_like_id(Integer forum_reply_like_id) {
		this.forum_reply_like_id = forum_reply_like_id;
	}

	public Integer getForum_reply_id() {
		return forum_reply_id;
	}

	public void setForum_reply_id(Integer forum_reply_id) {
		this.forum_reply_id = forum_reply_id;
	}

	public Integer getMember_id() {
		return member_id;
	}

	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
	}
}
