package com.comment_report.model;

import java.sql.*;

public class Comment_ReportVO implements java.io.Serializable{
	private Integer COMMENT_REPORT_ID;
	private Integer COMMENT_ID;
	private Integer MEMBER_ID;
	private String COMMENT_REPORT_REASON;
	private Timestamp COMMENT_REPORT_TIME;
	private Integer COMMENT_REPORT_STATUS;
	public Integer getCOMMENT_REPORT_ID() {
		return COMMENT_REPORT_ID;
	}
	public void setCOMMENT_REPORT_ID(Integer cOMMENT_REPORT_ID) {
		COMMENT_REPORT_ID = cOMMENT_REPORT_ID;
	}
	public Integer getCOMMENT_ID() {
		return COMMENT_ID;
	}
	public void setCOMMENT_ID(Integer cOMMENT_ID) {
		COMMENT_ID = cOMMENT_ID;
	}
	public Integer getMEMBER_ID() {
		return MEMBER_ID;
	}
	public void setMEMBER_ID(Integer mEMBER_ID) {
		MEMBER_ID = mEMBER_ID;
	}
	public String getCOMMENT_REPORT_REASON() {
		return COMMENT_REPORT_REASON;
	}
	public void setCOMMENT_REPORT_REASON(String cOMMENT_REPORT_REASON) {
		COMMENT_REPORT_REASON = cOMMENT_REPORT_REASON;
	}
	public Timestamp getCOMMENT_REPORT_TIME() {
		return COMMENT_REPORT_TIME;
	}
	public void setCOMMENT_REPORT_TIME(Timestamp cOMMENT_REPORT_TIME) {
		COMMENT_REPORT_TIME = cOMMENT_REPORT_TIME;
	}
	public Integer getCOMMENT_REPORT_STATUS() {
		return COMMENT_REPORT_STATUS;
	}
	public void setCOMMENT_REPORT_STATUS(Integer cOMMENT_REPORT_STATUS) {
		COMMENT_REPORT_STATUS = cOMMENT_REPORT_STATUS;
	}
	
	
}
