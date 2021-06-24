package com.party_participants_model;

import java.util.*;
import com.party.model.PartyVO;;;

public interface PartyParticipantsDAO_interface {
	public void insert(PartyParticipantsVO partyparticipantsVO);
	public void update(PartyParticipantsVO partyparticipantsVO);
	public void delete(Integer partyparticipants_id);
	public PartyParticipantsVO findByPrimaryKey(Integer partyparticipants_id);
	public List<PartyParticipantsVO> getAll();
	public Set<PartyVO> getpartyByParticipants(Integer partyparticipants);
}
