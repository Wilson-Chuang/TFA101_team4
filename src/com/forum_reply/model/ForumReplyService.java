package com.forum_reply.model;

import java.util.List;

public class ForumReplyService {
	
	private ForumReplyDAO dao;

	public ForumReplyService() {
		dao = new ForumReplyJDBCDAO();
	}
	
	public ForumReplyVO addForumReply(Integer member_id, 
			Integer forum_post_id, String forum_reply_content) {
		
		ForumReplyVO forumReply = new ForumReplyVO();
		
		forumReply.setMember_id(member_id);
		forumReply.setForum_post_id(forum_post_id);
		forumReply.setForum_reply_content(forum_reply_content);
		
		dao.add(forumReply);
		
		return forumReply;
	}
	
	public ForumReplyVO updateForumReply(String forum_reply_content, 
			Integer forum_reply_id) {
		
		ForumReplyVO forumReply = new ForumReplyVO();
		
		forumReply.setForum_reply_content(forum_reply_content);
		forumReply.setForum_reply_id(forum_reply_id);
		
		dao.update(forumReply);
		
		return forumReply;
	}
	
	public void updateStatusForumReply(Integer forum_reply_status, 
			Integer forum_reply_id) {
		
		ForumReplyVO forumReply = new ForumReplyVO();
		
		forumReply.setForum_reply_status(forum_reply_status);
		forumReply.setForum_reply_id(forum_reply_id);
		
		dao.updateStatus(forumReply);
	}
	
	public void updateLikePlus(Integer forum_reply_id) {
		dao.updateLikePlus(forum_reply_id);
	}
	
	public void updateLikeMinus(Integer forum_reply_id) {
		dao.updateLikeMinus(forum_reply_id);
	}
	
	public void deleteForumReply(Integer forum_reply_id) {
		dao.delete(forum_reply_id);
	}
	
	public ForumReplyVO getOneForumReply(Integer forum_reply_id) {
		return dao.findByPK(forum_reply_id);
	}
	
	public List<ForumReplyVO> getByFoumPostID(Integer forum_post_id){
		return dao.findByPostID(forum_post_id);
	}
	
	public List<ForumReplyVO> getAll(){
		return dao.getAll();
	}
	
	public Integer countByPostID(Integer forum_post_id) {
		return dao.countByPostID(forum_post_id);
	}
}

