package com.party.model;

import java.sql.Date;
import java.sql.Timestamp;

public class PartyVO implements java.io.Serializable {
	private Integer party_id;// 揪團編號
	private Integer member_id;// 揪團發起人會員ID
	private Integer shop_id;// 揪團目的地餐廳ID
	private Integer reserv_id;// 揪團餐廳訂位ID
	private String party_title;// 揪團標題
	private Timestamp party_start_time;// 揪團開始時間
	private Timestamp party_end_time;// 揪團結束時間
	private String party_intro;// 揪團介紹
	private Integer party_participants_max;// 成團人數最多限制
	private Integer party_participants_min;// 成團人數最少限制
	private String party_status;// 揪團狀態 0:未成立 1:已成立
	private Date hiredate;// 揪團日期
	
	
	
	public Integer getParty_id() {
		return party_id;
	}
	public void setParty_id(Integer party_id) {
		this.party_id = party_id;
	}
	public Integer getMember_id() {
		return member_id;
	}
	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
	}
	public Integer getShop_id() {
		return shop_id;
	}
	public void setShop_id(Integer shop_id) {
		this.shop_id = shop_id;
	}
	public Integer getReserv_id() {
		return reserv_id;
	}
	public void setReserv_id(Integer reserv_id) {
		this.reserv_id = reserv_id;
	}
	public String getParty_title() {
		return party_title;
	}
	public void setParty_title(String party_title) {
		this.party_title = party_title;
	}
	public Timestamp getParty_start_time() {
		return party_start_time;
	}
	public void setParty_start_time(Timestamp party_start_time) {
		this.party_start_time = party_start_time;
	}
	public Timestamp getParty_end_time() {
		return party_end_time;
	}
	public void setParty_end_time(Timestamp party_end_time) {
		this.party_end_time = party_end_time;
	}
	public String getParty_intro() {
		return party_intro;
	}
	public void setParty_intro(String party_intro) {
		this.party_intro = party_intro;
	}
	public Integer getParty_participants_max() {
		return party_participants_max;
	}
	public void setParty_participants_max(Integer party_participants_max) {
		this.party_participants_max = party_participants_max;
	}
	public Integer getParty_participants_min() {
		return party_participants_min;
	}
	public void setParty_participants_min(Integer party_participants_min) {
		this.party_participants_min = party_participants_min;
	}
	public String getParty_status() {
		return party_status;
	}
	public void setParty_status(String party_status) {
		this.party_status = party_status;
	}
	public Date getHiredate() {
		return hiredate;
	}
	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}

	
}
