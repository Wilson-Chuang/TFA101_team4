package com.forum_reply_like.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.Util;

public class ForumReplyLikeJDBCDAO implements ForumReplyLikeDAO {

	public static final String INSERT_STMT = "INSERT INTO FORUM_REPLY_LIKE(FORUM_REPLY_ID, MEMBER_ID) VALUES(?, ?)";
	public static final String UPDATE_STMT = "UPDATE FORUM_REPLY_LIKE SET FORUM_REPLY_ID = ?, MEMBER_ID = ? WHERE FORUM_REPLY_LIKE_ID = ?";
	public static final String DELETE_STMT = "DELETE FROM FORUM_REPLY_LIKE WHERE FORUM_REPLY_LIKE_ID = ?";
	public static final String FIND_BY_PK = "SELECT * FROM FORUM_REPLY_LIKE WHERE FORUM_REPLY_LIKE_ID = ?";
	public static final String GET_ALL = "SELECT * FROM FORUM_REPLY_LIKE";
	public static final String COUNT_BY_FORUM_REPLY_ID = "SELECT COUNT(*) FROM FORUM_REPLY_LIKE WHERE FORUM_REPLY_ID = ?";

	static {
		try {
			Class.forName(Util.DRIVER);
			System.out.println("載入驅動");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void add(ForumReplyLikeVO forumReplyLike) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, forumReplyLike.getForum_reply_id());
			pstmt.setInt(2, forumReplyLike.getMember_id());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
	}

	@Override
	public void update(ForumReplyLikeVO forumReplyLike) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setInt(1, forumReplyLike.getForum_reply_id());
			pstmt.setInt(2, forumReplyLike.getMember_id());
			pstmt.setInt(3, forumReplyLike.getForum_reply_like_id());

			pstmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
	}

	@Override
	public void delete(Integer forum_reply_like_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setInt(1, forum_reply_like_id);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
	}

	@Override
	public ForumReplyLikeVO findByPK(Integer forum_reply_like_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ForumReplyLikeVO forumReplyLike = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			
			pstmt.setInt(1, forum_reply_like_id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				forumReplyLike = new ForumReplyLikeVO();
				forumReplyLike.setForum_reply_id(rs.getInt("FORUM_REPLY_ID"));
				forumReplyLike.setMember_id(rs.getInt("MEMBER_ID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
		return forumReplyLike;
	}

	@Override
	public List<ForumReplyLikeVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ForumReplyLikeVO> likelist = new ArrayList();
		ForumReplyLikeVO forumReplyLike = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				forumReplyLike = new ForumReplyLikeVO();
				forumReplyLike.setForum_reply_like_id(rs.getInt("FORUM_REPLY_LIKE_ID"));
				forumReplyLike.setForum_reply_id(rs.getInt("FORUM_REPLY_ID"));
				forumReplyLike.setMember_id(rs.getInt("MEMBER_ID"));
				
				likelist.add(forumReplyLike);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
		
		return likelist;
	}

	@Override
	public Integer countByForumRelyID(Integer forum_reply_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Integer count = 0;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(COUNT_BY_FORUM_REPLY_ID);
			
			pstmt.setInt(1, forum_reply_id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

}
