package com.payment.model;

import java.util.*;



public interface PaymentDAO_interface {

	  public void insert(PaymentVO paymentVO);
	  public void update(PaymentVO paymentVO);
	  public void delete(Integer payment_no);
	  public PaymentVO findByPrimaryKey(Integer payment_no);
	  public List<PaymentVO> getAll();
}
