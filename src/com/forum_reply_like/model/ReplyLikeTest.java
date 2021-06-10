package com.forum_reply_like.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReplyLikeTest {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ForumReplyLikeVO forumReplyLike = new ForumReplyLikeVO();
		ForumReplyLikeDAO dao = new ForumReplyLikeJDBCDAO();
		
//		新增
		
		System.out.println("請輸入replyID");
		Integer forum_reply_id = sc.nextInt();
		System.out.println("請輸入memberID");
		Integer member_id = sc.nextInt();
		
		forumReplyLike.setForum_reply_id(forum_reply_id);
		forumReplyLike.setMember_id(member_id);
		
		dao.add(forumReplyLike);
		
//		修改
//		System.out.println("請輸入replyLikeID");
//		Integer forum_reply_like_id = sc.nextInt();
//		System.out.println("請輸入replyID");
//		Integer forum_reply_id = sc.nextInt();
//		System.out.println("請輸入memberID");
//		Integer member_id = sc.nextInt();
//		
//		forumReplyLike.setForum_reply_like_id(forum_reply_like_id);
//		forumReplyLike.setForum_reply_id(forum_reply_id);
//		forumReplyLike.setMember_id(member_id);
//
//		dao.update(forumReplyLike);
		
//		刪除
//		System.out.println("請輸入replyLikeID");
//		Integer forum_reply_like_id = sc.nextInt();
//		
//		forumReplyLike.setForum_reply_like_id(forum_reply_like_id);
//		dao.delete(forum_reply_like_id);
		
//		查詢一筆
//		System.out.println("請輸入replyLikeID");
//		Integer forum_reply_like_id= sc.nextInt();
//
//		forumReplyLike = dao.findByPK(forum_reply_like_id);
//		System.out.println("forum_reply_like_id= " + forum_reply_like_id);
//		System.out.println("forum_reply_id= " + forumReplyLike.getForum_reply_id());
//		System.out.println("member_id= " + forumReplyLike.getMember_id());
		
//		查多筆
//		List<ForumReplyLikeVO> likelist = new ArrayList();
//		likelist = dao.getAll();
//		for(ForumReplyLikeVO forumReplyLike : likelist) {
//			System.out.println("FORUM_REPLY_LIKE_ID= " + forumReplyLike.getForum_reply_like_id());
//			System.out.println("FORUM_REPLY_ID= " + forumReplyLike.getForum_reply_id());
//			System.out.println("MEMBER_ID" + forumReplyLike.getMember_id());
//			System.out.println("===========================");
//		}
		
//		按讚數
//		System.out.println("請輸入replyID");
//		Integer forum_reply_id = sc.nextInt();
//		System.out.println("FORUM_REPLY_ID= " + forum_reply_id + ",按讚= " + dao.countByForumRelyID(forum_reply_id));
		
	}

}


