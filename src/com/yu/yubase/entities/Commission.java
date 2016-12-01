package com.yu.yubase.entities;

public class Commission {
	String id;
	String name;
	String time;
	String money;
	String stutas;//invet：0已投，1未投 
	
	public final String getStutas() {
		return stutas;
	}
	public final void setStutas(String stutas) {
		this.stutas = stutas;
	}
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
	public final String getTime() {
		return time;
	}
	public final void setTime(String time) {
		this.time = time;
	}
	public final String getMoney() {
		return money;
	}
	public final void setMoney(String money) {
		this.money = money;
	}
	

}
