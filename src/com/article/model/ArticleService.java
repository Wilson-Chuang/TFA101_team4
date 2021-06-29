package com.article.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

public class ArticleService {

	private ArticleDAO_interface dao;

	public ArticleService() {
		dao = new ArticleDAO();
	}

	public ArticleVO addArticle(String article_title, String article_content, Timestamp article_create_time,
			Integer article_collection, Integer article_verify_status, Integer article_status, byte[] article_img,
			String article_img_name, Integer member_no, Integer shop_no, Integer category_no) {

		ArticleVO articleVO = new ArticleVO();

		articleVO.setArticle_title(article_title);
		articleVO.setArticle_content(article_content);
		articleVO.setArticle_create_time(new java.sql.Timestamp(new java.util.Date().getTime()));
		articleVO.setArticle_collection(article_collection);
		articleVO.setArticle_verify_status(article_verify_status);
		articleVO.setArticle_status(article_status);
		articleVO.setArticle_img(article_img);
		articleVO.setArticle_img_name(article_img_name);
		articleVO.setMember_no(member_no);
		articleVO.setShop_no(shop_no);
		articleVO.setCategory_no(category_no);
		dao.insert(articleVO);

		return articleVO;
	}
	
	
	public ArticleVO addArticle2(String article_title, String article_content, Timestamp article_create_time,
			Integer article_collection, Integer article_verify_status, Integer article_status, byte[] article_img,
			String article_img_name, Integer member_no, Integer shop_no, Integer category_no) {

		ArticleVO articleVO = new ArticleVO();

		articleVO.setArticle_title(article_title);
		articleVO.setArticle_content(article_content);
		articleVO.setArticle_create_time(new java.sql.Timestamp(new java.util.Date().getTime()));
		articleVO.setArticle_collection(article_collection);
		articleVO.setArticle_verify_status(article_verify_status);
		articleVO.setArticle_status(article_status);
		articleVO.setArticle_img(article_img);
		articleVO.setArticle_img_name(article_img_name);
		articleVO.setMember_no(member_no);
		articleVO.setShop_no(shop_no);
		articleVO.setCategory_no(category_no);
		dao.insert2(articleVO);

		return articleVO;
	}

	public ArticleVO updateArticle(Integer article_no, String article_title, String article_content, Timestamp article_create_time,
			Integer article_collection, Integer article_verify_status, Integer article_status, byte[] article_img,
			String article_img_name, Integer member_no, Integer shop_no, Integer category_no) {

		ArticleVO articleVO = new ArticleVO();
		
		articleVO.setArticle_no(article_no);
		articleVO.setArticle_title(article_title);
		articleVO.setArticle_content(article_content);
		articleVO.setArticle_create_time(new java.sql.Timestamp(new java.util.Date().getTime()));
		articleVO.setArticle_collection(article_collection);
		articleVO.setArticle_verify_status(article_verify_status);
		articleVO.setArticle_status(article_status);
		articleVO.setArticle_img(article_img);
		articleVO.setArticle_img_name(article_img_name);
		articleVO.setMember_no(member_no);
		articleVO.setShop_no(shop_no);
		articleVO.setCategory_no(category_no);
		
		dao.update(articleVO);

		return articleVO;
	}
	

	public void deleteArticle(Integer article_no) {
		dao.delete(article_no);
	}
	
	public void  update_verify_statusArticle(Integer article_no) {
		dao.update_verify_status(article_no);
	}
	
	public void  update_verify_statusArticle2(Integer article_no) {
		dao.update_verify_status2(article_no);
	}
	
	public void  update_statusArticle(Integer article_no) {
		dao.update_status(article_no);
	}
	
	public void  update_statusArticle2(Integer article_no) {
		dao.update_status2(article_no);
	}
	
	public ArticleVO getOneArticle(Integer article_no) {
		return dao.findByPrimaryKey(article_no);
	}

	public List<ArticleVO> getAll() {
		return dao.getAll();
	}
	
	public Set<ArticleVO> getArticle_ByMember_no(Integer member_no) {
		return dao.getArticle_ByMember_no(member_no);
	}
	

}