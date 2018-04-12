package com.sillyhat.groovy.chapter5.stubs.service;


import com.sillyhat.groovy.chapter5.dto.Basket;

public class EnterprisyBasket extends Basket {

	public EnterprisyBasket(ServiceLocator serviceLocator)
	{
		setWarehouseInventory(serviceLocator.getWarehouseInventory());
	}
}
