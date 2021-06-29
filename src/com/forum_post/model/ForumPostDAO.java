package com.forum_post.model;

import java.util.List;

import com.forum_reply.model.ForumReplyVO;

public interface ForumPostDAO {
	public void add(ForumPostVO forumPost);
	public void update(ForumPostVO forumPost);
//	public void updateStatus(ForumPostVO forumPost);
	public void updateStatus(Integer forum_post_status, Integer forum_post_id);
	public void updateLikePlus(Integer forum_post_id);
	public void updateLikeMinus(Integer forum_post_id);
	public void updateReplyPlus(Integer forum_post_id);
	public void updateReplyMinus(Integer forum_post_id);	
	public void delete(Integer forum_post_id);
	public ForumPostVO findByPK(Integer forum_post_id);
	public List<ForumPostVO> getAll();
}
