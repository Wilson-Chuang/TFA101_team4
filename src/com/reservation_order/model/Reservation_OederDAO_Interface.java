package com.reservation_order.model;

import java.util.List;


public interface Reservation_OederDAO_Interface {
	public void insert(Reservation_OederVO Reservation_OederVO);
    public void update(Reservation_OederVO Reservation_OederVO);
    public void delete(Integer Reservation_Oeder_id);
    public void delete_sf(Reservation_OederVO ReservationVO);
    public Reservation_OederVO getOneStmt(Integer Reservation_Oeder_id);
    public List<Reservation_OederVO> getAll();
}
