package com.yu.yubase.entities;

public class PaymentProject extends Project {
	/**
	 * 回款记录
	 */
	private static final long serialVersionUID = 1L;
	
	
	String paymentTime;
	String paymentMoney;
	String paymentStatus;
	
	public final String getPaymentTime() {
		return paymentTime;
	}
	public final void setPaymentTime(String paymentTime) {
		this.paymentTime = paymentTime;
	}
	public final String getPaymentMoney() {
		return paymentMoney;
	}
	public final void setPaymentMoney(String paymentMoney) {
		this.paymentMoney = paymentMoney;
	}
	public final String getPaymentStatus() {
		return paymentStatus;
	}
	public final void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	
	

}
