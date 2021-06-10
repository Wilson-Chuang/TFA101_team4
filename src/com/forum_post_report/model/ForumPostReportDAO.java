package com.forum_post_report.model;

import java.util.List;

public interface ForumPostReportDAO {
	public void add(ForumPostReportVO forumPostReport);
	public void updateStatus(ForumPostReportVO forumPostReport);
	public void delete(Integer forum_post_report_id);
	public ForumPostReportVO findByPK(Integer forum_post_report_id);
	public List<ForumPostReportVO> getAll();
}
