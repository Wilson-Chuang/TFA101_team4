package com.member_follower.model;

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

import com.comment.model.CommentVO;

public class Member_FollowerDAO implements Member_FollowerDAO_Interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Team4DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = 
			"INSERT INTO member_follower (member_id,member_id_follower) VALUES (?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT * FROM member_follower order by member_follower_id";
		private static final String GET_ONE_STMT = 
			"SELECT * FROM member_follower where member_follower_id= ?";
		private static final String DELETE = 
			"DELETE FROM member_follower where member_follower_id = ?";
		private static final String DELETE_FOL = 
				"DELETE FROM member_follower where member_id = ? and member_id_follower=?";
		private static final String UPDATE = 
			"UPDATE member_follower set member_id=?,member_id_follower=? where member_follower_id=?" ;
		private static final String GET_ALL_FOLLOWING = 
				"SELECT member_id FROM member_follower where member_id_follower=?";
		private static final String GET_ALL_FOLLOWED = 
				"SELECT member_id_follower FROM member_follower where member_id=?";
		private static final String CHECK_FOLLOWED = 
				"SELECT* FROM member_follower where member_id=? and member_id_follower=?";
		private static final String COUNT_FANS = 
				"SELECT count(*) FROM member_follower where member_id =?";
		@Override
		public void insert(Member_FollowerVO Member_FollowerVO) {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);
				pstmt.setInt(1,Member_FollowerVO.getMEMBER_ID());
				pstmt.setInt(2,Member_FollowerVO.getMEMBER_ID_FOLLOWER());
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
		public void update(Member_FollowerVO Member_FollowerVO) {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);
				pstmt.setInt(1,Member_FollowerVO.getMEMBER_ID());
				pstmt.setInt(2,Member_FollowerVO.getMEMBER_ID_FOLLOWER());
				pstmt.setInt(3	,Member_FollowerVO.getMEMBER_FOLLOWER_ID());
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
		public void delete(Integer MEMBER_FOLLOWER_ID) {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);
				pstmt.setInt(1,MEMBER_FOLLOWER_ID);

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
		public void delete(Member_FollowerVO Member_FollowerVO) {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE_FOL);
				pstmt.setInt(1,Member_FollowerVO.getMEMBER_ID());
				pstmt.setInt(2,Member_FollowerVO.getMEMBER_ID_FOLLOWER());
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
		public Member_FollowerVO getOneStmt(Integer MEMBER_FOLLOWER_ID) {
			Member_FollowerVO mem = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				con = ds.getConnection();
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
				con = ds.getConnection();
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
			List<Integer> list= new ArrayList<Integer>();
			Member_FollowerVO mem_fol=null;
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_FOLLOWING);
				pstmt.setInt(1, MEMBER_ID_FOLLOWER);
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					mem_fol = new Member_FollowerVO();
//					mem_fol.setMEMBER_FOLLOWER_ID(rs.getInt("MEMBER_FOLLOWER_ID"));
//					mem_fol.setMEMBER_ID(rs.getInt("MEMBER_ID"));
					int member_id=(rs.getInt("MEMBER_ID"));
//					mem_fol.setMEMBER_ID_FOLLOWER(rs.getInt("MEMBER_ID_FOLLOWER"));
					list.add(member_id);
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
		public List<Integer> GET_ALL_FOLLOWED(Integer MEMBER_ID) {
			List<Integer> list= new ArrayList<Integer>();
			Member_FollowerVO mem_fol=null;
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_FOLLOWED);
				pstmt.setInt(1, MEMBER_ID);
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					mem_fol = new Member_FollowerVO();
//					mem_fol.setMEMBER_FOLLOWER_ID(rs.getInt("MEMBER_FOLLOWER_ID"));
//					mem_fol.setMEMBER_ID(rs.getInt("MEMBER_ID"));
//					mem_fol.setMEMBER_ID_FOLLOWER(rs.getInt("MEMBER_ID_FOLLOWER"));
					int member_id_fol=(rs.getInt("MEMBER_ID_FOLLOWER"));
					list.add(member_id_fol);
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
		public boolean check_follow(Integer MEMBER_ID, Integer MEMBER_ID_FOLLOWER) {
			
			Member_FollowerVO mem_fol=null;
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(CHECK_FOLLOWED);
				pstmt.setInt(1, MEMBER_ID);
				pstmt.setInt(2, MEMBER_ID_FOLLOWER);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					mem_fol = new Member_FollowerVO();
					mem_fol.setMEMBER_FOLLOWER_ID(rs.getInt("MEMBER_FOLLOWER_ID"));
					mem_fol.setMEMBER_ID(rs.getInt("MEMBER_ID"));
					mem_fol.setMEMBER_ID_FOLLOWER(rs.getInt("MEMBER_ID_FOLLOWER"));
					return true;
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
			
			return false	;
		}
		@Override
		public Integer count_fans(Integer Member_ID) {
			int count=0;
			CommentVO com = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(COUNT_FANS);
				pstmt.setInt(1, Member_ID);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					count= rs.getInt(1);
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

			return count;
		}
		
}
