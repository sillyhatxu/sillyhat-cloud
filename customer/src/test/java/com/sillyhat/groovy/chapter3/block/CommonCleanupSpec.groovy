package com.sillyhat.groovy.chapter3.block

import com.sillyhat.groovy.chapter3.dto.Basket
import com.sillyhat.groovy.chapter3.dto.Product
import spock.lang.Subject

@Subject(Basket)
class CommonCleanupSpec extends spock.lang.Specification{

	Product tv
	Product camera
	Basket basket

	def setup() {
		tv = new Product(name:"bravia",price:1200,weight:18)
		camera = new Product(name:"panasonic",price:350,weight:2)
		basket = new Basket()
	}

	def "A basket with one product weights as that product"() {
		when: "user wants to buy the TV"
		basket.addProduct tv

		then: "basket weight is equal to all product weight"
		basket.currentWeight == tv.weight
	}

	def "A basket with two products weights as their sum"() {
		when: "user wants to buy the TV and the camera"
		basket.addProduct tv
		basket.addProduct camera

		then: "basket weight is equal to all product weight"
		basket.currentWeight == (tv.weight + camera.weight)
	}
	
	def cleanup()
	{
		basket.clearAllProducts()
	}
}

