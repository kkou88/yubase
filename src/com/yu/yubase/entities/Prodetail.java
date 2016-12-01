package com.yu.yubase.entities;

import java.util.ArrayList;

public class Prodetail {
	public boolean isNeedPassword=false;
	String canbuymoney;
	String bueyed;
	ArrayList<ProductInfo> products1;
	ArrayList<ProductInfo> products2;
	ArrayList<ProductInfo> products3;
	
	
	public final String getBueyed() {
		return bueyed;
	}
	public final void setBueyed(String bueyed) {
		this.bueyed = bueyed;
	}
	public final String getCanbuymoney() {
		return canbuymoney;
	}
	public final void setCanbuymoney(String canbuymoney) {
		this.canbuymoney = canbuymoney;
	}
	public final boolean isNeedPassword() {
		return isNeedPassword;
	}
	public final void setNeedPassword(boolean isNeedPassword) {
		this.isNeedPassword = isNeedPassword;
	}
	public final ArrayList<ProductInfo> getProducts1() {
		return products1;
	}
	public final void setProducts1(ArrayList<ProductInfo> products1) {
		this.products1 = products1;
	}
	public final ArrayList<ProductInfo> getProducts2() {
		return products2;
	}
	public final void setProducts2(ArrayList<ProductInfo> products2) {
		this.products2 = products2;
	}
	public final ArrayList<ProductInfo> getProducts3() {
		return products3;
	}
	public final void setProducts3(ArrayList<ProductInfo> products3) {
		this.products3 = products3;
	}
	

}
