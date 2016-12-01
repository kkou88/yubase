package com.yu.yubase.entities;

import java.io.Serializable;

public class VersionInfo implements Serializable {

	private static final long serialVersionUID = -3462205549265747617L;
	/**
	 * 版本号
	 */
	private String version; //版本号
	/**
	 * 时间
	 */
	private String date; //时间
	/**
	 * 版本描述
	 */
	private String desc; //版本描述
	
	/**
	 * 下载地址
	 */
	private String url; //下载地址
	/**
	 * 是否强制更新
	 */
	private int isCompelUpdate;
	
	

	public final int getIsCompelUpdate() {
		return isCompelUpdate;
	}

	public final void setIsCompelUpdate(int isCompelUpdate) {
		this.isCompelUpdate = isCompelUpdate;
	}

	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
}
