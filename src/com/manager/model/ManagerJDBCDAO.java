package com.manager.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ManagerJDBCDAO implements ManagerDAO_interface {
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/TEAM4DB?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";
	
	//尚未加入manager_picname
	private static final String INSERT_STMT = 
			"INSERT INTO manager(manager_account,manager_name,manager_pic,manager_email,manager_password,manager_phone,manager_picname) VALUES(?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT manager_id,manager_account,manager_name,manager_pic,manager_email,manager_password,manager_phone,manager_picname FROM manager ORDER BY manager_id";
	private static final String GET_ONE_STMT = 
			"SELECT manager_id,manager_account,manager_name,manager_pic,manager_email,manager_password,manager_phone,manager_picname FROM manager WHERE manager_id = ?";
	private static final String DELETE = 
			"DELETE FROM manager WHERE manager_id = ?";
	private static final String UPDATE = 
			"UPDATE manager SET manager_account=?, manager_name=?, manager_pic=?, manager_email=?, manager_password=?, manager_phone=?, manager_picname=? WHERE manager_id = ?";
	
	private static final String GETID =
			"SELECT manager_id FROM manager WHERE manager_email=?";
	
	@Override
	public void insert(ManagerVO managerVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			FileInputStream fis = new FileInputStream("C:/uploadpic/manager_pic1.png");

			pstmt.setString(1, managerVO.getManager_account());
			pstmt.setString(2, managerVO.getManager_name());
			pstmt.setBytes(3, managerVO.getManager_pic());
			pstmt.setString(4, managerVO.getManager_email());
			pstmt.setString(5, managerVO.getManager_password());
			pstmt.setString(6, managerVO.getManager_phone());
			System.out.println("圖片新增");
			
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
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			FileInputStream fis = new FileInputStream("C:/uploadpic/manager_pic1.png");

			pstmt.setString(1, managerVO.getManager_account());
			pstmt.setString(2, managerVO.getManager_name());
			pstmt.setBytes(3, managerVO.getManager_pic());
			pstmt.setString(4, managerVO.getManager_email());
			pstmt.setString(5, managerVO.getManager_password());
			pstmt.setString(6, managerVO.getManager_phone());
			pstmt.setInt(7, managerVO.getManager_id());

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
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, manager_id);

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
	public ManagerVO findByPrimaryKey(Integer manager_id) {
		
		ManagerVO managerVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
				managerVO.setManager_id(rs.getInt("manager_id"));
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
				managerVO.setManager_id(rs.getInt("manager_id"));
				list.add(managerVO); // Store the row in the list
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
	
	@Override
	public int getId(String manager_email) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int manager_id = 0;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GETID);

			pstmt.setString(1, manager_email);	//1個問號

			rs = pstmt.executeQuery();

			if (rs.next()) {
				// managerVO 也稱為 Domain objects
				manager_id = rs.getInt("manager_id");
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
		return manager_id;
	}
	
	public static void main(String[] args) throws IOException {
		
		ManagerJDBCDAO dao = new ManagerJDBCDAO();
		
		// 新增
//		ManagerVO managerVO1 = new ManagerVO();
//		managerVO1.setManager_account("David");
//		managerVO1.setManager_name("David");
//		byte[] manager_pic = getPictureByteArray("WebContent/manager/images/manager_pic1.png");
//		managerVO1.setManager_pic(manager_pic);
//		managerVO1.setManager_email("David@tibame.com");
//		managerVO1.setManager_password("123456");
//		managerVO1.setManager_phone("09xxxxxxxx");
//		dao.insert(managerVO1);	
		
		// 修改
//		ManagerVO managerVO2 = new ManagerVO();
//		managerVO2.setManager_account("test22");
//		managerVO2.setManager_name("更新測試");
////		managerVO2.setManager_pic(rs.getBytes("manager_pic"));
//		managerVO2.setManager_email("update@tibame.com");
//		managerVO2.setManager_password("123456");
//		managerVO2.setManager_phone("0900222222");
//		managerVO2.setManager_id(2);
//		dao.update(managerVO2);
		
		// 刪除
//		dao.delete(1);
		
		// 查詢
		ManagerVO managerVO3 = dao.findByPrimaryKey(2);
		System.out.print(managerVO3.getManager_account() + ",");
		System.out.print(managerVO3.getManager_name() + ",");
		System.out.print(managerVO3.getManager_pic() + ",");
		System.out.print(managerVO3.getManager_email() + ",");
		System.out.print(managerVO3.getManager_password() + ",");
		System.out.print(managerVO3.getManager_phone() + ",");
		System.out.print(managerVO3.getManager_picname() + ",");
		System.out.println(managerVO3.getManager_id());
		System.out.println("---------------------");
		
		// 查詢
		List<ManagerVO> list = dao.getAll();
		for (ManagerVO aManager : list) {
			System.out.print(aManager.getManager_account()+ ",");
			System.out.print(aManager.getManager_name() + ",");
			System.out.print(aManager.getManager_pic() + ",");
			System.out.print(aManager.getManager_email() + ",");
			System.out.print(aManager.getManager_password() + ",");
			System.out.print(aManager.getManager_phone() + ",");
			System.out.print(aManager.getManager_picname() + ",");
			System.out.print(aManager.getManager_id());
			System.out.println();
		}
		
		
	}


	private static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);		
		byte[] buffer = new byte[fis.available()];		//讀取源頭資料量的大小
		fis.read(buffer);			//必須自己先把資料讀進來放入byte陣列
		fis.close();
		return buffer;
	}


}
