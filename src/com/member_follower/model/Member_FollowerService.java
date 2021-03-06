package com.member_follower.model;

import java.util.List;

public class Member_FollowerService {
	private Member_FollowerDAO_Interface dao;

	public Member_FollowerService() {
		dao = new Member_FollowerDAO();
	}

	public Member_FollowerVO insert(Integer MEMBER_ID, Integer MEMBER_ID_FOLLOWER) {
		Member_FollowerVO Member_FollowerVO = new Member_FollowerVO();
		Member_FollowerVO.setMEMBER_ID(MEMBER_ID);
		Member_FollowerVO.setMEMBER_ID_FOLLOWER(MEMBER_ID_FOLLOWER);

		dao.insert(Member_FollowerVO);

		return Member_FollowerVO;
	}

	public Member_FollowerVO update(Integer MEMBER_ID, Integer MEMBER_ID_FOLLOWER, Integer MEMBER_FOLLOWER_ID) {
		Member_FollowerVO Member_FollowerVO = new Member_FollowerVO();
		Member_FollowerVO.setMEMBER_ID(MEMBER_ID);
		Member_FollowerVO.setMEMBER_ID_FOLLOWER(MEMBER_ID_FOLLOWER);
		Member_FollowerVO.setMEMBER_FOLLOWER_ID(MEMBER_FOLLOWER_ID);

		dao.update(Member_FollowerVO);

		return Member_FollowerVO;
	}

	public void delete(Integer MEMBER_FOLLOWER_ID) {
		dao.delete(MEMBER_FOLLOWER_ID);
	}

	public Member_FollowerVO getOneStmt(Integer MEMBER_FOLLOWER_ID) {
		return dao.getOneStmt(MEMBER_FOLLOWER_ID);

	}

	public List<Member_FollowerVO> getAll() {

		return dao.getAll();
	}

	public List<Integer> GET_ALL_FOLLOWING(Integer MEMBER_ID_FOLLOWER) {

		return dao.GET_ALL_FOLLOWING(MEMBER_ID_FOLLOWER);
	}
	public List<Integer> GET_ALL_FANS(Integer MEMBER_ID) {
		
		return dao.GET_ALL_FOLLOWED(MEMBER_ID);
	}
	public boolean check_follow(Integer MEMBER_ID, Integer MEMBER_ID_FOLLOWER) {
		
		return dao.check_follow(MEMBER_ID, MEMBER_ID_FOLLOWER);
	}
	public Integer count_fans(Integer Member_ID) {
		
	return dao.count_fans(Member_ID);
	
	}

}
