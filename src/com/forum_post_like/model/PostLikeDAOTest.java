package com.forum_post_like.model;

import java.util.List;
import java.util.Scanner;

import com.forum_post_like.model.*;


public class PostLikeDAOTest {

		
		public static void main(String[] args) {
//			新增
			Scanner sc = new Scanner(System.in);
			System.out.println("請輸入postid");
			int forum_post_id = sc.nextInt();
			System.out.println("請輸入會員id");
			int member_id = sc.nextInt();
			
			ForumPostLikeDAO dao = new ForumPostLikeJDBCDAO();
			System.out.println(dao.findOne(forum_post_id, member_id));
//			
//			// 包裝資料
//			ForumPostLikeVO forumPostLike = new ForumPostLikeVO();
//			forumPostLike.setForum_post_id(forum_post_id);
//			forumPostLike.setMember_id(member_id);
//			
//			ForumPostLikeDAO dao = new ForumPostLikeJDBCDAO();
//			dao.add(forumPostLike);
			
//			修改
//			Scanner sc = new Scanner(System.in);
//			System.out.println("請輸入postid");
//			int forum_post_id = sc.nextInt();
//			System.out.println("請輸入會員id");
//			int member_id = sc.nextInt();
//			System.out.println("請輸入流水id");
//			int forum_post_like_id = sc.nextInt();
//			
//			// 包裝資料
//			ForumPostLikeVO forumPostLike = new ForumPostLikeJDBCDAO();
//			forumPostLike.setForum_post_id(forum_post_id);
//			forumPostLike.setMember_id(member_id);
//			forumPostLike.setForum_post_like_id(forum_post_like_id);
//			
//			ForumPostLikeDAO dao = new ForumPostLikeJDBCDAO();
//			dao.update(forumPostLike);
			
//			刪除
//			Scanner sc = new Scanner(System.in);
//			System.out.println("請輸入流水id");
//			int forum_post_like_id = sc.nextInt();
//			
//			ForumPostLikeDAO dao = new ForumPostLikeJDBCDAO();
//			dao.delete(forum_post_like_id);
			
//			查一筆
//			Scanner sc = new Scanner(System.in);
//			System.out.println("請輸入流水id");
//			Integer forum_post_like_id = sc.nextInt();
//			
//			ForumPostLikeDAO dao = new ForumPostLikeJDBCDAO();
//			ForumPostLikeVO forumPostLike = dao.findByPK(forum_post_like_id);
//			System.out.println("FORUM_POST_ID = " + forum_post_like_id);
//			System.out.println("FORUM_POST_ID = " + forumPostLike.getForum_post_id());
//			System.out.println("MEMBER_ID = " + forumPostLike.getMember_id());
//			System.out.println("==================================");
			
//			查全部
//			ForumPostLikeDAO dao = new new ForumPostLikeJDBCDAO();
//			List<ForumPostLikeVO> likelist = dao.getAll();
//			for(ForumPostLikeVO forumLike : likelist) {
//				System.out.println("FORUM_POST_ID = " + forumLike.getForum_post_id());
//				System.out.println("MEMBER_ID = " + forumLike.getMember_id());
//				System.out.println("==================================");
//				
//			}
			
//			查一篇文章按讚
//			Scanner sc = new Scanner(System.in);
//			System.out.println("請輸入postid");
//			int forum_post_id = sc.nextInt();
//			
//			ForumPostLikeDAO dao = new ForumPostLikeJDBCDAO();
//			int count = dao.countByPostID(forum_post_id);
//			System.out.println("按讚數= " + count);
			
		}

	}


