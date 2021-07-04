package com.member.model;

import java.util.*;


public interface MemberDAO_Interface {
	public void insert(MemberVO MemberVO);
    public void update(MemberVO MemberVO);
    public void delete(String MEMBER_EMAIL);
    public MemberVO getOneStmt(String MEMBER_EMAIL);
    public MemberVO GET_ONE_BY_ID(Integer MEMBER_ID);
    public List<MemberVO> getAll();
    public List<String> accountCheck();
    public void point_update(MemberVO memberVO);
    public void change_password(String password,Integer MEMBERID);
    public void change_status(Integer MEMBER_ID);
    public Integer countMember();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
 
}
