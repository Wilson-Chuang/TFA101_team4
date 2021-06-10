package com.party.model;

import java.util.*;
import java.sql.*;


public class PartyJDBCDAO implements PartyDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/party?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = "INSERT INTO party (party_id, member_id, shop_id, reserv_id, party_title, party_start_time, party_end_time, party_intro, party_participants_max, party_participants_min, party_status, hiredate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT party_id, member_id, shop_id, reserv_id, party_title, party_start_time, party_end_time, party_intro, party_participants_max, party_participants_min, party_status, hiredate FROM party order by party_id";
	private static final String GET_ONE_STMT = "SELECT party_id, member_id, shop_id, reserv_id, party_title, party_start_time, party_end_time, party_intro, party_participants_max, party_participants_min, party_status, hiredate FROM party where party_id = ?";
	private static final String UPDATE = "UPDATE party set party_id=?, member_id=?, shop_id=?, reserv_id=?, party_title=?, party_start_time=?, party_end_time=?, party_intro=?, party_participants_max=?, party_participants_min=?, party_status=?, hiredate=? where party_id = ?";

	@Override
	public void insert(PartyVO partyVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setString(1, partyVO.getParty_title());
			pstmt.setTimestamp(2, partyVO.getParty_start_time());
			pstmt.setTimestamp(3, partyVO.getParty_end_time());
			pstmt.setString(4, partyVO.getParty_intro());
			pstmt.setInt(5, partyVO.getParty_participants_max());
			pstmt.setInt(6, partyVO.getParty_participants_min());
			pstmt.setString(7, partyVO.getParty_status());
			pstmt.setDate(8, partyVO.getHiredate());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(PartyVO partyVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, partyVO.getParty_title());
			pstmt.setTimestamp(2, partyVO.getParty_start_time());
			pstmt.setTimestamp(3, partyVO.getParty_end_time());
			pstmt.setString(4, partyVO.getParty_intro());
			pstmt.setInt(5, partyVO.getParty_participants_max());
			pstmt.setInt(6, partyVO.getParty_participants_min());
			pstmt.setString(7, partyVO.getParty_status());
			pstmt.setDate(8, partyVO.getHiredate());
			pstmt.setInt(9, partyVO.getParty_id());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public PartyVO findByPrimaryKey(Integer party_id) {

		partyVO partyVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, party_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				partyVO = new PartyVO();
				partyVO.setParty_participants_id(rs.getInt("party_id"));
				partyVO.setMember_id(rs.getInt("member_id"));
				partyVO.setShop_id(rs.getInt("shop_id"));
				partyVO.setReserv_id(rs.getInt("reserv_id"));
				partyVO.setParty_title(rs.getString("party_title"));
				partyVO.setParty_start_time(rs.getTimestamp("party_start_time"));
				partyVO.setParty_end_time(rs.getTimestamp("party_end_time"));
				partyVO.setParty_intro(rs.getString("party_intro"));
				partyVO.setParty_participants_max(rs.getInt("party_participants_max"));
				partyVO.setParty_participants_min(rs.getInt("party_participants_min"));
				partyVO.setParty_status(rs.getString("party_status"));
				partyVO.setHiredate(rs.getDate("hiredate"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
		return partyVO;
	}

	@Override
	public List<PartyVO> getAll() {
		List<PartyVO> list = new ArrayList<PartyVO>();
		PartyVO empVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				partyVO = new PartyVO();
				empVO.setEmpno(rs.getInt("empno"));
				empVO.setEname(rs.getString("ename"));
				empVO.setJob(rs.getString("job"));
				empVO.setHiredate(rs.getDate("hiredate"));
				empVO.setSal(rs.getDouble("sal"));
				empVO.setComm(rs.getDouble("comm"));
				empVO.setDeptno(rs.getInt("deptno"));
				list.add(empVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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

	public static void main(String[] args) {

       PartyJDBCDAO dao = new PartyJDBCDAO();

		// 新增
		EmpVO empVO1 = new EmpVO();
		empVO1.setEname("吳永志1");
		empVO1.setJob("MANAGER");
		empVO1.setHiredate(java.sql.Date.valueOf("2005-01-01"));
		empVO1.setSal(new Double(50000));
		empVO1.setComm(new Double(500));
		empVO1.setDeptno(10);
		dao.insert(empVO1);

		// 修改
		EmpVO empVO2 = new EmpVO();
		empVO2.setEmpno(7001);
		empVO2.setEname("吳永志2");
		empVO2.setJob("MANAGER2");
		empVO2.setHiredate(java.sql.Date.valueOf("2002-01-01"));
		empVO2.setSal(new Double(20000));
		empVO2.setComm(new Double(200));
		empVO2.setDeptno(20);
		dao.update(empVO2);

		// 刪除
		dao.delete(7014);

		// 查詢
		EmpVO empVO3 = dao.findByPrimaryKey(7001);
		System.out.print(empVO3.getEmpno() + ",");
		System.out.print(empVO3.getEname() + ",");
		System.out.print(empVO3.getJob() + ",");
		System.out.print(empVO3.getHiredate() + ",");
		System.out.print(empVO3.getSal() + ",");
		System.out.print(empVO3.getComm() + ",");
		System.out.println(empVO3.getDeptno());
		System.out.println("---------------------");

		// 查詢
		List<EmpVO> list = dao.getAll();
		for (EmpVO aEmp : list) {
			System.out.print(aEmp.getEmpno() + ",");
			System.out.print(aEmp.getEname() + ",");
			System.out.print(aEmp.getJob() + ",");
			System.out.print(aEmp.getHiredate() + ",");
			System.out.print(aEmp.getSal() + ",");
			System.out.print(aEmp.getComm() + ",");
			System.out.print(aEmp.getDeptno());
			System.out.println();
		}
	}

}
		

