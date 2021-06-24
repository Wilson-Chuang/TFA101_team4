package com.comment.model;
import java.util.List;



	
public interface CommentDAO_Interface {
	public void insert(CommentVO MemberVO);
    public void update(CommentVO MemberVO);
    public void delete(Integer Comment_ID);
    public CommentVO getOneStmt(Integer Comment_ID);
    public List<CommentVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 

}
