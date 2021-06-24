package com.article.model;

import java.util.*;

public interface ArticleDAO_interface {
		  public void insert(ArticleVO articleVO);
		  public void update(ArticleVO articleVO);
		  public void delete(Integer article_no);
		  public ArticleVO findByPrimaryKey(Integer article_no);
		  public List<ArticleVO> getAll();
}
