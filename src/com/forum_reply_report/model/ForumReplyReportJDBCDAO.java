package com.forum_reply_report.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import util.Util;

public class ForumReplyReportJDBCDAO implements ForumReplyReportDAO {
	public static final String INSERT_STMT = "INSERT INTO FORUM_REPLY_REPORT(FORUM_REPLY_ID, MEMBER_ID, FORUM_REPLY_REPORT_REASON) VALUES(?, ?, ?)";
	public static final String UPDATE_STATUS_STMT = "UPDATE FORUM_REPLY_REPORT SET FORUM_REPLY_REPORT_STATUS = ? WHERE FORUM_REPLY_REPORT_ID = ?";
	public static final String DELETE_STMT = "DELETE FROM FORUM_REPLY_REPORT WHERE FORUM_REPLY_REPORT_ID = ?";
	public static final String FIND_BY_PK = "SELECT * FROM FORUM_REPLY_REPORT WHERE FORUM_REPLY_REPORT_ID = ?";
	public static final String GET_ALL = "SELECT * FROM FORUM_REPLY_REPORT WHERE FORUM_REPLY_REPORT_STATUS = 2";

	static {
		try {
			Class.forName(Util.DRIVER);
			System.out.println("載入驅動");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void add(ForumReplyReportVO forumReplyReport) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, forumReplyReport.getForum_reply_id());
			pstmt.setInt(2, forumReplyReport.getMember_id());
			pstmt.setString(3, forumReplyReport.getForum_reply_report_reason());

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
	public void updateStatus(Integer forum_reply_report_status, Integer forum_reply_report_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STATUS_STMT);

			pstmt.setInt(1, forum_reply_report_status);
			pstmt.setInt(2, forum_reply_report_id);

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
	public void delete(Integer forum_reply_report_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setInt(1, forum_reply_report_id);
			
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
	public ForumReplyReportVO findByPK(Integer forum_reply_report_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ForumReplyReportVO forumReplyReport = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			
			pstmt.setInt(1, forum_reply_report_id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				forumReplyReport = new ForumReplyReportVO();
				forumReplyReport.setForum_reply_report_id(rs.getInt("FORUM_REPLY_REPORT_ID"));
				forumReplyReport.setForum_reply_id(rs.getInt("FORUM_REPLY_ID"));
				forumReplyReport.setMember_id(rs.getInt("MEMBER_ID"));
				forumReplyReport.setForum_reply_report_reason(rs.getString("FORUM_REPLY_REPORT_REASON"));
				forumReplyReport.setForum_reply_report_time(rs.getTimestamp("FORUM_REPLY_REPORT_TIME"));
				forumReplyReport.setForum_reply_report_status(rs.getInt("FORUM_REPLY_REPORT_STATUS"));
				
			}
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

		return forumReplyReport;
	}

	@Override
	public List<ForumReplyReportVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ForumReplyReportVO forumReplyReport = null;
		List<ForumReplyReportVO> reportlist = new ArrayList();
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				forumReplyReport = new ForumReplyReportVO();
				forumReplyReport.setForum_reply_report_id(rs.getInt("FORUM_REPLY_REPORT_ID"));
				forumReplyReport.setForum_reply_id(rs.getInt("FORUM_REPLY_ID"));
				forumReplyReport.setMember_id(rs.getInt("MEMBER_ID"));
				forumReplyReport.setForum_reply_report_reason(rs.getString("FORUM_REPLY_REPORT_REASON"));
				forumReplyReport.setForum_reply_report_time(rs.getTimestamp("FORUM_REPLY_REPORT_TIME"));
				forumReplyReport.setForum_reply_report_status(rs.getInt("FORUM_REPLY_REPORT_STATUS"));
				reportlist.add(forumReplyReport);
				
			}
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
		return reportlist;
	}
}

