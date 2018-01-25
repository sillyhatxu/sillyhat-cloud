package com.sillyhat.groovy.chapter3.block

import com.sillyhat.groovy.chapter3.dto.Basket
import com.sillyhat.groovy.chapter3.dto.Product
import spock.lang.Subject
import spock.lang.Title


@Title("Unit test for basket weight")
class BasketWeightSpec2 extends spock.lang.Specification{

	def "A basket with two products weights as their sum"() {
		given: "an empty basket"
		Basket basket = new Basket()

		and: "several products"
		Product tv = new Product(name:"bravia",price:1200,weight:18)
		Product camera = new Product(name:"panasonic",price:350,weight:2)

		when: "user wants to buy the TV and the camera and the hifi"
		basket.addProduct tv
		basket.addProduct camera

		then: "basket weight is equal to all product weight"
		basket.currentWeight == (tv.weight + camera.weight)
	}

	def "A basket with two products weights as their sum (better)"() {
		given: "an empty basket"
		//this is test class
		@Subject
		Basket basket = new Basket()

		and: "several products"
		Product tv = new Product(name:"bravia",price:1200,weight:18)
		Product camera = new Product(name:"panasonic",price:350,weight:2)

		when: "user wants to buy the TV and the camera and the hifi"
		basket.addProduct tv
		basket.addProduct camera

		then: "basket weight is equal to all product weight"
		basket.currentWeight == (tv.weight + camera.weight)
	}
}

