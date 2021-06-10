package com.forum_reply.model;

import java.util.List;
import java.util.Scanner;



public class ReplyDAOTest {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
//		ForumReplyVO forumReply = new ForumReplyVO();
		ForumReplyDAO dao = new ForumReplyJDBCDAO();
		
//		新增
		
//		System.out.println("請輸入會員");
//		int member_id = sc.nextInt();
//		System.out.println("請輸入post_id");
//		int forum_post_id = sc.nextInt();
//		System.out.println("請輸入回覆內容");
//		String forum_reply_content = sc.next();
//		
//		forumReply.setMember_id(member_id);
//		forumReply.setForum_post_id(forum_post_id);
//		forumReply.setForum_reply_content(forum_reply_content);
//		
//		dao.add(forumReply);
		
//		修改

//		System.out.println("請輸入修改內容");
//		String forum_reply_content = sc.next();
//		System.out.println("請輸入replyID");
//		Integer forum_reply_id = sc.nextInt();
//		
//		forumReply.setForum_reply_content(forum_reply_content);
//		forumReply.setForum_reply_id(forum_reply_id);
//		dao.update(forumReply);
		
//		修改狀態
//		System.out.println("請輸入修改狀態");
//		Integer forum_reply_status = sc.nextInt();
//		System.out.println("請輸入replyID");
//		Integer forum_reply_id = sc.nextInt();
//		
//		forumReply.setForum_reply_status(forum_reply_status);
//		forumReply.setForum_reply_id(forum_reply_id);
//		dao.updateStatus(forumReply);
		
//		刪除
//		System.out.println("請輸入replyID");
//		int forum_reply_id = sc.nextInt();

//		dao.delete(forum_reply_id);
		
//		查一筆
//		System.out.println("請輸入replyID");
//		int forum_reply_id = sc.nextInt();
//		
//		ForumReplyVO forumReply = dao.findByPK(forum_reply_id);
//		System.out.println("REPLY_ID= " + forum_reply_id);
//		System.out.println("MEMBER_ID= " + forumReply.getMember_id());
//		System.out.println("FORUM_POST_ID= " + forumReply.getForum_post_id());
//		System.out.println("FORUM_REPLY_CONTENT= " + forumReply.getForum_reply_content());
//		System.out.println("FORUM_REPLY_TIME= " + forumReply.getForum_reply_time());
//		System.out.println("FORUM_REPLY_UPDATE_TIME= " + forumReply.getForum_reply_update_time());
//		System.out.println("FORUM_REPLY_STATUS= " + forumReply.getForum_reply_status());
//		System.out.println("FORUM_REPLY_LIKE= " + forumReply.getForum_reply_like());
		
//		查全部
//		List<ForumReplyVO> replylist = dao.getAll();
//		
//		for(ForumReplyVO forumReply : replylist) {
//			System.out.println("REPLY_ID= " + forumReply.getForum_reply_id());
//			System.out.println("MEMBER_ID= " + forumReply.getMember_id());
//			System.out.println("FORUM_POST_ID= " + forumReply.getForum_post_id());
//			System.out.println("FORUM_REPLY_CONTENT= " + forumReply.getForum_reply_content());
//			System.out.println("FORUM_REPLY_TIME= " + forumReply.getForum_reply_time());
//			System.out.println("FORUM_REPLY_UPDATE_TIME= " + forumReply.getForum_reply_update_time());
//			System.out.println("FORUM_REPLY_STATUS= " + forumReply.getForum_reply_status());
//			System.out.println("FORUM_REPLY_LIKE= " + forumReply.getForum_reply_like());
//			System.out.println("==========================");
//		}
		
//		回應總數
//		System.out.println("請輸入postID");
//		Integer forum_post_id = sc.nextInt();
//		Integer count = dao.countByPostID(forum_post_id);
//		System.out.println("FORUM_POST_ID= " + forum_post_id);
//		System.out.println("回應總數 " + count);
		
//		System.out.println("請輸入replyID");
//		Integer forum_reply_id = sc.nextInt();
//		dao.updateLikePlus(forum_reply_id);
//		dao.updateLikeMinus(forum_reply_id);
		
//		取得單篇文章回應
		System.out.println("請輸入postid");
		Integer forum_post_id = sc.nextInt();
		
		List<ForumReplyVO> replylist = dao.findByPostID(forum_post_id);		
		for(ForumReplyVO forumReply : replylist) {
			System.out.println("REPLY_ID= " + forumReply.getForum_reply_id());
			System.out.println("MEMBER_ID= " + forumReply.getMember_id());
			System.out.println("FORUM_POST_ID= " + forumReply.getForum_post_id());
			System.out.println("FORUM_REPLY_CONTENT= " + forumReply.getForum_reply_content());
			System.out.println("FORUM_REPLY_TIME= " + forumReply.getForum_reply_time());
			System.out.println("FORUM_REPLY_UPDATE_TIME= " + forumReply.getForum_reply_update_time());
			System.out.println("FORUM_REPLY_STATUS= " + forumReply.getForum_reply_status());
			System.out.println("FORUM_REPLY_LIKE= " + forumReply.getForum_reply_like());
			System.out.println("==========================");
		}
	}
}
