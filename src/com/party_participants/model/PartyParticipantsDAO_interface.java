package com.party_participants.model;

import java.util.List;

public interface PartyParticipantsDAO_interface {
	public void insert(PartyParticipantsVO PartyParticipantsVO);
	public void update(PartyParticipantsVO PartyParticipantsVO);
	public void delete(Integer party_participants_id);
	public PartyParticipantsVO findByPK(Integer party_participants_id);
	public List<PartyParticipantsVO> getAll();
	public Integer countByPostID(Integer party_id);
}
