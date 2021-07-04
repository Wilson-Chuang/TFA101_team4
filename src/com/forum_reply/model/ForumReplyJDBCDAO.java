package com.forum_reply.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.Util;

public class ForumReplyJDBCDAO implements ForumReplyDAO{
	
	public static final String INSERT_STMT = 
			"INSERT INTO FORUM_REPLY(MEMBER_ID, FORUM_POST_ID, FORUM_REPLY_CONTENT) VALUES(?, ?, ?)";
	public static final String UPDATE_STMT = "UPDATE FORUM_REPLY SET FORUM_REPLY_CONTENT = ? WHERE FORUM_REPLY_ID = ?";
	public static final String UPDATE_STATUS_STMT = "UPDATE FORUM_REPLY SET FORUM_REPLY_STATUS = ? WHERE FORUM_REPLY_ID = ?";
	public static final String UPDATE_LIKE_PLUS = "UPDATE FORUM_REPLY SET FORUM_REPLY_LIKE = FORUM_REPLY_LIKE + 1 WHERE FORUM_REPLY_ID = ?";
	public static final String UPDATE_LIKE_MINUS = "UPDATE FORUM_REPLY SET FORUM_REPLY_LIKE = FORUM_REPLY_LIKE - 1 WHERE FORUM_REPLY_ID = ?";
//	public static final String DELETE_STMT = "DELETE FROM FORUM_REPLY WHERE FORUM_REPLY_ID = ?";
	public static final String DELETE_STMT = "UPDATE FORUM_REPLY SET FORUM_REPLY_CONTENT = '此內容已被刪除' WHERE FORUM_REPLY_ID = ?";
	public static final String FIND_BY_PK = "SELECT * FROM FORUM_REPLY WHERE FORUM_REPLY_ID = ?";
	public static final String FIND_BY_POST_ID = "SELECT * FROM FORUM_REPLY WHERE FORUM_POST_ID = ? AND FORUM_REPLY_STATUS = 1";
	public static final String GET_ALL = "SELECT * FROM FORUM_REPLY";
	public static final String COUNT_BY_POSTID = "SELECT COUNT(*) FROM FORUM_REPLY WHERE FORUM_POST_ID = ? AND FORUM_REPLY_STATUS = 1";
	
	static {
		try {
			Class.forName(Util.DRIVER);
			System.out.println("載入驅動");
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	@Override
	public void add(ForumReplyVO forumReply) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, forumReply.getMember_id());
			pstmt.setInt(2, forumReply.getForum_post_id());
			pstmt.setString(3, forumReply.getForum_reply_content());
			
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException se) {
			se.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			
			if(con != null) {
				try {
					con.setAutoCommit(true);
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
	}

	@Override
	public void update(ForumReplyVO forumReply) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, forumReply.getForum_reply_content());
			pstmt.setInt(2, forumReply.getForum_reply_id());

			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException se) {
			se.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			
			if(con != null) {
				try {
					con.setAutoCommit(true);
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
	}

	@Override
	public void updateStatus(Integer forum_reply_status, Integer forum_reply_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(UPDATE_STATUS_STMT);
			
			if(forum_reply_status == 0) {
				pstmt.setInt(1, forum_reply_status);
				pstmt.setInt(2, forum_reply_id);
//				delete(forum_reply_id);
			}else if(forum_reply_status == 1){
				pstmt.setInt(1, forum_reply_status);
				pstmt.setInt(2, forum_reply_id);
			}
			
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException se) {
			se.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			
			if(con != null) {
				try {
					con.setAutoCommit(true);
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
		
	}

	@Override
	public void updateLikePlus(Integer forum_reply_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_LIKE_PLUS);
			
			pstmt.setInt(1, forum_reply_id);
			
			pstmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			
			if(con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}	
	}

	@Override
	public void updateLikeMinus(Integer forum_reply_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_LIKE_MINUS);
			
			pstmt.setInt(1, forum_reply_id);
			
			pstmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			
			if(con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}	
		
	}

	@Override
	public void delete(Integer forum_reply_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setInt(1, forum_reply_id);
			
			updateStatus(0, forum_reply_id);
			
			pstmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			
			if(con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}	
	}

	@Override
	public ForumReplyVO findByPK(Integer forum_reply_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ForumReplyVO forumReply = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, forum_reply_id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				forumReply = new ForumReplyVO();
				forumReply.setForum_reply_id(rs.getInt("FORUM_REPLY_ID"));
				forumReply.setMember_id(rs.getInt("MEMBER_ID"));
				forumReply.setForum_post_id(rs.getInt("FORUM_POST_ID"));
				forumReply.setForum_reply_content(rs.getString("FORUM_REPLY_CONTENT"));
				forumReply.setForum_reply_time(rs.getTimestamp("FORUM_REPLY_TIME"));
				forumReply.setForum_reply_update_time(rs.getTimestamp("FORUM_REPLY_UPDATE_TIME"));
				forumReply.setForum_reply_status(rs.getInt("FORUM_REPLY_STATUS"));
				forumReply.setForum_reply_like(rs.getInt("FORUM_REPLY_LIKE"));
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			
			if(con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
		return forumReply;
	}

	@Override
	public List<ForumReplyVO> findByPostID(Integer forum_post_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ForumReplyVO> replylist = new ArrayList();
		ForumReplyVO forumReply = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_POST_ID);
			
			pstmt.setInt(1, forum_post_id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				forumReply = new ForumReplyVO();
				forumReply.setForum_reply_id(rs.getInt("FORUM_REPLY_ID"));
				forumReply.setMember_id(rs.getInt("MEMBER_ID"));
				forumReply.setForum_post_id(rs.getInt("FORUM_POST_ID"));
				forumReply.setForum_reply_content(rs.getString("FORUM_REPLY_CONTENT"));
				forumReply.setForum_reply_time(rs.getTimestamp("FORUM_REPLY_TIME"));
				forumReply.setForum_reply_update_time(rs.getTimestamp("FORUM_REPLY_UPDATE_TIME"));
				forumReply.setForum_reply_status(rs.getInt("FORUM_REPLY_STATUS"));
				forumReply.setForum_reply_like(rs.getInt("FORUM_REPLY_LIKE"));
				
				replylist.add(forumReply);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			
			if(con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
		
		return replylist;
	}

	@Override
	public List<ForumReplyVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ForumReplyVO> replylist = new ArrayList();
		ForumReplyVO forumReply = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				forumReply = new ForumReplyVO();
				forumReply.setForum_reply_id(rs.getInt("FORUM_REPLY_ID"));
				forumReply.setMember_id(rs.getInt("MEMBER_ID"));
				forumReply.setForum_post_id(rs.getInt("FORUM_POST_ID"));
				forumReply.setForum_reply_content(rs.getString("FORUM_REPLY_CONTENT"));
				forumReply.setForum_reply_time(rs.getTimestamp("FORUM_REPLY_TIME"));
				forumReply.setForum_reply_update_time(rs.getTimestamp("FORUM_REPLY_UPDATE_TIME"));
				forumReply.setForum_reply_status(rs.getInt("FORUM_REPLY_STATUS"));
				forumReply.setForum_reply_like(rs.getInt("FORUM_REPLY_LIKE"));
				
				replylist.add(forumReply);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			
			if(con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}

		return replylist;
	}

	@Override
	public Integer countByPostID(Integer forum_post_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Integer count = 0;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(COUNT_BY_POSTID);
			
			pstmt.setInt(1, forum_post_id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				count = rs.getInt(1);
			}
			
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			
			if(con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
		return count;
	}

}
