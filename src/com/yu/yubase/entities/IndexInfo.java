package com.yu.yubase.entities;

import java.util.ArrayList;

public class IndexInfo {
	String earnMoney ;
	String rePayMent ;
	String accumulatedInvestment ;
	Project project;
	ArrayList<BanPage> banPages;
	public final String getEarnMoney() {
		return earnMoney;
	}
	public final void setEarnMoney(String earnMoney) {
		this.earnMoney = earnMoney;
	}
	public final String getRePayMent() {
		return rePayMent;
	}
	public final void setRePayMent(String rePayMent) {
		this.rePayMent = rePayMent;
	}
	public final String getAccumulatedInvestment() {
		return accumulatedInvestment;
	}
	public final void setAccumulatedInvestment(String accumulatedInvestment) {
		this.accumulatedInvestment = accumulatedInvestment;
	}
	public final Project getProject() {
		return project;
	}
	public final void setProject(Project project) {
		this.project = project;
	}
	public final ArrayList<BanPage> getBanPages() {
		return banPages;
	}
	public final void setBanPages(ArrayList<BanPage> banPages) {
		this.banPages = banPages;
	}
	
	
	
	

}
