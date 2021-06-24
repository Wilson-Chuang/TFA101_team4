package com.article_favorite.model;

import java.util.*;

public interface Article_FavoriteDAO_interface {
	public void insert(Article_FavoriteVO article_favoriteVO);

	public void update(Article_FavoriteVO article_favoriteVO);

	public void delete(Integer article_favorite_no);

	public Article_FavoriteVO findByPrimaryKey(Integer article_favorite_no);

	public List<Article_FavoriteVO> getAll();
	public List<Article_FavoriteVO> getAllByMember(Integer member_id);

}
