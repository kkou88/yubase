package com.yu.yubase.entities;

public class Auth {
	String user_bankcard;// 银行卡
	String user_email;// 邮箱
	String user_images;// 头像图片
	String all_money;//总资产
	String now_money;//可用余额
	String user_phone;//手机
	String real_name;//真实姓名
	String id_card;//身份证
	
	
	
	
	public final String getReal_name() {
		return real_name;
	}
	public final void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	public final String getId_card() {
		return id_card;
	}
	public final void setId_card(String id_card) {
		this.id_card = id_card;
	}
	public final String getUser_phone() {
		return user_phone;
	}
	public final void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public final String getUser_bankcard() {
		return user_bankcard;
	}
	public final void setUser_bankcard(String user_bankcard) {
		this.user_bankcard = user_bankcard;
	}
	public final String getUser_email() {
		return user_email;
	}
	public final void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public final String getUser_images() {
		return user_images;
	}
	public final void setUser_images(String user_images) {
		this.user_images = user_images;
	}
	public final String getAll_money() {
		return all_money;
	}
	public final void setAll_money(String all_money) {
		this.all_money = all_money;
	}
	public final String getNow_money() {
		return now_money;
	}
	public final void setNow_money(String now_money) {
		this.now_money = now_money;
	}

	
}
