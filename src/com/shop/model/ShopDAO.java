package com.shop.model;

import java.util.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ShopDAO implements ShopDAO_interface {

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

	private static final String INSERT_STMT = "INSERT INTO shop (member_id,shop_tax_id,"
			+ "shop_name,shop_zip_code,shop_city,shop_address,"
			+ "shop_latitude,shop_longitude,shop_description,"
			+ "shop_tag,shop_rating,shop_rating_count,shop_rating_total,"
			+ "shop_email,shop_phone,shop_price_level,shop_opening_time,"
			+ "shop_website,shop_main_img,shop_gallery,shop_create_time,"
			+ "shop_update_time,shop_total_view,shop_reserv_status)"
			+ " VALUES (?, ?, ?, ?, ?, ? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,?)";
	private static final String GET_ALL_STMT = "SELECT shop_id,member_id,shop_tax_id,"
			+ "shop_name,shop_zip_code,shop_city,shop_address,"
			+ "shop_latitude,shop_longitude,shop_description,"
			+ "shop_tag,shop_rating,shop_rating_count,shop_rating_total,"
			+ "shop_email,shop_phone,shop_price_level,shop_opening_time,"
			+ "shop_website,shop_main_img,shop_gallery,shop_create_time,"
			+ "shop_update_time,shop_total_view,shop_reserv_status"
			+ " FROM shop ORDER BY shop_id";
	private static final String GET_ONE_STMT = "SELECT shop_id,member_id,shop_tax_id,"
			+ "shop_name,shop_zip_code,shop_city,shop_address,"
			+ "shop_latitude,shop_longitude,shop_description,"
			+ "shop_tag,shop_rating,shop_rating_count,shop_rating_total,"
			+ "shop_email,shop_phone,shop_price_level,shop_opening_time,"
			+ "shop_website,shop_main_img,shop_gallery,shop_create_time,"
			+ "shop_update_time,shop_total_view,shop_reserv_status"
			+ " FROM shop where shop_id = ?";
	private static final String DELETE = "DELETE FROM shop where shop_id = ?";
	private static final String UPDATE = "UPDATE shop set member_id=?, shop_tax_id=?, "
			+ "shop_name=?, shop_zip_code=?, shop_city=?, shop_address=?, "
			+ "shop_latitude=?, shop_longitude=?, shop_description=?, "
			+ "shop_tag=?, shop_rating=?, shop_rating_count=?, shop_rating_total=?, "
			+ "shop_email=?, shop_phone=?, shop_price_level=?, shop_opening_time=?, "
			+ "shop_website=?, shop_main_img=?, shop_gallery=?, "
			+ "shop_update_time=?, shop_total_view=?, shop_reserv_status=?"
			+ " where shop_id = ?";
	private static final String UPDATE_RATING = 
			"UPDATE shop set shop_rating=?,shop_tag=? where shop_id = ?";
	private static final String ADD_TOTAL_VIEW = 
			"UPDATE shop set shop_total_view=shop_total_view+1 where shop_id = ?";
	private static final String INSERT_SHOP = "INSERT INTO shop (member_id,"
			+ "shop_tax_id,shop_name,shop_zip_code,shop_city,shop_address,"
			+ "shop_latitude,shop_longitude,shop_description,shop_tag,"
			+ "shop_email,shop_phone,shop_price_level,shop_opening_time,"
			+ "shop_website,shop_main_img,shop_gallery,shop_reserv_status)"
			+ " VALUES (?, ?, ?, ?, ?, ? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? )";
	
	//===============郁昔的code===============
	private static final String COUNT_SHOP = "SELECT COUNT(*) FROM shop";

	@Override
	public void insert(ShopVO shopVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, shopVO.getMember_id());
			pstmt.setString(2, shopVO.getShop_tax_id());
			pstmt.setString(3, shopVO.getShop_name());
			pstmt.setString(4, shopVO.getShop_zip_code());
			pstmt.setString(5, shopVO.getShop_city());
			pstmt.setString(6, shopVO.getShop_address());
			pstmt.setDouble(7, shopVO.getShop_latitude());
			pstmt.setDouble(8, shopVO.getShop_longitude());
			pstmt.setString(9, shopVO.getShop_description());
			pstmt.setString(10, shopVO.getShop_tag());
			pstmt.setDouble(11, shopVO.getShop_rating());
			pstmt.setInt(12, shopVO.getShop_rating_count());
			pstmt.setInt(13, shopVO.getShop_rating_total());
			pstmt.setString(14, shopVO.getShop_email());
			pstmt.setString(15, shopVO.getShop_phone());
			pstmt.setInt(16, shopVO.getShop_price_level());
			pstmt.setString(17, shopVO.getShop_opening_time());
			pstmt.setString(18, shopVO.getShop_website());
			pstmt.setString(19, shopVO.getShop_main_img());
			pstmt.setString(20, shopVO.getShop_gallery());
			pstmt.setTimestamp(21, shopVO.getShop_create_time());
			pstmt.setTimestamp(22, shopVO.getShop_update_time());
			pstmt.setInt(23, shopVO.getShop_total_view());
			pstmt.setInt(24, shopVO.getShop_reserv_status());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("資料庫發生錯誤! 訊息為: " + se.getMessage());
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
	public void update(ShopVO shopVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, shopVO.getMember_id());
			pstmt.setString(2, shopVO.getShop_tax_id());
			pstmt.setString(3, shopVO.getShop_name());
			pstmt.setString(4, shopVO.getShop_zip_code());
			pstmt.setString(5, shopVO.getShop_city());
			pstmt.setString(6, shopVO.getShop_address());
			pstmt.setDouble(7, shopVO.getShop_latitude());
			pstmt.setDouble(8, shopVO.getShop_longitude());
			pstmt.setString(9, shopVO.getShop_description());
			pstmt.setString(10, shopVO.getShop_tag());
			pstmt.setDouble(11, shopVO.getShop_rating());
			pstmt.setInt(12, shopVO.getShop_rating_count());
			pstmt.setInt(13, shopVO.getShop_rating_total());
			pstmt.setString(14, shopVO.getShop_email());
			pstmt.setString(15, shopVO.getShop_phone());
			pstmt.setInt(16, shopVO.getShop_price_level());
			pstmt.setString(17, shopVO.getShop_opening_time());
			pstmt.setString(18, shopVO.getShop_website());
			pstmt.setString(19, shopVO.getShop_main_img());
			pstmt.setString(20, shopVO.getShop_gallery());
			pstmt.setTimestamp(21, shopVO.getShop_update_time());
			pstmt.setInt(22, shopVO.getShop_total_view());
			pstmt.setInt(23, shopVO.getShop_reserv_status());
			pstmt.setInt(24, shopVO.getShop_id());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("資料庫發生錯誤! 訊息為: " + se.getMessage());
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
	public void delete(Integer shop_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, shop_id);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("資料庫發生錯誤! 訊息為: " + se.getMessage());
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
	public ShopVO findByPrimaryKey(Integer shop_id) {

		ShopVO shopVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, shop_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				shopVO = new ShopVO();
				shopVO.setShop_id(rs.getInt("shop_id"));
				shopVO.setMember_id(rs.getInt("member_id"));
				shopVO.setShop_tax_id(rs.getString("shop_tax_id"));
				shopVO.setShop_name(rs.getString("shop_name"));
				shopVO.setShop_zip_code(rs.getString("shop_zip_code"));
				shopVO.setShop_city(rs.getString("shop_city"));
				shopVO.setShop_address(rs.getString("shop_address"));
				shopVO.setShop_latitude(rs.getDouble("shop_latitude"));
				shopVO.setShop_longitude(rs.getDouble("shop_longitude"));
				shopVO.setShop_description(rs.getString("shop_description"));
				shopVO.setShop_tag(rs.getString("shop_tag"));
				shopVO.setShop_rating(rs.getDouble("shop_rating"));
				shopVO.setShop_rating_count(rs.getInt("shop_rating_count"));
				shopVO.setShop_rating_total(rs.getInt("shop_rating_total"));
				shopVO.setShop_email(rs.getString("shop_email"));
				shopVO.setShop_phone(rs.getString("shop_phone"));
				shopVO.setShop_price_level(rs.getInt("shop_price_level"));
				shopVO.setShop_opening_time(rs.getString("shop_opening_time"));
				shopVO.setShop_website(rs.getString("shop_website"));
				shopVO.setShop_main_img(rs.getString("shop_main_img"));
				shopVO.setShop_gallery(rs.getString("shop_gallery"));
				shopVO.setShop_create_time(rs.getTimestamp("shop_create_time"));
				shopVO.setShop_update_time(rs.getTimestamp("shop_update_time"));
				shopVO.setShop_total_view(rs.getInt("shop_total_view"));
				shopVO.setShop_reserv_status(rs.getInt("shop_reserv_status"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("資料庫發生錯誤! 訊息為: " + se.getMessage());
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
		return shopVO;
	}
	
	@Override
	public List<ShopVO> getAll() {
		List<ShopVO> list = new ArrayList<ShopVO>();
		ShopVO shopVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				shopVO = new ShopVO();
				shopVO.setShop_id(rs.getInt("shop_id"));
				shopVO.setMember_id(rs.getInt("member_id"));
				shopVO.setShop_tax_id(rs.getString("shop_tax_id"));
				shopVO.setShop_name(rs.getString("shop_name"));
				shopVO.setShop_zip_code(rs.getString("shop_zip_code"));
				shopVO.setShop_city(rs.getString("shop_city"));
				shopVO.setShop_address(rs.getString("shop_address"));
				shopVO.setShop_latitude(rs.getDouble("shop_latitude"));
				shopVO.setShop_longitude(rs.getDouble("shop_longitude"));
				shopVO.setShop_description(rs.getString("shop_description"));
				shopVO.setShop_tag(rs.getString("shop_tag"));
				shopVO.setShop_rating(rs.getDouble("shop_rating"));
				shopVO.setShop_rating_count(rs.getInt("shop_rating_count"));
				shopVO.setShop_rating_total(rs.getInt("shop_rating_total"));
				shopVO.setShop_email(rs.getString("shop_email"));
				shopVO.setShop_phone(rs.getString("shop_phone"));
				shopVO.setShop_price_level(rs.getInt("shop_price_level"));
				shopVO.setShop_opening_time(rs.getString("shop_opening_time"));
				shopVO.setShop_website(rs.getString("shop_website"));
				shopVO.setShop_main_img(rs.getString("shop_main_img"));
				shopVO.setShop_gallery(rs.getString("shop_gallery"));
				shopVO.setShop_create_time(rs.getTimestamp("shop_create_time"));
				shopVO.setShop_update_time(rs.getTimestamp("shop_update_time"));
				shopVO.setShop_total_view(rs.getInt("shop_total_view"));
				shopVO.setShop_reserv_status(rs.getInt("shop_reserv_status"));
				list.add(shopVO); // Store the row in the list
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
	
	
	private static final String GET_TAX_STMT = "SELECT shop_id,member_id,shop_tax_id,"
			+ "shop_name,shop_zip_code,shop_city,shop_address,"
			+ "shop_latitude,shop_longitude,shop_description,"
			+ "shop_tag,shop_rating,shop_rating_count,shop_rating_total,"
			+ "shop_email,shop_phone,shop_price_level,shop_opening_time,"
			+ "shop_website,shop_main_img,shop_gallery,shop_create_time,"
			+ "shop_update_time,shop_total_view,shop_reserv_status"
			+ " FROM shop where shop_tax_id = ?";
	
	@Override
	public ShopVO findShop_tax_id(String shop_tax_id) {

		ShopVO shopVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_TAX_STMT);

			pstmt.setString(1, shop_tax_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				shopVO = new ShopVO();
				shopVO.setShop_id(rs.getInt("shop_id"));
				shopVO.setMember_id(rs.getInt("member_id"));
				shopVO.setShop_tax_id(rs.getString("shop_tax_id"));
				shopVO.setShop_name(rs.getString("shop_name"));
				shopVO.setShop_zip_code(rs.getString("shop_zip_code"));
				shopVO.setShop_city(rs.getString("shop_city"));
				shopVO.setShop_address(rs.getString("shop_address"));
				shopVO.setShop_latitude(rs.getDouble("shop_latitude"));
				shopVO.setShop_longitude(rs.getDouble("shop_longitude"));
				shopVO.setShop_description(rs.getString("shop_description"));
				shopVO.setShop_tag(rs.getString("shop_tag"));
				shopVO.setShop_rating(rs.getDouble("shop_rating"));
				shopVO.setShop_rating_count(rs.getInt("shop_rating_count"));
				shopVO.setShop_rating_total(rs.getInt("shop_rating_total"));
				shopVO.setShop_email(rs.getString("shop_email"));
				shopVO.setShop_phone(rs.getString("shop_phone"));
				shopVO.setShop_price_level(rs.getInt("shop_price_level"));
				shopVO.setShop_opening_time(rs.getString("shop_opening_time"));
				shopVO.setShop_website(rs.getString("shop_website"));
				shopVO.setShop_main_img(rs.getString("shop_main_img"));
				shopVO.setShop_gallery(rs.getString("shop_gallery"));
				shopVO.setShop_create_time(rs.getTimestamp("shop_create_time"));
				shopVO.setShop_update_time(rs.getTimestamp("shop_update_time"));
				shopVO.setShop_total_view(rs.getInt("shop_total_view"));
				shopVO.setShop_reserv_status(rs.getInt("shop_reserv_status"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("資料庫發生錯誤! 訊息為: " + se.getMessage());
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
		return shopVO;
	}
	
	
	private static final String GET_ALL_BY_LATLNG = "SELECT DISTINCT shop_id,member_id,shop_tax_id,"
			+ "shop_name,shop_zip_code,shop_city,shop_address,shop_latitude,shop_longitude,"
			+ "Truncate(shop_latitude,1),Truncate(shop_longitude,1),shop_description,"
			+ "shop_tag,shop_rating,shop_rating_count,shop_rating_total,"
			+ "shop_email,shop_phone,shop_price_level,shop_opening_time,"
			+ "shop_website,shop_main_img,shop_gallery,shop_create_time,"
			+ "shop_update_time,shop_total_view,shop_reserv_status"
			+ " FROM shop WHERE "
			+ "Truncate(shop_latitude,1) = ? AND "
			+ "Truncate(shop_longitude,1) = ?";
	
	@Override
	public List<ShopVO> getAllbyLatLng(Double lat, Double lng) {
		List<ShopVO> list = new ArrayList<ShopVO>();
		ShopVO shopVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_LATLNG);
			
			 if (lat > 0) {
				 lat = new BigDecimal(String.valueOf(lat)).setScale(1, RoundingMode.FLOOR).doubleValue();
			 } else {
				 lat = new BigDecimal(String.valueOf(lat)).setScale(1, RoundingMode.CEILING).doubleValue();
			 }
			 if (lng > 0) {
				 lng = new BigDecimal(String.valueOf(lng)).setScale(1, RoundingMode.FLOOR).doubleValue();
			 } else {
				 lng = new BigDecimal(String.valueOf(lng)).setScale(1, RoundingMode.CEILING).doubleValue();
			 }
			 
			pstmt.setDouble(1, lat);
			pstmt.setDouble(2, lng);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				shopVO = new ShopVO();
				shopVO.setShop_id(rs.getInt("shop_id"));
				shopVO.setMember_id(rs.getInt("member_id"));
				shopVO.setShop_tax_id(rs.getString("shop_tax_id"));
				shopVO.setShop_name(rs.getString("shop_name"));
				shopVO.setShop_zip_code(rs.getString("shop_zip_code"));
				shopVO.setShop_city(rs.getString("shop_city"));
				shopVO.setShop_address(rs.getString("shop_address"));
				shopVO.setShop_latitude(rs.getDouble("shop_latitude"));
				shopVO.setShop_longitude(rs.getDouble("shop_longitude"));
				shopVO.setShop_description(rs.getString("shop_description"));
				shopVO.setShop_tag(rs.getString("shop_tag"));
				shopVO.setShop_rating(rs.getDouble("shop_rating"));
				shopVO.setShop_rating_count(rs.getInt("shop_rating_count"));
				shopVO.setShop_rating_total(rs.getInt("shop_rating_total"));
				shopVO.setShop_email(rs.getString("shop_email"));
				shopVO.setShop_phone(rs.getString("shop_phone"));
				shopVO.setShop_price_level(rs.getInt("shop_price_level"));
				shopVO.setShop_opening_time(rs.getString("shop_opening_time"));
				shopVO.setShop_website(rs.getString("shop_website"));
				shopVO.setShop_main_img(rs.getString("shop_main_img"));
				shopVO.setShop_gallery(rs.getString("shop_gallery"));
				shopVO.setShop_create_time(rs.getTimestamp("shop_create_time"));
				shopVO.setShop_update_time(rs.getTimestamp("shop_update_time"));
				shopVO.setShop_total_view(rs.getInt("shop_total_view"));
				shopVO.setShop_reserv_status(rs.getInt("shop_reserv_status"));
				list.add(shopVO); // Store the row in the list
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
	
	private static final String SEARCH_SHOP_BOTH = "SELECT DISTINCT shop_id,member_id,shop_tax_id,"
			+ "shop_name,shop_zip_code,shop_city,shop_address,"
			+ "shop_latitude,shop_longitude,shop_description,"
			+ "shop_tag,shop_rating,shop_rating_count,shop_rating_total,"
			+ "shop_email,shop_phone,shop_price_level,shop_opening_time,"
			+ "shop_website,shop_main_img,shop_gallery,shop_create_time,"
			+ "shop_update_time,shop_total_view,shop_reserv_status"
			+ " FROM shop WHERE (shop_city LIKE ? OR shop_address LIKE ?) AND (";
	
	@Override
	public List<ShopVO> findShopBoth(String keyword, String place) {
		List<ShopVO> list = new ArrayList<ShopVO>();
		ShopVO shopVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			
			String tempStmt = SEARCH_SHOP_BOTH;
			String[] keyArray = keyword.split("\\s+");
			int keyCount = 0;
			for(int i = 0;i < keyArray.length;i++) {
				if(i == keyArray.length - 1) {
					tempStmt += 
							"(shop_name LIKE ? OR shop_description LIKE ?"
							+ " OR shop_tag LIKE ?))";
					keyCount += 3;
				}else {
					tempStmt += 
							"(shop_name LIKE ? OR shop_description LIKE ?"
							+ " OR shop_tag LIKE ?) AND ";
					keyCount += 3;
				}				
			}
			System.out.println(tempStmt);
			pstmt = con.prepareStatement(tempStmt);
			pstmt.setString(1, "%" + place + "%");
			pstmt.setString(2, "%" + place + "%");						
			int totalCount = 2;
			for(int temp = 0;temp < keyArray.length;) {
				for(;totalCount < keyCount; temp++) {
					totalCount++;
					pstmt.setString(totalCount, "%" + keyArray[temp] + "%");
					totalCount++;
					pstmt.setString(totalCount, "%" + keyArray[temp] + "%");
					totalCount++;
					pstmt.setString(totalCount, "%" + keyArray[temp] + "%");
				}				
			}
			
			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				shopVO = new ShopVO();
				shopVO.setShop_id(rs.getInt("shop_id"));
				shopVO.setMember_id(rs.getInt("member_id"));
				shopVO.setShop_tax_id(rs.getString("shop_tax_id"));
				shopVO.setShop_name(rs.getString("shop_name"));
				shopVO.setShop_zip_code(rs.getString("shop_zip_code"));
				shopVO.setShop_city(rs.getString("shop_city"));
				shopVO.setShop_address(rs.getString("shop_address"));
				shopVO.setShop_latitude(rs.getDouble("shop_latitude"));
				shopVO.setShop_longitude(rs.getDouble("shop_longitude"));
				shopVO.setShop_description(rs.getString("shop_description"));
				shopVO.setShop_tag(rs.getString("shop_tag"));
				shopVO.setShop_rating(rs.getDouble("shop_rating"));
				shopVO.setShop_rating_count(rs.getInt("shop_rating_count"));
				shopVO.setShop_rating_total(rs.getInt("shop_rating_total"));
				shopVO.setShop_email(rs.getString("shop_email"));
				shopVO.setShop_phone(rs.getString("shop_phone"));
				shopVO.setShop_price_level(rs.getInt("shop_price_level"));
				shopVO.setShop_opening_time(rs.getString("shop_opening_time"));
				shopVO.setShop_website(rs.getString("shop_website"));
				shopVO.setShop_main_img(rs.getString("shop_main_img"));
				shopVO.setShop_gallery(rs.getString("shop_gallery"));
				shopVO.setShop_create_time(rs.getTimestamp("shop_create_time"));
				shopVO.setShop_update_time(rs.getTimestamp("shop_update_time"));
				shopVO.setShop_total_view(rs.getInt("shop_total_view"));
				shopVO.setShop_reserv_status(rs.getInt("shop_reserv_status"));
				list.add(shopVO); 
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("資料庫發生錯誤! 訊息為: " + se.getMessage());
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
	
	private static final String SEARCH_SHOP_KEYWORD = "SELECT DISTINCT shop_id,member_id,shop_tax_id,"
			+ "shop_name,shop_zip_code,shop_city,shop_address,"
			+ "shop_latitude,shop_longitude,shop_description,"
			+ "shop_tag,shop_rating,shop_rating_count,shop_rating_total,"
			+ "shop_email,shop_phone,shop_price_level,shop_opening_time,"
			+ "shop_website,shop_main_img,shop_gallery,shop_create_time,"
			+ "shop_update_time,shop_total_view,shop_reserv_status"
			+ " FROM shop WHERE ";
	
	@Override
	public List<ShopVO> findShopKeyword(String keyword) {
		List<ShopVO> list = new ArrayList<ShopVO>();
		ShopVO shopVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			
			String tempStmt = SEARCH_SHOP_KEYWORD;
			String[] keyArray = keyword.split("\\s+");
			int keyCount = 0;
			for(int i = 0;i < keyArray.length;i++) {
				if(i == keyArray.length - 1) {
					tempStmt += 
							"(shop_name LIKE ? OR shop_description LIKE ?"
							+ " OR shop_tag LIKE ?)";
					keyCount += 3;
				}else {
					tempStmt += 
							"(shop_name LIKE ? OR shop_description LIKE ?"
							+ " OR shop_tag LIKE ?) AND ";
					keyCount += 3;
				}				
			}
			System.out.println(tempStmt);
			pstmt = con.prepareStatement(tempStmt);
			
			int totalCount = 0;
			for(int temp = 0;temp < keyArray.length;) {
				for(;totalCount < keyCount; temp++) {
					totalCount++;
					pstmt.setString(totalCount, "%" + keyArray[temp] + "%");
					totalCount++;
					pstmt.setString(totalCount, "%" + keyArray[temp] + "%");
					totalCount++;
					pstmt.setString(totalCount, "%" + keyArray[temp] + "%");
				}
			}
			
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				shopVO = new ShopVO();
				shopVO.setShop_id(rs.getInt("shop_id"));
				shopVO.setMember_id(rs.getInt("member_id"));
				shopVO.setShop_tax_id(rs.getString("shop_tax_id"));
				shopVO.setShop_name(rs.getString("shop_name"));
				shopVO.setShop_zip_code(rs.getString("shop_zip_code"));
				shopVO.setShop_city(rs.getString("shop_city"));
				shopVO.setShop_address(rs.getString("shop_address"));
				shopVO.setShop_latitude(rs.getDouble("shop_latitude"));
				shopVO.setShop_longitude(rs.getDouble("shop_longitude"));
				shopVO.setShop_description(rs.getString("shop_description"));
				shopVO.setShop_tag(rs.getString("shop_tag"));
				shopVO.setShop_rating(rs.getDouble("shop_rating"));
				shopVO.setShop_rating_count(rs.getInt("shop_rating_count"));
				shopVO.setShop_rating_total(rs.getInt("shop_rating_total"));
				shopVO.setShop_email(rs.getString("shop_email"));
				shopVO.setShop_phone(rs.getString("shop_phone"));
				shopVO.setShop_price_level(rs.getInt("shop_price_level"));
				shopVO.setShop_opening_time(rs.getString("shop_opening_time"));
				shopVO.setShop_website(rs.getString("shop_website"));
				shopVO.setShop_main_img(rs.getString("shop_main_img"));
				shopVO.setShop_gallery(rs.getString("shop_gallery"));
				shopVO.setShop_create_time(rs.getTimestamp("shop_create_time"));
				shopVO.setShop_update_time(rs.getTimestamp("shop_update_time"));
				shopVO.setShop_total_view(rs.getInt("shop_total_view"));
				shopVO.setShop_reserv_status(rs.getInt("shop_reserv_status"));
				list.add(shopVO);
			}
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("資料庫發生錯誤! 訊息為: " + se.getMessage());
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
	
	private static final String SEARCH_SHOP_PLACE = "SELECT DISTINCT shop_id,member_id,shop_tax_id,"
			+ "shop_name,shop_zip_code,shop_city,shop_address,"
			+ "shop_latitude,shop_longitude,shop_description,"
			+ "shop_tag,shop_rating,shop_rating_count,shop_rating_total,"
			+ "shop_email,shop_phone,shop_price_level,shop_opening_time,"
			+ "shop_website,shop_main_img,shop_gallery,shop_create_time,"
			+ "shop_update_time,shop_total_view,shop_reserv_status"
			+ " FROM shop WHERE "
			+ "shop_city LIKE ? OR "
			+ "shop_address LIKE ?";
	
	@Override
	public List<ShopVO> findShopPlace(String place) {
		List<ShopVO> list = new ArrayList<ShopVO>();
		ShopVO shopVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(SEARCH_SHOP_PLACE);
			pstmt.setString(1, "%" + place + "%");
			pstmt.setString(2, "%" + place + "%");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				shopVO = new ShopVO();
				shopVO.setShop_id(rs.getInt("shop_id"));
				shopVO.setMember_id(rs.getInt("member_id"));
				shopVO.setShop_tax_id(rs.getString("shop_tax_id"));
				shopVO.setShop_name(rs.getString("shop_name"));
				shopVO.setShop_zip_code(rs.getString("shop_zip_code"));
				shopVO.setShop_city(rs.getString("shop_city"));
				shopVO.setShop_address(rs.getString("shop_address"));
				shopVO.setShop_latitude(rs.getDouble("shop_latitude"));
				shopVO.setShop_longitude(rs.getDouble("shop_longitude"));
				shopVO.setShop_description(rs.getString("shop_description"));
				shopVO.setShop_tag(rs.getString("shop_tag"));
				shopVO.setShop_rating(rs.getDouble("shop_rating"));
				shopVO.setShop_rating_count(rs.getInt("shop_rating_count"));
				shopVO.setShop_rating_total(rs.getInt("shop_rating_total"));
				shopVO.setShop_email(rs.getString("shop_email"));
				shopVO.setShop_phone(rs.getString("shop_phone"));
				shopVO.setShop_price_level(rs.getInt("shop_price_level"));
				shopVO.setShop_opening_time(rs.getString("shop_opening_time"));
				shopVO.setShop_website(rs.getString("shop_website"));
				shopVO.setShop_main_img(rs.getString("shop_main_img"));
				shopVO.setShop_gallery(rs.getString("shop_gallery"));
				shopVO.setShop_create_time(rs.getTimestamp("shop_create_time"));
				shopVO.setShop_update_time(rs.getTimestamp("shop_update_time"));
				shopVO.setShop_total_view(rs.getInt("shop_total_view"));
				shopVO.setShop_reserv_status(rs.getInt("shop_reserv_status"));
				list.add(shopVO);
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("資料庫發生錯誤! 訊息為: " + se.getMessage());
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
	
	// ====================組長的code======================
	private static final String UPDATE_SHOP = "UPDATE shop set shop_name=?, shop_price_level=?, shop_opening_time=?,shop_address=?, "
			+ "shop_city=?,shop_website=?,shop_phone=?,shop_email=?,shop_description=?,shop_tag=?,shop_update_time=? ,shop_main_img=?,shop_gallery=? where shop_id = ?";

	
	@Override
	public void update_shop(ShopVO shopVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_SHOP);
			pstmt.setString(1, shopVO.getShop_name());
			pstmt.setInt(2, shopVO.getShop_price_level());
			pstmt.setString(3, shopVO.getShop_opening_time());
			pstmt.setString(4, shopVO.getShop_address());
			pstmt.setString(5, shopVO.getShop_city());
			pstmt.setString(6, shopVO.getShop_website());
			pstmt.setString(7, shopVO.getShop_phone());
			pstmt.setString(8, shopVO.getShop_email());
			pstmt.setString(9, shopVO.getShop_description());
			pstmt.setString(10, shopVO.getShop_tag());
			pstmt.setTimestamp(11, shopVO.getShop_update_time());
			pstmt.setString(12, shopVO.getShop_main_img());
			pstmt.setString(13, shopVO.getShop_gallery());
			pstmt.setInt(14, shopVO.getShop_id());


			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("資料庫發生錯誤! 訊息為: " + se.getMessage());
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
	private static final String GET_ONE_BY_MEMBER = "SELECT shop_id,member_id,shop_tax_id,"
			+ "shop_name,shop_zip_code,shop_city,shop_address,"
			+ "shop_latitude,shop_longitude,shop_description,"
			+ "shop_tag,shop_rating,shop_rating_count,shop_rating_total,"
			+ "shop_email,shop_phone,shop_price_level,shop_opening_time,"
			+ "shop_website,shop_main_img,shop_gallery,shop_create_time,"
			+ "shop_update_time,shop_total_view,shop_reserv_status"
			+ " FROM shop where member_id = ?";
	@Override
	public ShopVO findByMemberId(Integer member_id) {
		
		ShopVO shopVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_BY_MEMBER);
			pstmt.setInt(1, member_id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				shopVO = new ShopVO();
				shopVO.setShop_id(rs.getInt("shop_id"));
				shopVO.setMember_id(rs.getInt("member_id"));
				shopVO.setShop_tax_id(rs.getString("shop_tax_id"));
				shopVO.setShop_name(rs.getString("shop_name"));
				shopVO.setShop_zip_code(rs.getString("shop_zip_code"));
				shopVO.setShop_city(rs.getString("shop_city"));
				shopVO.setShop_address(rs.getString("shop_address"));
				shopVO.setShop_latitude(rs.getDouble("shop_latitude"));
				shopVO.setShop_longitude(rs.getDouble("shop_longitude"));
				shopVO.setShop_description(rs.getString("shop_description"));
				shopVO.setShop_tag(rs.getString("shop_tag"));
				shopVO.setShop_rating(rs.getDouble("shop_rating"));
				shopVO.setShop_rating_count(rs.getInt("shop_rating_count"));
				shopVO.setShop_rating_total(rs.getInt("shop_rating_total"));
				shopVO.setShop_email(rs.getString("shop_email"));
				shopVO.setShop_phone(rs.getString("shop_phone"));
				shopVO.setShop_price_level(rs.getInt("shop_price_level"));
				shopVO.setShop_opening_time(rs.getString("shop_opening_time"));
				shopVO.setShop_website(rs.getString("shop_website"));
				shopVO.setShop_main_img(rs.getString("shop_main_img"));
				shopVO.setShop_gallery(rs.getString("shop_gallery"));
				shopVO.setShop_create_time(rs.getTimestamp("shop_create_time"));
				shopVO.setShop_update_time(rs.getTimestamp("shop_update_time"));
				shopVO.setShop_total_view(rs.getInt("shop_total_view"));
				shopVO.setShop_reserv_status(rs.getInt("shop_reserv_status"));
			}
			
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("資料庫發生錯誤! 訊息為: " + se.getMessage());
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
		return shopVO;
	}
	@Override
	public void update_rating(ShopVO shopVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_RATING);
			
			pstmt.setDouble(1, shopVO.getShop_rating());
			pstmt.setString(2, shopVO.getShop_tag());
			pstmt.setInt(3, shopVO.getShop_id());
			
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
	public void add_total_view(Integer shop_id) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(ADD_TOTAL_VIEW);
			
			pstmt.setInt(1, shop_id);
			
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
	public void insert_shop(ShopVO shopVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_SHOP);
			
			pstmt.setInt(1, shopVO.getMember_id());
			pstmt.setString(2, shopVO.getShop_tax_id());
			pstmt.setString(3, shopVO.getShop_name());
			pstmt.setString(4, shopVO.getShop_zip_code());
			pstmt.setString(5, shopVO.getShop_city());
			pstmt.setString(6, shopVO.getShop_address());
			pstmt.setDouble(7, shopVO.getShop_latitude());
			pstmt.setDouble(8, shopVO.getShop_longitude());
			pstmt.setString(9, shopVO.getShop_description());
			pstmt.setString(10, shopVO.getShop_tag());
			pstmt.setString(11, shopVO.getShop_email());
			pstmt.setString(12, shopVO.getShop_phone());
			pstmt.setInt(13, shopVO.getShop_price_level());
			pstmt.setString(14, shopVO.getShop_opening_time());
			pstmt.setString(15, shopVO.getShop_website());
			pstmt.setString(16, shopVO.getShop_main_img());
			pstmt.setString(17, shopVO.getShop_gallery());
			pstmt.setInt(18,shopVO.getShop_reserv_status());
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

	//=========郁昔的code=============
	@Override
	public Integer countShop() {
		
		Integer countShop = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(COUNT_SHOP);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				countShop = rs.getInt(1);
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		
		return countShop;
	}


}