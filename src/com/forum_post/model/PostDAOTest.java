package com.forum_post.model;

import java.util.List;
import java.util.Scanner;

public class PostDAOTest {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ForumPostVO forumPost = new ForumPostVO();
		ForumPostDAO dao = new ForumPostJDBCDAO();
		
		System.out.println("postid");
		int forum_post_id = sc.nextInt();
		System.out.println("狀態");
		int forum_post_status = sc.nextInt();
		dao.updateStatus(forum_post_status, forum_post_id);
		
//		新增	
//		System.out.println("輸入會員編號");
//		int member_id = sc.nextInt();
//		System.out.println("請輸入標題");
//		String forum_post_title = sc.next();
//		System.out.println("請輸入內容");
//		String forum_post_content = sc.next();
//
//		forumPost.setMember_id(member_id);
//		forumPost.setForum_post_title(forum_post_title);
//		forumPost.setForum_post_content(forum_post_content);
//		
//		dao.add(forumPost);

//		修改
//		System.out.println("postID");
//		Integer forum_post_id = sc.nextInt();
//		System.out.println("請輸入標題");
//		String forum_post_title = sc.next();
//		System.out.println("請輸入內容");
//		String forum_post_content = sc.next();
//
//		forumPost.setForum_post_id(forum_post_id);
//		forumPost.setForum_post_title(forum_post_title);
//		forumPost.setForum_post_content(forum_post_content);
//		
//		dao.update(forumPost);
		
//		修改狀態
//		System.out.println("postID");
//		Integer forum_post_id = sc.nextInt();
//		System.out.println("請輸入修改狀態");
//		Integer forum_post_status = sc.nextInt();
//
//		forumPost.setForum_post_id(forum_post_id);
//		forumPost.setForum_post_status(forum_post_status);
//
//		dao.updateStatus(forumPost);

//		刪除
//		System.out.println("輸入發文編號");
//		int forum_post_id = sc.nextInt();
//		
//		dao.delete(forum_post_id);

//		查全部
//		List<ForumPostVO> forumlist = dao.getAll();
//		for(ForumPostVO forumPost : forumlist) {
//			System.out.println("MEMBER_ID = " + forumPost.getMember_id());
//			System.out.println("FORUM_POST_TITLE = " + forumPost.getForum_post_title());
//			System.out.println("FORUM_POST_CONTENT = " + forumPost.getForum_post_content());
//			System.out.println("FORUM_POST_TIME = " + forumPost.getForum_post_time());
//			System.out.println("FORUM_UPDATE_TIME = " + forumPost.getForum_update_time());
//			System.out.println("FORUM_POST_REPLY_TOTAL = " + forumPost.getForum_post_reply_total());
//			System.out.println("FORUM_POST_LIKE = " + forumPost.getForum_post_like());
//			System.out.println("==================================");
//			
//		}

		
//		查一筆
//		System.out.println("輸入發文編號");
//		int forum_post_id = sc.nextInt();
//
//		forumPost = dao.findByPK(forum_post_id);
//		System.out.println("MEMBER_ID = " + forumPost.getMember_id());
//		System.out.println("FORUM_POST_TITLE = " + forumPost.getForum_post_title());
//		System.out.println("FORUM_POST_CONTENT = " + forumPost.getForum_post_content());
//		System.out.println("FORUM_POST_TIME = " + forumPost.getForum_post_time());
//		System.out.println("FORUM_UPDATE_TIME = " + forumPost.getForum_update_time());
//		System.out.println("FORUM_POST_REPLY_TOTAL = " + forumPost.getForum_post_reply_total());
//		System.out.println("FORUM_POST_LIKE = " + forumPost.getForum_post_like());
//		System.out.println("==================================");
	
//		查按讚數
//		System.out.println("輸入發文編號");
//		int forum_post_id = sc.nextInt();
//		
//		int count = dao.countLikeByPostID(forum_post_id);
//		System.out.println("FORUM_POST_ID= " + forum_post_id + ", " + "按讚= " + count);
		
//		按讚+
//		System.out.println("輸入發文編號");
//		Integer forum_post_id = sc.nextInt();
//		
//		dao.updateLikePlus(forum_post_id);
//		dao.updateLikeMinus(forum_post_id);
//		dao.updateReplyPlus(forum_post_id);
//		dao.updateReplyMinus(forum_post_id);

	}

}
