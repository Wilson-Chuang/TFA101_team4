package com.reservation.model;

import java.sql.Timestamp;

public class ReservationVO {
	private Integer reservation_id;
	private Integer shop_id;
	private Integer reservation_people_limited;
	private Timestamp reservation_period_status;
	private Integer reservation_group_status;
	public Integer getReservation_id() {
		return reservation_id;
	}
	public void setReservation_id(Integer reservation_id) {
		this.reservation_id = reservation_id;
	}
	public Integer getShop_id() {
		return shop_id;
	}
	public void setShop_id(Integer shop_id) {
		this.shop_id = shop_id;
	}
	public Integer getReservation_people_limited() {
		return reservation_people_limited;
	}
	public void setReservation_people_limited(Integer reservation_people_limited) {
		this.reservation_people_limited = reservation_people_limited;
	}
	public Timestamp getReservation_period_status() {
		return reservation_period_status;
	}
	public void setReservation_period_status(Timestamp reservation_period_status) {
		this.reservation_period_status = reservation_period_status;
	}
	public Integer getReservation_group_status() {
		return reservation_group_status;
	}
	public void setReservation_group_status(Integer reservation_group_status) {
		this.reservation_group_status = reservation_group_status;
	}
}
