package com.sillyhat.groovy.chapter5.stubs

import com.sillyhat.groovy.chapter5.dto.Basket
import com.sillyhat.groovy.chapter5.dto.Product
import com.sillyhat.groovy.chapter5.stubs.service.WarehouseInventory
import spock.lang.Subject

@Subject(Basket.class)
class SequenceStubbingSpec extends spock.lang.Specification{


	def "Inventory is always checked in the last possible moment"() {
		given: "a basket and a TV"
		Product tv = new Product(name:"bravia",price:1200,weight:18)
		Basket basket = new Basket()

		and:"a warehouse with fluctuating stock levels"
		WarehouseInventory inventory = Stub(WarehouseInventory)
		inventory.isProductAvailable( "bravia", _) >>> true >> false
		inventory.isEmpty() >>> [false, true]
		basket.setWarehouseInventory(inventory)

		when: "user checks out the tv"
		basket.addProduct tv

		then: "order can be shipped right away"
		basket.canShipCompletely()
		
		when: "user wants another TV"
		basket.addProduct tv

		then: "order can no longer be shipped"
		!basket.canShipCompletely()

		when: "user wants another TV"
		basket.addProduct tv

		then: "order can no longer be shipped"
		!basket.canShipCompletely()
	}
}

