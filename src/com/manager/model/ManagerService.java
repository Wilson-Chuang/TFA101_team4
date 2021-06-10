package com.manager.model;

import java.util.List;

public class ManagerService {
	
	private ManagerDAO_interface dao;
	
	public ManagerService() {
		dao = new ManagerDAO();
	}
	
	public ManagerVO addManager(String manager_account, String manager_name,
			String manager_email, String manager_password, String manager_phone) {
//先拿掉byte[] manager_pic,圖片尚待處理，與ManagerServlet有關	
		
		ManagerVO managerVO = new ManagerVO();
		
		managerVO.setManager_account(manager_account);
		managerVO.setManager_name(manager_name);
//		managerVO.setManager_pic(manager_pic);
		managerVO.setManager_email(manager_email);
		managerVO.setManager_password(manager_password);
		managerVO.setManager_phone(manager_phone);
		dao.insert(managerVO);
		
		return managerVO;		
	}
	
	public ManagerVO updateManager(String manager_account, String manager_name, 
			String manager_email, String manager_password, String manager_phone) {
//先拿掉byte[] manager_pic,圖片尚待處理，與ManagerServlet有關
		
		ManagerVO managerVO = new ManagerVO();
		
		managerVO.setManager_account(manager_account);
		managerVO.setManager_name(manager_name);
//		managerVO.setManager_pic(manager_pic);  先拿掉byte[] manager_pic,圖片尚待處理，與ManagerServlet有關
		managerVO.setManager_email(manager_email);
		managerVO.setManager_password(manager_password);
		managerVO.setManager_phone(manager_phone);
		dao.update(managerVO);
		
		return managerVO;		
	}
	
	public void deleteManager(Integer manager_id) {
		dao.delete(manager_id);
	}

	public ManagerVO getOneManager(Integer manager_id) {
		return dao.findByPrimaryKey(manager_id);
	}

	public List<ManagerVO> getAll() {
		return dao.getAll();
	}
}
