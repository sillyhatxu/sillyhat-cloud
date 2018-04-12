package com.sillyhat.cloud.groovytest.eg6

import com.sillyhat.cloud.groovytest.model.Address
import com.sillyhat.cloud.groovytest.model.Customer
import spock.lang.Unroll

import static org.hamcrest.CoreMatchers.hasItem
import static org.hamcrest.CoreMatchers.not
import static spock.util.matcher.HamcrestSupport.expect
import static spock.util.matcher.HamcrestSupport.that

class HamcrestMatchersSpec extends spock.lang.Specification{

	def "trivial test with Hamcrest"() {
		given: "a list of products"
		List<String> products= ["camera", "laptop", "hifi"]

		expect: "camera should be one of them"
		products hasItem("camera")

		and: "hotdog is not one of them"
		products not(hasItem("hotdog"))
	}

	def "trivial test with Hamcrest (alt)"() {
		given: "an empty list"
		List<String> products= new ArrayList<String>()

		when: "it is filled with products"
		products.add("laptop")
		products.add("camera")
		products.add("hifi")

		then: "camera should be one of them"
		expect(products, hasItem("camera"))

		and: "hotdog is not one of them"
		that(products, not(hasItem("hotdog")))
	}

	def "trivial test with Groovy closure"() {
		given: "a list of products"
		List<String> products= ["camera", "laptop", "hifi"]

		expect: "camera should be one of them"
		products.any{ productName -> productName == "camera"}

		and: "hotdog is not one of them"
		products.every{ productName -> productName != "hotdog"}
	}
































	@Unroll
	def "customer"() {
		given: "a list of products"
		Customer customer = new Customer()
		Address address = new Address()

		and: ""
		address.with {
			id:addressId
		}

		customer.with {
			id:customerId
			name:name
			addressList:addressList
		}

		when: ""
		customer.addAddress(address)

		then: ""
		customer.getAddressList().every{
			addressDTO -> addressDTO.isDefault()
		}

		where: "some scenarios are"
		addressId | customerId | name | addressList
		    1     |   1        |  "a" |  []
		    2     |   2        |  "b" |  [new Address(id: 3,isDefault: true)]
	}
}

