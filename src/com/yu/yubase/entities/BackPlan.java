package com.yu.yubase.entities;

public class BackPlan {
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
	
}
