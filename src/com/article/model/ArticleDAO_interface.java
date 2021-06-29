package com.article.model;

import java.util.*;

public interface ArticleDAO_interface {
		  public void insert(ArticleVO articleVO);
		  public void insert2(ArticleVO articleVO);
		  public void update(ArticleVO articleVO);
		  public void delete(Integer article_no);
		  public void update_verify_status(Integer article_no);
		  public void update_verify_status2(Integer article_no);
		  public void update_status(Integer article_no);
		  public void update_status2(Integer article_no);
		  public ArticleVO findByPrimaryKey(Integer article_no);
		  public List<ArticleVO> getAll();
		  public Set<ArticleVO> getArticle_ByMember_no(Integer member_no);
}
