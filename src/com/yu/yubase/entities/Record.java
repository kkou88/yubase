package com.yu.yubase.entities;

public class Record {
	String name;//标题
	String payMoney;//支付金额
	String nowMoney;//账号余额
	String time;//时间
	public final String getName() {
		return name;
	}
	public final void setName(String name) {
		this.name = name;
	}
	public final String getPayMoney() {
		return payMoney;
	}
	public final void setPayMoney(String payMoney) {
		this.payMoney = payMoney;
	}
	public final String getNowMoney() {
		return nowMoney;
	}
	public final void setNowMoney(String nowMoney) {
		this.nowMoney = nowMoney;
	}
	public final String getTime() {
		return time;
	}
	public final void setTime(String time) {
		this.time = time;
	}
	
	

}
