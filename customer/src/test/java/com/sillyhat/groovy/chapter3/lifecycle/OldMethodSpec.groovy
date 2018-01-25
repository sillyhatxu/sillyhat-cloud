package com.sillyhat.groovy.chapter3.lifecycle

class OldMethodSpec extends spock.lang.Specification{

	def "Adding a second product to the basket increases its weight"() {
		given: "an empty basket"
		Basket basket = new Basket()
		
		and: "a tv is already added to the basket"
		Product tv = new Product(name:"bravia",price:1200,weight:18)
		basket.addProduct(tv)
		//注意，此时的basket的重量是18，只放了一个电视，标记这个时间点为A

		when: "user gets a camera too"
		Product camera = new Product(name:"panasonic",price:350,weight:2)
		basket.addProduct(camera)
		//这是basket中又放置了相机，所以weight为 18 + 2 = 20

		then: "basket weight is updated accordingly"
		basket.currentWeight == old(basket.currentWeight) + camera.weight
		//basket.currentWeight为20 == old(basket.currentWeight)这里使用的是A时间点的basket重量18 + 相机的重量2
	}

}

