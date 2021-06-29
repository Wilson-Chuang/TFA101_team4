package com.invoice.model;

import java.util.*;

public interface InvoiceDAO_interface {

	  public void insert(InvoiceVO invoiceVO);
	  public void update(InvoiceVO invoiceVO);
	  public void delete(Integer invoice_no);
	  public InvoiceVO findByPrimaryKey(Integer invoice_no);
	  public List<InvoiceVO> getAll();
}
