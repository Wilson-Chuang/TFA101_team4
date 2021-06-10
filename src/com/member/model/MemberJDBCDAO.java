package com.member.model;

import java.sql.*;
import java.util.*;

public class MemberJDBCDAO implements MemberDAO_Interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/Team4DB?serverTimezone=Asia/Taipei";
	String userid = "David";
	String passwd = "123456";
	private static final String INSERT_STMT = "INSERT INTO member (member_email,member_password) VALUES (?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM member order by member_email";
	private static final String GET_ONE_STMT = "SELECT * FROM member where member_email = ?";
	private static final String DELETE = "DELETE FROM member where member_email = ?";
	private static final String UPDATE = "UPDATE member set member_name=?,member_gender=?,member_birth=?,member_phone=?,member_address=?,member_update_time=? where member_email=?";
		@Override
		public void insert(MemberVO MemberVO) {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url,userid,passwd);
				pstmt = con.prepareStatement(INSERT_STMT);
				pstmt.setString(1,MemberVO.getMember_email());
				pstmt.setString(2,MemberVO.getMember_password());
				
				pstmt.execute();
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
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
		public void update(MemberVO MemberVO) {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url,userid,passwd);
				pstmt = con.prepareStatement(UPDATE);
				pstmt.setString(1,MemberVO.getMember_name());
				pstmt.setInt(2, MemberVO.getMember_gender());
				pstmt.setDate(3, MemberVO.getMember_birth());
				pstmt.setString(4, MemberVO.getMember_phone());
				pstmt.setString(5, MemberVO.getMember_address());
				pstmt.setTimestamp(6, MemberVO.getMember_update_time());
				pstmt.setString(7, MemberVO.getMember_email());
				pstmt.execute();
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
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
		public void delete(String MEMBER_EMAIL) {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url,userid,passwd);
				pstmt = con.prepareStatement(DELETE);
				pstmt.setString(1,MEMBER_EMAIL);

				pstmt.execute();
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
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
		public MemberVO getOneStmt(String MEMBER_EMAIL) {
			MemberVO mem = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url,userid,passwd);
				pstmt = con.prepareStatement(GET_ONE_STMT);
				pstmt.setString(1, MEMBER_EMAIL);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					mem = new MemberVO();
					mem.setMember_id(rs.getInt("MEMBER_ID"));
					mem.setMember_email(rs.getString("MEMBER_EMAIL"));
					mem.setMember_password(rs.getString("MEMBER_PASSWORD"));
					mem.setMember_name(rs.getString("MEMBER_NAME"));
					mem.setMember_fans(rs.getInt("MEMBER_FANS"));
					mem.setMember_age(rs.getInt("MEMBER_AGE"));
					mem.setMember_birth(rs.getDate("MEMBER_BIRTH"));
					mem.setMember_phone(rs.getString("MEMBER_PHONE"));
					mem.setMember_address(rs.getString("MEMBER_ADDRESS"));
					mem.setMember_point(rs.getInt("MEMBER_POINT"));
					mem.setMember_create_time(rs.getTimestamp("MEMBER_CREATE_TIME"));
					mem.setMember_update_time(rs.getTimestamp("MEMBER_UPDATE_TIME"));
					mem.setMember_status(rs.getInt("MEMBER_STATUS"));
				}

			} catch (SQLException se) {
				se.printStackTrace();
				// Clean up JDBC resources
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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

			return mem;
		
		}
		@Override
		public List<MemberVO> getAll() {
			List<MemberVO> list= new ArrayList<MemberVO>();
			MemberVO mem=null;
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url,userid,passwd);
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					mem = new MemberVO();
					mem.setMember_id(rs.getInt("MEMBER_ID"));
					mem.setMember_email(rs.getString("MEMBER_EMAIL"));
					mem.setMember_password(rs.getString("MEMBER_PASSWORD"));
					mem.setMember_name(rs.getString("MEMBER_NAME"));
					mem.setMember_fans(rs.getInt("MEMBER_FANS"));
					mem.setMember_age(rs.getInt("MEMBER_AGE"));
					mem.setMember_birth(rs.getDate("MEMBER_BIRTH"));
					mem.setMember_phone(rs.getString("MEMBER_PHONE"));
					mem.setMember_address(rs.getString("MEMBER_ADDRESS"));
					mem.setMember_point(rs.getInt("MEMBER_POINT"));
					mem.setMember_create_time(rs.getTimestamp("MEMBER_CREATE_TIME"));
					mem.setMember_update_time(rs.getTimestamp("MEMBER_UPDATE_TIME"));
					mem.setMember_status(rs.getInt("MEMBER_STATUS"));
					list.add(mem);
				}

			} catch (SQLException se) {
				se.printStackTrace();
				// Clean up JDBC resources
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
		public MemberVO GET_ONE_BY_ID(Integer MEMBER_ID) {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public List<String> accountCheck() {
			// TODO Auto-generated method stub
			return null;
		}
}
