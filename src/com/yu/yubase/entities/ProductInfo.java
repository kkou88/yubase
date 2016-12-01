package com.yu.yubase.entities;

import java.util.ArrayList;

public class ProductInfo {
	String title;
	String content;
	int type;
	ArrayList<String> images;
	
	String backtime;//预期还款时间
	String backtype;//预期还款类型
	String backnumber;//还款金额
	
	
	
	
	
	public final String getBacktime() {
		return backtime;
	}
	public final void setBacktime(String backtime) {
		this.backtime = backtime;
	}
	public final String getBacktype() {
		return backtype;
	}
	public final void setBacktype(String backtype) {
		this.backtype = backtype;
	}
	public final String getBacknumber() {
		return backnumber;
	}
	public final void setBacknumber(String backnumber) {
		this.backnumber = backnumber;
	}
	public final ArrayList<String> getImages() {
		return images;
	}
	public final void setImages(ArrayList<String> images) {
		this.images = images;
	}
	public final int getType() {
		return type;
	}
	public final void setType(int type) {
		this.type = type;
	}
	public final String getTitle() {
		return title;
	}
	public final void setTitle(String title) {
		this.title = title;
	}
	public final String getContent() {
		return content;
	}
	public final void setContent(String content) {
		this.content = content;
	}
	

}
