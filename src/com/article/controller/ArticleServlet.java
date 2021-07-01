package com.article.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.article.model.ArticleService;
import com.article.model.ArticleVO;
import com.vote.model.VoteVO;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Tuple;


import java.io.IOException;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


@MultipartConfig(fileSizeThreshold = 1024 * 1024 *10, maxFileSize = -1L, maxRequestSize = -1L)

public class ArticleServlet extends HttpServlet {
	
	static VoteVO votevo = new VoteVO();
	static JedisPool JEDIS_POOL = new JedisPool("localhost", 6379);
	final static String AUTHOR_ID = "author:id:incr";
	final static String AUTHOR_PREFIX = "author:";
	final static String AUTHOR_QUEUE = "author:queue";
	final static String AUTHOR_VOTE = "author:vote:";
	List<Long> authors = new ArrayList<>();
	static List voteListfoward = new ArrayList();
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		
        if ("insert".equals(action)) { // 來自addAtricle.jsp的請求  
			
			List<String> article_title_errorMsgs = new LinkedList<String>();
			req.setAttribute("article_title_errorMsgs", article_title_errorMsgs);
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			


			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String article_title = req.getParameter("article_title").trim();
				if (article_title == null || article_title.trim().length() == 0) {
					article_title_errorMsgs.add("* 務必填入文章標題");
					}
			
				
				String article_content = req.getParameter("article_content").trim();
				
				
				Timestamp article_create_time = new java.sql.Timestamp(new java.util.Date().getTime());
			
				Integer article_collection = 0;//收藏數
				
				Integer article_verify_status = 0; //0:未審核 1:審核中 
				
				Integer article_status = 0;//0:下架 1:上架 
				
				
				//圖片
				Part file = req.getPart("article_img");
					
			    String imageFileName = file.getSubmittedFileName();
			
			    String uploadPath = "C:/Users/Tibame_T14/Desktop/upload/"+imageFileName;
			    		     
			    FileOutputStream fos = new FileOutputStream(uploadPath);
			    
			    InputStream is = file.getInputStream();     
			     
			    byte[] data = new byte[is.available()];
			    
			    is.read(data);
			    
			    fos.write(data);
			    
			    fos.close();    		  
				
			    //圖片名 
			    String article_img_name = imageFileName.trim();
			    Integer member_no = 1;

//				Integer shop_no = new Integer(req.getParameter("shop_no").trim());
			    
			    Integer shop_no = 2;
			    
				Integer category_no = new Integer(req.getParameter("category_no").trim());
			
				ArticleVO articleVO = new ArticleVO();
				articleVO.setArticle_title(article_title);
				articleVO.setArticle_content(article_content);
				articleVO.setArticle_create_time(article_create_time);
				articleVO.setArticle_collection(article_collection);
				articleVO.setArticle_verify_status(article_verify_status);
				articleVO.setArticle_status(article_status);
				articleVO.setArticle_img(data);
				articleVO.setArticle_img_name(article_img_name);
				articleVO.setMember_no(member_no);
				articleVO.setShop_no(shop_no);;
				articleVO.setCategory_no(category_no);


				if (!errorMsgs.isEmpty()) {
					req.setAttribute("articleVO", articleVO); 
					RequestDispatcher failureView = req.getRequestDispatcher("/article/addArticle.jsp");
					failureView.forward(req, res);
					return;
				}
				
				if (!article_title_errorMsgs.isEmpty()) {
					req.setAttribute("articleVO", articleVO); 
					RequestDispatcher failureView = req.getRequestDispatcher("/article/addArticle.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				
				ArticleService articleSvc = new ArticleService();
				articleVO = articleSvc.addArticle(article_title, article_content, article_create_time, 
						article_collection,article_verify_status,article_status, data,article_img_name,member_no,shop_no,category_no);
				Set<ArticleVO> set = articleSvc.getArticle_ByMember_no(member_no);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				
				req.setAttribute("set", set);
				String url = "/article/listArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/article/addArticle.jsp");
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
				Integer article_no = new Integer(req.getParameter("article_no"));
				/***************************2.開始查詢資料****************************************/
				ArticleService articleSvc = new ArticleService();
				ArticleVO articleVO = articleSvc.getOneArticle(article_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("articleVO", articleVO);         // 資料庫取出的empVO物件,存入req
				String url = "/article/updateArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/article/listArticle.jsp");
				failureView.forward(req, res);
			}
		}
        
        
        
