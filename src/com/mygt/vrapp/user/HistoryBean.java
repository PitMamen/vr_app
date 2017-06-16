package com.mygt.vrapp.user;

public class HistoryBean {
	
	public String imgUrl;
	
	public String name;
	
	public String time;
	
	private int progress;
	
	public boolean isSelect;
	
	public HistoryBean() {
	}

	public HistoryBean(String imgUrl, String name, String time, int progress, boolean isSelect) {
		this.imgUrl = imgUrl;
		this.name = name;
		this.time = time;
		this.progress = progress;
		this.isSelect = isSelect;
	}

}
