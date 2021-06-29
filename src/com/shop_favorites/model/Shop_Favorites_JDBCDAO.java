package com.shop_favorites.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Shop_Favorites_JDBCDAO implements Shop_FavoritesDAO_Interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/new_schema?serverTimezone=Asia/Taipei";
	String userid = "David";
	String passwd = "123456";
	private static final String INSERT_STMT = 
			"INSERT INTO shop_favorites (member_id,shop_id) VALUES (?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT * FROM shop_favorites order by shop_favorites_id";
		private static final String GET_ONE_STMT = 
			"SELECT * FROM shop_favorites where shop_favorites_id= ?";
		private static final String DELETE = 
			"DELETE FROM shop_favorites where shop_favorites_id = ?";
		private static final String UPDATE = 
			"UPDATE shop_favorites set member_id=?,shop_id=? where shop_favorites_id=?" ;
		
		
	@Override
	public void insert(Shop_FavoritesVO Shop_FavoritesVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1,Shop_FavoritesVO.getMEMBER_ID());
			pstmt.setInt(2,Shop_FavoritesVO.getSHOP_ID());
			pstmt.execute();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
	public void update(Shop_FavoritesVO Shop_FavoritesVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1,Shop_FavoritesVO.getMEMBER_ID());
			pstmt.setInt(2,Shop_FavoritesVO.getSHOP_ID());
			pstmt.setInt(3	,Shop_FavoritesVO.getSHOP_FAVORITES_ID());
			pstmt.execute();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
	public void delete(Integer Shop_Favorites_ID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1,Shop_Favorites_ID);

			pstmt.execute();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
	public Shop_FavoritesVO getOneStmt(Integer Shop_Favorites_ID) {
		Shop_FavoritesVO sf = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, Shop_Favorites_ID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				sf = new Shop_FavoritesVO();
				sf.setSHOP_FAVORITES_ID(rs.getInt("SHOP_FAVORITES_ID"));
				sf.setMEMBER_ID(rs.getInt("MEMBER_ID"));
				sf.setSHOP_ID(rs.getInt("SHOP_ID"));
			}

		} catch (SQLException se) {
			se.printStackTrace();
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

		return sf;
	
	}

	@Override
	public List<Shop_FavoritesVO> getAll() {
		List<Shop_FavoritesVO> list= new ArrayList<Shop_FavoritesVO>();
		Shop_FavoritesVO sf=null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				sf = new Shop_FavoritesVO();
				sf.setSHOP_FAVORITES_ID(rs.getInt("SHOP_FAVORITES_ID"));
				sf.setMEMBER_ID(rs.getInt("MEMBER_ID"));
				sf.setSHOP_ID(rs.getInt("SHOP_ID"));
				list.add(sf);
			}

		} catch (SQLException se) {
			se.printStackTrace();
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public List<Shop_FavoritesVO> getAllByMember(Integer Member_ID) {
		// TODO Auto-generated method stub
		return null;
	}

}
