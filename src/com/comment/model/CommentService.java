package com.comment.model;

import java.math.BigDecimal;
import java.util.List;

public class CommentService {
	private CommentDAO_Interface dao;

	public CommentService() {
		dao = new CommentDAO();
	}
	public CommentVO insert(Integer member_id,Integer  shop_id, String comment_content, BigDecimal comment_rating,String comment_pic) {
		CommentVO CommentVO=new CommentVO();
		CommentVO.setMEMBER_ID(member_id);
		CommentVO.setSHOP_ID(shop_id);
		CommentVO.setCOMMENT_CONTENT(comment_content);
		CommentVO.setCOMMENT_RATING(comment_rating);
		CommentVO.setCOMMENT_PIC(comment_pic);
		dao.insert(CommentVO);
		return CommentVO;
	};
    public CommentVO update(Integer member_id,Integer  shop_id, String comment_content, BigDecimal comment_rating,String comment_pic,Integer comment_id) {
    	CommentVO CommentVO=new CommentVO();
		CommentVO.setMEMBER_ID(member_id);
		CommentVO.setSHOP_ID(shop_id);
		CommentVO.setCOMMENT_CONTENT(comment_content);
		CommentVO.setCOMMENT_RATING(comment_rating);
		CommentVO.setCOMMENT_PIC(comment_pic);
		CommentVO.setCOMMENT_ID(comment_id);
		dao.insert(CommentVO);
		return CommentVO;
    };
    public void delete(Integer comment_id) {
    	dao.delete(comment_id);
    };
    public CommentVO getOneStmt(Integer Comment_ID) {
    	return dao.getOneStmt(Comment_ID);
    };
    public List<CommentVO> getAll(){
    	return dao.getAll();
    	
    };
    public List<CommentVO> getAllByShop(Integer Shop_ID){
    	return dao.getAllByShop(Shop_ID);
    	
    };
    public Integer countByMember(Integer Member_ID) {
    	return dao.countByMember(Member_ID);
    }
    public Integer countByShop(Integer Shop_ID) {
    	return dao.countByShop(Shop_ID);
    }
    public Double countRatings(Integer Shop_ID) {
    	double rating_count=dao.countByShop(Shop_ID);
    	double rating_total=dao.countRating(Shop_ID);
    	double rating=rating_total/rating_count;
    	return rating;
    }
}
