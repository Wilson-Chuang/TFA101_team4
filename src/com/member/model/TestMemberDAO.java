package com.member.model;

public class TestMemberDAO {
	public static void main(String[] args) {
		MemberJDBCDAO dao = new MemberJDBCDAO();
		
//		//insert
//		MemberVO mem1 = new MemberVO();
//		mem1.setMEMBER_EMAIL("a@mail");
//		mem1.setMEMBER_PASSWORD("psw2");
//		dao.insert(mem1);
//		
//		
//		//update
//		MemberVO mem2 = new MemberVO();
//		mem2.setMEMBER_NAME("mem2");
//		mem2.setMEMBER_PIC("");
//		mem2.setMEMBER_GENDER(1);
//		mem2.setMEMBER_BIRTH(java.sql.Date.valueOf("2000-01-01"));
//		mem2.setMEMBER_PHONE("0987654321");
//		mem2.setMEMBER_ADDRESS("address1");
//		mem2.setMEMBER_UPDATE_TIME(new java.sql.Timestamp(new java.util.Date().getTime()));
//		mem2.setMEMBER_EMAIL("1@12345");
//		dao.update(mem2);
		
//		//delete
//		dao.delete(1);
//		
		//get_one_stmt
		MemberVO MemberVO=dao.getOneStmt("1@12345");
		System.out.println(MemberVO.getMember_id()+",");
		System.out.println(MemberVO.getMember_password()+",");
		System.out.println(MemberVO.getMember_name()+",");
		System.out.println(MemberVO.getMember_fans()+",");
		System.out.println(MemberVO.getMember_age()+",");
		System.out.println(MemberVO.getMember_birth()+",");
		System.out.println(MemberVO.getMember_email()+",");
		System.out.println(MemberVO.getMember_phone()+",");
		System.out.println(MemberVO.getMember_address()+",");
		System.out.println(MemberVO.getMember_point()+",");
		System.out.println(MemberVO.getMember_create_time()+",");
		System.out.println(MemberVO.getMember_update_time()+",");
		System.out.println(MemberVO.getMember_status());
//		
//		//get_all
//		List<MemberVO> list = dao.getAll();
//		for (MemberVO mem : list) {
//			System.out.println(mem.getMEMBER_ID()+",");
//			System.out.println(mem.getMEMBER_ACCOUNT()+",");
//			System.out.println(mem.getMEMBER_PASSWORD()+",");
//			System.out.println(mem.getMEMBER_NAME()+",");
//			System.out.println(mem.getMEMBER_FANS()+",");
//			System.out.println(mem.getMEMBER_AGE()+",");
//			System.out.println(mem.getMEMBER_BIRTH()+",");
//			System.out.println(mem.getMEMBER_EMAIL()+",");
//			System.out.println(mem.getMEMBER_PHONE()+",");
//			System.out.println(mem.getMEMBER_ADDRESS()+",");
//			System.out.println(mem.getMEMBER_POINT()+",");
//			System.out.println(mem.getMEMBER_CREATE_TIME()+",");
//			System.out.println(mem.getMEMBER_UPDATE_TIME()+",");
//			System.out.println(mem.getMEMBER_STATUS());
//		}
	}

}
