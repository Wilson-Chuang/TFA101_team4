package com.article_category.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.article_favorite.model.Article_FavoriteJDBCDAO;
import com.article_favorite.model.Article_FavoriteVO;
import com.article.model.ArticleVO;


public class Article_categoryDAO implements Article_categoryDAO_interface{
	
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
		private static final String INSERT_STMT = 
			"INSERT INTO ARTICLE_CATEGORY (ARTICLE_CATEGORY_NAME) VALUES (?)";
		private static final String GET_ALL_STMT = 
			"SELECT ARTICLE_CATEGORY_ID,ARTICLE_CATEGORY_NAME FROM ARTICLE_CATEGORY ORDER BY ARTICLE_CATEGORY_ID";
		private static final String GET_ONE_STMT = 
			"SELECT ARTICLE_CATEGORY_ID,ARTICLE_CATEGORY_NAME FROM ARTICLE_CATEGORY WHERE ARTICLE_CATEGORY_ID = ?";
		private static final String DELETE = 
			"DELETE FROM ARTICLE_CATEGORY WHERE ARTICLE_CATEGORY_ID= ?";
		private static final String UPDATE = 
			"UPDATE ARTICLE_CATEGORY SET ARTICLE_CATEGORY_NAME=? WHERE ARTICLE_CATEGORY_ID = ?";
		private static final String GET_Articles_ByArticle_category_no_STMT = "SELECT ARTICLE_ID, ARTICLE_TITLE, ARTICLE_CONTENT, ARTICLE_CREATE_TIME, ARTICLE_COLLECTIONS,"
				+ " ARTICLE_VERIFY_STATUS, ARTICLE_STATUS, ARTICLE_IMG, ARTICLE_IMG_NAME, MEMBER_ID, SHOP_ID, ARTICLE_CATEGORY_ID FROM ARTICLE WHERE ARTICLE_CATEGORY_ID = ? ORDER BY ARTICLE_ID DESC";
		
		@Override
		public void insert(Article_categoryVO article_categoryVO) {
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);
				
				pstmt.setString(1, article_categoryVO.getArticle_category_name());

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
		public void update(Article_categoryVO article_categoryVO) {
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);
				
				pstmt.setString(1, article_categoryVO.getArticle_category_name());
				pstmt.setInt(2, article_categoryVO.getArticle_category_no());
		
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
		public void delete(Integer article_catrgory_no) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);

				pstmt.setInt(1, article_catrgory_no);

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
		public Article_categoryVO findByPrimaryKey(Integer article_category_no) {
			Article_categoryVO article_categoryVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setInt(1,article_category_no);

				rs = pstmt.executeQuery();

				while (rs.next()) {
				
					article_categoryVO = new Article_categoryVO();
					article_categoryVO.setArticle_category_no(rs.getInt("article_category_id"));
					article_categoryVO.setArticle_category_name(rs.getString("article_category_name"));
				
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
			return article_categoryVO;
		}
		
		@Override
		public List<Article_categoryVO> getAll() {
			List<Article_categoryVO> list = new ArrayList<Article_categoryVO>();
			Article_categoryVO article_categoryVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
				
					article_categoryVO = new Article_categoryVO();
					article_categoryVO.setArticle_category_no(rs.getInt("article_category_id"));
					article_categoryVO.setArticle_category_name(rs.getString("article_category_name"));
					list.add(article_categoryVO); // Store the row in the list
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
		public Set<ArticleVO> getArticlesByarticle_category_no(Integer article_category_no) {
			Set<ArticleVO> set = new LinkedHashSet<ArticleVO>();
			ArticleVO articleVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_Articles_ByArticle_category_no_STMT);
				pstmt.setInt(1, article_category_no);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					articleVO = new ArticleVO();
					articleVO.setArticle_no(rs.getInt("article_id"));
					articleVO.setArticle_title(rs.getString("article_title"));
					articleVO.setArticle_content(rs.getString("article_content"));
					articleVO.setArticle_create_time(rs.getTimestamp("article_create_time"));
					articleVO.setArticle_collection(rs.getInt("article_collections"));
					articleVO.setArticle_verify_status(rs.getInt("article_verify_status"));
					articleVO.setArticle_status(rs.getInt("article_status"));
					articleVO.setArticle_img(rs.getBytes("article_img"));
					articleVO.setArticle_img_name(rs.getString("article_img_name"));
					articleVO.setMember_no(rs.getInt("member_id"));
					articleVO.setShop_no(rs.getInt("shop_id"));
					articleVO.setCategory_no(rs.getInt("article_category_id"));
					set.add(articleVO); // Store the row in the vector
				}
				// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. " + se.getMessage());
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
			return set;
		}

}
