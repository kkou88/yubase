package com.yu.yubase.entities;

import java.io.Serializable;

public class LDYSBankCard implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2533541786233019769L;
	private String bid; //id
	private String name; //银行卡名字
	private String zhihang;//开户支行
	private String code; //代码编号
	private String number; //银行号
	private String iconName; //名字字体图标
	private String iconPic; //银行图标
	private int bgColor;//银行卡背景颜色
	
	private int supportQuick;//是否支持快捷支付,1为支持,0为不支持
	private int agreeQuick;//是否开通了快捷支付功能,1开通，0未开通
	
	
	public static final int LDYS_BANKCARD_TOP=1;
	public static final int LDYS_BANKCARD_ITEM=2;
	public static final int LDYS_BANKCARD_BOTTOM=3;
	
	
	
	
	public final int getSupportQuick() {
		return supportQuick;
	}
	public final void setSupportQuick(int supportQuick) {
		this.supportQuick = supportQuick;
	}
	public final int getAgreeQuick() {
		return agreeQuick;
	}
	public final void setAgreeQuick(int agreeQuick) {
		this.agreeQuick = agreeQuick;
	}
	public final String getZhihang() {
		return zhihang;
	}
	public final void setZhihang(String zhihang) {
		this.zhihang = zhihang;
	}
	public final int getBgColor() {
		return bgColor;
	}
	public final void setBgColor(int bgColor) {
		this.bgColor = bgColor;
	}
	public final String getIconName() {
		return iconName;
	}
	public final void setIconName(String iconName) {
		this.iconName = iconName;
	}
	public final String getIconPic() {
		return iconPic;
	}
	public final void setIconPic(String iconPic) {
		this.iconPic = iconPic;
	}
	
	
	public final String getNumber() {
		return number;
	}
	public final void setNumber(String number) {
		this.number = number;
	}
	public final String getBid() {
		return bid;
	}
	public final void setBid(String string) {
		this.bid = string;
	}
	public final String getName() {
		return name;
	}
	public final void setName(String name) {
		this.name = name;
	}
	public final String getCode() {
		return code;
	}
	public final void setCode(String code) {
		this.code = code;
	}

	
	
}
