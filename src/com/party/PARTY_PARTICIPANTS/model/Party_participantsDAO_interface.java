package com.party.PARTY_PARTICIPANTS.model;

import java.util.List;



public interface Party_participantsDAO_interface {
	public void insert(Party_participantsVO Party_participantsVO);
    public void update(Party_participantsVO Party_participantsVO);
    public void delete(Integer empno);
    public Party_participantsVO findByPrimaryKey(Integer empno);
    public List<Party_participantsVO> getAll();
}
