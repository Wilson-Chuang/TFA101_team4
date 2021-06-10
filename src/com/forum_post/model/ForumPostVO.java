package com.forum_post.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class ForumPostVO implements Serializable {
	private Integer forum_post_id;
	private Integer member_id;
	private String forum_post_title;
	private String forum_post_content;
	private Timestamp forum_post_time;
	private Timestamp forum_update_time;
	private Integer forum_post_reply_total;
	private Integer forum_post_like;
	private Integer forum_post_status;
	
	public ForumPostVO() {
		super();
	}

	public ForumPostVO(Integer forum_post_id, Integer member_id, String forum_post_title, String forum_post_content,
			Timestamp forum_post_time, Timestamp forum_update_time, Integer forum_post_reply_total,
			Integer forum_post_like, Integer forum_post_status) {
		super();
		this.forum_post_id = forum_post_id;
		this.member_id = member_id;
		this.forum_post_title = forum_post_title;
		this.forum_post_content = forum_post_content;
		this.forum_post_time = forum_post_time;
		this.forum_update_time = forum_update_time;
		this.forum_post_reply_total = forum_post_reply_total;
		this.forum_post_like = forum_post_like;
		this.forum_post_status = forum_post_status;
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

	public String getForum_post_title() {
		return forum_post_title;
	}

	public void setForum_post_title(String forum_post_title) {
		this.forum_post_title = forum_post_title;
	}

	public String getForum_post_content() {
		return forum_post_content;
	}

	public void setForum_post_content(String forum_post_content) {
		this.forum_post_content = forum_post_content;
	}

	public Timestamp getForum_post_time() {
		return forum_post_time;
	}

	public void setForum_post_time(Timestamp forum_post_time) {
		this.forum_post_time = forum_post_time;
	}

	public Timestamp getForum_update_time() {
		return forum_update_time;
	}

	public void setForum_update_time(Timestamp forum_update_time) {
		this.forum_update_time = forum_update_time;
	}

	public Integer getForum_post_reply_total() {
		return forum_post_reply_total;
	}

	public void setForum_post_reply_total(Integer forum_post_reply_total) {
		this.forum_post_reply_total = forum_post_reply_total;
	}

	public Integer getForum_post_like() {
		return forum_post_like;
	}

	public void setForum_post_like(Integer forum_post_like) {
		this.forum_post_like = forum_post_like;
	}

	public Integer getForum_post_status() {
		return forum_post_status;
	}

	public void setForum_post_status(Integer forum_post_status) {
		this.forum_post_status = forum_post_status;
	}
	
}

