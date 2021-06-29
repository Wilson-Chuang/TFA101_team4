package com.invoice.model;

import java.util.List;

public class InvoiceService {

	private InvoiceDAO_interface dao;

	public InvoiceService() {
		dao = new InvoiceDAO();
	}

	public InvoiceVO addInvoice(String invoice_name) {

		InvoiceVO invoiceVO = new InvoiceVO();
		
		invoiceVO.setInvoice_name(invoice_name);
		
		dao.insert(invoiceVO);

		return invoiceVO;
	}

	public InvoiceVO updateInvoice(Integer invoice_no, String invoice_name) {

		InvoiceVO invoiceVO = new InvoiceVO();
		
		invoiceVO.setInvoice_no(invoice_no);
		invoiceVO.setInvoice_name(invoice_name);
		
		dao.update(invoiceVO);

		return invoiceVO;
	}
	

	public void deleteInvoice(Integer invoice_no) {
		dao.delete(invoice_no);
	}

	public InvoiceVO getOneInvoice(Integer invoice_no) {
		return dao.findByPrimaryKey(invoice_no);
	}

	public List<InvoiceVO> getAll() {
		return dao.getAll();
	}
	
}
