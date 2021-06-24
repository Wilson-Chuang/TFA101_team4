package com.forum_post.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import util.Util;

public class ForumPostJNDIDAO implements ForumPostDAO {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Team4DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public static final String INSERT_STMT = 
			"INSERT INTO FORUM_POST(MEMBER_ID, FORUM_POST_TITLE, FORUM_POST_CONTENT) VALUES(?, ?, ?)";
	public static final String UPDATE_STMT = "UPDATE FORUM_POST SET FORUM_POST_TITLE = ?, FORUM_POST_CONTENT = ? WHERE FORUM_POST_ID = ?";
	public static final String UPDATE_STATUS_STMT = "UPDATE FORUM_POST SET FORUM_POST_STATUS = ? WHERE FORUM_POST_ID = ?";
	public static final String UPDATE_LIKE_PLUS = "UPDATE FORUM_POST SET FORUM_POST_LIKE = FORUM_POST_LIKE + 1 WHERE FORUM_POST_ID = ?";
	public static final String UPDATE_LIKE_MINUS = "UPDATE FORUM_POST SET FORUM_POST_LIKE = FORUM_POST_LIKE - 1 WHERE FORUM_POST_ID = ?";
	public static final String UPDATE_REPLY_PLUS = "UPDATE FORUM_POST SET FORUM_POST_REPLY_TOTAL = FORUM_POST_REPLY_TOTAL + 1 WHERE FORUM_POST_ID = ?";
	public static final String UPDATE_REPLY_MINUS = "UPDATE FORUM_POST SET FORUM_POST_REPLY_TOTAL = FORUM_POST_REPLY_TOTAL - 1 WHERE FORUM_POST_ID = ?";
//	public static final String DELETE_STMT = "DELETE FROM FORUM_POST WHERE FORUM_POST_ID = ?";
	public static final String DELETE_STMT = "UPDATE FORUM_POST SET FORUM_POST_CONTENT = '此內容已被刪除' WHERE FORUM_POST_ID = ?";
	public static final String FIND_BY_PK = "SELECT * FROM FORUM_POST WHERE FORUM_POST_ID = ?";
	public static final String GET_ALL = "SELECT * FROM FORUM_POST";
	public static final String GET_ALL_BY_MEMBER = "SELECT * FROM FORUM_POST where member_id=? order by FORUM_POST_ID desc";
	
	@Override
	public void add(ForumPostVO forumPost) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, forumPost.getMember_id());
			pstmt.setString(2, forumPost.getForum_post_title());
			pstmt.setString(3, forumPost.getForum_post_content());
			
			pstmt.executeUpdate();
			con.commit();
		} catch(SQLException se) {
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
	public void update(ForumPostVO forumPost) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setString(1, forumPost.getForum_post_title());
			pstmt.setString(2, forumPost.getForum_post_content());
			pstmt.setInt(3, forumPost.getForum_post_id());
			
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
	public void updateStatus(ForumPostVO forumPost) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STATUS_STMT);

			pstmt.setInt(1, forumPost.getForum_post_status());
			pstmt.setInt(2, forumPost.getForum_post_id());

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
	public void updateLikePlus(Integer forum_post_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_LIKE_PLUS);
			
			pstmt.setInt(1, forum_post_id);

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
	public void updateLikeMinus(Integer forum_post_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_LIKE_MINUS);
			
			pstmt.setInt(1, forum_post_id);

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
	public void updateReplyPlus(Integer forum_post_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_REPLY_PLUS);
			
			pstmt.setInt(1, forum_post_id);

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
	public void updateReplyMinus(Integer forum_post_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_REPLY_MINUS);
			
			pstmt.setInt(1, forum_post_id);

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
	public void delete(Integer forum_post_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setInt(1, forum_post_id);
			
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
	public ForumPostVO findByPK(Integer forum_post_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ForumPostVO forumPost = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, forum_post_id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				forumPost = new ForumPostVO();
				forumPost.setForum_post_id(rs.getInt("FORUM_POST_ID"));
				forumPost.setMember_id(rs.getInt("MEMBER_ID"));
				forumPost.setForum_post_title(rs.getString("FORUM_POST_TITLE"));
				forumPost.setForum_post_content(rs.getString("FORUM_POST_CONTENT"));
				forumPost.setForum_post_time(rs.getTimestamp("FORUM_POST_TIME"));
				forumPost.setForum_update_time(rs.getTimestamp("FORUM_POST_UPDATE_TIME"));
				forumPost.setForum_post_reply_total(rs.getInt("FORUM_POST_REPLY_TOTAL"));
				forumPost.setForum_post_like(rs.getInt("FORUM_POST_LIKE"));
				
			}

			
		} catch(SQLException se) {
			se.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			
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
		
		return forumPost;
	}

	@Override
	public List<ForumPostVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ForumPostVO> forumlist = new ArrayList<ForumPostVO>();
		ForumPostVO forumPost = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				forumPost = new ForumPostVO();
				forumPost.setForum_post_id(rs.getInt("FORUM_POST_ID"));
				forumPost.setMember_id(rs.getInt("MEMBER_ID"));
				forumPost.setForum_post_title(rs.getString("FORUM_POST_TITLE"));
				forumPost.setForum_post_content(rs.getString("FORUM_POST_CONTENT"));
				forumPost.setForum_post_time(rs.getTimestamp("FORUM_POST_TIME"));
				forumPost.setForum_update_time(rs.getTimestamp("FORUM_POST_UPDATE_TIME"));
				forumPost.setForum_post_reply_total(rs.getInt("FORUM_POST_REPLY_TOTAL"));
				forumPost.setForum_post_like(rs.getInt("FORUM_POST_LIKE"));
				
				forumlist.add(forumPost);
			}

			
		} catch(SQLException se) {
			se.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			
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
		return forumlist;
	}

	@Override
	public List<ForumPostVO> getMyForum(Integer member_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ForumPostVO> forumlist = new ArrayList<ForumPostVO>();
		ForumPostVO forumPost = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_MEMBER);
			pstmt.setInt(1, member_id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				forumPost = new ForumPostVO();
				forumPost.setForum_post_id(rs.getInt("FORUM_POST_ID"));
				forumPost.setMember_id(rs.getInt("MEMBER_ID"));
				forumPost.setForum_post_title(rs.getString("FORUM_POST_TITLE"));
				forumPost.setForum_post_content(rs.getString("FORUM_POST_CONTENT"));
				forumPost.setForum_post_time(rs.getTimestamp("FORUM_POST_TIME"));
				forumPost.setForum_update_time(rs.getTimestamp("FORUM_POST_UPDATE_TIME"));
				forumPost.setForum_post_reply_total(rs.getInt("FORUM_POST_REPLY_TOTAL"));
				forumPost.setForum_post_like(rs.getInt("FORUM_POST_LIKE"));
				
				forumlist.add(forumPost);
			}

			
		} catch(SQLException se) {
			se.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			
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
		return forumlist;
	}	

}
