package com.party.model;


import java.sql.Timestamp;
import java.util.List;

public class PartyService {

	private PartyDAO_interface dao;

	public PartyService() {
		dao = new PartyDAO();
	}

	public PartyVO addParty( String party_title, Timestamp party_start_time, 
			Timestamp party_end_time,String party_intro, Integer party_participants_max,
			Integer party_participants_min) {

		PartyVO partyVO = new PartyVO();

		partyVO.setParty_title(party_title);
		partyVO.setParty_start_time(party_start_time);
		partyVO.setParty_end_time(party_end_time);
		partyVO.setParty_intro(party_intro);
		partyVO.setParty_participants_max(party_participants_max);
		partyVO.setParty_participants_min(party_participants_min);
		dao.insert(partyVO);

		return partyVO;
	}

	public PartyVO updateParty(Integer party_id , String party_title, Timestamp party_start_time,
			Timestamp party_end_time, String party_intro, Integer party_participants_max,
			Integer party_participants_min) {

		PartyVO partyVO = new PartyVO();

		partyVO.setParty_id(party_id);
		partyVO.setParty_title(party_title);
		partyVO.setParty_start_time(party_start_time);
		partyVO.setParty_end_time(party_end_time);
		partyVO.setParty_intro(party_intro);
		partyVO.setParty_participants_max(party_participants_max);
		partyVO.setParty_participants_min(party_participants_min);
		dao.update(partyVO);

		return partyVO;
	}

	public PartyVO getOneParty(Integer party_id) {
		return dao.findByPrimaryKey(party_id);
	}
	
	public void deleteParty(Integer party_id) {
		dao.delete(party_id);
	}

	public List<PartyVO> getAll() {
		return dao.getAll();
	}
}
