package com.payment.model;

import java.util.List;

public class PaymentService {

	private PaymentDAO_interface dao;

	public PaymentService() {
		dao = new PaymentDAO();
	}

	public PaymentVO addPayment(String payment_name) {

		PaymentVO paymentVO = new PaymentVO();
		
		paymentVO.setPayment_name(payment_name);
		
		dao.insert(paymentVO);

		return paymentVO;
	}

	public PaymentVO updatePayment(Integer payment_no, String payment_name) {

		PaymentVO paymentVO = new PaymentVO();
		
		paymentVO.setPayment_no(payment_no);
		paymentVO.setPayment_name(payment_name);
		
		dao.update(paymentVO);

		return paymentVO;
	}
	

	public void deletePayment(Integer payment_no) {
		dao.delete(payment_no);
	}

	public PaymentVO getOnePayment(Integer payment_no) {
		return dao.findByPrimaryKey(payment_no);
	}

	public List<PaymentVO> getAll() {
		return dao.getAll();
	}
	
}
