package com.comment_report.model;
import java.util.List;



	
public interface Comment_ReportDAO_Interface {
	public void insert(Comment_ReportVO MemberVO);
    public void update(Comment_ReportVO MemberVO);
    public void update_status(Integer Comment_report_status,Integer Comment_report_id);
    public void delete(Integer Comment_ID);
    public Comment_ReportVO getOneStmt(Integer Comment_ID);
    public List<Comment_ReportVO> getAll();
    public boolean reported(Integer Comment_ID,Integer Member_ID);
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 

}
