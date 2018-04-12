package com.sillyhat.groovy.chapter4.params.dto;

import lombok.Data;

@Data
public class Order {
	
	private int totalPrice;

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	

}
