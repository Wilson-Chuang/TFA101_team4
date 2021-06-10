package com.article_category.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.article_favorite.model.Article_FavoriteJDBCDAO;
import com.article_favorite.model.Article_FavoriteVO;

public class Article_CategoryJDBCDAO implements Article_CategoryDAO_interface{
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/Team4?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "123456";
	
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
		
		
		@Override
		public void insert(Article_CategoryVO article_categoryVO) {
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(INSERT_STMT);
				
				pstmt.setString(1, article_categoryVO.getArticle_category_name());

				pstmt.executeUpdate();
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
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
		public void update(Article_CategoryVO article_categoryVO) {
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATE);
				
				pstmt.setString(1, article_categoryVO.getArticle_category_name());
				pstmt.setInt(2, article_categoryVO.getArticle_category_no());
		
				pstmt.executeUpdate();
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
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
		public void delete(Integer article_category_no) {
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(DELETE);

				pstmt.setInt(1, article_category_no);

				pstmt.executeUpdate();

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
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
		public Article_CategoryVO findByPrimaryKey(Integer article_category_no) {
			Article_CategoryVO article_categoryVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setInt(1,article_category_no);

				rs = pstmt.executeQuery();

				while (rs.next()) {
				
					article_categoryVO = new Article_CategoryVO();
					article_categoryVO.setArticle_category_no(rs.getInt("article_category_id"));
					article_categoryVO.setArticle_category_name(rs.getString("article_category_name"));
				
				}

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
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
		public List<Article_CategoryVO> getAll() {
			List<Article_CategoryVO> list = new ArrayList<Article_CategoryVO>();
			Article_CategoryVO article_categoryVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
				
					article_categoryVO = new Article_CategoryVO();
					article_categoryVO.setArticle_category_no(rs.getInt("article_category_id"));
					article_categoryVO.setArticle_category_name(rs.getString("article_category_name"));
					list.add(article_categoryVO); // Store the row in the list
				}

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
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
		
		
public static void main(String[] args) {
			
			Article_CategoryJDBCDAO dao = new Article_CategoryJDBCDAO();

	
			Article_CategoryVO article_categoryVO1 = new Article_CategoryVO();
			article_categoryVO1.setArticle_category_name(new String("測試"));
			dao.insert(article_categoryVO1);


//			Article_CategoryVO article_categoryVO2 = new Article_CategoryVO();
//			article_categoryVO2.setArticle_category_no(2);
//			article_categoryVO2.setArticle_category_name(new String("測試2"));
//			dao.update(article_categoryVO2);

			
//			dao.delete(1);

		
			Article_CategoryVO article_categoryVO3 = dao.findByPrimaryKey(1);
			System.out.print(article_categoryVO3.getArticle_category_no() + ",");
			System.out.println(article_categoryVO3.getArticle_category_name() + ",");
			System.out.println("---------------------");

	
			List<Article_CategoryVO> list = dao.getAll();
			for (Article_CategoryVO aArticle_Category : list) {
				System.out.print(aArticle_Category.getArticle_category_no() + ",");
				System.out.print(aArticle_Category.getArticle_category_name() + ",");
				System.out.println();
			}	
					
			
		}
		
	

}
