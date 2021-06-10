package com.forum_reply.model;

import java.util.List;

public interface ForumReplyDAO {
	public void add(ForumReplyVO forumReply);
	public void update(ForumReplyVO forumReply);
	public void updateStatus(ForumReplyVO forumReply);
	public void updateLikePlus(Integer forum_reply_id);
	public void updateLikeMinus(Integer forum_reply_id);
	public void delete(Integer forum_reply_id);
	public ForumReplyVO findByPK(Integer forum_reply_id);
	public List<ForumReplyVO> findByPostID(Integer forum_post_id);
	public List<ForumReplyVO> getAll();
	public Integer countByPostID(Integer forum_post_id);
	
}
