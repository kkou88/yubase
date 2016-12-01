package com.yu.yubase.entities;

public class InvestProject extends Project {
	/**
	 * 已经投资的产品
	 */
	private static final long serialVersionUID = 1L;
	
	
	String investTime;
	String investMoney;
	String investStatus;
	String investIncome;
	String nearPaymentTime;
	String alreadyPayment;
	String waitPayment;
	String pdfNumber;
	
	
	
	
	
	

	public final String getPdfNumber() {
		return pdfNumber;
	}

	public final void setPdfNumber(String pdfNumber) {
		this.pdfNumber = pdfNumber;
	}

	public final String getInvestIncome() {
		return investIncome;
	}

	public final void setInvestIncome(String investIncome) {
		this.investIncome = investIncome;
	}

	public final String getNearPaymentTime() {
		return nearPaymentTime;
	}

	public final void setNearPaymentTime(String nearPaymentTime) {
		this.nearPaymentTime = nearPaymentTime;
	}

	public final String getAlreadyPayment() {
		return alreadyPayment;
	}

	public final void setAlreadyPayment(String alreadyPayment) {
		this.alreadyPayment = alreadyPayment;
	}

	public final String getWaitPayment() {
		return waitPayment;
	}

	public final void setWaitPayment(String waitPayment) {
		this.waitPayment = waitPayment;
	}


	public final String getInvestStatus() {
		return investStatus;
	}

	public final void setInvestStatus(String investStatus) {
		this.investStatus = investStatus;
	}

	public final String getInvestTime() {
		return investTime;
	}

	public final void setInvestTime(String investTime) {
		this.investTime = investTime;
	}

	public final String getInvestMoney() {
		return investMoney;
	}

	public final void setInvestMoney(String investMoney) {
		this.investMoney = investMoney;
	}

}
