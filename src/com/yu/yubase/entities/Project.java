package com.yu.yubase.entities;

import java.io.Serializable;

public class Project implements Serializable{
	String id;// 序号
	String name;// 项目名称
	String code;// 标的编号
	String beginTime;// 开始时间
	String beginMoney;// 起投金额
	String payedMoney;// 已投金额
	String canPayMoney;// 可购金额         可投金额
	String totalMoney;// 总金额
	String alreadlyBuyNumber;//已购人数
	
	int progress;// 进度
	String time;// 期限
	String present;// 年化收益率
	String getFunction;// 还款方式
	String allMoney;// 融资金额
	String company;// 保理机构
	String howToUse;// 资金用途
	String status;// 状态，如新、热、火
	String statusN;//状态，如可购、即将发布、售罄

	int type;// 标的种类
	String typet;//保利通、多赢宝、稳盈宝、全部
	
	
	

	public final String getTypet() {
		return typet;
	}

	public final void setTypet(String typet) {
		this.typet = typet;
	}

	public final String getStatusN() {
		return statusN;
	}

	public final void setStatusN(String statusN) {
		this.statusN = statusN;
	}

	public final String getTotalMoney() {
		return totalMoney;
	}

	public final void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}

	public final String getAlreadlyBuyNumber() {
		return alreadlyBuyNumber;
	}

	public final void setAlreadlyBuyNumber(String alreadlyBuyNumber) {
		this.alreadlyBuyNumber = alreadlyBuyNumber;
	}

	public final String getPayedMoney() {
		return payedMoney;
	}

	public final void setPayedMoney(String payedMoney) {
		this.payedMoney = payedMoney;
	}

	public static final int TYPE_ALL = 1;// 全部产品
	public static final int TYPE_BAO = 2;// 保利通
	public static final int TYPE_DUO = 3;// 多赢宝
	public static final int TYPE_GUN = 4;// 滚利宝

	public final String getId() {
		return id;
	}

	public final void setId(String id) {
		this.id = id;
	}

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final String getStatus() {
		return status;
	}

	public final void setStatus(String status) {
		this.status = status;
	}

	public final String getCode() {
		return code;
	}

	public final void setCode(String code) {
		this.code = code;
	}

	public final String getBeginTime() {
		return beginTime;
	}

	public final void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public final String getBeginMoney() {
		return beginMoney;
	}

	public final void setBeginMoney(String beginMoney) {
		this.beginMoney = beginMoney;
	}

	public final String getCanPayMoney() {
		return canPayMoney;
	}

	public final void setCanPayMoney(String canPayMoney) {
		this.canPayMoney = canPayMoney;
	}

	public final int getProgress() {
		return progress;
	}

	public final void setProgress(int progress) {
		this.progress = progress;
	}

	public final String getTime() {
		return time;
	}

	public final void setTime(String time) {
		this.time = time;
	}

	public final String getPresent() {
		return present;
	}

	public final void setPresent(String present) {
		this.present = present;
	}

	public final String getGetFunction() {
		return getFunction;
	}

	public final void setGetFunction(String getFunction) {
		this.getFunction = getFunction;
	}

	public final String getAllMoney() {
		return allMoney;
	}

	public final void setAllMoney(String allMoney) {
		this.allMoney = allMoney;
	}

	public final String getCompany() {
		return company;
	}

	public final void setCompany(String company) {
		this.company = company;
	}

	public final String getHowToUse() {
		return howToUse;
	}

	public final void setHowToUse(String howToUse) {
		this.howToUse = howToUse;
	}

	public final int getType() {
		return type;
	}

	public final void setType(int type) {
		this.type = type;
	}

}
