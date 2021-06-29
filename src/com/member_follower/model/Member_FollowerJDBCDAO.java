package com.member_follower.model;

import java.sql.*;
import java.util.*;


public class Member_FollowerJDBCDAO implements Member_FollowerDAO_Interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/Team4DB?serverTimezone=Asia/Taipei";
	String userid = "David";
	String passwd = "123456";
	private static final String INSERT_STMT = 
			"INSERT INTO member_follower (member_id,member_id_follower) VALUES (?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT * FROM member_follower order by member_follower_id";
		private static final String GET_ONE_STMT = 
			"SELECT * FROM member_follower where member_follower_id= ?";
		private static final String DELETE = 
			"DELETE FROM member_follower where member_follower_id = ?";
		private static final String UPDATE = 
			"UPDATE member_follower set member_id=?,member_id_follower=? where member_follower_id=?" ;
		
		
		@Override
		public void insert(Member_FollowerVO Member_FollowerVO) {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url,userid,passwd);
				pstmt = con.prepareStatement(INSERT_STMT);
				pstmt.setInt(1,Member_FollowerVO.getMEMBER_ID());
				pstmt.setInt(2,Member_FollowerVO.getMEMBER_ID_FOLLOWER());
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
		public void update(Member_FollowerVO Member_FollowerVO) {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url,userid,passwd);
				pstmt = con.prepareStatement(UPDATE);
				pstmt.setInt(1,Member_FollowerVO.getMEMBER_ID());
				pstmt.setInt(2,Member_FollowerVO.getMEMBER_ID_FOLLOWER());
				pstmt.setInt(3	,Member_FollowerVO.getMEMBER_FOLLOWER_ID());
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
		public void delete(Integer MEMBER_FOLLOWER_ID) {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url,userid,passwd);
				pstmt = con.prepareStatement(DELETE);
				pstmt.setInt(1,MEMBER_FOLLOWER_ID);

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
		public Member_FollowerVO getOneStmt(Integer MEMBER_FOLLOWER_ID) {
			Member_FollowerVO mem = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url,userid,passwd);
				pstmt = con.prepareStatement(GET_ONE_STMT);
				pstmt.setInt(1, MEMBER_FOLLOWER_ID);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					mem = new Member_FollowerVO();
					mem.setMEMBER_FOLLOWER_ID(rs.getInt("MEMBER_FOLLOWER_ID"));
					mem.setMEMBER_ID(rs.getInt("MEMBER_ID"));
					mem.setMEMBER_ID_FOLLOWER(rs.getInt("MEMBER_ID_FOLLOWER"));
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
		public List<Member_FollowerVO> getAll() {
			List<Member_FollowerVO> list= new ArrayList<Member_FollowerVO>();
			Member_FollowerVO mem_fol=null;
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url,userid,passwd);
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					mem_fol = new Member_FollowerVO();
					mem_fol.setMEMBER_FOLLOWER_ID(rs.getInt("MEMBER_FOLLOWER_ID"));
					mem_fol.setMEMBER_ID(rs.getInt("MEMBER_ID"));
					mem_fol.setMEMBER_ID_FOLLOWER(rs.getInt("MEMBER_ID_FOLLOWER"));
					list.add(mem_fol);
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
		public List<Integer> GET_ALL_FOLLOWING(Integer MEMBER_ID_FOLLOWER) {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public void delete(Member_FollowerVO Member_FollowerVO) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public List<Integer> GET_ALL_FOLLOWED(Integer MEMBER_ID) {
			// TODO Auto-generated method stub
			return null;
		}
}
