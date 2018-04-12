package com.sillyhat.groovy.chapter5.stubs

import com.sillyhat.groovy.chapter5.dto.Basket
import com.sillyhat.groovy.chapter5.dto.Product
import com.sillyhat.groovy.chapter5.stubs.service.WarehouseInventory
import spock.lang.Subject

@Subject(Basket.class)
class ArgumentStubbingSpec extends spock.lang.Specification{

	
	def "If warehouse has both products everything is fine"() {
		given: "a basket, a TV and a camera"
		Product tv = new Product(name:"bravia",price:1200,weight:18)
		Product camera = new Product(name:"panasonic",price:350,weight:2)
		Basket basket = new Basket()
		
		and:"a warehouse with enough stock"
		WarehouseInventory inventory = Stub(WarehouseInventory)
		inventory.isProductAvailable( _, 1) >> true
		basket.setWarehouseInventory(inventory)

		when: "user checks out the tv and the camera"
		basket.addProduct tv
		basket.addProduct camera

		then: "order can be shipped right away"
		basket.canShipCompletely()
	}
	
	def "If warehouse is fully stocked stock everything is fine"() {
		given: "a basket, a TV and a camera"
		Product tv = new Product(name:"bravia",price:1200,weight:18)
		Product camera = new Product(name:"panasonic",price:350,weight:2)
		Basket basket = new Basket()
		
		and:"a warehouse with limitless stock"
		WarehouseInventory inventory = Stub(WarehouseInventory)
		inventory.isProductAvailable( _, _) >> true
		basket.setWarehouseInventory(inventory)

		when: "user checks out multiple products"
		basket.addProduct tv,33
		basket.addProduct camera,12

		then: "order can be shipped right away"
		basket.canShipCompletely()
	}
	
	
	
	
}

