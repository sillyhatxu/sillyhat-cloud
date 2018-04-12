package com.sillyhat.groovy.chapter6.rest

import groovyx.net.http.RESTClient
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Stepwise

/**
 * Rest tests using Groovy Rest client
 * 
 * @author Kostis
 *
 */
@Stepwise
class GroovyRestClientSpec extends Specification {
	
	@Shared
	def client = new RESTClient("http://cloud-dt.deja.fashion/shop/admin/")

	def "Simple status checker"() {
		when: "a rest call is performed to the status page"
		def response = client.get(path : "banners")
		
		then: "the correct message is expected"
		with(response) {
			data.ret == 0
			data.msg == "success"
		}
	}
	
	def "Cleaning all products"() {
		given: "a rest call is performed that deletes everything"
		client.delete(path : "products")
		
		when: "a product list is requested"
		def response = client.get(path : "products")
		
		then: "it should be empty"
		with(response) {
			data.isEmpty()
			status == 200
		}
	}
	
	def "Creating a product"() {
		when: "a new product is created"
		def response = client.post(path : "products")
		
		and: "product list is requested again"
		def listResponse = client.get(path : "products")
		
		then: "it should have default values"
		with(response) {
			data.name == "A product"
			data.stock == 0
			data.price == 0
			status == 200
		}
		
		and: "product list should contain it"
		listResponse.data.size() == 1
	}

	
}
