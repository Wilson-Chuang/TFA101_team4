package com.manager.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.manager.model.ManagerService;
import com.manager.model.ManagerVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 10 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)

@WebServlet("/manager/GetPic.do")
public class GetPic extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("image/*");
		ServletOutputStream out = res.getOutputStream();
		
		try {
			/*************************** 1.接收請求參數 ****************************************/
			Integer manager_id = new Integer(req.getParameter("manager_id"));
			
			/*************************** 2.開始查詢資料 ****************************************/
			ManagerService managerSvc = new ManagerService();
			ManagerVO managerVO = managerSvc.getOneManager(manager_id);
			
			byte[] b = managerVO.getManager_pic();
			out.write(b);
		
		} catch (Exception e) {
			
			System.out.println(e); //給錯時錯誤印在console
			//錯誤時，顯示此圖
			InputStream is = getServletContext().getResourceAsStream("/manager/images/manager_pic1.png");
			byte[] b = new byte[is.available()];
			is.read(b);
			out.write(b);
			is.close();
		}
	}
}
