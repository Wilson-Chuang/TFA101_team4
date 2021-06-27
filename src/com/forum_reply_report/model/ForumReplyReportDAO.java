package com.forum_reply_report.model;

import java.util.List;

public interface ForumReplyReportDAO {
	public void add(ForumReplyReportVO forumReplyReport);
//	public void updateStatus(ForumReplyReportVO forumReplyReport);
	public void updateStatus(Integer forum_reply_report_status, Integer forum_reply_report_id);
	public void delete(Integer forum_reply_report_id);
	public ForumReplyReportVO findByPK(Integer forum_reply_report_id);
	public List<ForumReplyReportVO> getAll();
}