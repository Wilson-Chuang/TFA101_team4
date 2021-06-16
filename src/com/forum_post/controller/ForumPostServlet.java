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
import com.forum_post_report.model.ForumPostReportService;
import com.forum_post_report.model.ForumPostReportVO;
import com.forum_reply.model.ForumReplyService;
import com.forum_reply.model.ForumReplyVO;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;


public class ForumPostServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println(action);
		
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
				RequestDispatcher sucessView = req.getRequestDispatcher("/forumPost/allPost.jsp");
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
				} catch(NumberFormatException e) {
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
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/forumPost/addPost.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("getOne_Post_For_update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String post_content = req.getParameter("post_content");
				if(!"此內容已被刪除".equals(post_content)) {
					Integer postid = new Integer(req.getParameter("postid"));
					
					/***************************2.開始查詢資料****************************************/
					ForumPostService forumPostSvc = new ForumPostService();
					ForumPostVO forumPost = forumPostSvc.getOneForumPost(postid);
					
					/***************************3.查詢完成,準備轉交(Send the Success view)************/
					req.setAttribute("forumPost", forumPost);
					String url = "/forumPost/postUpdateInput.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
				} else {
					Integer postid = new Integer(req.getParameter("postid"));
					ForumPostService forumPostSvc = new ForumPostService();
					ForumPostVO forumPost = forumPostSvc.getOneForumPost(postid);
					req.setAttribute("forumPost", forumPost);
					String url = "/forumPost/onePost.jsp";
					RequestDispatcher alreadyDeleteView = req.getRequestDispatcher(url);
					alreadyDeleteView.forward(req, res);
				}
				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch(Exception e) {
				errorMsgs.add("無法取得要修改的資料" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/forumPost/onePost.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("post_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer postid = new Integer(req.getParameter("postid").trim());
				
				String title = req.getParameter("title").trim();
				if(title == null || title.trim().length() == 0) {
					errorMsgs.add("請輸入標題!");
				}
				
				String content = req.getParameter("content").trim();
				if(content == null || content.trim().length() == 0) {
					errorMsgs.add("請輸入內容!");
				}
				
				System.out.println(postid);
				System.out.println(title);
				System.out.println(content);
				
				ForumPostVO forumPost = new ForumPostVO();
				forumPost.setForum_post_id(postid);
				forumPost.setForum_post_title(title);
				forumPost.setForum_post_content(content);
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("forumPost", forumPost);
					RequestDispatcher failureView = req.getRequestDispatcher("/forumPost/postUpdateInput.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始修改資料*****************************************/
				ForumPostService forumPostSvc = new ForumPostService();
				forumPost = forumPostSvc.updateForumPost(title, content, postid);
				forumPost = forumPostSvc.getOneForumPost(postid);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("forumPost", forumPost);
				String url = "/forumPost/onePost.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch(Exception e) {
				errorMsgs.add("資料修改失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/forumPost/postUpdateInput.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		
		if("post_delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				
				/***************************1.接收請求參數***************************************/
				Integer postid = new Integer(req.getParameter("postid"));
				
				/***************************2.開始刪除資料***************************************/
				ForumPostService forumPostSvc = new ForumPostService();
				forumPostSvc.delete(postid);
				ForumPostVO forumPost = forumPostSvc.getOneForumPost(postid);
				req.setAttribute("forumPost", forumPost);
				req.setAttribute("postid", postid);

				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
				String url = "/forumPost/onePost.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch(Exception e) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/forumPost/onePost.jsp");
			}
		}
		
		if("getOne_Post_For_report".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String post_content = req.getParameter("post_content");
				System.out.println(post_content);
				
				if(!"此內容已被刪除".equals(post_content)) {
					Integer postid = new Integer(req.getParameter("postid"));
					
					/***************************2.開始查詢資料****************************************/
					ForumPostService forumPostSvc = new ForumPostService();
					ForumPostVO forumPost = forumPostSvc.getOneForumPost(postid);
					
					/***************************3.查詢完成,準備轉交(Send the Success view)************/
					req.setAttribute("forumPost", forumPost);
					String url = "/forumPost/postReport.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
				} else {
					Integer postid = new Integer(req.getParameter("postid"));
					ForumPostService forumPostSvc = new ForumPostService();
					ForumPostVO forumPost = forumPostSvc.getOneForumPost(postid);
					req.setAttribute("forumPost", forumPost);
					String url = "/forumPost/onePost.jsp";
					RequestDispatcher alreadyDeleteView = req.getRequestDispatcher(url);
					alreadyDeleteView.forward(req, res);
				}
			} catch(Exception e) {
				errorMsgs.add("無法取得要檢舉的資料" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/forumPost/onePost.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("post_report".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String reportRadios = req.getParameter("reportRadios");
				
				if("其他".equals(reportRadios)) {
					System.out.println(reportRadios);
					reportRadios = req.getParameter("report_reason").trim();
					System.out.println(reportRadios);
					
					if(reportRadios == null || reportRadios.trim().length() == 0) {
						errorMsgs.add("請輸入檢舉原因!");
					}
				}
				
				Integer report_memberID = null;
				try {
					report_memberID = new Integer(req.getParameter("report_memberID").trim());
					System.out.println(report_memberID);
				} catch(NumberFormatException e){
					errorMsgs.add("會員編號格是不正確!");
				}
				
				Integer postid = new Integer(req.getParameter("postid").trim());
				
				ForumPostReportVO forumPostReport = new ForumPostReportVO();
				forumPostReport.setForum_post_id(postid);
				forumPostReport.setForum_post_report_reason(reportRadios);
				forumPostReport.setMember_id(report_memberID);
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("forumPostReport", forumPostReport);
					RequestDispatcher failureView = req.getRequestDispatcher("/forumPost/postReport.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始修改資料*****************************************/
				ForumPostService forumPostSvc = new ForumPostService();
				ForumPostVO forumPost = forumPostSvc.getOneForumPost(postid);
				
				ForumPostReportService forumPostReportSvc = new ForumPostReportService();
				forumPostReportSvc.addForumPostReport(postid, report_memberID, reportRadios);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("forumPost", forumPost);
				String url = "/forumPost/onePost.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch(Exception e) {
				errorMsgs.add("檢舉失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/forumPost/postReport.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
