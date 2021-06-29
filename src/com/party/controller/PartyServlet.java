package com.party.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.party.model.PartyService;
import com.party.model.PartyVO;

import oracle.sql.TIMESTAMP;

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
				req.setAttribute("partyVO", partyVO); // 資料庫取出的empVO物件,存入req
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

		if ("insert".equals(action)) { // 來自listAllEmp.jsp的請求
System.out.println("33333333");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String party_title = req.getParameter("party_title").trim();
				System.out.println(party_title);
//				if (party_title == null || party_title.trim().length() == 0) {
//					errorMsgs.add("標題請勿空白");
//					System.out.println("22222");
//				}
				String party_intro = req.getParameter("party_intro").trim();
				System.out.println(party_intro);
//				if (party_intro == null || party_intro.trim().length() == 0) {
//					errorMsgs.add("內容請勿空白");
//					System.out.println("111111");
//				}
//				Integer member_id = null;
//				try {
//					member_id = new Integer(req.getParameter("memberID"));
//				} catch (NumberFormatException e) {
//					errorMsgs.add("請檢查是否已登入");
//				}
				Date date = new Date();
				Timestamp Party_start_time = new Timestamp(date.getTime());
				System.out.println(Party_start_time);
				Timestamp Party_end_time = new Timestamp(date.getTime());
				System.out.println(Party_end_time);
//				Timestamp Party_end_time = new Timestamp(System.currentTimeMillis()); 
				Integer party_participants_max = new Integer(req.getParameter("party_participants_max").trim());
				System.out.println(party_participants_max);
				Integer party_participants_min = new Integer(req.getParameter("party_participants_min").trim());
				System.out.println(party_participants_min);

				PartyVO partyVO = new PartyVO();

				partyVO.setParty_title(party_title);
				partyVO.setParty_start_time(Party_start_time);
				partyVO.setParty_end_time(Party_end_time);
				partyVO.setParty_intro(party_intro);
				partyVO.setParty_participants_max(party_participants_max);
				partyVO.setParty_participants_min(party_participants_min);
//		partyVO.setMember_id(member_id);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("partyVO", partyVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/party/addparty.jsp");
					failureView.forward(req, res);
					System.out.println("555555");
					return;
				}
				
				/*************************** 2.開始新增資料 ****************************************/
				PartyService partySvc = new PartyService();
				partyVO = partySvc.addParty( party_title, Party_start_time, Party_end_time, party_intro,
						party_participants_max, party_participants_min);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ************/
				String url = "/party/listAllParty.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/party/addparty.jsp");
				failureView.forward(req, res);
				System.out.println("123455");

			}
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String party_title = req.getParameter("party_title").trim();
				if (party_title == null || party_title.trim().length() == 0) {
					errorMsgs.add("標題請勿空白");
				}
				String party_intro = req.getParameter("party_intro").trim();
				if (party_intro == null || party_intro.trim().length() == 0) {
					errorMsgs.add("內容請勿空白");
				}
//				Integer member_id = null;
//				try {
//					member_id = new Integer(req.getParameter("memberID"));
//				} catch (NumberFormatException e) {
//					errorMsgs.add("請檢查是否已登入");
//				}

				Date date = new Date();
				Timestamp Party_start_time = new Timestamp(date.getTime());
				Timestamp Party_end_time = new Timestamp(date.getTime());
//				Timestamp Party_end_time = new Timestamp(System.currentTimeMillis()); 
				Integer party_participants_max = new Integer(req.getParameter("participants_max").trim());
				Integer party_participants_min = new Integer(req.getParameter("participants_min").trim());
				Integer party_id = new Integer(req.getParameter("party_id").trim());

				PartyVO partyVO = new PartyVO();


				partyVO.setParty_id(party_id);
				partyVO.setParty_title(party_title);
				partyVO.setParty_start_time(Party_start_time);
				partyVO.setParty_end_time(Party_end_time);
				partyVO.setParty_intro(party_intro);
				partyVO.setParty_participants_max(party_participants_max);
				partyVO.setParty_participants_min(party_participants_min);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("partyVO", partyVO);
RequestDispatcher failureView = req.getRequestDispatcher("/party/update_party_input.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始修改資料 *****************************************/
				PartyService partySvc = new PartyService();
				partyVO = partySvc.updateParty( party_id, party_title, Party_start_time, Party_end_time, party_intro,
						party_participants_max, party_participants_min);
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
	}
}
