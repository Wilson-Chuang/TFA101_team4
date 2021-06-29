package com.member.model;
import java.sql.Date;
import java.sql.Timestamp;



public class MemberVO implements java.io.Serializable{
	private Integer member_id;
	private String member_email;
	private String member_password;
	private String member_name;
	private String member_pic;
	private Integer member_fans;
	private Integer member_age;
	private Integer member_gender;
	private Date member_birth;
	private String member_phone;
	private String member_address;
	private Integer member_point;
	private Timestamp member_create_time;
	private Timestamp member_update_time;
	private Integer member_status;
	private String path;
	
	
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Integer getMember_id() {
		return member_id;
	}
	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
	}
	public String getMember_email() {
		return member_email;
	}
	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}
	public String getMember_password() {
		return member_password;
	}
	public void setMember_password(String member_password) {
		this.member_password = member_password;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getMember_pic() {
		return member_pic;
	}
	public void setMember_pic(String member_pic) {
		this.member_pic = member_pic;
	}
	public Integer getMember_fans() {
		return member_fans;
	}
	public void setMember_fans(Integer member_fans) {
		this.member_fans = member_fans;
	}
	public Integer getMember_age() {
		return member_age;
	}
	public void setMember_age(Integer member_age) {
		this.member_age = member_age;
	}
	public Integer getMember_gender() {
		return member_gender;
	}
	public void setMember_gender(Integer member_gender) {
		this.member_gender = member_gender;
	}
	public Date getMember_birth() {
		return member_birth;
	}
	public void setMember_birth(Date member_birth) {
		this.member_birth = member_birth;
	}
	public String getMember_phone() {
		return member_phone;
	}
	public void setMember_phone(String member_phone) {
		this.member_phone = member_phone;
	}
	public String getMember_address() {
		return member_address;
	}
	public void setMember_address(String member_address) {
		this.member_address = member_address;
	}
	public Integer getMember_point() {
		return member_point;
	}
	public void setMember_point(Integer member_point) {
		this.member_point = member_point;
	}
	public Timestamp getMember_create_time() {
		return member_create_time;
	}
	public void setMember_create_time(Timestamp member_create_time) {
		this.member_create_time = member_create_time;
	}
	public Timestamp getMember_update_time() {
		return member_update_time;
	}
	public void setMember_update_time(Timestamp member_update_time) {
		this.member_update_time = member_update_time;
	}
	public Integer getMember_status() {
		return member_status;
	}
	public void setMember_status(Integer member_status) {
		this.member_status = member_status;
	}
	
}
