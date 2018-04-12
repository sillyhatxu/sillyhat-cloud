package com.sillyhat.spock.customer

import com.sillyhat.cloud.customer.model.Customer
import com.sillyhat.cloud.customer.repository.CustomerRepository
import com.sillyhat.cloud.customer.service.CustomerService
import com.sillyhat.cloud.customer.service.impl.CustomerServiceImpl
import spock.lang.Subject

@Subject(CustomerServiceImpl.class)
class CustomerServiceSpec extends spock.lang.Specification{

    //TODO Chapter 2
    def "Testing find all custoemr 1"(){
        when: "query params is null"

        CustomerRepository customerRepository = Stub(CustomerRepository)
        customerRepository.findAll() >>> [
                [
                        new Customer(id: 1l,stripeCustomerId: "stripeCustomerId-11",email: "test-111@gmail.com"),
                        new Customer(id: 2L,stripeCustomerId: "stripeCustomerId-22",email: "test-222@gmail.com")
                ],
                []
        ]
        CustomerService customerService = new CustomerServiceImpl(customerRepository)

        then: "find all customer"
        List<Customer> list = customerService.queryAllCustomerList();
        List<Customer> list2 = customerService.queryAllCustomerList();

        then: "customer list size"
        println list.size()
        println list2.size()
        list.size() == 2
        list2.size() == 0
//        list.every{
//            customer ->
//                println "customer is [id:$customer.id,stripeCustomerId:$customer.stripeCustomerId,email:$customer.email]"
//        }
    }

    def "Testing find all custoemr 2"(){
        when: "query params is null"

        CustomerRepository customerRepository = Stub(CustomerRepository)
        customerRepository.save() >> true
        CustomerService customerService = new CustomerServiceImpl(customerRepository)

        then: "find all customer"
        List<Customer> list = customerService.save();

        then: "customer list size"
        println list.size()
        list.size() == 2
//        list.every{
//            customer ->
//                println "customer is [id:$customer.id,stripeCustomerId:$customer.stripeCustomerId,email:$customer.email]"
//        }
    }


//    def "Testing invalid address detection"() {
//        when: "an address does not have a postcode"
//        Address address = new Address(country:"Greece",number:23)
//
//        def dummyAddressDao = new Expando()
//        dummyAddressDao.load = { return address}
//
//        Stamper stamper = new Stamper(dummyAddressDao as AddressDao)
//
//        then: "this address is rejected"
//        !stamper.isValid(1)
//    }
//
//    def "Testing invalid and valid address detection"() {
//        when: "two different addresses are checked"
//        Address invalidAddress = new Address(country:"Greece",number:23)
//        Address validAddress = new Address(country:"Greece",number:23,street:"Argous", postCode:"4534")
//
//        def dummyAddressDao = new Expando()
//        dummyAddressDao.load = { id -> return id==2?validAddress:invalidAddress}
//
//        Stamper stamper = new Stamper(dummyAddressDao as AddressDao)
//
//        then: "Only the address with street and postcode is accepted"
//        !stamper.isValid(1)
//        stamper.isValid(2)
//    }

}
