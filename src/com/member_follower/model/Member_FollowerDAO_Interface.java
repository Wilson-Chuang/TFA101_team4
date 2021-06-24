package com.member_follower.model;

import java.util.*;


public interface Member_FollowerDAO_Interface {
	public void insert(Member_FollowerVO Member_FollowerVO);
    public void update(Member_FollowerVO Member_FollowerVO);
    public void delete(Integer Member_Follower_ID);
    public void delete(Member_FollowerVO Member_FollowerVO);
    public Member_FollowerVO getOneStmt(Integer Member_Follower_ID);
    public List<Member_FollowerVO> getAll();
    public List<Integer> GET_ALL_FOLLOWING(Integer MEMBER_ID_FOLLOWER);
    public List<Integer> GET_ALL_FOLLOWED(Integer MEMBER_ID);
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map);
}
