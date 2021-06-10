package com.article.model;

import java.util.*;
import java.sql.*;

public class ArticleJDBCDAO implements ArticleDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/Team4?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "123456";
	
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

		
		@Override
		public void insert(ArticleVO articleVO) {

			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
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
		public void update(ArticleVO articleVO) {

			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
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
		public void delete(Integer article_no) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(DELETE);

				pstmt.setInt(1, article_no);

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
		public ArticleVO findByPrimaryKey(Integer article_no) {
			ArticleVO articleVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
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

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
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
					list.add(articleVO); // Store the row in the list
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

			ArticleJDBCDAO dao = new ArticleJDBCDAO();

			// �s�W
			ArticleVO articleVO1 = new ArticleVO();
			articleVO1.setTitle("Hank");
			articleVO1.setContent(new String("12345"));
			articleVO1.setCreate_time(new java.sql.Timestamp(new java.util.Date().getTime())); 
			articleVO1.setCollection(new Integer(50000));
			articleVO1.setVerify_status(new Integer(1));
			articleVO1.setArticle_status(new Integer(1));
			articleVO1.setMember_no(new Integer(1013));
			articleVO1.setShop_no(new Integer(2021));
			articleVO1.setCategory_no(new Integer(1));
			
			dao.insert(articleVO1);

//			ArticleVO articleVO2 = new ArticleVO();
//			articleVO2.setArticle_no(42);
//			articleVO2.setTitle("xxxxx");
//			articleVO2.setContent(new String("24680"));
//			articleVO2.setCreate_time(new java.sql.Timestamp(new java.util.Date().getTime()));
//			articleVO2.setCollection(new Integer(50001));
//			articleVO2.setVerify_status(new Integer(5));
//			articleVO2.setArticle_status(new Integer(5));
//			articleVO2.setMember_no(new Integer(1014));
//			articleVO2.setShop_no(new Integer(2022));
//			articleVO2.setCategory_no(5);
//			dao.update(articleVO2);

		
//			dao.delete(1);

		
			ArticleVO articleVO3 = dao.findByPrimaryKey(3);
			System.out.print(articleVO3.getArticle_no() + ",");
			System.out.print(articleVO3.getTitle() + ",");
			System.out.print(articleVO3.getContent() + ",");
			System.out.print(articleVO3.getCreate_time() + ",");
			System.out.print(articleVO3.getCollection() + ",");
			System.out.print(articleVO3.getVerify_status() + ",");
			System.out.print(articleVO3.getArticle_status() + ",");
			System.out.print(articleVO3.getMember_no() + ",");
			System.out.print(articleVO3.getShop_no() + ",");
			System.out.println(articleVO3.getCategory_no());
			System.out.println("---------------------");

		
			List<ArticleVO> list = dao.getAll();
			for (ArticleVO aArticle : list) {
				System.out.print(aArticle.getArticle_no() + ",");
				System.out.print(aArticle.getTitle() + ",");
				System.out.print(aArticle.getContent() + ",");
				System.out.print(aArticle.getCreate_time() + ",");
				System.out.print(aArticle.getCollection() + ",");
				System.out.print(aArticle.getVerify_status() + ",");
				System.out.print(aArticle.getArticle_status() + ",");
				System.out.print(aArticle.getMember_no() + ",");
				System.out.print(aArticle.getShop_no() + ",");
				System.out.print(aArticle.getCategory_no());
				System.out.println();
			}
		}
}
