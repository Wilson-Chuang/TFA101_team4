package com.party_participants.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


import util.Util;

import com.party.model.PartyVO;;

public class PartyParticipantsJDBCDAO implements PartyParticipantsDAO_interface {
	static String driver = "com.mysql.cj.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306/party?serverTimezone=Asia/Taipei";
	static String userid = "root";
	static String passwd = "password";
	
	public static final String INSERT_STMT = "INSERT INTO PARTY_PARTICIPANTS(PARTY_MEMBER_ID, PARTY_ID, PARTY_UP_TIME) VALUES(?, ?, ?)";
	public static final String UPDATE_STMT = "UPDATE PARTY_PARTICIPANTS SET PARTY_MEMBER_ID = ?, PARTY_ID = ? PARTY_UP_TIME = ? WHERE PARTY_PARTICIPANTS_ID = ?";
	public static final String DELETE_STMT = "DELETE FROM PARTY_PARTICIPANTS WHERE PARTY_PARTICIPANTS_ID = ?";
	public static final String FIND_BY_PK = "SELECT * FROM PARTY_PARTICIPANTS WHERE PARTY_PARTICIPANTS_ID = ?";
	public static final String GET_ALL = "SELECT * FROM PARTY_PARTICIPANTS";
	public static final String COUNT_BY_POST_ID = "SELECT COUNT(*) FROM PARTY_PARTICIPANTS WHERE PARTY_ID = ?";
	public static final String FIND_BY_MEMBER_ID = "SELECT * FROM PARTY_PARTICIPANTS WHERE MEMBER_ID = ?";
	
	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}
	
	@Override
	public void insert(PartyParticipantsVO PartyParticipants) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, PartyParticipants.getParty_member_id());
			pstmt.setInt(2, PartyParticipants.getParty_id());
			pstmt.setTimestamp(3, PartyParticipants.getParty_up_time());

			pstmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
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

	}

	@Override
	public void update(PartyParticipantsVO PartyParticipants) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setInt(1, PartyParticipants.getParty_member_id());
			pstmt.setInt(2, PartyParticipants.getParty_id());
			pstmt.setTimestamp(3, PartyParticipants.getParty_up_time());
			pstmt.setInt(4, PartyParticipants.getParty_participants_id());

			pstmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
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

	}

	@Override
	public void delete(Integer party_participants_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setInt(1, party_participants_id);
			
			pstmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
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
		

	}

	@Override
	public PartyParticipantsVO findByPK(Integer party_participants_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PartyParticipantsVO forumPostLike = null;
		
		try {
			con = DriverManager.getConnection(url,userid,passwd);
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
			con = DriverManager.getConnection(url,userid,passwd);
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
	public Integer countByPostID(Integer party_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Integer count = 0;
		
		try {
			con = DriverManager.getConnection(url,userid,passwd);
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
	public List<PartyParticipantsVO> getMemberAll(Integer member_id) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<PartyParticipantsVO> likelist = new ArrayList();
		PartyParticipantsVO forumPostLike = null;
		
		try {
			con = DriverManager.getConnection(url,userid,passwd);
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
	
	
//	public static void main(String[] args) {
//		PartyParticipantsJDBCDAO dao = new PartyParticipantsJDBCDAO();
//		PartyParticipantsVO t1 = new PartyParticipantsVO();
		
//		t1.setParty_member_id(1);
//		t1.setParty_participants_id(2);
//		t1.setParty_id(1);
//		t1.setParty_up_time(Timestamp.valueOf("2021-06-14 00:00:00"));
//		dao.insert(t1);
//	}
//	
//		PartyParticipantsVO t2 = new PartyParticipantsVO();
//	t2.setParty_member_id(1);
//	t2.setParty_participants_id(2);
//	t2.setParty_id(1);
//	t2.setParty_up_time(Timestamp.valueOf("2021-06-15 00:00:00"));
//	dao.update(t2);
//}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
