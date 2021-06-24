package com.reservation_order.model;

import java.sql.Timestamp;

public class Reservation_OederVO {
	private Integer reservation_order_id;
	private Integer member_id;
	private Integer reservation_id;
	private Timestamp reservation_order_period;
	private Integer reservation_order_status;
	private Integer reservation_order_people_amount;
	private Integer reservation_order_phone;
	
	
	
	public Integer getReservation_order_id() {
		return reservation_order_id;
	}
	public void setReservation_order_id(Integer reservation_order_id) {
		this.reservation_order_id = reservation_order_id;
	}
	public Integer getMember_id() {
		return member_id;
	}
	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
	}
	public Integer getReservation_id() {
		return reservation_id;
	}
	public void setReservation_id(Integer reservation_id) {
		this.reservation_id = reservation_id;
	}
	public Timestamp getReservation_order_period() {
		return reservation_order_period;
	}
	public void setReservation_order_period(Timestamp reservation_order_period) {
		this.reservation_order_period = reservation_order_period;
	}
	public Integer getReservation_order_status() {
		return reservation_order_status;
	}
	public void setReservation_order_status(Integer reservation_order_status) {
		this.reservation_order_status = reservation_order_status;
	}
	public Integer getReservation_order_people_amount() {
		return reservation_order_people_amount;
	}
	public void setReservation_order_people_amount(Integer reservation_order_people_amount) {
		this.reservation_order_people_amount = reservation_order_people_amount;
	}
	public Integer getReservation_order_phone() {
		return reservation_order_phone;
	}
	public void setReservation_order_phone(Integer reservation_order_phone) {
		this.reservation_order_phone = reservation_order_phone;
	}
	
}
