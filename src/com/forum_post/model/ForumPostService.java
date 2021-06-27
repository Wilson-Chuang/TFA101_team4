package com.forum_post.model;

import java.util.List;

public class ForumPostService {
	
	private ForumPostDAO dao;

	public ForumPostService() {
		dao = new ForumPostJDBCDAO();
	}
	
	public ForumPostVO addForumPost(Integer member_id, String forum_post_title, 
			String forum_post_content) {
		
		ForumPostVO forumPost = new ForumPostVO();
		
		forumPost.setMember_id(member_id);
		forumPost.setForum_post_title(forum_post_title);
		forumPost.setForum_post_content(forum_post_content);
		
		dao.add(forumPost);
		
		return forumPost;
	}
	
	public ForumPostVO updateForumPost(String forum_post_title, 
			String forum_post_content, Integer forum_post_id) {
		
		ForumPostVO forumPost = new ForumPostVO();
		
		forumPost.setForum_post_title(forum_post_title);
		forumPost.setForum_post_content(forum_post_content);
		forumPost.setForum_post_id(forum_post_id);
		
		dao.update(forumPost);
		
		return forumPost;
	}
	
	public void updateStatusForumPost(Integer forum_post_status, 
			Integer forum_post_id) {
		dao.updateStatus(forum_post_status, forum_post_id);	
	}
	
	public void updateLikePlus(Integer forum_post_id) {
		dao.updateLikePlus(forum_post_id);
	}
	
	public void updateLikeMinus(Integer forum_post_id) {
		dao.updateReplyMinus(forum_post_id);
	}
	
	public void updateReplyPlus(Integer forum_post_id) {
		dao.updateReplyPlus(forum_post_id);
	}
	
	public void updateReplyMinus(Integer forum_post_id) {
		dao.updateReplyMinus(forum_post_id);
	}
	
	public void delete(Integer forum_post_id) {
		dao.delete(forum_post_id);
	}
	
	public ForumPostVO getOneForumPost(Integer forum_post_id) {
		return dao.findByPK(forum_post_id);
	}
	
	public List<ForumPostVO> getAll(){
		return dao.getAll();
	}
}
