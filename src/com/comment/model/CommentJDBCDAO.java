package com.comment.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.member.model.MemberVO;

public class CommentJDBCDAO implements CommentDAO_Interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/Team4DB?serverTimezone=Asia/Taipei";
	String userid = "David";
	String passwd = "123456";
	private static final String INSERT_STMT = 
			"INSERT INTO comment (member_id,shop_id,comment_content,comment_rating,comment_pic) VALUES (?, ?, ?, ?, ?)";
			private static final String GET_ALL_STMT = 
			"SELECT * FROM comment order by comment_id";
		private static final String GET_ONE_STMT = 
			"SELECT * FROM comment where comment_id = ?";
		private static final String GET_ONE_SHOP = 
				"SELECT * FROM shop where shop_id = ?";
		private static final String DELETE = 
			"DELETE FROM comment where comment_id = ?";
		private static final String UPDATE = 
			"UPDATE comment set member_id=?,shop_id=?,comment_content=?,comment_reting=?,comment_time=?,comment_status=? where comment_id=?";
		
		
	@Override
	public void insert(CommentVO CommentVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1,CommentVO.getMEMBER_ID());
			pstmt.setInt(2,CommentVO.getSHOP_ID());
			pstmt.setString(3,CommentVO.getCOMMENT_CONTENT());
			pstmt.setBigDecimal(4, CommentVO.getCOMMENT_RATING());
			pstmt.setString(5, CommentVO.getCOMMENT_PIC());
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
	public void update(CommentVO CommentVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1,CommentVO.getMEMBER_ID());
			pstmt.setInt(2,CommentVO.getSHOP_ID());
			pstmt.setString(3,CommentVO.getCOMMENT_CONTENT());
			pstmt.setBigDecimal(4, CommentVO.getCOMMENT_RATING());
			pstmt.setTimestamp(5, CommentVO.getCOMMENT_TIME());
			pstmt.setInt(6, CommentVO.getCOMMENT_STATUS());
			pstmt.setInt(7, CommentVO.getCOMMENT_ID());
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
	public void delete(Integer Comment_ID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1,Comment_ID);

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
	public CommentVO getOneStmt(Integer Comment_ID) {
		CommentVO com = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
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
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
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
				list.add(com);
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
	public List<CommentVO> getAllByShop(Integer Shop_ID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer countByMember(Integer Member_ID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer countByShop(Integer Shop_ID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer countRating(Integer Shop_ID) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void update_status(Integer Comment_ststus, Integer Comment_ID) {
		// TODO Auto-generated method stub
		
	}
}


