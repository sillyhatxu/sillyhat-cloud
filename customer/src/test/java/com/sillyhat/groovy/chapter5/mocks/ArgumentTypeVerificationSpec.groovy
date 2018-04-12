package com.sillyhat.groovy.chapter5.mocks

import com.sillyhat.groovy.chapter5.dto.Basket
import com.sillyhat.groovy.chapter5.dto.Product
import com.sillyhat.groovy.chapter5.stubs.service.WarehouseInventory
import spock.lang.Subject

@Subject(Basket.class)
class ArgumentTypeVerificationSpec extends spock.lang.Specification{

	def "Warehouse is queried for each product - null "() {
		given: "a basket, a TV and a camera"
		Product tv = new Product(name:"bravia",price:1200,weight:18)
		Product camera = new Product(name:"panasonic",price:350,weight:2)
		Basket basket = new Basket()
		
		and: "a warehouse with limitless stock"
		WarehouseInventory inventory = Mock(WarehouseInventory)
		basket.setWarehouseInventory(inventory)

		when: "user checks out both products"
		basket.addProduct tv
		basket.addProduct camera
		boolean readyToShip = basket.canShipCompletely()

		then: "order can be shipped"
		readyToShip
		2 * inventory.isProductAvailable(!null ,1) >> true
	}
	
	def "Warehouse is queried for each product - type "() {
		given: "a basket, a TV and a camera"
		Product tv = new Product(name:"bravia",price:1200,weight:18)
		Product camera = new Product(name:"panasonic",price:350,weight:2)
		Basket basket = new Basket()
		
		and: "a warehouse with limitless stock"
		WarehouseInventory inventory = Mock(WarehouseInventory)
		basket.setWarehouseInventory(inventory)

		when: "user checks out both products"
		basket.addProduct tv
		basket.addProduct camera
		boolean readyToShip = basket.canShipCompletely()

		then: "order can be shipped"
		readyToShip
		2 * inventory.isProductAvailable(_ as String ,_ as Integer) >> true
	}
	
	
}

