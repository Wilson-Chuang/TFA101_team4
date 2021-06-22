package com.search.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class SearchDAO implements SearchDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Team4DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO search (search_type,search_key,search_count)"
			+ " VALUES (?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT search_id,search_type,search_key,search_count"
			+ " FROM search ORDER BY search_count";
	private static final String GET_ONE_STMT = "SELECT search_id,search_type,search_key,search_count"
			+ " FROM search WHERE search_key = ? ORDER BY search_count";
	private static final String DELETE = "DELETE FROM search WHERE search_id = ?";
	private static final String UPDATE = "UPDATE search set search_type=?, search_key=?, search_count=?"
			+ " WHERE search_id = ?";

	@Override
	public void insert(SearchVO searchVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, searchVO.getSearch_type());
			pstmt.setString(2, searchVO.getSearch_key());
			pstmt.setInt(3, searchVO.getSearch_count());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("資料庫發生錯誤! 訊息為: " + se.getMessage());
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
	public void update(SearchVO searchVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, searchVO.getSearch_type());
			pstmt.setString(2, searchVO.getSearch_key());
			pstmt.setInt(3, searchVO.getSearch_count());
			pstmt.setInt(4, searchVO.getSearch_id());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("資料庫發生錯誤! 訊息為: " + se.getMessage());
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
	public void delete(Integer search_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, search_id);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("資料庫發生錯誤! 訊息為: " + se.getMessage());
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
	public SearchVO findByKeyword(String search_key) {

		SearchVO searchVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, search_key);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				searchVO = new SearchVO();
				searchVO.setSearch_id(rs.getInt("search_id"));
				searchVO.setSearch_type(rs.getInt("search_type"));
				searchVO.setSearch_key(rs.getString("search_key"));
				searchVO.setSearch_count(rs.getInt("search_count"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("資料庫發生錯誤! 訊息為: " + se.getMessage());
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
		return searchVO;
	}

	@Override
	public List<SearchVO> getAll() {
		List<SearchVO> list = new ArrayList<SearchVO>();
		SearchVO searchVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				searchVO = new SearchVO();
				searchVO.setSearch_id(rs.getInt("search_id"));
				searchVO.setSearch_type(rs.getInt("search_type"));
				searchVO.setSearch_key(rs.getString("search_key"));
				searchVO.setSearch_count(rs.getInt("search_count"));
				list.add(searchVO);
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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