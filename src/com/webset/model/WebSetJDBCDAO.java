package com.webset.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.manager.model.ManagerJDBCDAO;
import com.manager.model.ManagerVO;

public class WebSetJDBCDAO implements WebSetDAO_interface {
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/TEAM4?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";
	
	private static final String GET_ALL_STMT = 
			"SELECT website_url,site_title,footer_text,site_logo,site_status FROM WebSet";
	private static final String UPDATE = 
			"UPDATE WebSet SET site_title=?, footer_text=?, site_logo=?, site_status=? WHERE website_url = ?";
	
	@Override
	public void update(WebSetVO webSetVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, webSetVO.getSite_title());
			pstmt.setString(2, webSetVO.getFooter_text());
			pstmt.setBytes(3, webSetVO.getSite_logo());
			pstmt.setInt(4, webSetVO.getSite_status());
			pstmt.setString(5, webSetVO.getWebsite_url());

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
	public List<WebSetVO> getAll() {
		
		List<WebSetVO> list = new ArrayList<WebSetVO>();
		WebSetVO webSetVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// webSetVO 也稱為 Domain objects
				webSetVO = new WebSetVO();
				webSetVO.setSite_title(rs.getString("site_title"));
				webSetVO.setFooter_text(rs.getString("footer_text"));
//				webSetVO.setSite_logo(rs.getBytes("site_logo"));
				webSetVO.setSite_status(rs.getInt("site_status"));
				webSetVO.setWebsite_url(rs.getString("website_url"));
				list.add(webSetVO); // Store the row in the list
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
		
		WebSetJDBCDAO dao = new WebSetJDBCDAO();
		
		// 修改
		WebSetVO webSetVO1 = new WebSetVO();
		webSetVO1.setSite_title("Guide好食");
		webSetVO1.setFooter_text("測試頁腳文字2");
//		webSetVO1.setSite_logo(rs.getBytes("site_logo"));
		webSetVO1.setSite_status(0);
		webSetVO1.setWebsite_url("https://test.com");
		dao.update(webSetVO1);
		
		// 查詢
		List<WebSetVO> list = dao.getAll();
		for (WebSetVO aWebSet : list) {
			System.out.print(aWebSet.getSite_title()+ ",");
			System.out.print(aWebSet.getFooter_text() + ",");
//			System.out.print(aWebSet.getSite_logo() + ",");
			System.out.print(aWebSet.getSite_status() + ",");
			System.out.print(aWebSet.getWebsite_url());
			System.out.println();
		}
	}

}
