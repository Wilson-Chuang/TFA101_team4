package com.manager.model;

import java.util.List;

public interface ManagerDAO_interface {
	public void insert(ManagerVO managerVO);
	public void update(ManagerVO managerVO);
	public void delete(Integer manager_id);
	public ManagerVO findByPrimaryKey(Integer manager_id);
	public List<ManagerVO> getAll();
	
	 //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<ManagerVO> getAll(Map<String, String[]> map); 

}
