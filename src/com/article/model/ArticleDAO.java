package com.article.model;

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

public class ArticleDAO implements ArticleDAO_interface{
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
			"INSERT INTO ARTICLE (ARTICLE_TITLE,ARTICLE_CONTENT,ARTICLE_CREATE_TIME,ARTICLE_COLLECTIONS,ARTICLE_VERIFY_STATUS,ARTICLE_STATUS,MEMBER_ID,SHOP_ID,ARTICLE_CATEGORY_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT ARTICLE_ID,ARTICLE_TITLE,ARTICLE_CONTENT,ARTICLE_CREATE_TIME,ARTICLE_COLLECTIONS,ARTICLE_VERIFY_STATUS,ARTICLE_STATUS,MEMBER_ID,SHOP_ID,ARTICLE_CATEGORY_ID FROM ARTICLE ORDER BY ARTICLE_ID";
		
		private static final String GET_ONE_STMT = 
			"SELECT ARTICLE_ID,ARTICLE_TITLE,ARTICLE_CONTENT,ARTICLE_CREATE_TIME,ARTICLE_COLLECTIONS,ARTICLE_VERIFY_STATUS,ARTICLE_STATUS,MEMBER_ID,SHOP_ID,ARTICLE_CATEGORY_ID FROM ARTICLE WHERE ARTICLE_ID = ?";
		private static final String DELETE = 
			"DELETE FROM ARTICLE WHERE ARTICLE_ID= ?";
		private static final String UPDATE = 
			"UPDATE ARTICLE SET ARTICLE_TITLE=?, ARTICLE_CONTENT=?, ARTICLE_CREATE_TIME=?, ARTICLE_COLLECTIONS=?, ARTICLE_VERIFY_STATUS=?, ARTICLE_STATUS=?, MEMBER_ID=?, SHOP_ID=?, ARTICLE_CATEGORY_ID=? WHERE ARTICLE_ID = ?";
		private static final String GET_MY_ARTICLE =
				"SELECT * FROM ARTICLE WHERE MEMBER_ID=? order by article_id desc";
		private static final String GET_ARTICLE_FOLLOWED =
				"SELECT * FROM ARTICLE WHERE ARTICLE_ID=? order by article_id desc";
	@Override
	public void insert(ArticleVO articleVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, articleVO.getTitle());
			pstmt.setString(2, articleVO.getContent());
			pstmt.setTimestamp(3, articleVO.getCreate_time());
			pstmt.setInt(4, articleVO.getCollection());
			pstmt.setInt(5, articleVO.getVerify_status());
			pstmt.setInt(6, articleVO.getArticle_status());
			pstmt.setInt(7, articleVO.getMember_no());
			pstmt.setInt(8, articleVO.getShop_no());
			pstmt.setInt(9, articleVO.getCategory_no());
			

			pstmt.executeUpdate();
			
			
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
	public void update(ArticleVO articleVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			
			pstmt.setString(1, articleVO.getTitle());
			pstmt.setString(2, articleVO.getContent());
			pstmt.setTimestamp(3, articleVO.getCreate_time());
			pstmt.setInt(4, articleVO.getCollection());
			pstmt.setInt(5, articleVO.getVerify_status());
			pstmt.setInt(6, articleVO.getArticle_status());
			pstmt.setInt(7, articleVO.getMember_no());
			pstmt.setInt(8, articleVO.getShop_no());
			pstmt.setInt(9, articleVO.getCategory_no());
			pstmt.setInt(10, articleVO.getArticle_no());

			pstmt.executeUpdate();
			
			
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
	public void delete(Integer article_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, article_no);

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
	public ArticleVO findByPrimaryKey(Integer article_no) {
		ArticleVO articleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1,article_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				articleVO = new ArticleVO();
				articleVO.setArticle_no(rs.getInt("article_id"));
				articleVO.setTitle(rs.getString("article_title"));
				articleVO.setContent(rs.getString("article_content"));
				articleVO.setCreate_time(rs.getTimestamp("article_create_time"));
				articleVO.setCollection(rs.getInt("article_collections"));
				articleVO.setVerify_status(rs.getInt("article_verify_status"));
				articleVO.setArticle_status(rs.getInt("article_status"));
				articleVO.setMember_no(rs.getInt("member_id"));
				articleVO.setShop_no(rs.getInt("shop_id"));
				articleVO.setCategory_no(rs.getInt("article_category_id"));
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
		return articleVO;
	}

	@Override
	public List<ArticleVO> getAll() {
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		ArticleVO articleVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
			
				articleVO = new ArticleVO();
				articleVO.setArticle_no(rs.getInt("article_id"));
				articleVO.setTitle(rs.getString("article_title"));
				articleVO.setContent(rs.getString("article_content"));
				articleVO.setCreate_time(rs.getTimestamp("article_create_time"));
				articleVO.setCollection(rs.getInt("article_collections"));
				articleVO.setVerify_status(rs.getInt("article_verify_status"));
				articleVO.setArticle_status(rs.getInt("article_status"));
				articleVO.setMember_no(rs.getInt("member_id"));
				articleVO.setShop_no(rs.getInt("shop_id"));
				articleVO.setCategory_no(rs.getInt("article_category_id"));
				articleVO.setArticle_img(rs.getBytes("article_img"));
				articleVO.setArticle_img_name(rs.getString("article_img_name"));
				
				list.add(articleVO); // Store the row in the list
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

	@Override
	public List<ArticleVO> getMyArticle(Integer member_id) {
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		ArticleVO articleVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_MY_ARTICLE);
			pstmt.setInt(1, member_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
			
				articleVO = new ArticleVO();
				articleVO.setArticle_no(rs.getInt("article_id"));
				articleVO.setTitle(rs.getString("article_title"));
				articleVO.setContent(rs.getString("article_content"));
				articleVO.setCreate_time(rs.getTimestamp("article_create_time"));
				articleVO.setCollection(rs.getInt("article_collections"));
				articleVO.setVerify_status(rs.getInt("article_verify_status"));
				articleVO.setArticle_status(rs.getInt("article_status"));
				articleVO.setMember_no(rs.getInt("member_id"));
				articleVO.setShop_no(rs.getInt("shop_id"));
				articleVO.setCategory_no(rs.getInt("article_category_id"));
				articleVO.setArticle_img(rs.getBytes("article_img"));
				articleVO.setArticle_img_name(rs.getString("article_img_name"));
				list.add(articleVO); // Store the row in the list
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

	@Override
	public ArticleVO getArticleFollowed(Integer article_id) {
		ArticleVO articleVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ARTICLE_FOLLOWED);
			pstmt.setInt(1, article_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				articleVO = new ArticleVO();
				articleVO.setArticle_no(rs.getInt("article_id"));
				articleVO.setTitle(rs.getString("article_title"));
				articleVO.setContent(rs.getString("article_content"));
				articleVO.setCreate_time(rs.getTimestamp("article_create_time"));
				articleVO.setCollection(rs.getInt("article_collections"));
				articleVO.setVerify_status(rs.getInt("article_verify_status"));
				articleVO.setArticle_status(rs.getInt("article_status"));
				articleVO.setMember_no(rs.getInt("member_id"));
				articleVO.setShop_no(rs.getInt("shop_id"));
				articleVO.setCategory_no(rs.getInt("article_category_id"));
				articleVO.setArticle_img(rs.getBytes("article_img"));
				articleVO.setArticle_img_name(rs.getString("article_img_name"));
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
		return articleVO;
	}

}
