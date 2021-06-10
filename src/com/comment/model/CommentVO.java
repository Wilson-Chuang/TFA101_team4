package com.comment.model;

import java.math.BigDecimal;
import java.sql.*;

public class CommentVO implements java.io.Serializable{
	private Integer COMMENT_ID;
	private Integer MEMBER_ID;
	private Integer SHOP_ID;
	private String COMMENT_CONTENT;
	private BigDecimal COMMENT_RATING;
	private Timestamp COMMENT_TIME;
	private Integer COMMENT_STATUS;
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
	public Integer getSHOP_ID() {
		return SHOP_ID;
	}
	public void setSHOP_ID(Integer sHOP_ID) {
		SHOP_ID = sHOP_ID;
	}
	public String getCOMMENT_CONTENT() {
		return COMMENT_CONTENT;
	}
	public void setCOMMENT_CONTENT(String cOMMENT_CONTENT) {
		COMMENT_CONTENT = cOMMENT_CONTENT;
	}
	public BigDecimal getCOMMENT_RATING() {
		return COMMENT_RATING;
	}
	public void setCOMMENT_RATING(BigDecimal cOMMENT_RATING) {
		COMMENT_RATING = cOMMENT_RATING;
	}
	public Timestamp getCOMMENT_TIME() {
		return COMMENT_TIME;
	}
	public void setCOMMENT_TIME(Timestamp cOMMENT_TIME) {
		COMMENT_TIME = cOMMENT_TIME;
	}
	public Integer getCOMMENT_STATUS() {
		return COMMENT_STATUS;
	}
	public void setCOMMENT_STATUS(Integer cOMMENT_STATUS) {
		COMMENT_STATUS = cOMMENT_STATUS;
	}
	
	
}
