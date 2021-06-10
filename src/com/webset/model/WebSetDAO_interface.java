package com.webset.model;

import java.util.List;

public interface WebSetDAO_interface {
	public void update(WebSetVO webSetVO);
	public List<WebSetVO> getAll();
	
	 //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<ManagerVO> getAll(Map<String, String[]> map); 
}
