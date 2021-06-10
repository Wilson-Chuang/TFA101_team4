package com.forum_post.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forum_post.model.ForumPostService;
import com.forum_post.model.ForumPostVO;


public class ForumPostServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("getOne_For_Display".equals(action)) { // 
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String str = req.getParameter("postid");
				if(str == null || str.trim().length() == 0) {
					errorMsgs.add("文章編號不可為空");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/forumPost/allPost.jsp");
					failureView.forward(req, res);
					return;
 				}
				
				Integer postid = null;
				try {
					postid = new Integer(str);
				} catch(Exception e) {
					errorMsgs.add("文章編號格是錯誤");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/forumPost/allPost.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始查詢資料*****************************************/
				ForumPostService forumPostSvc = new ForumPostService();
				ForumPostVO forumPost = forumPostSvc.getOneForumPost(postid);
				if(forumPost == null) {
					errorMsgs.add("查無資料");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/forumPost/allPost.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("forumPost", forumPost);
				String url = "/forumPost/onePost.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			} catch(Exception e) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher sucessView = req.getRequestDispatcher("/forum/allPost.jsp");
				sucessView.forward(req, res);
			}
			
		}
		
		if("insert".equals(action)) { // 來自addPost.jsp的請求  
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String title = req.getParameter("title").trim();
				if(title == null || title.trim().length() == 0) {
					errorMsgs.add("請輸入標題:標題請勿空白!");
				}
				
				String content = req.getParameter("content").trim();
				if(content == null || content.trim().length() == 0) {
					errorMsgs.add("請輸入內容:內容請勿空白!");
				}
				
				Integer memberID = null;
				try {
					memberID = new Integer(req.getParameter("memberID"));
				} catch(NumberFormatException e){
					errorMsgs.add("請檢查是否已登入!");
				}
				
				ForumPostVO forumPost = new ForumPostVO();
				forumPost.setForum_post_title(title);
				forumPost.setForum_post_content(content);
				forumPost.setMember_id(memberID);
				
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("forumPost", forumPost);
					RequestDispatcher failureView = req.getRequestDispatcher("/forumPost/addPost.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				ForumPostService forumPostSvc = new ForumPostService();
				forumPost = forumPostSvc.addForumPost(memberID, title, content);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/forumPost/allPost.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			} catch(Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/forumPost/addPost.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
