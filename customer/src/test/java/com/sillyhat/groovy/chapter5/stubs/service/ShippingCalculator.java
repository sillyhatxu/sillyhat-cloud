package com.sillyhat.groovy.chapter5.stubs.service;


import com.sillyhat.groovy.chapter5.dto.Product;

public interface ShippingCalculator {
	int findShippingCostFor(Product product, int times);
}
