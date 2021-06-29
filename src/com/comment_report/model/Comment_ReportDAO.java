package com.comment_report.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Comment_ReportDAO  implements Comment_ReportDAO_Interface{
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
			"INSERT INTO comment_report (comment_id,member_id,comment_report_reason) VALUES (?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT * FROM comment_report order by comment_report_id desc";
		private static final String GET_ONE_STMT = 
			"SELECT * FROM comment_report where comment_report_id = ?";
		private static final String DELETE = 
			"DELETE FROM comment_report where comment_report_id = ?";
		private static final String UPDATE = 
			"UPDATE comment_report set comment_id=?,member_id=?,comment_report_reason=?,comment_report_time=?,comment_report_status=? where comment_report_id=?";
		private static final String REPORTED=
				"SELECT * FROM COMMENT_REPORT where comment_id=? and member_id=?";
		
	@Override
	public void insert(Comment_ReportVO Comment_ReportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1,Comment_ReportVO.getCOMMENT_ID());
			pstmt.setInt(2,Comment_ReportVO.getMEMBER_ID());
			pstmt.setString(3,Comment_ReportVO.getCOMMENT_REPORT_REASON());
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
	public void update(Comment_ReportVO Comment_ReportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1,Comment_ReportVO.getCOMMENT_ID());
			pstmt.setInt(2,Comment_ReportVO.getMEMBER_ID());
			pstmt.setString(3,Comment_ReportVO.getCOMMENT_REPORT_REASON());
			pstmt.setTimestamp(4, Comment_ReportVO.getCOMMENT_REPORT_TIME());
			pstmt.setInt(5, Comment_ReportVO.getCOMMENT_REPORT_STATUS());
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
	public void delete(Integer Comment_Report_ID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1,Comment_Report_ID);

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
	public boolean reported(Integer comment_id,Integer member_id) {
		Comment_ReportVO com = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean reported=false;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(REPORTED);
			pstmt.setInt(1,comment_id);
			pstmt.setInt(2,member_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				com = new Comment_ReportVO();
				com.setCOMMENT_REPORT_ID(rs.getInt("COMMENT_REPORT_ID"));
				com.setCOMMENT_ID(rs.getInt("COMMENT_ID"));
				com.setMEMBER_ID(rs.getInt("MEMBER_ID"));
				com.setCOMMENT_REPORT_REASON(rs.getString("COMMENT_REPORT_REASON"));
				com.setCOMMENT_REPORT_TIME(rs.getTimestamp("COMMENT_REPORT_TIME"));
				com.setCOMMENT_REPORT_STATUS(rs.getInt("COMMENT_REPORT_STATUS"));
				reported = true;
			}
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
		return reported;
	}
	@Override
	public Comment_ReportVO getOneStmt(Integer Comment_Report_ID) {
		Comment_ReportVO com = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, Comment_Report_ID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				com = new Comment_ReportVO();
				com.setCOMMENT_REPORT_ID(rs.getInt("COMMENT_REPORT_ID"));
				com.setCOMMENT_ID(rs.getInt("COMMENT_ID"));
				com.setMEMBER_ID(rs.getInt("MEMBER_ID"));
				com.setCOMMENT_REPORT_REASON(rs.getString("COMMENT_REPORT_REASON"));
				com.setCOMMENT_REPORT_TIME(rs.getTimestamp("COMMENT_REPORT_TIME"));
				com.setCOMMENT_REPORT_STATUS(rs.getInt("COMMENT_REPORT_STATUS"));
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
	public List<Comment_ReportVO> getAll() {
		List<Comment_ReportVO> list= new ArrayList<Comment_ReportVO>();
		Comment_ReportVO com=null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				com = new Comment_ReportVO();
				com.setCOMMENT_REPORT_ID(rs.getInt("COMMENT_REPORT_ID"));
				com.setCOMMENT_ID(rs.getInt("COMMENT_ID"));
				com.setMEMBER_ID(rs.getInt("MEMBER_ID"));
				com.setCOMMENT_REPORT_REASON(rs.getString("COMMENT_REPORT_REASON"));
				com.setCOMMENT_REPORT_TIME(rs.getTimestamp("COMMENT_REPORT_TIME"));
				com.setCOMMENT_REPORT_STATUS(rs.getInt("COMMENT_REPORT_STATUS"));
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

}


