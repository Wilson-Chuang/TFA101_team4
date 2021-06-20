package com.manager.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ManagerDAO implements ManagerDAO_interface {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Team4DB");
		}catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = 
			"INSERT INTO manager(manager_account,manager_name,manager_pic,manager_email,manager_password,manager_phone,manager_picname) VALUES(?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT manager_id,manager_account,manager_name,manager_pic,manager_email,manager_password,manager_phone,manager_picname FROM manager ORDER BY manager_id";
	private static final String GET_ONE_STMT = 
			"SELECT manager_id,manager_account,manager_name,manager_pic,manager_email,manager_password,manager_phone,manager_picname FROM manager WHERE manager_id = ?";
	private static final String DELETE = 
			"DELETE FROM manager WHERE manager_id = ?";
	private static final String UPDATE = 
			"UPDATE manager SET manager_account=?, manager_name=?, manager_pic=?, manager_email=?, manager_password=?, manager_phone=?, manager_picname=? WHERE manager_id = ?";
	
	
	@Override
	public void insert(ManagerVO managerVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, managerVO.getManager_account());
			pstmt.setString(2, managerVO.getManager_name());
			pstmt.setBytes(3, managerVO.getManager_pic());
			pstmt.setString(4, managerVO.getManager_email());
			pstmt.setString(5, managerVO.getManager_password());
			pstmt.setString(6, managerVO.getManager_phone());
			pstmt.setString(7, managerVO.getManager_picname());

			pstmt.executeUpdate();

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
	public void update(ManagerVO managerVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, managerVO.getManager_account());
			pstmt.setString(2, managerVO.getManager_name());
			pstmt.setBytes(3, managerVO.getManager_pic());
			pstmt.setString(4, managerVO.getManager_email());
			pstmt.setString(5, managerVO.getManager_password());
			pstmt.setString(6, managerVO.getManager_phone());
			pstmt.setString(7, managerVO.getManager_picname());
			pstmt.setInt(8, managerVO.getManager_id());

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void delete(Integer manager_id) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, manager_id);

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public ManagerVO findByPrimaryKey(Integer manager_id) {
		
		ManagerVO managerVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, manager_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// managerVO 也稱為 Domain objects
				managerVO = new ManagerVO();
				managerVO.setManager_account(rs.getString("manager_account"));
				managerVO.setManager_name(rs.getString("manager_name"));
				managerVO.setManager_pic(rs.getBytes("manager_pic"));
				managerVO.setManager_email(rs.getString("manager_email"));
				managerVO.setManager_password(rs.getString("manager_password"));
				managerVO.setManager_phone(rs.getString("manager_phone"));
				managerVO.setManager_picname(rs.getString("manager_picname"));
				managerVO.setManager_id(rs.getInt("manager_id"));
			}

			// Handle any driver errors
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
		return managerVO;
	}
	
	
	@Override
	public List<ManagerVO> getAll() {
		
		List<ManagerVO> list = new ArrayList<ManagerVO>();
		ManagerVO managerVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// managerVO 也稱為 Domain objects
				managerVO = new ManagerVO();
				managerVO.setManager_account(rs.getString("manager_account"));
				managerVO.setManager_name(rs.getString("manager_name"));
				managerVO.setManager_pic(rs.getBytes("manager_pic"));
				managerVO.setManager_email(rs.getString("manager_email"));
				managerVO.setManager_password(rs.getString("manager_password"));
				managerVO.setManager_phone(rs.getString("manager_phone"));
				managerVO.setManager_picname(rs.getString("manager_picname"));
				managerVO.setManager_id(rs.getInt("manager_id"));
				list.add(managerVO); // Store the row in the list
			}

			// Handle any driver errors
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
}
