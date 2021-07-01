package com.article_category.model;

import java.util.*;

import com.article.model.ArticleVO;


public interface Article_categoryDAO_interface {
	
	public void insert(Article_categoryVO article_categoryVO);

	public void update(Article_categoryVO article_categoryVO);

	public void delete(Integer article_category_no);

	public Article_categoryVO findByPrimaryKey(Integer article_category_no);

	public List<Article_categoryVO> getAll();

	public Set<ArticleVO> getArticlesByarticle_category_no(Integer article_category_no);
}
