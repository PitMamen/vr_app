package com.mygt.vrapp.api.bean;

import java.io.Serializable;

/**
 * @author zxy
 * @date 2017年4月20日 上午10:46:59
 * 
 */
public class AdvertisVo implements Serializable{

	private static final long serialVersionUID = 1L;


	private String id;
    /**
     * 路径
     */
    private String url;

    /**
     * 广告标题
     */
    private String title;
    
    /**
     * 广告图片
     */
    private String image;

    /**
     * 广告类型    类型 YS:影视  YX:游戏
     */
    private String type;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 业务参数JSON(如：type为影视时，保存影视id;type为游戏时，保存游戏id）
     */
    private String business;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

}
