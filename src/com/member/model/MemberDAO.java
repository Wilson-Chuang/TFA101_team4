package com.member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemberDAO implements MemberDAO_Interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/team4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO member (member_email,member_password) VALUES (?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM member order by member_id";
	private static final String ACCOUNT_CHECK = "SELECT MEMBER_EMAIL FROM member order by member_id";
	private static final String GET_ONE_STMT = "SELECT * FROM member where member_email = ?";
	private static final String GET_ONE_BY_ID = "SELECT * FROM member where member_id = ?";
	private static final String DELETE = "DELETE FROM member where member_email = ?";
	private static final String UPDATE = "UPDATE member set member_name=?,member_gender=?,member_birth=?,member_phone=?,member_address=?,member_pic=?,member_update_time=?,member_age=? where member_email=?";
	@Override
	public void insert(MemberVO MemberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1,MemberVO.getMember_email());
			pstmt.setString(2,MemberVO.getMember_password());
			pstmt.execute();
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1,MemberVO.getMember_name());
			pstmt.setInt(2, MemberVO.getMember_gender());
			pstmt.setDate(3, MemberVO.getMember_birth());
			pstmt.setString(4, MemberVO.getMember_phone());
			pstmt.setString(5, MemberVO.getMember_address());
			pstmt.setString(6, MemberVO.getMember_pic());
			pstmt.setTimestamp(7, MemberVO.getMember_update_time());
			pstmt.setInt(8, MemberVO.getMember_age());
			pstmt.setString(9, MemberVO.getMember_email());
			pstmt.execute();
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1,MEMBER_EMAIL);

			pstmt.execute();
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
		
		MemberVO MemberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, MEMBER_EMAIL);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MemberVO = new MemberVO();
				MemberVO.setMember_id(rs.getInt("MEMBER_ID"));
				MemberVO.setMember_email(rs.getString("MEMBER_EMAIL"));
				MemberVO.setMember_password(rs.getString("MEMBER_PASSWORD"));
				MemberVO.setMember_pic(rs.getString("MEMBER_PIC"));
				MemberVO.setMember_name(rs.getString("MEMBER_NAME"));
				MemberVO.setMember_gender(rs.getInt("MEMBER_gender"));
				MemberVO.setMember_fans(rs.getInt("MEMBER_FANS"));
				MemberVO.setMember_age(rs.getInt("MEMBER_AGE"));
				MemberVO.setMember_birth(rs.getDate("MEMBER_BIRTH"));
				MemberVO.setMember_phone(rs.getString("MEMBER_PHONE"));
				MemberVO.setMember_address(rs.getString("MEMBER_ADDRESS"));
				MemberVO.setMember_point(rs.getInt("MEMBER_POINT"));
				MemberVO.setMember_create_time(rs.getTimestamp("MEMBER_CREATE_TIME"));
				MemberVO.setMember_update_time(rs.getTimestamp("MEMBER_UPDATE_TIME"));
				MemberVO.setMember_status(rs.getInt("MEMBER_STATUS"));
			}

		} catch (SQLException se) {
			se.printStackTrace();
			// Clean up JDBC resources
		}  finally {
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

		return MemberVO;
	
	}
	@Override
	public MemberVO GET_ONE_BY_ID(Integer MEMBER_ID) {
		
		MemberVO MemberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_BY_ID);
			
			pstmt.setInt(1, MEMBER_ID);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MemberVO = new MemberVO();
				MemberVO.setMember_id(rs.getInt("MEMBER_ID"));
				MemberVO.setMember_email(rs.getString("MEMBER_EMAIL"));
				MemberVO.setMember_password(rs.getString("MEMBER_PASSWORD"));
				MemberVO.setMember_pic(rs.getString("MEMBER_PIC"));
				MemberVO.setMember_name(rs.getString("MEMBER_NAME"));
				MemberVO.setMember_gender(rs.getInt("MEMBER_gender"));
				MemberVO.setMember_fans(rs.getInt("MEMBER_FANS"));
				MemberVO.setMember_age(rs.getInt("MEMBER_AGE"));
				MemberVO.setMember_birth(rs.getDate("MEMBER_BIRTH"));
				MemberVO.setMember_phone(rs.getString("MEMBER_PHONE"));
				MemberVO.setMember_address(rs.getString("MEMBER_ADDRESS"));
				MemberVO.setMember_point(rs.getInt("MEMBER_POINT"));
				MemberVO.setMember_create_time(rs.getTimestamp("MEMBER_CREATE_TIME"));
				MemberVO.setMember_update_time(rs.getTimestamp("MEMBER_UPDATE_TIME"));
				MemberVO.setMember_status(rs.getInt("MEMBER_STATUS"));
			}
			
		} catch (SQLException se) {
			se.printStackTrace();
			// Clean up JDBC resources
		}  finally {
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
		
		return MemberVO;
		
	}
	@Override
	public List<MemberVO> getAll() {
		List<MemberVO> list= new ArrayList<MemberVO>();
		MemberVO mem=null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
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
	public List<String> accountCheck() {
		List<String> list= new ArrayList<String>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(ACCOUNT_CHECK);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("MEMBER_EMAIL"));
				}
			
			}catch (SQLException se) {
				se.printStackTrace();
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
