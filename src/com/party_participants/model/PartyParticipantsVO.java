package com.party_participants.model;

import java.sql.Timestamp;

public class PartyParticipantsVO implements java.io.Serializable{
	private Integer party_participants_id;// 揪團報名者編號
	private Integer party_member_id;// 參與會員編號
	private Integer party_id;// 揪團編號
	private Timestamp party_up_time;// 報名時間        //PARTY_MEMBER_ID, PARTY_ID, PARTY_UP_TIME

	public PartyParticipantsVO() {
		super();
	}
	
	public PartyParticipantsVO(Integer party_participants_id, Integer party_member_id, Integer party_id, Timestamp party_up_time ) {
		super();
		this.party_participants_id = party_participants_id;
		this.party_member_id = party_member_id;
		this.party_id = party_id;
		this.party_up_time = party_up_time;
	}
	
	
	public Integer getParty_participants_id() {
		return party_participants_id;
	}

	public void setParty_participants_id(Integer party_participants_id) {
		this.party_participants_id = party_participants_id;
	}

	public Integer getParty_member_id() {
		return party_member_id;
	}

	public void setParty_member_id(Integer party_member_id) {
		this.party_member_id = party_member_id;
	}

	public Integer getParty_id() {
		return party_id;
	}

	public void setParty_id(Integer party_id) {
		this.party_id = party_id;
	}

	public Timestamp getParty_up_time() {
		return party_up_time;
	}

	public void setParty_up_time(Timestamp party_up_time) {
		this.party_up_time = party_up_time;
	}
}

