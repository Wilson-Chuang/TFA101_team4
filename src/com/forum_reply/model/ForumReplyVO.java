package com.forum_reply.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class ForumReplyVO implements Serializable {
	private Integer forum_reply_id;
	private Integer member_id;
	private Integer forum_post_id;
	private String forum_reply_content;
	private Timestamp forum_reply_time;
	private Timestamp forum_reply_update_time;
	private Integer forum_reply_status;
	private Integer forum_reply_like;
	
	public ForumReplyVO() {
		super();
	}

	public ForumReplyVO(Integer forum_reply_id, Integer member_id, Integer forum_post_id, String forum_reply_content,
			Timestamp forum_reply_time, Timestamp forum_reply_update_time, Integer forum_reply_status,
			Integer forum_reply_like) {
		super();
		this.forum_reply_id = forum_reply_id;
		this.member_id = member_id;
		this.forum_post_id = forum_post_id;
		this.forum_reply_content = forum_reply_content;
		this.forum_reply_time = forum_reply_time;
		this.forum_reply_update_time = forum_reply_update_time;
		this.forum_reply_status = forum_reply_status;
		this.forum_reply_like = forum_reply_like;
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

	public Integer getForum_post_id() {
		return forum_post_id;
	}

	public void setForum_post_id(Integer forum_post_id) {
		this.forum_post_id = forum_post_id;
	}

	public String getForum_reply_content() {
		return forum_reply_content;
	}

	public void setForum_reply_content(String forum_reply_content) {
		this.forum_reply_content = forum_reply_content;
	}

	public Timestamp getForum_reply_time() {
		return forum_reply_time;
	}

	public void setForum_reply_time(Timestamp forum_reply_time) {
		this.forum_reply_time = forum_reply_time;
	}

	public Timestamp getForum_reply_update_time() {
		return forum_reply_update_time;
	}

	public void setForum_reply_update_time(Timestamp forum_reply_update_time) {
		this.forum_reply_update_time = forum_reply_update_time;
	}

	public Integer getForum_reply_status() {
		return forum_reply_status;
	}

	public void setForum_reply_status(Integer forum_reply_status) {
		this.forum_reply_status = forum_reply_status;
	}

	public Integer getForum_reply_like() {
		return forum_reply_like;
	}

	public void setForum_reply_like(Integer forum_reply_like) {
		this.forum_reply_like = forum_reply_like;
	}
}
