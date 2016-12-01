package com.yu.yubase.entities;

import android.util.Log;

public class ShareData {
	String title;
	String content;
	String imageurl;
	String url;
	
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
	public final String getImageurl() {
		return imageurl;
	}
	public final void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	public final String getUrl() {
		return url;
	}
	public final void setUrl(String url) {
		this.url = url;
	}
	
	public void show(){
		if (title!= null) {
			Log.e("ShareData", title.toString());
		}
		if (content!= null) {
			Log.e("ShareData", content.toString());
		}
		if (imageurl!= null) {
			Log.e("ShareData", imageurl.toString());
		}
		if (url!= null) {
			Log.e("ShareData", url.toString());
		}
	}
	

}
