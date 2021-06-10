package com.party.model;

import java.util.*;


public interface PartyDAO_interface {
	public void insert(PartyVO partyVO);
    public void update(PartyVO partyVO);
    public PartyVO findByPrimaryKey(Integer party_id);
    public List<PartyVO> getAll();
}
