package com.article_category.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import com.article.model.ArticleVO;
import com.product.model.ProductVO;

public class Article_categoryService {

	private Article_categoryDAO_interface dao;

	public Article_categoryService() {
		dao = new Article_categoryDAO();
	}

	public Article_categoryVO addArticle_category(String article_category_name) {

		Article_categoryVO article_categoryVO = new Article_categoryVO();

		article_categoryVO.setArticle_category_name(article_category_name);
		
		dao.insert(article_categoryVO);

		return article_categoryVO;
	}

	public Article_categoryVO updateArticle_category(Integer article_category_no, String article_category_name) {

		Article_categoryVO article_categoryVO = new Article_categoryVO();
		
		article_categoryVO.setArticle_category_no(article_category_no);
		article_categoryVO.setArticle_category_name(article_category_name);
		
		
		dao.update(article_categoryVO);

		return article_categoryVO;
	}
	

	public void deleteArticle_category(Integer article_category_no) {
		dao.delete(article_category_no);
	}

	public Article_categoryVO getOneArticle_category(Integer article_category_no) {
		return dao.findByPrimaryKey(article_category_no);
	}

	public List<Article_categoryVO> getAll() {
		return dao.getAll();
	}
	
	public Set<ArticleVO> getArticlesByariticle_category_no(Integer ariticle_category_no) {
		return dao.getArticlesByarticle_category_no(ariticle_category_no);
	}
	

}
