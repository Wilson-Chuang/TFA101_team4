package com.party_participants_model;

import java.sql.Timestamp;

public class PartyParticipantsVO {
		private Integer party_participants;//揪團報名者ID
		private Integer member_id;//參與會員編號
		private Integer party_id;//揪團編號
		private Timestamp party_up_time;//報名時間
		
		
		
		public Integer getParty_participants() {
			return party_participants;
		}
		public void setParty_participants(Integer party_participants) {
			this.party_participants = party_participants;
		}
		public Integer getMember_id() {
			return member_id;
		}
		public void setMember_id(Integer member_id) {
			this.member_id = member_id;
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
