package com.article_favorite.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Article_FavoriteJDBCDAO implements Article_FavoriteDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/Team4?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "123456";
	
		private static final String INSERT_STMT = 
			"INSERT INTO ARTICLE_FAVORITE (MEMBER_ID,ARTICLE_ID) VALUES (?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT ARTICLE_FAVORITE_ID,MEMBER_ID,ARTICLE_ID FROM ARTICLE_FAVORITE ORDER BY ARTICLE_FAVORITE_ID";
		private static final String GET_ONE_STMT = 
			"SELECT ARTICLE_FAVORITE_ID,MEMBER_ID,ARTICLE_ID FROM ARTICLE_FAVORITE WHERE ARTICLE_FAVORITE_ID = ?";
		private static final String DELETE = 
			"DELETE FROM ARTICLE_FAVORITE WHERE ARTICLE_FAVORITE_ID= ?";
		private static final String UPDATE = 
			"UPDATE ARTICLE_FAVORITE SET MEMBER_ID=?,ARTICLE_ID=? WHERE ARTICLE_FAVORITE_ID = ?";
		@Override
		public void insert(Article_FavoriteVO article_favoriteVO) {
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(INSERT_STMT);
				
				pstmt.setInt(1, article_favoriteVO.getMember_no());
				pstmt.setInt(2, article_favoriteVO.getArticle_no());


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
		public void update(Article_FavoriteVO article_favoriteVO) {
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATE);
				
				pstmt.setInt(1, article_favoriteVO.getMember_no());
				pstmt.setInt(2, article_favoriteVO.getArticle_no());
				pstmt.setInt(3, article_favoriteVO.getArticle_favorite_no());
				


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
		public void delete(Integer article_favorite_no) {
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(DELETE);

				pstmt.setInt(1, article_favorite_no);

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
		public Article_FavoriteVO findByPrimaryKey(Integer article_favorite_no) {
			Article_FavoriteVO article_favoriteVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
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
			return article_favoriteVO;
		}
		@Override
		public List<Article_FavoriteVO> getAll() {
			List<Article_FavoriteVO> list = new ArrayList<Article_FavoriteVO>();
			Article_FavoriteVO article_favoriteVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
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
			
			Article_FavoriteJDBCDAO dao = new Article_FavoriteJDBCDAO();

			// �s�W
			Article_FavoriteVO article_favoriteVO1 = new Article_FavoriteVO();
			article_favoriteVO1.setMember_no(new Integer(1));
			article_favoriteVO1.setArticle_no(new Integer(1));
			dao.insert(article_favoriteVO1);


//			Article_FavoriteVO article_favoriteVO2 = new Article_FavoriteVO();
//			article_favoriteVO2.setMember_no(new Integer(3));
//			article_favoriteVO2.setArticle_no(new Integer(200));
//			article_favoriteVO2.setArticle_favorite_no(new Integer(2));
//			dao.update(article_favoriteVO2);

	
//			dao.delete(1);

		
			Article_FavoriteVO article_favoriteVO3 = dao.findByPrimaryKey(1);
			System.out.print(article_favoriteVO3.getArticle_favorite_no() + ",");
			System.out.print(article_favoriteVO3.getMember_no() + ",");
			System.out.println(article_favoriteVO3.getArticle_no() + ",");
			System.out.println("---------------------");

	
			List<Article_FavoriteVO> list = dao.getAll();
			for (Article_FavoriteVO aArticle_Favorite : list) {
				System.out.print(aArticle_Favorite.getArticle_favorite_no() + ",");
				System.out.print(aArticle_Favorite.getMember_no() + ",");
				System.out.print(aArticle_Favorite.getArticle_no() + ",");
				System.out.println();
			}	
					
			
		}

		@Override
		public List<Article_FavoriteVO> getAllByMember(Integer member_id) {
			// TODO Auto-generated method stub
			return null;
		}
		
}
