package com.forum_reply_like.model;

import java.util.List;

public class ForumReplyLikeService {

	private ForumReplyLikeDAO dao;

	public ForumReplyLikeService() {
		dao = new ForumReplyLikeJDBCDAO();
	}

	public ForumReplyLikeVO addForumReplyLike(Integer forum_reply_id, Integer member_id) {

		ForumReplyLikeVO forumReplyLike = new ForumReplyLikeVO();

		forumReplyLike.setForum_reply_id(forum_reply_id);
		forumReplyLike.setMember_id(member_id);
		dao.add(forumReplyLike);

		return forumReplyLike;
	}

	public ForumReplyLikeVO updateForumReplyLike(Integer forum_reply_id, Integer member_id,
			Integer forum_reply_like_id) {

		ForumReplyLikeVO forumReplyLike = new ForumReplyLikeVO();

		forumReplyLike.setForum_reply_id(forum_reply_id);
		forumReplyLike.setMember_id(member_id);
		forumReplyLike.setForum_reply_like_id(forum_reply_like_id);

		dao.update(forumReplyLike);

		return forumReplyLike;
	}
	
	public void deleteForumReplyLike(Integer forum_reply_like_id) {
		dao.delete(forum_reply_like_id);
	}
	
	public ForumReplyLikeVO getOneForumReplyLike(Integer forum_reply_like_id) {
		return dao.findByPK(forum_reply_like_id);
	}
	
	public List<ForumReplyLikeVO> getAll() {
		return dao.getAll();
	}
	
	public Integer countByForumRelyID(Integer forum_reply_id) {
		return dao.countByForumRelyID(forum_reply_id);
	}

}
