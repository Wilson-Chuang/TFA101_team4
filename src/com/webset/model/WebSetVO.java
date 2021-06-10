package com.webset.model;

public class WebSetVO implements java.io.Serializable {
	
	private String website_url;
	private String site_title;
	private String footer_text;
	private byte[] site_logo;
	private Integer site_status;
	
	public String getWebsite_url() {
		return website_url;
	}
	public void setWebsite_url(String website_url) {
		this.website_url = website_url;
	}
	public String getSite_title() {
		return site_title;
	}
	public void setSite_title(String site_title) {
		this.site_title = site_title;
	}
	public String getFooter_text() {
		return footer_text;
	}
	public void setFooter_text(String footer_text) {
		this.footer_text = footer_text;
	}
	public byte[] getSite_logo() {
		return site_logo;
	}
	public void setSite_logo(byte[] site_logo) {
		this.site_logo = site_logo;
	}
	public Integer getSite_status() {
		return site_status;
	}
	public void setSite_status(Integer site_status) {
		this.site_status = site_status;
	}
	
	
}
