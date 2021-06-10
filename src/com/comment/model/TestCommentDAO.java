package com.comment.model;

import java.math.BigDecimal;
import java.util.List;

public class TestCommentDAO {
	public static void main(String[] args) {
		CommentJDBCDAO dao = new CommentJDBCDAO();
		
		//insert
//		CommentVO com1 = new CommentVO();
//		com1.setMEMBER_ID(2);
//		com1.setSHOP_ID(2);
//		com1.setCOMMENT_CONTENT("Really good!");
//		com1.setCOMMENT_RATING(new BigDecimal("4.2"));
//		com1.setCOMMENT_TIME(new java.sql.Timestamp(new java.util.Date().getTime()));
//		com1.setCOMMENT_STATUS(1);
//		dao.insert(com1);
//		
//		
//		//update
//		CommentVO com1 = new CommentVO();
//		com1.setMEMBER_ID(3);
//		com1.setSHOP_ID(1);
//		com1.setCOMMENT_CONTENT("Really good!");
//		com1.setCOMMENT_RATING(new BigDecimal("3.2"));
//		com1.setCOMMENT_TIME(new java.sql.Timestamp(new java.util.Date().getTime()));
//		com1.setCOMMENT_STATUS(1);
//		com1.setCOMMENT_ID(1);
//		dao.insert(com1);
//		
//		//delete
//		dao.delete(1);
//		
//		//get_one_stmt
//		CommentVO com1=dao.getOneStmt(1);
//		System.out.println(com1.getCOMMENT_ID()+",");
//		System.out.println(com1.getMEMBER_ID()+",");
//		System.out.println(com1.getSHOP_ID()+",");
//		System.out.println(com1.getCOMMENT_CONTENT()+",");
//		System.out.println(com1.getCOMMENT_RATING()+",");
//		System.out.println(com1.getCOMMENT_TIME()+",");
//		System.out.println(com1.getCOMMENT_STATUS());
		//get_all
//		List<CommentVO> list = dao.getAll();
//		for (CommentVO com1 : list) {
//			System.out.println(com1.getCOMMENT_ID()+",");
//			System.out.println(com1.getMEMBER_ID()+",");
//			System.out.println(com1.getSHOP_ID()+",");
//			System.out.println(com1.getCOMMENT_CONTENT()+",");
//			System.out.println(com1.getCOMMENT_RATING()+",");
//			System.out.println(com1.getCOMMENT_TIME()+",");
//			System.out.println(com1.getCOMMENT_STATUS());
//		}
	}
}
