package com.reservation_order.model;

import java.util.List;

public class Reservation_Oeder_JDBCDAO implements Reservation_OederDAO_Interface	{

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/new_schema?serverTimezone=Asia/Taipei";
	String userid = "David";
	String passwd = "123456";
	private static final String INSERT_STMT = 
			"INSERT INTO Reservation (member_id,reservation_id,reservation_people_limited,reservation_period_status,reservation_group_status) VALUES (?, ? ,? ,?)";
		private static final String GET_ALL_STMT = 
			"SELECT * FROM Reservation order by Reservation_id";
		private static final String GET_ONE_STMT = 
			"SELECT * FROM Reservation where Reservation_id= ?";
		private static final String DELETE = 
			"DELETE FROM Reservation where Reservation_id = ?";
		private static final String UPDATE = 
			"UPDATE Reservation set shop_id=?,reservation_people_limited=?,reservation_period_status=? ,reservation_group_status=?  where Reservation_id=?" ;
		
	
	
	
	
	@Override
	public void insert(Reservation_OederVO Reservation_OederVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Reservation_OederVO Reservation_OederVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer Reservation_Oeder_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete_sf(Reservation_OederVO ReservationVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Reservation_OederVO getOneStmt(Integer Reservation_Oeder_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reservation_OederVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
