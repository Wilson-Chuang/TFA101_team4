package com.forum_reply.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forum_post.model.ForumPostService;
import com.forum_post.model.ForumPostVO;
import com.forum_post_report.model.ForumPostReportService;
import com.forum_reply.model.ForumReplyService;
import com.forum_reply.model.ForumReplyVO;
import com.forum_reply_report.model.ForumReplyReportService;
import com.forum_reply_report.model.ForumReplyReportVO;


public class ForumReplyServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		doPost(req, res);

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("reply".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try { 
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				Integer memberID = null;
				try {
					memberID = new Integer(req.getParameter("memberID").trim());
				} catch(NumberFormatException e){
					errorMsgs.add("請檢查是否已登入!");
				}
			
				String reply = req.getParameter("reply").trim();
				if(reply == null || reply.trim().length() == 0) {
					errorMsgs.add("請輸入內容:內容請勿空白!");
				}
				
				Integer postid = null;
				try {
					postid  = new Integer(req.getParameter("postid"));
				}	catch(NumberFormatException e) {
					errorMsgs.add("請確認回覆文章!");
				}
				
				ForumReplyVO forumReply = new ForumReplyVO();
				forumReply.setMember_id(memberID);
				forumReply.setForum_reply_content(reply);
				forumReply.setForum_post_id(postid);
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("forumReply", forumReply);
					req.setAttribute("postid", postid);
					RequestDispatcher failureView = req.getRequestDispatcher("/forumPost/replyFailed.jsp");
					failureView.forward(req, res);
					return;
				}
				
				ForumPostService forumPostSvc = new ForumPostService();
				ForumPostVO forumPost = forumPostSvc.getOneForumPost(postid);
				forumPostSvc.updateReplyPlus(postid);
			
				/***************************2.開始新增資料***************************************/
				req.setAttribute("forumPost", forumPost);
				req.setAttribute("postid", postid);
				ForumReplyService forumReplySvc = new ForumReplyService();
				forumReply = forumReplySvc.addForumReply(memberID, postid, reply);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/forumPost/onePost.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch(Exception e) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/forumPost/onePost.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("getOne_Reply_For_update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String reply_content = req.getParameter("reply_content");
				
				if(!"此內容已被刪除".equals(reply_content)) {
					Integer replyid = new Integer(req.getParameter("replyid"));
					System.out.println(replyid);
					
					/***************************2.開始查詢資料****************************************/
					ForumReplyService forumReplySvc = new ForumReplyService();
					ForumReplyVO forumReply = forumReplySvc.getOneForumReply(replyid);
					
					/***************************3.查詢完成,準備轉交(Send the Success view)************/
					req.setAttribute("forumReply", forumReply);
					String url = "/forumPost/replyUpdateInput.jsp";
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
				errorMsgs.add("無法取得要修改的資料" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/forumPost/onePost.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("reply_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer replyid = new Integer(req.getParameter("replyid").trim());
				Integer postid = new Integer(req.getParameter("postid").trim());
				Integer memberID = new Integer(req.getParameter("memberID").trim());
				
				String content = req.getParameter("content").trim();
				if(content == null || content.trim().length() == 0) {
					errorMsgs.add("請輸入內容!");
				}
				
				ForumReplyVO forumReply = new ForumReplyVO();
				forumReply.setForum_reply_id(replyid);
				forumReply.setForum_post_id(postid);
				forumReply.setMember_id(memberID);
				forumReply.setForum_reply_content(content);
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("forumReply", forumReply);
					RequestDispatcher failureView = req.getRequestDispatcher("/forumPost/replyUpdateInput.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始修改資料*****************************************/
				ForumReplyService forumReplySvc = new ForumReplyService();
				forumReply = forumReplySvc.updateForumReply(content, replyid);
				forumReply = forumReplySvc.getOneForumReply(replyid);
				
				ForumPostService forumPostSvc = new ForumPostService();
				ForumPostVO forumPost = forumPostSvc.getOneForumPost(postid);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("forumReply", forumReply);
				req.setAttribute("forumPost", forumPost);
				String url = "/forumPost/onePost.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch(Exception e) {
				errorMsgs.add("資料修改失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/forumPost/replyUpdateInput.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("reply_delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數***************************************/
				Integer replyid = new Integer(req.getParameter("replyid"));
				Integer postid = new Integer(req.getParameter("postid"));
				
				System.out.println(replyid);
				System.out.println(postid);
				
				/***************************2.開始刪除資料***************************************/
				ForumReplyService forumReplySvc = new ForumReplyService();
				forumReplySvc.deleteForumReply(replyid);
				
				
				ForumPostService forumPostSvc = new ForumPostService();
				forumPostSvc.updateReplyMinus(postid);
				ForumPostVO forumPost = forumPostSvc.getOneForumPost(postid);				
				req.setAttribute("forumPost", forumPost);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
				String url = "/forumPost/onePost.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch(Exception e) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/forumPost/onePost.jsp");
			}
		}
		
		if("getOne_Reply_For_report".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String reply_content = req.getParameter("reply_content");
				
				if(!"此內容已被刪除".equals(reply_content)) {
					
					/***************************2.開始查詢資料****************************************/
					Integer replyid = new Integer(req.getParameter("replyid"));
					
					ForumReplyService forumReplySvc = new ForumReplyService();
					ForumReplyVO forumReply = forumReplySvc.getOneForumReply(replyid);
					
					/***************************3.查詢完成,準備轉交(Send the Success view)************/
					req.setAttribute("forumReply", forumReply);
					String url = "/forumPost/replyReport.jsp";
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
		
		if("reply_report".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String reportRadios = req.getParameter("reportRadios");
				System.out.println(reportRadios);
				if("其他".equals(reportRadios)) {
					reportRadios = req.getParameter("report_reason").trim();
					
					System.out.println(reportRadios);
					
					if(reportRadios == null || reportRadios.trim().length() == 0) {
						errorMsgs.add("請出入檢舉內容!");
					}
				}
				
				Integer report_memberID = null;
				try {
					report_memberID = new Integer(req.getParameter("report_memberID").trim());
				} catch(NumberFormatException e) {
					errorMsgs.add("會員編號格是不正確!");
				}
				
				Integer replyid = new Integer(req.getParameter("replyid").trim());
				
				ForumReplyReportVO forumReplyReport = new ForumReplyReportVO();
				forumReplyReport.setForum_reply_id(replyid);
				forumReplyReport.setMember_id(report_memberID);
				forumReplyReport.setForum_reply_report_reason(reportRadios);
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("forumReplyReport", forumReplyReport);
					RequestDispatcher failureView = req.getRequestDispatcher("/forumPost/replyReport.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增檢舉資料*****************************************/
				ForumReplyReportService forumReplyReportSvc = new ForumReplyReportService();
				forumReplyReportSvc.addForumReplyReport(replyid, report_memberID, reportRadios);
				
				/***************************3.新增檢舉完成,準備轉交(Send the Success view)*************/
				String url = "/forumPost/allPost.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch(Exception e) {
				errorMsgs.add("檢舉失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/forumPost/replyReport.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if("check_reply_report".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				Integer forum_reply_report_id = new Integer(req.getParameter("forum_reply_report_id").trim());
				Integer forum_reply_report_status = new Integer(req.getParameter("forum_reply_report_status").trim());
				Integer replyid = new Integer(req.getParameter("replyid").trim());
				
				ForumReplyReportService forumReplyReportSvc = new ForumReplyReportService();
				if(forum_reply_report_status == 0) {
					forumReplyReportSvc.updateStatusForumReplyReport(forum_reply_report_status, forum_reply_report_id);
					ForumReplyService forumReplySvc = new ForumReplyService();
					forumReplySvc.updateStatusForumReply(forum_reply_report_status, replyid);
				}else {
					forumReplyReportSvc.updateStatusForumReplyReport(forum_reply_report_status, forum_reply_report_id);
				}
				
				String url = "/forumPost/allReplyReport.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				
			} catch(Exception e) {
				errorMsgs.add("處理回覆檢舉失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/forumPost/allReplyReport.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
