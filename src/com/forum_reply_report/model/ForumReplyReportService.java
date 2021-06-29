package com.forum_reply_report.model;

import java.util.List;

public class ForumReplyReportService {
	
	private ForumReplyReportDAO dao;

	public ForumReplyReportService() {
		dao = new ForumReplyReportJDBCDAO();
	}
	
	public ForumReplyReportVO addForumReplyReport(Integer forum_reply_id, 
			Integer member_id, String forum_reply_report_reason) {
		
		ForumReplyReportVO forumReplyReport = new ForumReplyReportVO();
		
		forumReplyReport.setForum_reply_id(forum_reply_id);
		forumReplyReport.setMember_id(member_id);
		forumReplyReport.setForum_reply_report_reason(forum_reply_report_reason);
		
		dao.add(forumReplyReport);
		
		return forumReplyReport;
	}
	
	public void updateStatusForumReplyReport(Integer forum_reply_report_status, 
			Integer forum_reply_report_id) {		
		dao.updateStatus(forum_reply_report_status, forum_reply_report_id);
	}
	
	public void deleteForumReplyReport(Integer forum_reply_report_id) {
		dao.delete(forum_reply_report_id);
	}
	
	public ForumReplyReportVO getOne(Integer forum_reply_report_id) {
		return dao.findByPK(forum_reply_report_id);
	}
	
	public List<ForumReplyReportVO> getAll() {
		return dao.getAll();
	}
}

