package com.forum_post_report.model;

import java.util.List;
import java.util.Scanner;

public class PostReportTest {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		ForumPostReportVO forumPostReport = new ForumPostReportVO();
		ForumPostReportDAO dao = new ForumPostReportJDBCDAO();
		
//		新增
//		System.out.println("postID");
//		Integer forum_post_id = sc.nextInt();
//		System.out.println("memberID");
//		Integer member_id = sc.nextInt();
//		System.out.println("reportReason");
//		String forum_post_report_reason = sc.next();
//		
//		
//		forumPostReport.setForum_post_id(forum_post_id);
//		forumPostReport.setMember_id(member_id);
//		forumPostReport.setForum_post_report_reason(forum_post_report_reason);
//		
//		dao.add(forumPostReport);
		
//		修改狀態
		
//		System.out.println("postReportID");
//		Integer forum_post_report_id = sc.nextInt();
//		System.out.println("reportStatus");
//		Integer forum_post_report_status = sc.nextInt();
//		
//		forumPostReport.setForum_post_report_status(forum_post_report_status);
//		forumPostReport.setForum_post_report_id(forum_post_report_id);;
//	
//		dao.updateStatus(forumPostReport);
		
//		刪除
//		System.out.println("postReportID");
//		Integer forum_post_report_id = sc.nextInt();
//		dao.delete(forum_post_report_id);
		
//		查一筆
//		System.out.println("postReportID");
//		Integer forum_post_report_id = sc.nextInt();
//		dao.findByPK(forum_post_report_id);
//		System.out.println("FORUM_POST_REPORT_ID= " + forum_post_report_id);
//		System.out.println("FORUM_POST_ID= " + forumPostReport.getForum_post_id());
//		System.out.println("MEMBER_ID= " + forumPostReport.getMember_id());
//		System.out.println("FORUM_POST_REPORT_REASON= " + forumPostReport.getForum_post_report_reason());
//		System.out.println("FORUM_POST_REPORT_TIME= " + forumPostReport.getForum_post_report_time());
//		System.out.println("FORUM_POST_REPORT_STATUS= " + forumPostReport.getForum_post_report_status());
		
//		查全部
//		List<ForumPostReportVO> reportlist = dao.getAll();
//		for(ForumPostReportVO forumPostReport : reportlist) {
//			System.out.println("FORUM_POST_REPORT_ID= " + forumPostReport.getForum_post_report_id());
//			System.out.println("FORUM_POST_ID= " + forumPostReport.getForum_post_id());
//			System.out.println("MEMBER_ID= " + forumPostReport.getMember_id());
//			System.out.println("FORUM_POST_REPORT_REASON= " + forumPostReport.getForum_post_report_reason());
//			System.out.println("FORUM_POST_REPORT_TIME= " + forumPostReport.getForum_post_report_time());
//			System.out.println("FORUM_POST_REPORT_STATUS= " + forumPostReport.getForum_post_report_status());
//			System.out.println("==================================");
//		}
	}
}
