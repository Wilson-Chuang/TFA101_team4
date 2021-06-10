package com.forum_reply_report.model;

import java.util.List;
import java.util.Scanner;

public class ReplyReportTest {
	
	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		ForumReplyReportVO forumReplyReport = new ForumReplyReportVO();
//		ForumReplyReportDAO dao = new ForumReplyReportJDBCDAO();
		
//		新增
//		System.out.println("replyID");
//		Integer forum_reply_id = sc.nextInt();
//		System.out.println("memberID");
//		Integer member_id = sc.nextInt();
//		System.out.println("reportReason");
//		String forum_reply_report_reason = sc.next();

//		forumReplyReport.setForum_reply_id(forum_reply_id);
//		forumReplyReport.setMember_id(member_id);
//		forumReplyReport.setForum_reply_report_reason(forum_reply_report_reason);
	
//		dao.add(forumReplyReport);
		
//		修改狀態
//		System.out.println("replyReportID");
//		Integer forum_reply_report_id = sc.nextInt();
//		System.out.println("reportStatus");
//		Integer forum_reply_report_status = sc.nextInt();
//		forumReplyReport.setForum_reply_report_status(forum_reply_report_status);
//		forumReplyReport.setForum_reply_report_id(forum_reply_report_id);;
//		dao.updateStatus(forumReplyReport);
		
//		刪除
//		System.out.println("replyReportID");
//		Integer forum_reply_report_id = sc.nextInt();
//		dao.delete(forum_reply_report_id);
		
//		查一筆
//		Scanner sc = new Scanner(System.in);
//		System.out.println("postReportID");
//		Integer forum_reply_report_id = sc.nextInt();
//		ForumReplyReportVO forumReplyReport = dao.findByPK(forum_reply_report_id);
//		System.out.println("FORUM_REPLY_REPORT_ID= " + forum_reply_report_id);
//		System.out.println("FORUM_REPLY_ID= " + forumReplyReport.getForum_reply_id());
//		System.out.println("MEMBER_ID= " + forumReplyReport.getMember_id());
//		System.out.println("FORUM_REPLY_REPORT_REASON= " + forumReplyReport.getForum_reply_report_reason());
//		System.out.println("FORUM_REPLY_REPORT_TIME= " + forumReplyReport.getForum_reply_report_time());
//		System.out.println("FORUM_REPLY_REPORT_STATUS= " + forumReplyReport.getForum_reply_report_status());
		
//		查全部
		ForumReplyReportDAO dao = new ForumReplyReportJDBCDAO();
		List<ForumReplyReportVO> reportlist = dao.getAll();
		for(ForumReplyReportVO forumReplyReport : reportlist) {
			System.out.println("FORUM_REPLY_REPORT_ID= " + forumReplyReport.getForum_reply_report_id());
			System.out.println("FORUM_REPLY_ID= " + forumReplyReport.getForum_reply_id());
			System.out.println("MEMBER_ID= " + forumReplyReport.getMember_id());
			System.out.println("FORUM_REPLY_REPORT_REASON= " + forumReplyReport.getForum_reply_report_reason());
			System.out.println("FORUM_REPLY_REPORT_TIME= " + forumReplyReport.getForum_reply_report_time());
			System.out.println("FORUM_REPLY_REPORT_STATUS= " + forumReplyReport.getForum_reply_report_status());
			System.out.println("==================================");
		}
		
	}

}

