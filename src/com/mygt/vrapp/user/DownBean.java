package com.mygt.vrapp.user;

public class DownBean {
	
	public String imgUrl;
	
	public String name;
	
	public String memory;
	
	public String progress;
	
	public String speed;
	
	public boolean isSelect;
	
	public boolean isPause;
	
	public DownBean() {
	}

	public DownBean(String imgUrl, String name, String memory, String progress, String speed, boolean isSelect) {
		this.imgUrl = imgUrl;
		this.name = name;
		this.memory = memory;
		this.progress = progress;
		this.speed = speed;
		this.isSelect = isSelect;
	}

}
