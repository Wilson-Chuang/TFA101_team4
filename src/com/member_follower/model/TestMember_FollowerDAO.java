package com.member_follower.model;

import java.util.List;


public class TestMember_FollowerDAO{
	public static void main(String[] args) {
		Member_FollowerJDBCDAO dao = new Member_FollowerJDBCDAO();
		
//		//insert
//		Member_FollowerVO mem_fol1 = new Member_FollowerVO();
//		mem_fol1.setMEMBER_ID(2);
//		mem_fol1.setMEMBER_ID_FOLLOWER(3);
//		dao.insert(mem_fol1);
//		
//		//update
//		Member_FollowerVO mem_fol2 = new Member_FollowerVO();
//		mem_fol2.setMEMBER_ID(3);
//		mem_fol2.setMEMBER_ID_FOLLOWER(2);
//		mem_fol2.setMEMBER_FOLLOWER_ID(5);
//		dao.update(mem_fol2);
//		
//		//delete
//		dao.delete(5);
//		
//		//get_one_stmt
//		Member_FollowerVO mem_fol3=dao.getOneStmt(5);
//		System.out.println(mem_fol3.getMEMBER_FOLLOWER_ID()+",");
//		System.out.println(mem_fol3.getMEMBER_ID()+",");
//		System.out.println(mem_fol3.getMEMBER_ID_FOLLOWER());
//		
		//get_all
		List<Member_FollowerVO> list = dao.getAll();
		for (Member_FollowerVO mem_fol : list) {
			System.out.println(mem_fol.getMEMBER_FOLLOWER_ID()+",");
			System.out.println(mem_fol.getMEMBER_ID()+",");
			System.out.println(mem_fol.getMEMBER_ID_FOLLOWER()+",");
		}
		
	}
}
