package com.yu.yubase.entities;

import android.R.string;


public class UserInfo {
	String uid;// 用户id
	String user_name;// 用户名
	String token;// 令牌
	
	String all_money;//总资产
	String now_money;//可用余额
	
	String user_timestamp;// 登陆时间
	String user_bankcard;// 银行卡
	String user_email;// 邮箱
	String user_images;// 头像图片
	String user_phone;//手机
	String real_name;//真实姓名
	String id_card;//身份证
	LDYSBankCard bankcard;
	
	
	String paymentWait;//待回款金额
	String freezeMoney;//冻结中金额
//	String can_use_money;//可用余额==now_money
//	String total_money;//总资产==all_money
	String totalGetMoney;//累计收益
	
	String score;//积分
	String jinxb;//锦绣币
	
	
	
	
	
	
	public final String getScore() {
		return score;
	}
	public final void setScore(String score) {
		this.score = score;
	}
	public final String getJinxb() {
		return jinxb;
	}
	public final void setJinxb(String jinxb) {
		this.jinxb = jinxb;
	}
	public final String getPaymentWait() {
		return paymentWait;
	}
	public final void setPaymentWait(String paymentWait) {
		this.paymentWait = paymentWait;
	}
	public final String getFreezeMoney() {
		return freezeMoney;
	}
	public final void setFreezeMoney(String freezeMoney) {
		this.freezeMoney = freezeMoney;
	}
	public final String getTotalGetMoney() {
		return totalGetMoney;
	}
	public final void setTotalGetMoney(String totalGetMoney) {
		this.totalGetMoney = totalGetMoney;
	}
	public final LDYSBankCard getBankcard() {
		return bankcard;
	}
	public final void setBankcard(LDYSBankCard bankcard) {
		this.bankcard = bankcard;
	}
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
	public final String getUid() {
		return uid;
	}
	public final void setUid(String uid) {
		this.uid = uid;
	}
	public final String getUser_name() {
		return user_name;
	}
	public final void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public final String getUser_timestamp() {
		return user_timestamp;
	}
	public final void setUser_timestamp(String user_timestamp) {
		this.user_timestamp = user_timestamp;
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
	public final String getToken() {
		return token;
	}
	public final void setToken(String token) {
		this.token = token;
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
