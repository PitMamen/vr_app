package com.mygt.vrapp.user;

public class CollectionBean {
	
	public String imgUrl;
	
	public String name;
	
	public String time;
	
	public boolean isSelect;
	
	public CollectionBean() {
	}

	public CollectionBean(String imgUrl, String name, String time, boolean isSelect) {
		this.imgUrl = imgUrl;
		this.name = name;
		this.time = time;
		this.isSelect = isSelect;
	}

}
