package com.credit_card.model;

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


public class Credit_cardDAO implements Credit_cardDAO_interface{

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
		
		private static final String INSERT_STMT = "INSERT INTO CREDIT_CARD (CREDIT_CARD_NUMBER, CREDIT_CARD_CVV, CREDIT_CARD_MONTH, CREDIT_CARD_YEAR, CREDIT_CARD_NAME, "
				+ "CREDIT_CARD_BIRTHDAY, CREDIT_CARD_PHONE, CREDIT_CARD_ADDRESS) VALUES (?,?,?,?,?,?,?,?)";
		private static final String GET_ALL_STMT = "SELECT * FROM CREDIT_CARD";
		private static final String GET_ONE_STMT = "SELECT * FROM CREDIT_CARD WHERE CREDIT_CARD_ID = ?";
		private static final String DELETE = "DELETE FROM CREDIT_CARD WHERE CREDIT_CARD_ID = ?";

		@Override
		public void insert(Credit_cardVO credit_cardVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setInt(1, credit_cardVO.getCredit_card_number());
				pstmt.setInt(2, credit_cardVO.getCredit_card_cvv());
				pstmt.setInt(3, credit_cardVO.getCredit_card_month());
				pstmt.setInt(4, credit_cardVO.getCredit_card_year());
				pstmt.setString(5, credit_cardVO.getCredit_card_name());
				pstmt.setDate(6, credit_cardVO.getCredit_card_bithday());
				pstmt.setString(7, credit_cardVO.getCredit_card_phone());
				pstmt.setString(8, credit_cardVO.getCredit_card_address());
				

				pstmt.executeUpdate();
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
		public void delete(Integer credit_card_no) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);

				pstmt.setInt(1, credit_card_no);

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
		public Credit_cardVO findByPrimaryKey(Integer credit_card_no) {

			Credit_cardVO credit_cardVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setInt(1, credit_card_no);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVo 也稱為 Domain objects
					credit_cardVO = new Credit_cardVO();
					credit_cardVO.setCredit_card_no(rs.getInt("credit_card_id"));
					credit_cardVO.setCredit_card_number(rs.getInt("credit_card_number"));
					credit_cardVO.setCredit_card_cvv(rs.getInt("credit_card_cvv"));
					credit_cardVO.setCredit_card_month(rs.getInt("credit_card_month"));
					credit_cardVO.setCredit_card_year(rs.getInt("credit_card_year"));
					credit_cardVO.setCredit_card_name(rs.getString("credit_card_name"));
					credit_cardVO.setCredit_card_bithday(rs.getDate("credit_card_birthday"));
					credit_cardVO.setCredit_card_phone(rs.getString("credit_card_phone"));
					credit_cardVO.setCredit_card_address(rs.getString("credit_card_address"));

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
			return credit_cardVO;
		}

		@Override
		public List<Credit_cardVO> getAll() {
			List<Credit_cardVO> list = new ArrayList<Credit_cardVO>();
			Credit_cardVO credit_cardVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVo 也稱為 Domain objects
					credit_cardVO = new Credit_cardVO();
					credit_cardVO.setCredit_card_no(rs.getInt("credit_card_id"));
					credit_cardVO.setCredit_card_number(rs.getInt("credit_card_number"));
					credit_cardVO.setCredit_card_cvv(rs.getInt("credit_card_cvv"));
					credit_cardVO.setCredit_card_month(rs.getInt("credit_card_month"));
					credit_cardVO.setCredit_card_year(rs.getInt("credit_card_year"));
					credit_cardVO.setCredit_card_name(rs.getString("credit_card_name"));
					credit_cardVO.setCredit_card_bithday(rs.getDate("credit_card_birthday"));
					credit_cardVO.setCredit_card_phone(rs.getString("credit_card_phone"));
					credit_cardVO.setCredit_card_address(rs.getString("credit_card_address"));
					
					list.add(credit_cardVO); 

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

}