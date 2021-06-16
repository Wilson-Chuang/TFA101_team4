package com.shop_favorites.model;

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

import com.member_follower.model.Member_FollowerVO;

public class Shop_FavoritesDAO implements Shop_FavoritesDAO_Interface{
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
			"INSERT INTO shop_favorites (member_id,shop_id) VALUES (?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT * FROM shop_favorites order by shop_favorites_id";
		private static final String GET_ALL_BY_MEMBER = 
				"SELECT * FROM shop_favorites where member_id = ?";
		private static final String GET_ONE_STMT = 
			"SELECT * FROM shop_favorites where shop_favorites_id= ?";
		private static final String DELETE = 
			"DELETE FROM shop_favorites where shop_favorites_id = ?";
		private static final String DELETE_SF = 
			"DELETE FROM shop_favorites where member_id = ? and shop_id=?";
		private static final String UPDATE = 
			"UPDATE shop_favorites set member_id=?,shop_id=? where shop_favorites_id=?" ;
		
		
	@Override
	public void insert(Shop_FavoritesVO Shop_FavoritesVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1,Shop_FavoritesVO.getMEMBER_ID());
			pstmt.setInt(2,Shop_FavoritesVO.getSHOP_ID());
			pstmt.execute();
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1,Shop_FavoritesVO.getMEMBER_ID());
			pstmt.setInt(2,Shop_FavoritesVO.getSHOP_ID());
			pstmt.setInt(3	,Shop_FavoritesVO.getSHOP_FAVORITES_ID());
			pstmt.execute();
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
	public void delete_sf(Shop_FavoritesVO Shop_Favorites) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_SF);
			pstmt.setInt(1,Shop_Favorites.getMEMBER_ID());
			pstmt.setInt(2,Shop_Favorites.getSHOP_ID());
			
			pstmt.execute();
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1,Shop_Favorites_ID);

			pstmt.execute();
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
			con = ds.getConnection();
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
	public List<Shop_FavoritesVO> getAllByMember(Integer Member_ID) {
		List<Shop_FavoritesVO> list= new ArrayList<Shop_FavoritesVO>();
		Shop_FavoritesVO sf=null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_MEMBER);
			pstmt.setInt(1, Member_ID);
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
	public List<Shop_FavoritesVO> getAll() {
		List<Shop_FavoritesVO> list= new ArrayList<Shop_FavoritesVO>();
		Shop_FavoritesVO sf=null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
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
