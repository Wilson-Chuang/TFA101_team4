package com.party.model;

import java.util.*;
import java.sql.*;
import java.sql.Timestamp;


public class PartyJDBCDAO implements PartyDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/party?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = "INSERT INTO party (party_id, party_title, party_start_time, party_end_time, party_intro, party_participants_max, party_participants_min) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT party_id, party_title, party_start_time, party_end_time, party_intro, party_participants_max, party_participants_min FROM party order by party_id";
	private static final String GET_ONE_STMT = "SELECT party_id, party_title, party_start_time, party_end_time, party_intro, party_participants_max, party_participants_min FROM party where party_id = ?";
	private static final String UPDATE = "UPDATE party set party_id=?, party_title=?, party_start_time=?, party_end_time=?, party_intro=?, party_participants_max=?, party_participants_min=? where party_id = ?";

	@Override
	public void insert(PartyVO partyVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setInt(1, partyVO.getParty_id());
			pstmt.setString(2, partyVO.getParty_title());
			pstmt.setTimestamp(3, partyVO.getParty_start_time());
			pstmt.setTimestamp(4, partyVO.getParty_end_time());
			pstmt.setString(5, partyVO.getParty_intro());
			pstmt.setInt(6, partyVO.getParty_participants_max());
			pstmt.setInt(7, partyVO.getParty_participants_min());

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

			pstmt.setInt(1, partyVO.getParty_id());
			pstmt.setString(2, partyVO.getParty_title());
			pstmt.setTimestamp(3, partyVO.getParty_start_time());
			pstmt.setTimestamp(4, partyVO.getParty_end_time());
			pstmt.setString(5, partyVO.getParty_intro());
			pstmt.setInt(6, partyVO.getParty_participants_max());
			pstmt.setInt(7, partyVO.getParty_participants_min());

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

		PartyVO partyVO = null;
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
//	partyVO.setParty_id(rs.getInt("party_id"));
//	partyVO.setMember_id(rs.getInt("member_id"));
//	partyVO.setShop_id(rs.getInt("shop_id"));
//	partyVO.setReserv_id(rs.getInt("reserv_id"));
				partyVO.setParty_title(rs.getString("party_title"));
				partyVO.setParty_start_time(rs.getTimestamp("party_start_time"));
				partyVO.setParty_end_time(rs.getTimestamp("party_end_time"));
				partyVO.setParty_intro(rs.getString("party_intro"));
				partyVO.setParty_participants_max(rs.getInt("party_participants_max"));
				partyVO.setParty_participants_min(rs.getInt("party_participants_min"));
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
		PartyVO partyVO = null;

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
				partyVO.setParty_id(rs.getInt("party_id"));
				partyVO.setParty_title(rs.getString("party_title"));
				partyVO.setParty_start_time(rs.getTimestamp("party_start_time"));
				partyVO.setParty_end_time(rs.getTimestamp("party_end_time"));
				partyVO.setParty_intro(rs.getString("party_intro"));
				partyVO.setParty_participants_max(rs.getInt("party_participants_max"));
				partyVO.setParty_participants_min(rs.getInt("party_participants_min"));
				list.add(partyVO); // Store the row in the list
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
//		PartyVO partyVO1 = new PartyVO();
//		partyVO1.setParty_id(0003);
//		System.out.println("111111");
//		partyVO1.setParty_title("揪團標題測試");
//		System.out.println("111111");
//		partyVO1.setParty_intro("揪團介紹測試");
//		System.out.println("111111");
//		partyVO1.setParty_participants_max(10);
//		System.out.println("111111");
//		partyVO1.setParty_participants_min(20);
//		System.out.println("111111");
//		partyVO1.setParty_start_time(Timestamp.valueOf("2021-06-14 00:00:00"));
//		System.out.println("111111");
//		partyVO1.setParty_end_time(Timestamp.valueOf("2021-06-14 00:00:00"));
//		System.out.println("111111");
//		dao.insert(partyVO1);
////	}
//
		// 修改
		PartyVO partyVO2 = new PartyVO();
		partyVO2.setParty_id(7001);
		System.out.println("111111");
		
		partyVO2.setParty_title("修改測試");
		System.out.println("111111");
		
		partyVO2.setParty_intro("修改測試");
		System.out.println("111111");
		
		partyVO2.setParty_participants_max(20);
		System.out.println("111111");
		
		partyVO2.setParty_participants_min(40);
		System.out.println("111111");
		
		partyVO2.setParty_start_time(Timestamp.valueOf("2021-06-14 00:00:00"));
		System.out.println("111111");
		
		partyVO2.setParty_end_time(Timestamp.valueOf("2021-06-14 00:00:00"));
		System.out.println("111111");
		
		dao.update(partyVO2);
	}
//
//		// 刪除
//		dao.delete(7014);
//
//		// 查詢
//		EmpVO empVO3 = dao.findByPrimaryKey(7001);
//		System.out.print(empVO3.getEmpno() + ",");
//		System.out.print(empVO3.getEname() + ",");
//		System.out.print(empVO3.getJob() + ",");
//		System.out.print(empVO3.getHiredate() + ",");
//		System.out.print(empVO3.getSal() + ",");
//		System.out.print(empVO3.getComm() + ",");
//		System.out.println(empVO3.getDeptno());
//		System.out.println("---------------------");
//
//		// 查詢
//		List<EmpVO> list = dao.getAll();
//		for (EmpVO aEmp : list) {
//			System.out.print(aEmp.getEmpno() + ",");
//			System.out.print(aEmp.getEname() + ",");
//			System.out.print(aEmp.getJob() + ",");
//			System.out.print(aEmp.getHiredate() + ",");
//			System.out.print(aEmp.getSal() + ",");
//			System.out.print(aEmp.getComm() + ",");
//			System.out.print(aEmp.getDeptno());
//			System.out.println();
//		}
//	}

}
		

