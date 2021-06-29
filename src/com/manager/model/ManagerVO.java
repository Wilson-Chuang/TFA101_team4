package com.manager.model;

import java.sql.Blob;

public class ManagerVO implements java.io.Serializable{
	
	private Integer manager_id;
	private String manager_account;
	private String manager_name;
	private byte[] manager_pic;
	private String manager_email;
	private String manager_password;
	private String manager_phone;
	private String manager_picname;

	
	public Integer getManager_id() {
		return manager_id;
	}
	public void setManager_id(Integer manager_id) {
		this.manager_id = manager_id;
	}
	public String getManager_account() {
		return manager_account;
	}
	public void setManager_account(String manager_account) {
		this.manager_account = manager_account;
	}
	public String getManager_name() {
		return manager_name;
	}
	public void setManager_name(String manager_name) {
		this.manager_name = manager_name;
	}
	public byte[] getManager_pic() {
		return manager_pic;
	}
	public void setManager_pic(byte[] manager_pic) {
		this.manager_pic = manager_pic;
	}
	public String getManager_email() {
		return manager_email;
	}
	public void setManager_email(String manager_email) {
		this.manager_email = manager_email;
	}
	public String getManager_password() {
		return manager_password;
	}
	public void setManager_password(String manager_password) {
		this.manager_password = manager_password;
	}
	public String getManager_phone() {
		return manager_phone;
	}
	public void setManager_phone(String manager_phone) {
		this.manager_phone = manager_phone;
	}
	public String getManager_picname() {
		return manager_picname;
	}
	public void setManager_picname(String manager_picname) {
		this.manager_picname = manager_picname;
	}
	
	
}
