package com.forum_post_report.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class ForumPostReportVO implements Serializable{
	private Integer forum_post_report_id;
	private Integer forum_post_id;
	private Integer member_id;
	private String forum_post_report_reason;
	private Timestamp forum_post_report_time;
	private Integer forum_post_report_status;
	
	public ForumPostReportVO() {
		super();
	}

	public ForumPostReportVO(Integer forum_post_report_id, Integer forum_post_id, Integer member_id,
			String forum_post_report_reason, Timestamp forum_post_report_time, Integer forum_post_report_status) {
		super();
		this.forum_post_report_id = forum_post_report_id;
		this.forum_post_id = forum_post_id;
		this.member_id = member_id;
		this.forum_post_report_reason = forum_post_report_reason;
		this.forum_post_report_time = forum_post_report_time;
		this.forum_post_report_status = forum_post_report_status;
	}

	public Integer getForum_post_report_id() {
		return forum_post_report_id;
	}

	public void setForum_post_report_id(Integer forum_post_report_id) {
		this.forum_post_report_id = forum_post_report_id;
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

	public String getForum_post_report_reason() {
		return forum_post_report_reason;
	}

	public void setForum_post_report_reason(String forum_post_report_reason) {
		this.forum_post_report_reason = forum_post_report_reason;
	}

	public Timestamp getForum_post_report_time() {
		return forum_post_report_time;
	}

	public void setForum_post_report_time(Timestamp forum_post_report_time) {
		this.forum_post_report_time = forum_post_report_time;
	}

	public Integer getForum_post_report_status() {
		return forum_post_report_status;
	}

	public void setForum_post_report_status(Integer forum_post_report_status) {
		this.forum_post_report_status = forum_post_report_status;
	}
	
	
}

