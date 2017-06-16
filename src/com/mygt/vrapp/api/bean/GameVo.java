package com.mygt.vrapp.api.bean;

import java.io.Serializable;

/**
 * @author zxy
 * @date 2017年4月20日 上午9:44:20
 * 
 */
public class GameVo implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;  //主键
	private String gamename;  //游戏名称
	private String icon;  //游戏icon
	private String publicity;  //宣传图
	private String type;       //游戏类型
	private String groups;      // 游戏分组
	private String lables;     //游戏标签
	private String descinfo;   //描述
	private String version;    //版本号
	private String apksize;      //文件大小
	private Integer downloadnumber;  //下载次数
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGamename() {
		return gamename;
	}
	public void setGamename(String gamename) {
		this.gamename = gamename;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getPublicity() {
		return publicity;
	}
	public void setPublicity(String publicity) {
		this.publicity = publicity;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getGroups() {
		return groups;
	}
	public void setGroups(String groups) {
		this.groups = groups;
	}
	public String getLables() {
		return lables;
	}
	public void setLables(String lables) {
		this.lables = lables;
	}
	public String getDescinfo() {
		return descinfo;
	}
	public void setDescinfo(String descinfo) {
		this.descinfo = descinfo;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getApksize() {
		return apksize;
	}
	public void setApksize(String apksize) {
		this.apksize = apksize;
	}
	public Integer getDownloadnumber() {
		return downloadnumber;
	}
	public void setDownloadnumber(Integer downloadnumber) {
		this.downloadnumber = downloadnumber;
	}
	
	

}
