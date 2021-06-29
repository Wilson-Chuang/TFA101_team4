package com.forum_post_like.model;

import java.util.List;

public interface ForumPostLikeDAO {
	public void add(ForumPostLikeVO forumPostLike);
	public void update(ForumPostLikeVO forumPostLike);
	public void delete(Integer forum_post_id, Integer member_id);
	public ForumPostLikeVO findByPK(Integer forum_post_like_id);
	public List<ForumPostLikeVO> getAll();
	public Integer countByPostID(Integer forum_post_id);
	public boolean findOne(Integer forum_post_id, Integer member_id);
	
}
