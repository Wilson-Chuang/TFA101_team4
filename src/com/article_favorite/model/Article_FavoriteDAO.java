package com.article_favorite.model;

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

public class Article_FavoriteDAO implements Article_FavoriteDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Team4DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = 
			"INSERT INTO ARTICLE_FAVORITE (MEMBER_ID,ARTICLE_ID) VALUES (?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT ARTICLE_FAVORITE_ID,MEMBER_ID,ARTICLE_ID FROM ARTICLE_FAVORITE ORDER BY ARTICLE_FAVORITE_ID";
		private static final String GET_ONE_STMT = 
			"SELECT ARTICLE_FAVORITE_ID,MEMBER_ID,ARTICLE_ID FROM ARTICLE_FAVORITE WHERE ARTICLE_FAVORITE_ID = ?";
		private static final String GET_ALL_BT_MEMBER = 
				"SELECT ARTICLE_FAVORITE_ID,MEMBER_ID,ARTICLE_ID FROM ARTICLE_FAVORITE WHERE MEMBER_ID = ? order by article_favorite_id desc";
		private static final String DELETE = 
			"DELETE FROM ARTICLE_FAVORITE WHERE ARTICLE_FAVORITE_ID= ?";
		private static final String UPDATE = 
			"UPDATE ARTICLE_FAVORITE SET MEMBER_ID=?,ARTICLE_ID=? WHERE ARTICLE_FAVORITE_ID = ?";
		
	@Override
	public void insert(Article_FavoriteVO article_favoriteVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, article_favoriteVO.getMember_no());
			pstmt.setInt(2, article_favoriteVO.getArticle_no());


			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		}finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}				
	}

	@Override
	public void update(Article_FavoriteVO article_favoriteVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, article_favoriteVO.getMember_no());
			pstmt.setInt(2, article_favoriteVO.getArticle_no());
			pstmt.setInt(3, article_favoriteVO.getArticle_favorite_no());
			


			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		}finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}				
	}

	@Override
	public void delete(Integer article_favorite_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, article_favorite_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}			
	}

	@Override
	public Article_FavoriteVO findByPrimaryKey(Integer article_favorite_no) {
		Article_FavoriteVO article_favoriteVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1,article_favorite_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				article_favoriteVO = new Article_FavoriteVO();
				article_favoriteVO.setArticle_favorite_no(rs.getInt("article_favorite_id"));
				article_favoriteVO.setMember_no(rs.getInt("member_id"));
				article_favoriteVO.setArticle_no(rs.getInt("article_id"));
			
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return article_favoriteVO;	}

	@Override
	public List<Article_FavoriteVO> getAll() {
		List<Article_FavoriteVO> list = new ArrayList<Article_FavoriteVO>();
		Article_FavoriteVO article_favoriteVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
			
				article_favoriteVO = new Article_FavoriteVO();
				article_favoriteVO.setArticle_favorite_no(rs.getInt("article_favorite_id"));
				article_favoriteVO.setMember_no(rs.getInt("member_id"));
				article_favoriteVO.setArticle_no(rs.getInt("article_id"));
				list.add(article_favoriteVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;	}

	@Override
	public List<Article_FavoriteVO> getAllByMember(Integer member_id) {
		List<Article_FavoriteVO> list = new ArrayList<Article_FavoriteVO>();
		Article_FavoriteVO article_favoriteVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BT_MEMBER);
			pstmt.setInt(1,member_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
			
				article_favoriteVO = new Article_FavoriteVO();
				article_favoriteVO.setArticle_favorite_no(rs.getInt("article_favorite_id"));
				article_favoriteVO.setMember_no(rs.getInt("member_id"));
				article_favoriteVO.setArticle_no(rs.getInt("article_id"));
				list.add(article_favoriteVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;	
	}

}
