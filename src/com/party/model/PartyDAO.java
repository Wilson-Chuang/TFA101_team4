package com.party.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.party_participants.model.PartyParticipantsVO;

import util.Util;


public class PartyDAO implements PartyDAO_interface {
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Team4DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO party (party_title, party_start_time, party_end_time, party_intro, party_participants_max, party_participants_min, party_remarks, member_id, shop_id) VALUES (?, ? ,?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT party_id, party_title, party_start_time, party_end_time, party_intro, party_participants_max, party_participants_min, party_remarks, member_id, shop_id FROM party order by party_id";
	private static final String GET_ONE_STMT = "SELECT party_id, party_title, party_start_time, party_end_time, party_intro, party_participants_max, party_participants_min, party_remarks, member_id, shop_id FROM party where party_id = ?";
	private static final String DELETE = "DELETE FROM party where party_id = ?";
	private static final String UPDATE = "UPDATE party set party_title=?, party_start_time=?, party_end_time=?, party_intro=?, party_participants_max=?, party_participants_min=?, party_remarks=?, member_id=?, shop_id=? where party_id = ?";
	public static final String FIND_BY_ALL_MEMBER_ID = "SELECT * FROM party where member_id = ?";
	
	
	
	
	public static final String FIND_BY_PARTY_ID = "SELECT * FROM party where party_id = ?";
	
	@Override
	public void insert(PartyVO partyVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
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

			con = ds.getConnection();
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, party_id);

			pstmt.executeUpdate();

			// Handle any driver errors
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, party_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
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
			}

			// Handle any driver errors
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

			con = ds.getConnection();
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
				partyVO.setParty_remarks(rs.getString("party_remarks"));
				partyVO.setMember_id(rs.getInt("member_id"));
				partyVO.setShop_id(rs.getInt("shop_id"));  
				list.add(partyVO); // Store the row in the list
			}

			// Handle any driver errors
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

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<PartyVO> likelist = new ArrayList();
		PartyVO forumPostLike = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_ALL_MEMBER_ID);
			pstmt.setInt(1, party_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				forumPostLike = new PartyVO();
				forumPostLike.setParty_id(rs.getInt("party_id"));
				System.out.println(rs.getInt("party_id"));
				forumPostLike.setParty_title(rs.getString("party_title"));
				forumPostLike.setParty_start_time(rs.getTimestamp("party_start_time"));
				forumPostLike.setParty_end_time(rs.getTimestamp("party_end_time"));
				forumPostLike.setParty_intro(rs.getString("party_intro"));
				forumPostLike.setParty_participants_max(rs.getInt("party_participants_max"));
				forumPostLike.setParty_participants_min(rs.getInt("party_participants_min"));
				forumPostLike.setParty_remarks(rs.getString("party_remarks"));
				forumPostLike.setMember_id(rs.getInt("member_id"));
				forumPostLike.setShop_id(rs.getInt("shop_id"));  
				likelist.add(forumPostLike); // Store the row in the list
			}

			// Handle any driver errors
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
		return likelist;
	
	}
	@Override
	public Set<PartyVO> getAllmypartybymember(Integer member_id) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Set<PartyVO> likelist = new LinkedHashSet();
		PartyVO forumPostLike = null;
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PARTY_ID);
			pstmt.setInt(1, member_id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVO 也稱為 Domain objects
				forumPostLike = new PartyVO();
				forumPostLike.setParty_id(rs.getInt("party_id"));
				System.out.println(rs.getInt("party_id"));
				forumPostLike.setParty_title(rs.getString("party_title"));
				forumPostLike.setParty_start_time(rs.getTimestamp("party_start_time"));
				forumPostLike.setParty_end_time(rs.getTimestamp("party_end_time"));
				forumPostLike.setParty_intro(rs.getString("party_intro"));
				forumPostLike.setParty_participants_max(rs.getInt("party_participants_max"));
				forumPostLike.setParty_participants_min(rs.getInt("party_participants_min"));
				forumPostLike.setParty_remarks(rs.getString("party_remarks"));
				forumPostLike.setMember_id(rs.getInt("member_id"));
				forumPostLike.setShop_id(rs.getInt("shop_id"));  
				likelist.add(forumPostLike); // Store the row in the list
			}
			
			// Handle any driver errors
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
		return likelist;
		
	}
	
	
	@Override
	public List<PartyVO> getAllmamber(Integer member_id) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<PartyVO> likelist = new ArrayList();
		PartyVO forumPostLike = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PARTY_ID);
			pstmt.setInt(1, member_id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVO 也稱為 Domain objects
				forumPostLike = new PartyVO();
				forumPostLike.setParty_id(rs.getInt("party_id"));
				forumPostLike.setParty_title(rs.getString("party_title"));
				forumPostLike.setParty_start_time(rs.getTimestamp("party_start_time"));
				forumPostLike.setParty_end_time(rs.getTimestamp("party_end_time"));
				forumPostLike.setParty_intro(rs.getString("party_intro"));
				forumPostLike.setParty_participants_max(rs.getInt("party_participants_max"));
				forumPostLike.setParty_participants_min(rs.getInt("party_participants_min"));
				forumPostLike.setParty_remarks(rs.getString("party_remarks"));
				forumPostLike.setMember_id(rs.getInt("member_id"));
				forumPostLike.setShop_id(rs.getInt("shop_id"));  
				likelist.add(forumPostLike); // Store the row in the list
			}
			
			// Handle any driver errors
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
		return likelist;
		
	}
	
	
	
}

