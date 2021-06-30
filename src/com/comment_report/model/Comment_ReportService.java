package com.comment_report.model;

import java.sql.Timestamp;
import java.util.List;

public class Comment_ReportService {
	private Comment_ReportDAO_Interface dao;

	public Comment_ReportService() {
		dao = new Comment_ReportDAO();
	}
	public Comment_ReportVO insert(Integer comment_id,Integer member_id, String comment_report_reason) {
		Comment_ReportVO Comment_ReportVO=new Comment_ReportVO();
		Comment_ReportVO.setCOMMENT_ID(comment_id);
		Comment_ReportVO.setMEMBER_ID(member_id);
		Comment_ReportVO.setCOMMENT_REPORT_REASON(comment_report_reason);
		dao.insert(Comment_ReportVO);
		return Comment_ReportVO;
	};
	
	
	
    public Comment_ReportVO update(Integer comment_id,Integer member_id, String comment_report_reason,Timestamp comment_report_time,Integer comment_report_status,Integer comment_report_id) {
    	Comment_ReportVO Comment_ReportVO=new Comment_ReportVO();
    	Comment_ReportVO.setCOMMENT_ID(comment_id);
		Comment_ReportVO.setMEMBER_ID(member_id);
		Comment_ReportVO.setCOMMENT_REPORT_REASON(comment_report_reason);
		Comment_ReportVO.setCOMMENT_REPORT_TIME(comment_report_time);
		Comment_ReportVO.setCOMMENT_REPORT_STATUS(comment_report_status);
		Comment_ReportVO.setCOMMENT_REPORT_ID(comment_report_id);
		dao.insert(Comment_ReportVO);
		return Comment_ReportVO;
    };
    public void update_status(Integer Comment_report_status,Integer comment_report_id) {
    	dao.update_status(Comment_report_status,comment_report_id);
    }
    public void delete(Integer comment_report_id) {
    	dao.delete(comment_report_id);
    };
    public Comment_ReportVO getOneStmt(Integer comment_report_id) {
    	return dao.getOneStmt(comment_report_id);
    };
    public List<Comment_ReportVO> getAll(){
    	return dao.getAll();
    	
    };
    public boolean reported(Integer comment_id,Integer member_id) {
    	return dao.reported(comment_id, member_id);
    }
}
