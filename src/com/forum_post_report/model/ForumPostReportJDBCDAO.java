package com.forum_post_report.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.Util;

public class ForumPostReportJDBCDAO implements ForumPostReportDAO {
	public static final String INSERT_STMT = 
			"INSERT INTO FORUM_POST_REPORT(FORUM_POST_ID, MEMBER_ID, FORUM_POST_REPORT_REASON) VALUES(?, ?, ?)";
	public static final String UPDATE_STATUS_STMT = "UPDATE FORUM_POST_REPORT SET FORUM_POST_REPORT_STATUS = ? WHERE FORUM_POST_REPORT_ID = ?";
	public static final String DELETE_STMT = "DELETE FROM FORUM_POST_REPORT WHERE FORUM_POST_REPORT_ID = ?";
	public static final String FIND_BY_PK = "SELECT * FROM FORUM_POST_REPORT WHERE FORUM_POST_REPORT_ID = ?";
	public static final String GET_ALL = "SELECT * FROM FORUM_POST_REPORT";
	
	static {
		try {
			Class.forName(Util.DRIVER);
			System.out.println("載入驅動");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void add(ForumPostReportVO forumPostReport) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, forumPostReport.getForum_post_id());
			pstmt.setInt(2, forumPostReport.getMember_id());
			pstmt.setString(3, forumPostReport.getForum_post_report_reason());

			pstmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
	}

	@Override
	public void updateStatus(ForumPostReportVO forumPostReport) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STATUS_STMT);

			pstmt.setInt(1, forumPostReport.getForum_post_report_status());
			pstmt.setInt(2, forumPostReport.getForum_post_report_id());

			pstmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
	}

	@Override
	public void delete(Integer forum_post_report_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setInt(1, forum_post_report_id);
			
			pstmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
	}

	@Override
	public ForumPostReportVO findByPK(Integer forum_post_report_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ForumPostReportVO forumPostReport = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			
			pstmt.setInt(1, forum_post_report_id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				forumPostReport = new ForumPostReportVO();
				forumPostReport.setForum_post_report_id(rs.getInt("FORUM_POST_REPORT_ID"));
				forumPostReport.setForum_post_id(rs.getInt("FORUM_POST_ID"));
				forumPostReport.setMember_id(rs.getInt("MEMBER_ID"));
				forumPostReport.setForum_post_report_reason(rs.getString("FORUM_POST_REPORT_REASON"));
				forumPostReport.setForum_post_report_time(rs.getTimestamp("FORUM_POST_REPORT_TIME"));
				forumPostReport.setForum_post_report_status(rs.getInt("FORUM_POST_REPORT_STATUS"));
				
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
		
		return forumPostReport;
	}

	@Override
	public List<ForumPostReportVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ForumPostReportVO forumPostReport = null;
		List<ForumPostReportVO> reportlist = new ArrayList();
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				forumPostReport = new ForumPostReportVO();
				forumPostReport.setForum_post_report_id(rs.getInt("FORUM_POST_REPORT_ID"));
				forumPostReport.setForum_post_id(rs.getInt("FORUM_POST_ID"));
				forumPostReport.setMember_id(rs.getInt("MEMBER_ID"));
				forumPostReport.setForum_post_report_reason(rs.getString("FORUM_POST_REPORT_REASON"));
				forumPostReport.setForum_post_report_time(rs.getTimestamp("FORUM_POST_REPORT_TIME"));
				forumPostReport.setForum_post_report_status(rs.getInt("FORUM_POST_REPORT_STATUS"));
				reportlist.add(forumPostReport);
				
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
		return reportlist;
	}

}
