package com.forum_post_like.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.Util;

public class ForumPostLikeJDBCDAO implements ForumPostLikeDAO {

	public static final String INSERT_STMT = "INSERT INTO FORUM_POST_LIKE(FORUM_POST_ID, MEMBER_ID) VALUES(?, ?)";
	public static final String UPDATE_STMT = "UPDATE FORUM_POST_LIKE SET FORUM_POST_ID = ?, MEMBER_ID = ? WHERE FORUM_POST_LIKE_ID = ?";
	public static final String DELETE_STMT = "DELETE FROM FORUM_POST_LIKE WHERE FORUM_POST_ID = ? AND MEMBER_ID = ?";
	public static final String FIND_BY_PK = "SELECT * FROM FORUM_POST_LIKE WHERE FORUM_POST_LIKE_ID = ?";
	public static final String GET_ALL = "SELECT * FROM FORUM_POST_LIKE";
	public static final String COUNT_BY_POST_ID = "SELECT COUNT(*) FROM FORUM_POST_LIKE WHERE FORUM_POST_ID = ?";
	public static final String FIND_ONE = "SELECT * FROM FORUM_POST_LIKE WHERE FORUM_POST_ID = ? AND MEMBER_ID = ?";
	
	static {
		try {
			Class.forName(Util.DRIVER);
			System.out.println("載入驅動");
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	@Override
	public void add(ForumPostLikeVO forumPostLike) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, forumPostLike.getForum_post_id());
			pstmt.setInt(2, forumPostLike.getMember_id());

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
	public void update(ForumPostLikeVO forumPostLike) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setInt(1, forumPostLike.getForum_post_id());
			pstmt.setInt(2, forumPostLike.getMember_id());
			pstmt.setInt(3, forumPostLike.getForum_post_like_id());

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
	public void delete(Integer forum_post_id, Integer member_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setInt(1, forum_post_id);
			pstmt.setInt(2, member_id);
			
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
	public ForumPostLikeVO findByPK(Integer forum_post_like_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ForumPostLikeVO forumPostLike = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, forum_post_like_id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				forumPostLike = new ForumPostLikeVO();
				forumPostLike.setForum_post_id(rs.getInt("FORUM_POST_ID"));
				forumPostLike.setMember_id(rs.getInt("MEMBER_ID"));
			}
			
		} catch (SQLException se) {
			se.printStackTrace();
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
		return forumPostLike;
	}

	@Override
	public List<ForumPostLikeVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ForumPostLikeVO> likelist = new ArrayList();
		ForumPostLikeVO forumPostLike = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				forumPostLike = new ForumPostLikeVO();
				forumPostLike.setForum_post_id(rs.getInt("FORUM_POST_LIKE_ID"));
				forumPostLike.setForum_post_id(rs.getInt("FORUM_POST_ID"));
				forumPostLike.setMember_id(rs.getInt("MEMBER_ID"));
				
				likelist.add(forumPostLike);
			}
			
		} catch (SQLException se) {
			se.printStackTrace();
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
	public Integer countByPostID(Integer forum_post_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Integer count = 0;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(COUNT_BY_POST_ID);
			pstmt.setInt(1, forum_post_id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
		
		return count;
	}

	@Override
	public boolean findOne(Integer forum_post_id, Integer member_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FIND_ONE);
			pstmt.setInt(1, forum_post_id);
			pstmt.setInt(2, member_id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				if(rs.getInt("FORUM_POST_ID") == forum_post_id && rs.getInt("MEMBER_ID") == member_id) {
					return true;
				}
				
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return false;
	}

}
