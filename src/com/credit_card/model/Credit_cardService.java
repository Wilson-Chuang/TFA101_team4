package com.credit_card.model;

import java.sql.Date;
import java.util.List;

public class Credit_cardService {

	private Credit_cardDAO_interface dao;

	public Credit_cardService() {
		dao = new Credit_cardDAO();
	}

	public Credit_cardVO addCredit_card(Integer credit_card_number,Integer credit_card_cvv,Integer credit_card_month,Integer credit_card_year,String credit_card_name,
			Date credit_card_bithday,String credit_card_phone,String credit_card_address) {
		
		Credit_cardVO credit_cardVO = new Credit_cardVO();
		
		credit_cardVO.setCredit_card_number(credit_card_number);
		credit_cardVO.setCredit_card_cvv(credit_card_cvv);
		credit_cardVO.setCredit_card_month(credit_card_month);
		credit_cardVO.setCredit_card_year(credit_card_year);
		credit_cardVO.setCredit_card_name(credit_card_name);
		credit_cardVO.setCredit_card_bithday(credit_card_bithday);
		credit_cardVO.setCredit_card_phone(credit_card_phone);
		credit_cardVO.setCredit_card_address(credit_card_address);
		
		dao.insert(credit_cardVO);

		return credit_cardVO;
	}
	

	public void deleteCredit_card(Integer credit_card_no) {
		dao.delete(credit_card_no);
	}

	public Credit_cardVO getOneCredit_card(Integer credit_card_no) {
		return dao.findByPrimaryKey(credit_card_no);
	}

	public List<Credit_cardVO> getAll() {
		return dao.getAll();
	}
	
}
