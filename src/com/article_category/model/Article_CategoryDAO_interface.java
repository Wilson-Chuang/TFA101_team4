package com.article_category.model;

import java.util.*;

public interface Article_CategoryDAO_interface {
	
	public void insert(Article_CategoryVO article_categoryVO);

	public void update(Article_CategoryVO article_categoryVO);

	public void delete(Integer article_category_no);

	public Article_CategoryVO findByPrimaryKey(Integer article_category_no);

	public List<Article_CategoryVO> getAll();

}
