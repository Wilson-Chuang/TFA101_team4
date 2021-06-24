package com.reservation.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shop_favorites.model.Shop_FavoritesVO;

public class Reservation_JDBCDAO implements ReservationDAO_Interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/new_schema?serverTimezone=Asia/Taipei";
	String userid = "David";
	String passwd = "123456";
	private static final String INSERT_STMT = 
			"INSERT INTO Reservation (shop_id,reservation_people_limited,reservation_period_status,reservation_group_status) VALUES (?, ? ,? ,?)";
		private static final String GET_ALL_STMT = 
			"SELECT * FROM Reservation order by Reservation_id";
		private static final String GET_ONE_STMT = 
			"SELECT * FROM Reservation where Reservation_id= ?";
		private static final String DELETE = 
			"DELETE FROM Reservation where Reservation_id = ?";
		private static final String UPDATE = 
			"UPDATE Reservation set shop_id=?,reservation_people_limited=?,reservation_period_status=? ,reservation_group_status=?  where Reservation_id=?" ;
		
		
		
	@Override
	public void insert(ReservationVO ReservationVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1,ReservationVO.getShop_id());
			pstmt.setInt(2,ReservationVO.getReservation_people_limited());
			pstmt.setTimestamp(3,ReservationVO.getReservation_period_status());
			pstmt.setInt(4,ReservationVO.getReservation_group_status());
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
	public void update(ReservationVO ReservationVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1,ReservationVO.getShop_id());
			pstmt.setInt(2,ReservationVO.getReservation_people_limited());
			pstmt.setTimestamp(3,ReservationVO.getReservation_period_status());
			pstmt.setInt(4,ReservationVO.getReservation_group_status());
			pstmt.setInt(5,ReservationVO.getReservation_id());
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
	public void delete(Integer Reservation_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1,Reservation_id);

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
	public ReservationVO getOneStmt(Integer Reservation_id) {
		ReservationVO sf = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, Reservation_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				sf = new ReservationVO();
				sf.setReservation_id(rs.getInt("Reservation_id"));
				sf.setShop_id(rs.getInt("Shop_id"));
				sf.setReservation_people_limited(rs.getInt("Reservation_people_limited"));
				sf.setReservation_period_status(rs.getTimestamp("Reservation_period_status"));
				sf.setReservation_group_status(rs.getInt("Reservation_group_status"));
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

		return sf;
	}

	@Override
	public List<ReservationVO> getAll() {
		List<ReservationVO> list= new ArrayList<ReservationVO>();
		ReservationVO sf=null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				sf = new ReservationVO();
				sf.setReservation_id(rs.getInt("Reservation_id"));
				sf.setShop_id(rs.getInt("Shop_id"));
				sf.setReservation_people_limited(rs.getInt("Reservation_people_limited"));
				sf.setReservation_period_status(rs.getTimestamp("Reservation_period_status"));
				sf.setReservation_group_status(rs.getInt("Reservation_group_status"));
				list.add(sf);
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

}
