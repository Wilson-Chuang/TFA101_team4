package com.forum_reply_like.model;

import java.util.List;

public interface ForumReplyLikeDAO {
	public void add(ForumReplyLikeVO forumReplyLike);
	public void update(ForumReplyLikeVO forumReplyLike);
	public void delete(Integer forum_reply_like_id);
	public ForumReplyLikeVO findByPK(Integer forum_reply_like_id);
	public List<ForumReplyLikeVO> getAll();
	public Integer countByForumRelyID(Integer forum_reply_id);
}
