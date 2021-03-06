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

import com.google.gson.Gson;
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
		res.setContentType("application/json; charset=utf-8");
		String action = req.getParameter("action");
		
		if ("shop_search".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String place = "";
				String shop = "";
				String route = req.getParameter("btn-route");
				String reachtime = req.getParameter("reachtime");
				Double lat = 0.0;
				Double lng = 0.0;
				if (req.getParameter("place-bar") != null) {
					place = req.getParameter("place-bar").trim();
				}
				if (req.getParameter("shop-keyword-bar") != null) {
					shop = req.getParameter("shop-keyword-bar").trim();
				}			
				if (req.getParameter("lat") != null) {			
					try {
						lat = Double.parseDouble(req.getParameter("lat").trim());
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
				}
				if (req.getParameter("lng") != null) {
					try {
						lng = Double.parseDouble(req.getParameter("lng").trim());
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}					
				}
				List<ShopVO> list = null;
				JSONObject resJSON = new JSONObject();
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
				} else if (place.length() > 0) {
					list = shopSvc.findShopPlace(place);
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
				} else {
					list = shopSvc.getAllbyLatLng(lat, lng);
				}
				
				if (list.size() == 0) {
					resJSON.put("status", "Failed");
				} else {
					resJSON.put("status", "OK");
				}
				
				if (!errorMsgs.isEmpty()) {
					System.out.println(errorMsgs);
					res.sendRedirect(req.getContextPath());
					return;
				}
				resJSON.put("list", list);
				resJSON.put("route", route);
				resJSON.put("reachtime", reachtime);
				/***************************3.????????????,????????????(Send the Success view)************/
				req.setAttribute("searchResult", resJSON);
				String url = "/search/search.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				res.sendRedirect(req.getContextPath());
				errorMsgs.add("??????????????????:" + e.getMessage());
				System.out.println(errorMsgs);
			}
		} else if ("article_search".equals(action)) {
			String article = req.getParameter("article-keyword-bar");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		} else if ("product_search".equals(action)) {
			String product = req.getParameter("product-keyword-bar");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		} else if ("party_search".equals(action)) {
			String party = req.getParameter("party-keyword-bar");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		} else if ("part_search".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {				
				/***************************1.??????????????????****************************************/
				Double lat = 0.0;
				Double lng = 0.0;
				if (req.getParameter("lat") != null) {					
					try {
						lat = Double.parseDouble(req.getParameter("lat").trim());
					} catch (NumberFormatException e) {
						e.printStackTrace();
						errorMsgs.add("???????????????????????????");
					}
				}
				if (req.getParameter("lng") != null) {
					try {
						lng = Double.parseDouble(req.getParameter("lng").trim());
					} catch (NumberFormatException e) {
						e.printStackTrace();
						errorMsgs.add("???????????????????????????");
					}					
				}
				if (lat == 0.0 && lng == 0.0) {
					res.sendRedirect(req.getContextPath());
					return;
				}
				/***************************2.??????????????????****************************************/
				List<ShopVO> list =	shopSvc.getAllbyLatLng(lat, lng);
				JSONObject resJSON = new JSONObject();
				
				if (list.size() == 0) {
					errorMsgs.add("????????????");
				}
				if (!errorMsgs.isEmpty()) {
					System.out.println(errorMsgs);
					res.sendRedirect(req.getContextPath());
					return;
				}
				resJSON.put("list",list);
				resJSON.put("status", "OK");
				/***************************3.????????????,????????????(Send the Success view)************/
				req.setAttribute("searchResult", resJSON);
				String url = "/search/search.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/***************************???????????????????????????************************************/
			} catch (Exception e) {
				res.sendRedirect(req.getContextPath());
				throw new ServletException(e);
			}
		} else if ("getPartQuery".equals(action)) {			
			try {
				/***************************1.??????????????????****************************************/
				Double lat = 0.0;
				Double lng = 0.0;
				if (req.getParameter("lat") != null) {					
					try {
						lat = Double.parseDouble(req.getParameter("lat").trim());
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
				}
				if (req.getParameter("lng") != null) {
					try {
						lng = Double.parseDouble(req.getParameter("lng").trim());
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}					
				}
				if (lat == 0.0 && lng == 0.0) {
					res.sendRedirect(req.getContextPath());
					return;
				}
				/***************************2.??????????????????****************************************/
				List<ShopVO> list =	shopSvc.getAllbyLatLng(lat, lng);
				JSONObject resJSON = new JSONObject();
				resJSON.put("list",list);
				resJSON.put("status", "OK");
				/***************************3.????????????,????????????(Send the Success view)************/
				PrintWriter out = res.getWriter();				 
		        out.println(resJSON);

				/***************************???????????????????????????************************************/
			} catch (Exception e) {
				res.sendRedirect(req.getContextPath());
				throw new ServletException(e);
			}
		} else if ("navi".equals(action)) {			
			try {
				/***************************1.??????????????????****************************************/
				String shop_tax_id = "";
				if(req.getParameter("shop_tax_id") != null) {
					shop_tax_id = req.getParameter("shop_tax_id").trim();
				}
				String route = req.getParameter("btn-route");
				String reachtime = req.getParameter("reachtime");
				/***************************2.??????????????????****************************************/
				ShopVO shopVO = shopSvc.findShop_tax_id(shop_tax_id);
				Gson gson = new Gson();
		        String list = gson.toJson(shopVO);
				JSONObject resJSON = new JSONObject();
				resJSON.put("list", list);
				resJSON.put("status", "OK");
				resJSON.put("route", route);
				resJSON.put("reachtime", reachtime);
				/***************************3.????????????,????????????(Send the Success view)************/
		        req.setAttribute("searchResult", resJSON);
				String url = "/search/navi.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				res.sendRedirect(req.getContextPath());
				throw new ServletException(e);
			}
			} else if("fromShopid".equals(action)) {
				res.setContentType("application/json; charset=utf-8");
				String tmp_shop_id = "";
				if(req.getParameter("shop_id") != null) {
					tmp_shop_id = req.getParameter("shop_id").trim();
				}
				Integer shop_id = 0;
				if(tmp_shop_id != null && tmp_shop_id.length() != 0) {
					try {
						shop_id = Integer.parseInt(tmp_shop_id);
					} catch (NumberFormatException e) {
						e.printStackTrace();
						res.sendRedirect(req.getContextPath());
					}
				}				
				ShopVO shopVO = shopSvc.getOneShop(shop_id);			
				Gson gson = new Gson();
		        String list = gson.toJson(shopVO);
				JSONObject resJSON = new JSONObject();
				resJSON.put("list", list);
				resJSON.put("status", "OK");
		        PrintWriter out = res.getWriter();				 
		        out.println(resJSON);
		} else {
			res.sendRedirect(req.getContextPath());
		}
		
	}
}
