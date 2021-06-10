package com.forum_post_like.model;

import java.util.List;

public class ForumPostLikeService {
	
	private ForumPostLikeDAO dao;

	public ForumPostLikeService() {
		dao = new ForumPostLikeJDBCDAO();
	}
	
	public ForumPostLikeVO addForumPostLike(Integer forum_post_id, 
			Integer member_id) {
		
		ForumPostLikeVO forumPostLike = new ForumPostLikeVO();
		
		forumPostLike.setForum_post_id(forum_post_id);
		forumPostLike.setMember_id(member_id);
		dao.add(forumPostLike);
		
		return forumPostLike;
	}
	
	public ForumPostLikeVO updateForumPostLike(Integer forum_post_id, 
			Integer member_id, Integer forum_post_like_id) {
		
		ForumPostLikeVO forumPostLike = new ForumPostLikeVO();
		
		forumPostLike.setForum_post_id(forum_post_id);
		forumPostLike.setMember_id(member_id);
		forumPostLike.setForum_post_like_id(forum_post_like_id);
		dao.update(forumPostLike);
		
		return forumPostLike;
	}
	
	public void deleteForumPostLike(Integer forum_post_like_id) {
		dao.delete(forum_post_like_id);
	}
	
	public ForumPostLikeVO getOneForumPostLike(Integer forum_post_like_id) {
		return dao.findByPK(forum_post_like_id);
	}
	
	public List<ForumPostLikeVO> getAll() {
		return dao.getAll();
	}
	
	public Integer countByPostID(Integer forum_post_id) {
		return dao.countByPostID(forum_post_id);
	}

}