        if ("update".equals(action)) { 
			
			List<String> article_title_errorMsgs = new LinkedList<String>();
			req.setAttribute("article_title_errorMsgs", article_title_errorMsgs);
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			


			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				Integer article_no = new Integer(req.getParameter("article_no").trim());
				String article_title = req.getParameter("article_title").trim();
				if (article_title == null || article_title.trim().length() == 0) {
					article_title_errorMsgs.add("* 務必填入文章標題");
					}
			
				
				String article_content = req.getParameter("article_content").trim();
				
				Timestamp article_create_time = new java.sql.Timestamp(new java.util.Date().getTime());
			
				Integer article_collection = 0;//收藏數
				
				Integer article_verify_status = 0; //0:未審核 1:審核中 
				
				Integer article_status = 0;//0:下架 1:上架 
				
				ArticleService articleSvc = new ArticleService();
				
				ArticleVO articleVO2 = articleSvc.getOneArticle(article_no);
				
				String filename = articleVO2.getArticle_img_name();
	
				
				byte[] data = null;
				
				if(!((req.getPart("article_img")).getSize()==0)) {
					//這邊如果有選擇檔案就會進入一般上傳,"filename"會被取代
					 Part file = req.getPart("article_img");
						
				     filename = file.getSubmittedFileName();		   
	    
				     String uploadPath = "C:/Users/Tibame_T14/Desktop/upload/"+filename;	     
				     
				     FileOutputStream fos = new FileOutputStream(uploadPath);
				     InputStream is = file.getInputStream();     
				     
				     data = new byte[is.available()];
				     is.read(data);
				     fos.write(data);
				     fos.close();    	
				}
				
			    
			    Integer member_no = 1;

//				Integer shop_no = new Integer(req.getParameter("shop_no").trim());
			    
			    Integer shop_no = 2;
			    
				Integer category_no = new Integer(req.getParameter("category_no").trim());
			
				ArticleVO articleVO = new ArticleVO();
				articleVO.setArticle_no(article_no);
				articleVO.setArticle_title(article_title);
				articleVO.setArticle_content(article_content);
				articleVO.setArticle_create_time(article_create_time);
				articleVO.setArticle_collection(article_collection);
				articleVO.setArticle_verify_status(article_verify_status);
				articleVO.setArticle_status(article_status);
				articleVO.setArticle_img(data);
				articleVO.setArticle_img_name(filename);
				articleVO.setMember_no(member_no);
				articleVO.setShop_no(shop_no);;
				articleVO.setCategory_no(category_no);


				if (!errorMsgs.isEmpty()) {
					req.setAttribute("articleVO", articleVO); 
					RequestDispatcher failureView = req.getRequestDispatcher("/article/addArticle.jsp");
					failureView.forward(req, res);
					return;
				}
				
				if (!article_title_errorMsgs.isEmpty()) {
					req.setAttribute("articleVO", articleVO); 
					RequestDispatcher failureView = req.getRequestDispatcher("/article/addArticle.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
			
				
				articleVO = articleSvc.updateArticle(article_no,article_title, article_content, article_create_time, 
						article_collection,article_verify_status,article_status, data,filename,member_no,shop_no,category_no);
				Set<ArticleVO> set = articleSvc.getArticle_ByMember_no(member_no);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				
				req.setAttribute("set", set);
				String url = "/article/listArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
		
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/article/addArticle.jsp");
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
				Integer article_no = new Integer(req.getParameter("article_no"));
//				Integer member_no = new Integer(req.getParameter("member_no"));
				/***************************2.開始刪除資料***************************************/
				ArticleService articleSvc = new ArticleService();
				articleSvc.deleteArticle(article_no);
				Set<ArticleVO> set = articleSvc.getArticle_ByMember_no(1);//之後放session member_no
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/	
				req.setAttribute("set", set);
				String url = "/article/listArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/article/listArticle.jsp");
				failureView.forward(req, res);
			}
		}
        
        
        if ("delete2".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer article_no = new Integer(req.getParameter("article_no"));
				
				/***************************2.開始刪除資料***************************************/
				ArticleService articleSvc = new ArticleService();
				articleSvc.deleteArticle(article_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/article/listArticle_back_end.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/article/listArticle_back_end.jsp");
				failureView.forward(req, res);
			}
		}
        
        
        if ("update_verify_status".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer article_no = new Integer(req.getParameter("article_no"));
				
				/***************************2.開始刪除資料***************************************/
				ArticleService articleSvc = new ArticleService();
				articleSvc.update_verify_statusArticle(article_no);
				Set<ArticleVO> set = articleSvc.getArticle_ByMember_no(1);//之後放session member_no
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/		
				req.setAttribute("set", set);
				String url = "/article/listArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("更新資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/article/listArticle.jsp");
				failureView.forward(req, res);
			}
		}
        
        

        if ("cancel_verify_status".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer article_no = new Integer(req.getParameter("article_no"));
				
				/***************************2.開始刪除資料***************************************/
				ArticleService articleSvc = new ArticleService();
				articleSvc.update_verify_statusArticle2(article_no);
				Set<ArticleVO> set = articleSvc.getArticle_ByMember_no(1);//之後放session member_no
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/		
				req.setAttribute("set", set);
				String url = "/article/listArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("更新資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/article/listArticle.jsp");
				failureView.forward(req, res);
			}
		}
        
        if ("update_status".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer article_no = new Integer(req.getParameter("article_no"));
				
				/***************************2.開始刪除資料***************************************/
				ArticleService articleSvc = new ArticleService();
				articleSvc.update_statusArticle(article_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/article/listArticle_back_end.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("更新資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/article/listArticle_back_end.jsp");
				failureView.forward(req, res);
			}
		}
        
        

        if ("cancel_status".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer article_no = new Integer(req.getParameter("article_no"));
				
				/***************************2.開始刪除資料***************************************/
				ArticleService articleSvc = new ArticleService();
				articleSvc.update_statusArticle2(article_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/article/listArticle_back_end.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("更新資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/article/listArticle_back_end.jsp");
				failureView.forward(req, res);
			}
		}
        
        
        if ("view".equals(action)) { // 來自listProdCatg.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer article_no = new Integer(req.getParameter("article_no"));
			
				/***************************2.開始查詢資料****************************************/
				ArticleService articleSvc = new ArticleService();
				ArticleVO articleVO = articleSvc.getOneArticle(article_no);

				/***************************3.查詢完成,準備轉交(Send the Success view)************/
		
				req.setAttribute("articleVO", articleVO);

				String url = "/article/viewArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/article/listArticle.jsp");
				failureView.forward(req, res);
			}
		}
		
        
        
        if ("view_back_end".equals(action)) { // 來自listProdCatg.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer article_no = new Integer(req.getParameter("article_no"));
			
				/***************************2.開始查詢資料****************************************/
				ArticleService articleSvc = new ArticleService();
				ArticleVO articleVO = articleSvc.getOneArticle(article_no);

				/***************************3.查詢完成,準備轉交(Send the Success view)************/
		
				req.setAttribute("articleVO", articleVO);

				String url = "/article/viewArticle_back_end.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/article/listArticle_back_end.jsp");
				failureView.forward(req, res);
			}
		}
        
        if ("post".equals(action)) { // 來自listProdCatg.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer member_no = new Integer(req.getParameter("member_no"));
				
				/***************************2.開始查詢資料****************************************/
				ArticleService articleSvc = new ArticleService();
						
				Set<ArticleVO> set = articleSvc.getArticle_ByMember_no(member_no);

				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("set", set);         // 資料庫取出的empVO物件,存入req
				
				String url = "/article/listArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/article/listArticle.jsp");
				failureView.forward(req, res);
			}
		}
        
        
        if ("see_article".equals(action)) { // 來自listProdCatg.jsp的請求
 		
 			List<String> errorMsgs = new LinkedList<String>();
 			// Store this set in the request scope, in case we need to
 			// send the ErrorPage view.
 			req.setAttribute("errorMsgs", errorMsgs);
 			
 			try {
 				/***************************1.接收請求參數****************************************/
 				Integer article_no = new Integer(req.getParameter("article_no"));
 			
 				/***************************2.開始查詢資料****************************************/
 				ArticleService articleSvc = new ArticleService();
 				ArticleVO articleVO = articleSvc.getOneArticle(article_no);

 				/***************************3.查詢完成,準備轉交(Send the Success view)************/
 		
 				req.setAttribute("articleVO", articleVO);

 				String url = "/article/article_content.jsp";
 				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
 				successView.forward(req, res);

 				/***************************其他可能的錯誤處理**********************************/
 			} catch (Exception e) {
 				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
 				RequestDispatcher failureView = req
 						.getRequestDispatcher("/article/listArticle.jsp");
 				failureView.forward(req, res);
 			}
 		}
        
        
        if ("scraping".equals(action)) { // 來自listProdCatg.jsp的請求
        	
 			List<String> errorMsgs = new LinkedList<String>();
 			// Store this set in the request scope, in case we need to
 			// send the ErrorPage view.
 			req.setAttribute("errorMsgs", errorMsgs);
 			
 			try {
 				/***************************1.接收請求參數****************************************/
 				String scraping_url = new String(req.getParameter("scraping_url"));
 				String article_title = "文章標題";
				
				Timestamp article_create_time = new java.sql.Timestamp(new java.util.Date().getTime());
			
				Integer article_collection = 0;//收藏數
				
				Integer article_verify_status = 0; //0:未審核 1:審核中 
				
				Integer article_status = 0;//0:下架 1:上架 
				
				String article_img_name = "preview.png";
				
				Integer member_no = 1;
				
				Integer shop_no = 2;
				
				Integer category_no = 4;
 				/***************************2.開始查詢資料****************************************/
				
				String content = null;
				
				Elements temp = null;
				
 				try {
 					Document doc = Jsoup.connect(scraping_url).get();
 					
 					if(doc.select("div.article-body")==null) {
 						temp = doc.select("div.article-content");
 					}else{
 						temp = doc.select("div.article-body");
 					};
 					
 				    for (Element script : doc.getElementsByTag("script")) { //除去所有 script
 				        script.remove();
 				    }
 				    
 				   	
 				   	
 					content = temp.toString();
 									
 				
 				
 				} catch (IOException e) {
 					// TODO Auto-generated catch block
 					e.printStackTrace();
 				}
 				
 				
 				
 				ArticleVO articleVO = new ArticleVO();
 				
				articleVO.setArticle_title(article_title);
				articleVO.setArticle_content(content);
				articleVO.setArticle_create_time(article_create_time);
				articleVO.setArticle_collection(article_collection);
				articleVO.setArticle_verify_status(article_verify_status);
				articleVO.setArticle_status(article_status);
				articleVO.setArticle_img(null);
				articleVO.setArticle_img_name(article_img_name);
				articleVO.setMember_no(member_no);
				articleVO.setShop_no(shop_no);;
				articleVO.setCategory_no(category_no);

 				/***************************3.查詢完成,準備轉交(Send the Success view)************/

				ArticleService articleSvc = new ArticleService();
				
				articleVO = articleSvc.addArticle2(article_title, content, article_create_time, 
						article_collection,article_verify_status,article_status, null,article_img_name,member_no,shop_no,category_no);
 			
				Integer article_no = articleVO.getArticle_no();
				
				ArticleVO articleVO2 = articleSvc.getOneArticle(article_no);
				
				req.setAttribute("articleVO", articleVO2);         // 資料庫取出的empVO物件,存入req
				String url = "/article/updateArticle.jsp";
				
// 				String url = "/article/listArticle.jsp";
 				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
 				successView.forward(req, res);

 				/***************************其他可能的錯誤處理**********************************/
 			} catch (Exception e) {
 				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
 				RequestDispatcher failureView = req
 						.getRequestDispatcher("/article/listArticle.jsp");
 				failureView.forward(req, res);
 			}
 		}
        
        if ("vote".equals(action)) {
               	
        
        	
        	Long author_no = new Long(req.getParameter("author_no"));
 	
        	vote(author_no,213L); //記得改用登入會員編號
        	
        	rank();      
        	     	
        	Long addResult = votevo.getAddResult();
    	
        	int author_1_score = (int)votevo.getAuthor_1_score();
        	int author_2_score = (int)votevo.getAuthor_2_score();
        	int author_3_score = (int)votevo.getAuthor_3_score();
        	int author_4_score = (int)votevo.getAuthor_4_score();
        	int author_5_score = (int)votevo.getAuthor_5_score();
        	int author_6_score = (int) votevo.getAuthor_6_score();
        	int author_8_score = (int) votevo.getAuthor_8_score();
        	int author_9_score = (int) votevo.getAuthor_9_score();
        	int author_10_score = (int) votevo.getAuthor_10_score();
        	int author_11_score = (int) votevo.getAuthor_11_score();
        	int author_12_score = (int) votevo.getAuthor_12_score();
        	int author_13_score = (int) votevo.getAuthor_13_score();
        	

//        	--------------------------------------------
        	req.setAttribute("addResult", addResult);
        	req.setAttribute("voteList", voteListfoward);
        	req.setAttribute("author_1_score", author_1_score);
        	req.setAttribute("author_2_score", author_2_score);
        	req.setAttribute("author_3_score", author_3_score);
        	req.setAttribute("author_4_score", author_4_score);
        	req.setAttribute("author_5_score", author_5_score);
        	req.setAttribute("author_6_score", author_6_score);
        	req.setAttribute("author_8_score", author_8_score);
        	req.setAttribute("author_9_score", author_9_score);
        	req.setAttribute("author_10_score", author_10_score);
        	req.setAttribute("author_11_score", author_11_score);
        	req.setAttribute("author_12_score", author_12_score);
        	req.setAttribute("author_13_score", author_13_score);
        	
        	
        	
        	String url = "/article/article_vote_result.jsp";
        	String url2 = "/article/article_vote_fail.jsp";
			
        	if(addResult == 0) {
	        	RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
        	}else {
	        	RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
        	}
        }
        
	}
	
	
	public static void vote(Long authorId, Long userId) {
	    Jedis resource = JEDIS_POOL.getResource();
	    
	 
	    try {
	        //檢查當前使用者是否已經投過票(使用set)
	        Long addResult = resource.sadd(AUTHOR_VOTE + authorId, userId.toString());
	        
	        
	        votevo.setAddResult(addResult);
	        
	        if (addResult == 0) {
	            System.out.println("此使用者已為此作家投過票，請勿重複投票！");
	            return;
	        }
	        
	        
	        resource.zincrby(AUTHOR_QUEUE, 1D, AUTHOR_PREFIX + authorId);
	        System.out.println(String.format("投票成功，會員：【%s】,投給作家：【%s】", userId, authorId));
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        resource.close();
	    }
	}
	
	//獲取排行榜資訊
	public static void rank() {
	    Jedis resource = JEDIS_POOL.getResource();
	    List voteList = new ArrayList();
	    try {
	        //獲取排行榜
	        Set<Tuple> tuples = resource.zrevrangeWithScores(AUTHOR_QUEUE, 0L, -1L);
	        
//	        for (Tuple tuple : tuples) {
//	            System.out.println("作家編號：" + tuple.getElement() + ",投票數：" + tuple.getScore());
//	        	a.add(tuple.getScore());        	
//	        }
//	        Set<String> zrange = resource.zrange("author:queue", 0, -1);
//	        System.out.println("zrange = " + zrange);
	        
	        Double author_1_score = resource.zscore("author:queue", "author:1");
	        Double author_2_score = resource.zscore("author:queue", "author:2");
	        Double author_3_score = resource.zscore("author:queue", "author:3");
	        Double author_4_score = resource.zscore("author:queue", "author:4");
	        Double author_5_score = resource.zscore("author:queue", "author:5");
	        Double author_6_score = resource.zscore("author:queue", "author:6");
	        Double author_8_score = resource.zscore("author:queue", "author:8");
	        Double author_9_score = resource.zscore("author:queue", "author:9");
	        Double author_10_score = resource.zscore("author:queue", "author:10");
	        Double author_11_score = resource.zscore("author:queue", "author:11");
	        Double author_12_score = resource.zscore("author:queue", "author:12");
	        Double author_13_score = resource.zscore("author:queue", "author:13");
//	        System.out.println("zscore = " + author_1_score);
	        votevo.setAuthor_1_score(author_1_score);
	        votevo.setAuthor_2_score(author_2_score);
	        votevo.setAuthor_3_score(author_3_score);
	        votevo.setAuthor_4_score(author_4_score);
	        votevo.setAuthor_5_score(author_5_score);
	        votevo.setAuthor_6_score(author_6_score);
	        votevo.setAuthor_8_score(author_8_score);
	        votevo.setAuthor_9_score(author_9_score);
	        votevo.setAuthor_10_score(author_10_score);
	        votevo.setAuthor_11_score(author_11_score);
	        votevo.setAuthor_12_score(author_12_score);
	        votevo.setAuthor_13_score(author_13_score);
	        
	        for (Tuple tuple : tuples) {
	        	tuple.getScore();
	        	tuple.getElement();
//	        	System.out.println(resource.hget(tuple.getElement(),"name"));
	        	resource.hget(tuple.getElement(),"img_name");
	        	
//	        	voteList = resource.hmget(tuple.getElement(),"name","img_name","id");
	        	voteList.add(resource.hmget(tuple.getElement(),"name","img_name","id"));
	        }

	        voteListfoward = voteList;
	        
//	        System.out.println(voteList);
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        resource.close();
	    }
	}
}
