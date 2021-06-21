package com.search.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.search.model.SearchService;
import com.search.model.SearchVO;
import com.shop.model.ShopService;
import com.shop.model.ShopVO;

public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ShopService shopSvc;
	private SearchService searchSvc;
	
	@Override
	public void init() throws ServletException {
		shopSvc = new ShopService();
		searchSvc = new SearchService();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("shop_search".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String place;
				String shop;
				if (req.getParameter("place-bar") == null) {
					place = "";
				} else {
					place = req.getParameter("place-bar").trim();
				}
				if (req.getParameter("shop-keyword-bar") == null) {
					shop = "";
				} else {
					shop = req.getParameter("shop-keyword-bar").trim();
				}

				if (place == "" && shop == "") {
					res.sendRedirect(req.getContextPath());
					return;
				}
				List<ShopVO> list;
				SearchVO searchedShop;
				SearchVO searchedPlace;				
				if (place.length() > 0 && shop.length() > 0) {
					list = shopSvc.findShopBoth(shop, place);
					searchedShop = searchSvc.getOneSearch(shop);
					if (searchedShop == null) {
						searchSvc.addSearch(2, shop, 1);
					} else {
						searchSvc.updateSearch(
								searchedShop.getSearch_id(),
								searchedShop.getSearch_type(),
								searchedShop.getSearch_key(),
								searchedShop.getSearch_count()+1);
					}
					searchedPlace = searchSvc.getOneSearch(place);
					if (searchedPlace == null) {
						searchSvc.addSearch(1, place, 1);
					} else {
						searchSvc.updateSearch(
								searchedPlace.getSearch_id(),
								searchedPlace.getSearch_type(),
								searchedPlace.getSearch_key(),
								searchedPlace.getSearch_count()+1);
					}
				} else if (shop.length() > 0) {
					list = shopSvc.findShopKeyword(shop);
					searchSvc.addSearch(2, shop, 1);
				} else {
					list = shopSvc.findShopPlace(place);
					searchSvc.addSearch(1, place, 1);
				}
				if (list.size() == 0) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					System.out.println(errorMsgs);
					res.sendRedirect(req.getContextPath());
					return;
				}
				req.setAttribute("searchResult", list);
				String url = "/index.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				System.out.println(errorMsgs);
			}
		}
		if ("article_search".equals(action)) {
			String article = req.getParameter("article-keyword-bar");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		}
		if ("product_search".equals(action)) {
			String product = req.getParameter("product-keyword-bar");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		}
		if ("party_search".equals(action)) {
			String party = req.getParameter("party-keyword-bar");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		}
		if ("getPartQuery".equals(action)) {
			res.setContentType("application/json; charset=utf-8");
			try {
				/***************************1.接收請求參數****************************************/
				Double lat = Double.parseDouble(req.getParameter("lat"));
				Double lng = Double.parseDouble(req.getParameter("lng"));
				/***************************2.開始查詢資料****************************************/
				List<ShopVO> list =	shopSvc.getAllbyLatLng(lat, lng);
				JSONObject resJSON = new JSONObject();
				resJSON.put("list",list);
				resJSON.put("status", "OK");
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				PrintWriter out = res.getWriter();				 
		        out.println(resJSON);

				/***************************其他可能的錯誤處理************************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}

	}
}
