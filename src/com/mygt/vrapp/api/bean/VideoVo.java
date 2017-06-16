package com.mygt.vrapp.api.bean;

import java.io.Serializable;

/**
 * @author zxy
 * @date 2017年4月20日 上午9:41:15
 * 
 */
public class VideoVo implements Serializable{

	private static final long serialVersionUID = 1L;

	private String id;

    /**
     * 视频名称
     */
    private String videoname;

    /**
     * 缩略图
     */
    private String thumbnails;

    /**
     * 视频时长
     */
    private String duration;

    /**
     * 年份
     */
    private String year;

    /**
     * 获赞次数
     */
    private Integer laudnumber;

    /**
     * 下载次数
     */
    private Integer downloadnumber;

    /**
     * 播放次数
     */
    private Integer playnumber;

    /**
     * 收藏次数
     */
    private Integer collectnumber;

    /**
     * 标签:4K、3D、全景等
     */
    private String labels;

    /**
     * 所属分类:性感美女、极致美景、劲爆热舞、游戏视频、开心娱乐、科幻惊悚、文艺知音、高清MV等
     */
    private String classification;

    /**
     * 概要描述
     */
    private String description;

    /**
     * 详细描述
     */
    private String detaileddescription;

    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVideoname() {
		return videoname;
	}

	public void setVideoname(String videoname) {
		this.videoname = videoname;
	}

	public String getThumbnails() {
		return thumbnails;
	}

	public void setThumbnails(String thumbnails) {
		this.thumbnails = thumbnails;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Integer getLaudnumber() {
		return laudnumber;
	}

	public void setLaudnumber(Integer laudnumber) {
		this.laudnumber = laudnumber;
	}

	public Integer getDownloadnumber() {
		return downloadnumber;
	}

	public void setDownloadnumber(Integer downloadnumber) {
		this.downloadnumber = downloadnumber;
	}

	public Integer getPlaynumber() {
		return playnumber;
	}

	public void setPlaynumber(Integer playnumber) {
		this.playnumber = playnumber;
	}

	public Integer getCollectnumber() {
		return collectnumber;
	}

	public void setCollectnumber(Integer collectnumber) {
		this.collectnumber = collectnumber;
	}

	public String getLabels() {
		return labels;
	}

	public void setLabels(String labels) {
		this.labels = labels;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDetaileddescription() {
		return detaileddescription;
	}

	public void setDetaileddescription(String detaileddescription) {
		this.detaileddescription = detaileddescription;
	}
    
}
