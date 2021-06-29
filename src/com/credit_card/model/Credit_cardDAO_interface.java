package com.credit_card.model;

import java.util.*;

public interface Credit_cardDAO_interface {

	  public void insert(Credit_cardVO credit_cardVO);
	  public void delete(Integer credit_card_no);
	  public Credit_cardVO findByPrimaryKey(Integer credit_card_no);
	  public List<Credit_cardVO> getAll();
}
