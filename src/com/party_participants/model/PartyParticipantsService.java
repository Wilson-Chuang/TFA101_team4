package com.party_participants.model;

import java.sql.Timestamp;
import java.util.*;
import com.party.model.PartyVO;

public class PartyParticipantsService {
	
	private PartyParticipantsDAO_interface dao;
	
	public PartyParticipantsService() {
		dao = new PartyParticipantsDAO();
	}
	
	public PartyParticipantsVO addPartyParticipants(Integer party_member_id, 
			Integer party_id, Timestamp party_up_time) {
		
		PartyParticipantsVO partyParticipants = new PartyParticipantsVO();
		
		partyParticipants.setParty_member_id(party_member_id);
		partyParticipants.setParty_id(party_id);
		partyParticipants.setParty_up_time(party_up_time);
		dao.insert(partyParticipants);
		
		return partyParticipants;
	}
	
	public PartyParticipantsVO updatePartyParticipants(Integer party_member_id, 
			Integer party_id, Timestamp party_up_time, Integer party_participants_id) {
		
		PartyParticipantsVO partyParticipants = new PartyParticipantsVO();
		
		partyParticipants.setParty_member_id(party_member_id);
		partyParticipants.setParty_id(party_id);
		partyParticipants.setParty_up_time(party_up_time);
		partyParticipants.setParty_participants_id(party_participants_id);
		dao.update(partyParticipants);
		
		return partyParticipants;
	}
	
	public void deletePartyParticipants(Integer party_participants_id) {
		dao.delete(party_participants_id);
	}
	
	public PartyParticipantsVO getOnePartyParticipants(Integer party_participants_id) {
		return dao.findByPK(party_participants_id);
	}
	
	public List<PartyParticipantsVO> getAll() {
		return dao.getAll();
	}
	
	public Integer countByPostID(Integer party_id) {
		return dao.countByPostID(party_id);
	}

}
	
