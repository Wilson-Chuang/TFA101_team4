package com.party_participants.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.party.model.PartyVO;

import util.Util;

public class PartyParticipantsDAO implements PartyParticipantsDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Team4DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public static final String INSERT_STMT = "INSERT INTO PARTY_PARTICIPANTS(PARTY_MEMBER_ID, PARTY_ID) VALUES(?, ?)";
	public static final String UPDATE_STMT = "UPDATE PARTY_PARTICIPANTS SET PARTY_MEMBER_ID = ?, PARTY_ID = ? PARTY_UP_TIME = ? WHERE PARTY_PARTICIPANTS_ID = ?";
	public static final String DELETE_STMT = "DELETE FROM PARTY_PARTICIPANTS WHERE PARTY_MEMBER_ID = ? AND PARTY_ID = ?";
	public static final String FIND_BY_PK = "SELECT * FROM PARTY_PARTICIPANTS WHERE PARTY_PARTICIPANTS_ID = ?";
	public static final String GET_ALL = "SELECT * FROM PARTY_PARTICIPANTS";
	public static final String COUNT_BY_POST_ID = "SELECT COUNT(*) FROM PARTY_PARTICIPANTS WHERE PARTY_ID = ?";
	public static final String FIND_BY_MEMBER_ID = "SELECT * FROM PARTY_PARTICIPANTS WHERE PARTY_MEMBER_ID = ?";
	
	@Override
	public void insert(PartyParticipantsVO partyparticipantsVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, partyparticipantsVO.getParty_member_id());
			pstmt.setInt(2, partyparticipantsVO.getParty_id());
			
			pstmt.executeUpdate("set auto_increment_offset=1;");
			pstmt.executeUpdate("set auto_increment_increment=1;");
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
		public void update(PartyParticipantsVO partyparticipantsVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE_STMT);
				pstmt.setInt(1, partyparticipantsVO.getParty_member_id());
				pstmt.setInt(2, partyparticipantsVO.getParty_id());
				pstmt.setTimestamp(3, partyparticipantsVO.getParty_up_time());
				pstmt.setInt(4, partyparticipantsVO.getParty_participants_id());
				
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
		public void delete(Integer party_member_id, Integer party_id) {
			int updateCount_party = 0;

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();

				// 1●設定於 pstm.executeUpdate()之前
				con.setAutoCommit(false);

				// 先刪除員工
				pstmt = con.prepareStatement(DELETE_STMT);
				pstmt.setInt(1, party_member_id);
				pstmt.setInt(2, party_id);
				updateCount_party = pstmt.executeUpdate();
				// 再刪除部門
//				pstmt = con.prepareStatement(DELETE_STMT);
//				pstmt.setInt(1, deptno);
//				pstmt.executeUpdate();

				// 2●設定於 pstm.executeUpdate()之後
				con.commit();
				con.setAutoCommit(true);
//				System.out.println("刪除部門編號" + deptno + "時,共有員工" + updateCount_EMPs
//						+ "人同時被刪除");
				
				// Handle any SQL errors
			} catch (SQLException se) {
				if (con != null) {
					try {
						// 3●設定於當有exception發生時之catch區塊內
						con.rollback();
					} catch (SQLException excep) {
						throw new RuntimeException("rollback error occured. "
								+ excep.getMessage());
					}
				}
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
		public Integer countByPostID(Integer party_id) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			Integer count = 0;
			
			try {
				con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
				pstmt = con.prepareStatement(COUNT_BY_POST_ID);
				pstmt.setInt(1, party_id);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					count = rs.getInt(1);
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
			
			return count;
		}
		
		
		@Override
		public PartyParticipantsVO findByPK(Integer party_participants_id) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			PartyParticipantsVO forumPostLike = null;
			
			try {
				con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
				pstmt = con.prepareStatement(FIND_BY_PK);
				pstmt.setInt(1, party_participants_id);
				
				rs = pstmt.executeQuery();
				while(rs.next()) {
					forumPostLike = new PartyParticipantsVO();
					forumPostLike.setParty_member_id(rs.getInt("PARTY_MEMBER"));
					forumPostLike.setParty_id(rs.getInt("PARTY_ID"));
					forumPostLike.setParty_up_time(rs.getTimestamp("PARTY_UP_TIME"));
				}
				
			} catch (SQLException se) {
				se.printStackTrace();
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException se) {
						se.printStackTrace();
					}
				}
				
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						se.printStackTrace();
					}
				}

				if (con != null) {
					try {
						con.close();
					} catch (SQLException se) {
						se.printStackTrace();
					}
				}
			}	
			return forumPostLike;
		}

		
		@Override
		public List<PartyParticipantsVO> getAll() {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<PartyParticipantsVO> likelist = new ArrayList();
			PartyParticipantsVO forumPostLike = null;
			
			try {
				con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
				pstmt = con.prepareStatement(GET_ALL);
				
				rs = pstmt.executeQuery();
				while(rs.next()) {
					forumPostLike = new PartyParticipantsVO();
					forumPostLike.setParty_participants_id(rs.getInt("PARTY_PARTICIPANTS_ID"));
					forumPostLike.setParty_member_id(rs.getInt("PARTY_MEMBER_ID"));
					forumPostLike.setParty_id(rs.getInt("PARTY_ID"));
					forumPostLike.setParty_up_time(rs.getTimestamp("PARTY_UP_TIME"));
					
					likelist.add(forumPostLike);
				}
				
			} catch (SQLException se) {
				se.printStackTrace();
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException se) {
						se.printStackTrace();
					}
				}
				
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						se.printStackTrace();
					}
				}

				if (con != null) {
					try {
						con.close();
					} catch (SQLException se) {
						se.printStackTrace();
					}
				}
			}	
			
			return likelist;
		}

		@Override
		public List<PartyParticipantsVO> getMemberAll(Integer member_id) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<PartyParticipantsVO> likelist = new ArrayList();
			PartyParticipantsVO forumPostLike = null;
			
			try {
				con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
				pstmt = con.prepareStatement(FIND_BY_MEMBER_ID);
				pstmt.setInt(1, member_id);
				
				rs = pstmt.executeQuery();
				while(rs.next()) {
					forumPostLike = new PartyParticipantsVO();
					forumPostLike.setParty_participants_id(rs.getInt("PARTY_PARTICIPANTS_ID"));
					forumPostLike.setParty_member_id(rs.getInt("PARTY_MEMBER_ID"));
					forumPostLike.setParty_id(rs.getInt("PARTY_ID"));
					forumPostLike.setParty_up_time(rs.getTimestamp("PARTY_UP_TIME"));
					
					likelist.add(forumPostLike);
				}
				
			} catch (SQLException se) {
				se.printStackTrace();
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException se) {
						se.printStackTrace();
					}
				}
				
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						se.printStackTrace();
					}
				}

				if (con != null) {
					try {
						con.close();
					} catch (SQLException se) {
						se.printStackTrace();
					}
				}
			}	
			
			return likelist;
		}
	}
