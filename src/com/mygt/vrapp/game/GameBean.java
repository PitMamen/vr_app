package com.mygt.vrapp.game;

public class GameBean {
	
	public String imgUrl;
	
	public String name;
	
	public String memory;
	
	public String downCount;
	
	public String type;
	
	public String info;

	public GameBean() {
	}

	public GameBean(String imgUrl, String name, String memory, String downCount, String type, String info) {
		this.imgUrl = imgUrl;
		this.name = name;
		this.memory = memory;
		this.downCount = downCount;
		this.type = type;
		this.info = info;
	}

}
