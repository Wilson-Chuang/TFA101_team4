package com.forum_reply_report.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class ForumReplyReportVO implements Serializable{
	private Integer forum_reply_report_id;
	private Integer forum_reply_id;
	private Integer member_id;
	private String forum_reply_report_reason;
	private Timestamp forum_reply_report_time;
	private Integer forum_reply_report_status;
	
	public ForumReplyReportVO() {
		super();
	}

	public ForumReplyReportVO(Integer forum_reply_report_id, Integer forum_reply_id, Integer member_id,
			String forum_reply_report_reason, Timestamp forum_reply_report_time, Integer forum_reply_report_status) {
		super();
		this.forum_reply_report_id = forum_reply_report_id;
		this.forum_reply_id = forum_reply_id;
		this.member_id = member_id;
		this.forum_reply_report_reason = forum_reply_report_reason;
		this.forum_reply_report_time = forum_reply_report_time;
		this.forum_reply_report_status = forum_reply_report_status;
	}

	public Integer getForum_reply_report_id() {
		return forum_reply_report_id;
	}

	public void setForum_reply_report_id(Integer forum_reply_report_id) {
		this.forum_reply_report_id = forum_reply_report_id;
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

	public String getForum_reply_report_reason() {
		return forum_reply_report_reason;
	}

	public void setForum_reply_report_reason(String forum_reply_report_reason) {
		this.forum_reply_report_reason = forum_reply_report_reason;
	}

	public Timestamp getForum_reply_report_time() {
		return forum_reply_report_time;
	}

	public void setForum_reply_report_time(Timestamp forum_reply_report_time) {
		this.forum_reply_report_time = forum_reply_report_time;
	}

	public Integer getForum_reply_report_status() {
		return forum_reply_report_status;
	}

	public void setForum_reply_report_status(Integer forum_reply_report_status) {
		this.forum_reply_report_status = forum_reply_report_status;
	}	
}
