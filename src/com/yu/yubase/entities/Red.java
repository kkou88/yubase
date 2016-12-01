package com.yu.yubase.entities;

public class Red {
	String id;//id编号
	String name;//名字==type
	String money;//金额red_money
	String status;//展示状态
	String canUseMoney;//启用金额red_condition_money
	String beginTime;//开始时间
	String endTime;//结束时间
	String isUse;//是否可用is_used
	String timeStatus;//是否过期time_status
	String redType;//红包类型
	
	/*
	 * 红包id：rid
红包状态：is_used 1未使用；0使用
过期状态：time_status（1未使用；0已过期）
红包金额：red_money
红包来源：red_channel
使用条件：red_type（1现金红包；2代金劵红包；3体验金红包）
有效期：add_time至end_time
逾期金额：red_condition_money（红包使用条件(1=代金券使用,每次投标的最少金额;2=现金红包,最少累计投资金额;3=体验金,.每次投标的最大金额)）

	 * */
	
	public final String getName() {
		return name;
	}
	public final String getId() {
		return id;
	}
	public final void setId(String id) {
		this.id = id;
	}
	public final String getIsUse() {
		return isUse;
	}
	public final void setIsUse(String isUse) {
		this.isUse = isUse;
	}
	public final String getTimeStatus() {
		return timeStatus;
	}
	public final void setTimeStatus(String timeStatus) {
		this.timeStatus = timeStatus;
	}
	public final String getRedType() {
		return redType;
	}
	public final void setRedType(String redType) {
		this.redType = redType;
	}
	public final void setName(String name) {
		this.name = name;
	}
	public final String getMoney() {
		return money;
	}
	public final void setMoney(String money) {
		this.money = money;
	}
	public final String getStatus() {
		return status;
	}
	public final void setStatus(String status) {
		this.status = status;
	}
	public final String getCanUseMoney() {
		return canUseMoney;
	}
	public final void setCanUseMoney(String canUseMoney) {
		this.canUseMoney = canUseMoney;
	}
	public final String getBeginTime() {
		return beginTime;
	}
	public final void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public final String getEndTime() {
		return endTime;
	}
	public final void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	

}
