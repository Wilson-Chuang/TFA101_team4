package com.party.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.member.model.*;
import com.party.model.*;
import com.party_participants.model.PartyParticipantsService;
import com.party_participants.model.PartyParticipantsVO;



public class PartyServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("party_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/party/party_select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer party_id = null;
				try {
					party_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/party/party_select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				PartyService partySvc = new PartyService();
				PartyVO partyVO = partySvc.getOneParty(party_id);
				if (partyVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/party/party_select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("partyVO", partyVO); // 資料庫取出的partyVO物件,存入req
				String url = "/party/listOneParty.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/party/party_select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		
		if ("getOne_For_party".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer party_id = new Integer(req.getParameter("party_id").trim());

				/*************************** 2.開始查詢資料 *****************************************/
				PartyService partySvc = new PartyService();
				PartyVO partyVO = partySvc.getOneParty(party_id);
				// Send the use back to the form, if there were errors

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("partyVO", partyVO); // 資料庫取出的partyVO物件,存入req
				String url = "/party/partyNews.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/party/party_select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		if ("getOne_For_member".equals(action)) { // 來自select_page.jsp的請求
System.out.println("12345");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer member_id = new Integer(req.getParameter("member_id").trim());
System.out.println(member_id);
				/*************************** 2.開始查詢資料 *****************************************/
				PartyService partySvc = new PartyService();
				Set<PartyVO> partyVO = partySvc.getAllmypartybymember(member_id);
				System.out.println(partyVO);
				for(PartyVO pa:partyVO) {
				System.out.println(pa.getParty_id());
				}
				// Send the use back to the form, if there were errors

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("member_id", member_id); // 資料庫取出的partyVO物件,存入req
				String url = "/party/listAllParty2.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				System.out.println("88888");
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				System.out.println("111234567");
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/party/listAllParty.jsp");
				failureView.forward(req, res);
			}
		}
		
		

		if ("insert".equals(action)) { // 來自listAllEmp.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String party_title = req.getParameter("party_title").trim();
				if (party_title == null || party_title.trim().length() == 0) {
					errorMsgs.add("標題請勿空白");
				}
				String party_intro = req.getParameter("party_intro").trim();
				if (party_intro == null || party_intro.trim().length() == 0) {
					errorMsgs.add("內容請勿空白");
				}
				
				Integer member_id = null;
				try {
					member_id = new Integer(req.getParameter("member_id"));
				} catch (NumberFormatException e) {
					errorMsgs.add("請檢查是否已登入");
				}
			
				Integer shop_id = null;
				try {
					shop_id = new Integer(req.getParameter("shop_id"));
				} catch (NumberFormatException e) {
					errorMsgs.add("沒有餐廳資訊");
				}
				
				
				Integer party_participants_max = null;
				try {
					party_participants_max = new Integer(req.getParameter("party_participants_max").trim());
				} catch (NumberFormatException e) {
					party_participants_max = 0;
					errorMsgs.add("請填最高人數");
				}
				
				Integer party_participants_min = null;
				try {
					party_participants_min = new Integer(req.getParameter("party_participants_min").trim());
				} catch (NumberFormatException e) {
					party_participants_min = 0;
					errorMsgs.add("請填最低人數");
				}
				
				
				  java.sql.Timestamp Party_start_time = null;
				    try {
				    	System.out.println(req.getParameter("party_start_time").trim());
				    	Party_start_time = java.sql.Timestamp.valueOf(req.getParameter("party_start_time").trim() + ":00");
				    } catch (IllegalArgumentException e) {
				    	Party_start_time = new java.sql.Timestamp(System.currentTimeMillis());
				     errorMsgs.add("請輸入日期!");
				    }
				    
				    java.sql.Timestamp Party_end_time = null;
				    try {
				    	Party_end_time = java.sql.Timestamp.valueOf(req.getParameter("party_end_time").trim() + ":00");
				    } catch (IllegalArgumentException e) {
				    	Party_end_time = new java.sql.Timestamp(System.currentTimeMillis());
				    	errorMsgs.add("請輸入日期!");
				    }
				    
				    String party_remarks = req.getParameter("party_remarks").trim();
					if (party_remarks == null || party_remarks.trim().length() == 0) {
						errorMsgs.add("請勿空白");
					}
				    
				    
				
//				Date date = new Date();
//				Timestamp Party_start_time = new Timestamp(date.getTime());
//				Timestamp Party_end_time = new Timestamp(date.getTime());
				PartyVO partyVO = new PartyVO();
				partyVO.setParty_title(party_title);
		        partyVO.setMember_id(member_id);
		        partyVO.setShop_id(shop_id);	
				partyVO.setParty_start_time(Party_start_time);
				partyVO.setParty_end_time(Party_end_time);
				partyVO.setParty_intro(party_intro);
				partyVO.setParty_participants_max(party_participants_max);
				partyVO.setParty_participants_min(party_participants_min);
				partyVO.setParty_remarks(party_remarks);
//		partyVO.setMember_id(member_id);
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("partyVO", partyVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/party/addparty.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/*************************** 2.開始新增資料 ****************************************/
				PartyService partySvc = new PartyService();
				partyVO = partySvc.addParty(party_title, Party_start_time, Party_end_time, party_intro,
						party_participants_max, party_participants_min, party_remarks, member_id, shop_id);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ************/
				String url = "/party/listMYParty.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/party/addparty.jsp");
				failureView.forward(req, res);

			}
		}
		
		
		
		
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer party_id = new Integer(req.getParameter("party_id"));
				
				/***************************2.開始查詢資料****************************************/
				PartyService partySvc = new PartyService();
				PartyVO partyVO = partySvc.getOneParty(party_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("partyVO", partyVO);         // 資料庫取出的empVO物件,存入req
				String url = "/party/update_party_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/party/listAllParty.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer party_id = new Integer(req.getParameter("party_id").trim());
				
				String party_title = req.getParameter("party_title").trim();   
				if (party_title == null || party_title.trim().length() == 0) {
					errorMsgs.add("標題請勿空白");
				}
				String party_intro = req.getParameter("party_intro").trim();    
//				if (party_intro == null || party_intro.trim().length() == 0) {
//					errorMsgs.add("內容請勿空白");
//				}
				
				Integer member_id = null;
				try {
					member_id = new Integer(req.getParameter("member_id"));
					System.out.println(member_id);
				} catch (NumberFormatException e) {
					errorMsgs.add("請檢查是否已登入");
				}
				
				
				
				Integer shop_id = null;
				try {
					shop_id = new Integer(req.getParameter("shop_id"));
					System.out.println(shop_id);
				} catch (NumberFormatException e) {
					errorMsgs.add("查無此餐廳");
				}
				
				
				Integer party_participants_max = null;
				try {
					party_participants_max = new Integer(req.getParameter("party_participants_max").trim());
				} catch (NumberFormatException e) {
					party_participants_max = 0;
					errorMsgs.add("請填最高人數");
				}
				Integer party_participants_min = null;
				try {
					party_participants_min = new Integer(req.getParameter("party_participants_min").trim());
				} catch (NumberFormatException e) {
					party_participants_min = 0;
					errorMsgs.add("請填最低人數");
				}
				
				
				 java.sql.Timestamp Party_start_time = null;
				    try {
				    	Party_start_time = java.sql.Timestamp.valueOf(req.getParameter("party_start_time").trim() + ":00");
				    } catch (IllegalArgumentException e) {
				     errorMsgs.add("請輸入日期!");
				    }
				    
				    java.sql.Timestamp Party_end_time = null;
				    try {
				    	Party_end_time = java.sql.Timestamp.valueOf(req.getParameter("party_end_time").trim() + ":00");
				    } catch (IllegalArgumentException e) {
				    	Party_end_time = new java.sql.Timestamp(System.currentTimeMillis());
				    	errorMsgs.add("請輸入日期!");
				    }
				    
				    String party_remarks = req.getParameter("party_remarks").trim();
					if (party_remarks == null || party_remarks.trim().length() == 0) {
						errorMsgs.add("請勿空白");
					}
				    
				

				PartyVO partyVO = new PartyVO();


				partyVO.setParty_id(party_id);
				partyVO.setParty_title(party_title);
				partyVO.setMember_id(member_id);
				partyVO.setShop_id(shop_id);
				partyVO.setParty_start_time(Party_start_time);
				partyVO.setParty_end_time(Party_end_time);
				partyVO.setParty_intro(party_intro);
				partyVO.setParty_participants_max(party_participants_max);
				partyVO.setParty_remarks(party_remarks);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("partyVO", partyVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/party/update_party_input.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始修改資料 *****************************************/
				PartyService partySvc = new PartyService();
				partyVO = partySvc.updateParty(party_id, party_title, Party_start_time, Party_end_time, party_intro,
						party_participants_max, party_participants_min, party_remarks, member_id, shop_id);
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("partyVO", partyVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/party/listOneParty.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
RequestDispatcher failureView = req.getRequestDispatcher("/party/update_party_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("join".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer party_id = new Integer(req.getParameter("party_id").trim());
				Integer member_id = new Integer(req.getParameter("member_id").trim());

				/*************************** 2.開始查詢資料 *****************************************/
				PartyParticipantsService partySvc = new PartyParticipantsService();
				PartyParticipantsVO PartyParticipantsVO = partySvc.addPartyParticipants(member_id, party_id);
				// Send the use back to the form, if there were errors

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("PartyParticipantsVO", PartyParticipantsVO); // 資料庫取出的partyVO物件,存入req
				String url = "/party/myparty.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/party/partyNew.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer party_id = new Integer(req.getParameter("party_id"));
				
				/***************************2.開始刪除資料***************************************/
				PartyService partySvc = new PartyService();
				partySvc.deleteParty(party_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/party/listAllParty.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/party/listAllParty.jsp");
				failureView.forward(req, res);
			}
		}
	}
	
	
	
}
