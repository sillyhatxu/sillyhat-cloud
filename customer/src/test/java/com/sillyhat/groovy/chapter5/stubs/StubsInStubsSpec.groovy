package com.sillyhat.groovy.chapter5.stubs

import com.sillyhat.groovy.chapter5.dto.Basket
import com.sillyhat.groovy.chapter5.dto.Product
import com.sillyhat.groovy.chapter5.stubs.service.EnterprisyBasket
import com.sillyhat.groovy.chapter5.stubs.service.ServiceLocator
import com.sillyhat.groovy.chapter5.stubs.service.WarehouseInventory
import spock.lang.Subject

@Subject(Basket.class)
class StubsInStubsSpec extends spock.lang.Specification{

	def "If warehouse is empty nothing can be shipped"() {
		given: "a TV"
		Product tv = new Product(name:"bravia",price:1200,weight:18)
		
		and:"an empty warehouse"
		WarehouseInventory inventory = Stub(WarehouseInventory)
		inventory.isEmpty() >> true
		ServiceLocator serviceLocator = Stub(ServiceLocator)
		serviceLocator.getWarehouseInventory() >> inventory
		
		and: "a basket"
		EnterprisyBasket basket = new EnterprisyBasket(serviceLocator)
		
		when: "user checks out the tv"
		basket.addProduct tv

		then: "order cannot be shipped"
		!basket.canShipCompletely()
	}
	
	
	
}

