package com.party.model;

import java.util.*;
import java.sql.*;


public class PartyJDBCDAO implements PartyDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/Team4DB?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = "INSERT INTO party (party_title, party_start_time, party_end_time, party_intro, party_participants_max, party_participants_min, party_remarks, member_id, shop_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT party_id, party_title, party_start_time, party_end_time, party_intro, party_participants_max, party_participants_min, party_remarks, member_id, shop_id FROM party order by party_id";
	private static final String GET_ONE_STMT = "SELECT party_id, party_title, party_start_time, party_end_time, party_intro, party_participants_max, party_participants_min, party_remarks, member_id, shop_id FROM party where party_id = ?";
	private static final String UPDATE = "UPDATE party set party_title=?, party_start_time=?, party_end_time=?, party_intro=?, party_participants_max=?, party_participants_min=?, party_remarks=? , member_id=?, shop_id=? where party_id = ?";
	private static final String DELETE = "DELETE FROM party where party_id = ?";
	public static final String FIND_BY_ALL_MEMBER_ID = "SELECT * FROM party where member_id = ?";
	public static final String FIND_BY_MEMBER_ID = "SELECT * FROM party where member_id = ?";
	
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
			pstmt.setString(7, partyVO.getParty_remarks());
			pstmt.setInt(8, partyVO.getMember_id()); 
			pstmt.setInt(9, partyVO.getShop_id());

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
			pstmt.setString(7, partyVO.getParty_remarks());
			pstmt.setInt(8, partyVO.getMember_id()); 
			pstmt.setInt(9, partyVO.getShop_id());
			pstmt.setInt(10, partyVO.getParty_id());

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
	public void delete(Integer party_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, party_id);

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
				// empVo ????????? Domain objects
				partyVO = new PartyVO();
				partyVO.setParty_title(rs.getString("party_title"));
				partyVO.setParty_start_time(rs.getTimestamp("party_start_time"));
				partyVO.setParty_end_time(rs.getTimestamp("party_end_time"));
				partyVO.setParty_intro(rs.getString("party_intro"));
				partyVO.setParty_participants_max(rs.getInt("party_participants_max"));
				partyVO.setParty_participants_min(rs.getInt("party_participants_min"));
				partyVO.setParty_remarks(rs.getString("party_remarks"));
				partyVO.setMember_id(rs.getInt("member_id"));
				partyVO.setShop_id(rs.getInt("shop_id"));
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
				// empVO ????????? Domain objects
				partyVO = new PartyVO();
				partyVO.setParty_id(rs.getInt("party_id"));
				partyVO.setParty_title(rs.getString("party_title"));
				partyVO.setParty_start_time(rs.getTimestamp("party_start_time"));
				partyVO.setParty_end_time(rs.getTimestamp("party_end_time"));
				partyVO.setParty_intro(rs.getString("party_intro"));
				partyVO.setParty_participants_max(rs.getInt("party_participants_max"));
				partyVO.setParty_participants_min(rs.getInt("party_participants_min"));
				partyVO.setParty_remarks(rs.getString("party_remarks"));
				partyVO.setMember_id(rs.getInt("member_id"));
				partyVO.setShop_id(rs.getInt("shop_id"));
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
	
	
	@Override
	public List<PartyVO> getAllmyparty(Integer party_id) {
		List<PartyVO> list = new ArrayList<PartyVO>();
		PartyVO partyVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FIND_BY_ALL_MEMBER_ID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO ????????? Domain objects
				partyVO = new PartyVO();
				partyVO.setParty_id(rs.getInt("party_id"));
				partyVO.setParty_title(rs.getString("party_title"));
				partyVO.setParty_start_time(rs.getTimestamp("party_start_time"));
				partyVO.setParty_end_time(rs.getTimestamp("party_end_time"));
				partyVO.setParty_intro(rs.getString("party_intro"));
				partyVO.setParty_participants_max(rs.getInt("party_participants_max"));
				partyVO.setParty_participants_min(rs.getInt("party_participants_min"));
				partyVO.setParty_remarks(rs.getString("party_remarks"));
				partyVO.setMember_id(rs.getInt("member_id"));
				partyVO.setShop_id(rs.getInt("shop_id"));
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
	
	

	@Override
	public Set<PartyVO> getAllmypartybymember(Integer member_id) {
		Set<PartyVO> list = new LinkedHashSet();
		PartyVO partyVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FIND_BY_ALL_MEMBER_ID);
			pstmt.setInt(1, member_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO ????????? Domain objects
				partyVO = new PartyVO();
				partyVO.setParty_id(rs.getInt("party_id"));
				partyVO.setParty_title(rs.getString("party_title"));
				partyVO.setParty_start_time(rs.getTimestamp("party_start_time"));
				partyVO.setParty_end_time(rs.getTimestamp("party_end_time"));
				partyVO.setParty_intro(rs.getString("party_intro"));
				partyVO.setParty_participants_max(rs.getInt("party_participants_max"));
				partyVO.setParty_participants_min(rs.getInt("party_participants_min"));
				partyVO.setParty_remarks(rs.getString("party_remarks"));
				partyVO.setMember_id(rs.getInt("member_id"));
				partyVO.setShop_id(rs.getInt("shop_id"));
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
		return null;
	}
	
	
	
	
	

	public static void main(String[] args) {

       PartyJDBCDAO dao = new PartyJDBCDAO();

		// ??????
//		PartyVO partyVO1 = new PartyVO();
//		System.out.println("1");
//		partyVO1.setMember_id(1);
//		System.out.println("2");
//		partyVO1.setShop_id(1);
//		System.out.println("3");
//		partyVO1.setParty_title("??????");
//		System.out.println("4");
//		partyVO1.setParty_intro("??????");
//		System.out.println("5");
//		partyVO1.setParty_participants_max(10);
//		System.out.println("6");
//		partyVO1.setParty_participants_min(20);
//		System.out.println("7");
//		partyVO1.setParty_start_time(Timestamp.valueOf("2021-06-14 00:00:00"));
//		System.out.println("8");
//		partyVO1.setParty_end_time(Timestamp.valueOf("2021-06-14 00:00:00"));
//		System.out.println("9");
//		partyVO1.setParty_remarks("??????");
//		System.out.println("10");
//		dao.insert(partyVO1);
//	}
//
//		 ??????
//		PartyVO partyVO2 = new PartyVO();
//		partyVO2.setParty_id(3);
//		System.out.println("1");
//		
//		partyVO2.setParty_title("????????????123");
//		System.out.println("2");
//		
//		partyVO2.setParty_intro("????????????123");
//		System.out.println("3");
//		
//		partyVO2.setParty_participants_max(50);
//		System.out.println("4");
//		
//		partyVO2.setParty_participants_min(30);
//		System.out.println("5");
//		
//		partyVO2.setParty_start_time(Timestamp.valueOf("2021-08-06 01:01:01"));
//		System.out.println("6");
//		
//		partyVO2.setParty_end_time(Timestamp.valueOf("2021-04-25 02:02:02"));
//		System.out.println("7");
//		
//		partyVO2.setParty_remarks("??????11");
//		System.out.println("8");
//		partyVO2.setMember_id(1);
//		System.out.println("9");
//		partyVO2.setShop_id(1);
//		System.out.println("10");
//		dao.update(partyVO2);
//	}
//
//		 ??????
//		dao.delete(2);
//		System.out.println("??????");
//	}
//
//		// ??????
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
//		// ??????
       Set<PartyVO> set = dao.getAllmypartybymember(1);
		for (PartyVO aEmp : set) {
			System.out.print(aEmp.getParty_id() + ",");
			System.out.print(aEmp.getMember_id() + ",");
			System.out.print(aEmp.getShop_id() + ",");
			System.out.print(aEmp.getParty_title() + ",");
			System.out.print(aEmp.getParty_start_time() + ",");
			System.out.print(aEmp.getParty_end_time() + ",");
			System.out.print(aEmp.getParty_intro() + ",");
			System.out.print(aEmp.getParty_participants_max() + ",");
			System.out.print(aEmp.getParty_participants_min() + ",");
			System.out.print(aEmp.getParty_remarks() + ",");
			System.out.println();
		}
	}

	

}
		

