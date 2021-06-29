package com.comment.model;

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

public class CommentDAO  implements CommentDAO_Interface{
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
			"INSERT INTO comment (member_id,shop_id,comment_content,comment_rating,comment_pic) VALUES (?, ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT * FROM comment order by comment_id";
		private static final String GET_ALL_BY_SHOP = 
				"SELECT * FROM comment where shop_id=? order by comment_id desc";
		private static final String GET_ONE_STMT = 
			"SELECT * FROM comment where comment_id = ?";
		private static final String DELETE = 
			"DELETE FROM comment where comment_id = ?";
		private static final String UPDATE = 
			"UPDATE comment set member_id=?,shop_id=?,comment_content=?,comment_reting=?,comment_pic=? where comment_id=?";
		private static final String COUNT_BY_MEMBER = 
			"SELECT count(*) FROM comment where member_id =?";
		private static final String COUNT_BY_SHOP = 
				"SELECT count(*) FROM comment where shop_id =?";
		
		
	@Override
	public void insert(CommentVO CommentVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1,CommentVO.getMEMBER_ID());
			pstmt.setInt(2,CommentVO.getSHOP_ID());
			pstmt.setString(3,CommentVO.getCOMMENT_CONTENT());
			pstmt.setBigDecimal(4, CommentVO.getCOMMENT_RATING());
			pstmt.setString(5, CommentVO.getCOMMENT_PIC());
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
	public void update(CommentVO CommentVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1,CommentVO.getMEMBER_ID());
			pstmt.setInt(2,CommentVO.getSHOP_ID());
			pstmt.setString(3,CommentVO.getCOMMENT_CONTENT());
			pstmt.setBigDecimal(4, CommentVO.getCOMMENT_RATING());
			pstmt.setString(5, CommentVO.getCOMMENT_PIC());
			pstmt.setInt(6, CommentVO.getCOMMENT_ID());
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
	public void delete(Integer Comment_ID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1,Comment_ID);

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
	public CommentVO getOneStmt(Integer Comment_ID) {
		CommentVO com = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, Comment_ID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				com = new CommentVO();
				com.setCOMMENT_ID(rs.getInt("COMMENT_ID"));
				com.setMEMBER_ID(rs.getInt("MEMBER_ID"));
				com.setSHOP_ID(rs.getInt("SHOP_ID"));
				com.setCOMMENT_CONTENT(rs.getString("COMMENT_CONTENT"));
				com.setCOMMENT_RATING(rs.getBigDecimal("COMMENT_RATING"));
				com.setCOMMENT_TIME(rs.getTimestamp("COMMENT_TIME"));
				com.setCOMMENT_STATUS(rs.getInt("COMMENT_STATUS"));
				com.setCOMMENT_PIC(rs.getString("COMMENT_PIC"));
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

		return com;
	
	}

	@Override
	public List<CommentVO> getAll() {
		List<CommentVO> list= new ArrayList<CommentVO>();
		CommentVO com=null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				com = new CommentVO();
				com.setCOMMENT_ID(rs.getInt("COMMENT_ID"));
				com.setMEMBER_ID(rs.getInt("MEMBER_ID"));
				com.setSHOP_ID(rs.getInt("SHOP_ID"));
				com.setCOMMENT_CONTENT(rs.getString("COMMENT_CONTENT"));
				com.setCOMMENT_RATING(rs.getBigDecimal("COMMENT_RATING"));
				com.setCOMMENT_TIME(rs.getTimestamp("COMMENT_TIME"));
				com.setCOMMENT_STATUS(rs.getInt("COMMENT_STATUS"));
				com.setCOMMENT_PIC(rs.getString("COMMENT_PIC"));
				list.add(com);
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
	public List<CommentVO> getAllByShop(Integer Shop_ID) {
		List<CommentVO> list= new ArrayList<CommentVO>();
		CommentVO com=null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_SHOP);
			pstmt.setInt(1, Shop_ID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				com = new CommentVO();
				com.setCOMMENT_ID(rs.getInt("COMMENT_ID"));
				com.setMEMBER_ID(rs.getInt("MEMBER_ID"));
				com.setSHOP_ID(rs.getInt("SHOP_ID"));
				com.setCOMMENT_CONTENT(rs.getString("COMMENT_CONTENT"));
				com.setCOMMENT_RATING(rs.getBigDecimal("COMMENT_RATING"));
				com.setCOMMENT_TIME(rs.getTimestamp("COMMENT_TIME"));
				com.setCOMMENT_STATUS(rs.getInt("COMMENT_STATUS"));
				com.setCOMMENT_PIC(rs.getString("COMMENT_PIC"));
				list.add(com);
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
	public Integer countByMember(Integer Member_ID) {
		int count=0;
		CommentVO com = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(COUNT_BY_MEMBER);
			pstmt.setInt(1, Member_ID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count= rs.getInt(1);
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

		return count;
	}

	@Override
	public Integer countByShop(Integer Shop_ID) {
		int count=0;
		CommentVO com = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(COUNT_BY_MEMBER);
			pstmt.setInt(1, Shop_ID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count= rs.getInt(1);
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

		return count;
	}
}


