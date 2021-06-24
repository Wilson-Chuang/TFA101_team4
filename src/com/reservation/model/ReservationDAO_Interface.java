package com.reservation.model;

import java.util.List;


public interface ReservationDAO_Interface {
	public void insert(ReservationVO ReservationVO);
    public void update(ReservationVO ReservationVO);
    public void delete(Integer ReservationVO_id);
    public ReservationVO getOneStmt(Integer ReservationVO_id);
    public List<ReservationVO> getAll();
}
