package com.forum_post_report.model;

import java.util.List;

public class ForumPostReportService {
	
	private ForumPostReportDAO dao;

	public ForumPostReportService() {
		dao = new ForumPostReportJDBCDAO();
	}
	
	public ForumPostReportVO addForumPostReport(Integer forum_post_id, 
			Integer member_id, String forum_post_report_reason) {
		
		ForumPostReportVO forumPostReport = new ForumPostReportVO();
		
		forumPostReport.setForum_post_id(forum_post_id);
		forumPostReport.setMember_id(member_id);
		forumPostReport.setForum_post_report_reason(forum_post_report_reason);
		dao.add(forumPostReport);
		
		return forumPostReport;
	}
	
	public void updateStatusForumPostReport(Integer forum_post_report_status, 
			Integer forum_post_report_id) {
		
		ForumPostReportVO forumPostReport = new ForumPostReportVO();
		
		forumPostReport.setForum_post_report_status(forum_post_report_status);
		forumPostReport.setForum_post_report_id(forum_post_report_id);
		
		dao.updateStatus(forumPostReport);
	}
	
	public void deleteForumPostReport(Integer forum_post_report_id) {
		dao.delete(forum_post_report_id);
	}
	
	public ForumPostReportVO getOneForumPostReport(Integer forum_post_report_id) {
		return dao.findByPK(forum_post_report_id);
	}
	
	public List<ForumPostReportVO> getAll() {
		return dao.getAll();
	}
}
