package com.sillyhat.groovy.chapter4.params.dto;

public class Customer {
	private boolean vip = false;

	public boolean isVip() {
		return vip;
	}

	public void setVip(boolean vip) {
		this.vip = vip;
	}

	private int bonusPoints = 0;

	public int getBonusPoints() {
		return bonusPoints;
	}

	public void setBonusPoints(int bonusPoints) {
		this.bonusPoints = bonusPoints;
	}

}
